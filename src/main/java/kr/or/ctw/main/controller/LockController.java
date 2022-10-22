package kr.or.ctw.main.controller;

import java.security.PrivateKey;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ctw.common.LoginSessionScope;
import kr.or.ctw.common.RSAGenerateKey;
import kr.or.ctw.member.service.MemberService;
import kr.or.ctw.member.vo.MemberVO;

@RequestMapping("/lock")
@Controller
public class LockController {
	
	// 서비스
	@Resource(name = "memberService")
	MemberService service;
		
	// 프로필 - 잠금화면 (공통)
	@RequestMapping(value = "/lockscreen")
	public String lockscreen(HttpSession ses,HttpServletRequest req) {
		//해당하는 세션이없다면 null 값을 반환함.
		HttpSession session = req.getSession(false);
		if(session == null){ //최초의 request가 unlock으로 발생한다면 잘못된접근이다.
			// 잘못된 접근시 메인페이지로 redirect 시킨다.
			return "redirect:/index/main";
		}else{
			// 잠금화면으로 오면 세션에 lock 정보를 담아둠
			// session 에 lock==key값을 넣어두면 인터셉터 에서 확인여부를 판단하고 페이지를 redirect 시킨다.
			ses.setAttribute("lock", "lock");
		}
		//잠금화면페이지로 이동
		return "mainform/lockscreen";
	}
	
	// unlock - 잠금해제
	@RequestMapping(value="/unlock", method=RequestMethod.POST)
	public String unlock(Model model,HttpServletRequest req, Map<String, Object> params,RedirectAttributes rda){
		
		System.out.println("사용자 unlock 요청");
		
		//해당하는 세션이없다면 null 값을 반환함.
		HttpSession session = req.getSession(false);
		if(session == null){ //최초의 request가 unlock으로 발생한다면 잘못된접근이다.
			// 잘못된 접근시 메인페이지로 redirect 시킨다.
			return "redirect:/index/main";
		}else{
			// 세션에 lock 값이 있다면 해당 값을 가져온다.
			session.getAttribute("lock");
		}
		
		String id = null;
		String pw = null;
		String page = null;
		
		id = req.getParameter("mem_id");
		pw = req.getParameter("mem_pass");
		
		RSAGenerateKey generateKey = new RSAGenerateKey();

		PrivateKey privateKey = (PrivateKey) session.getAttribute("rsaPrivateKey");
		params.put("mem_id", generateKey.decryptRSA(privateKey, id));
		params.put("mem_pass", generateKey.decryptRSA(privateKey, pw));
		
		System.out.println("로그인유저 아이디    : "+params.get("mem_id"));
		System.out.println("로그인유저 패스워드 : "+params.get("mem_pass"));
		
		MemberVO login = null;
		 
		login = service.login(params);
		
		//로그인정보 확인
		System.out.println("로그인 요청결과 : "+login);
		
		try{
			//로그인 정보가 없을시
			if (login==null){
				//로그인정보가 없고 lockscreen 에서 로그인 시도를 했을경우
				System.out.println("잠금화면 로그인 실패");
				//lockscreen 에서 로그인 시도를했으나 로그인정보가 틀렸을경우
				model.addAttribute("loginCheck", false);
				return "mainform/lockscreen";
				
			//로그인 정보가 있을 시 
			}else{
				System.out.println("로그인 정보 확인 성공");
				
				//성공적으로 로그인이 되었다면 unlock을 시켜주기위해서 세션을 제거한다.
				System.out.println("사용자  unlock 성공 ==> 세션 제거");
				session.removeAttribute("lock");
				
				//redirect해도 url안에 queryString 값이 붙기때문에 해당값을 보이지않게 하기위하여 선언함.
				rda.addFlashAttribute("member", login);
				//session안에 유저의 정보를 담아둠.
				session.setAttribute("member",login);
				//common -> LoginSessionScope 객체호출 -> addConnectUser -> connectUserList의 Map안에 세션들을 보관함.
				LoginSessionScope.addConnectUser(login.getMem_id(), session);
				
				//권한 레벨에 맞게 페이지를 redirect 시켜줌.
				if(login.getGrad_pk() >= 5){
					page =  "redirect:/manager/main";
				}else{				
					page = "redirect:/member/main";
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return page;
		
	}

}
