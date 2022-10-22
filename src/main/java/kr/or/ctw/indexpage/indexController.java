package kr.or.ctw.indexpage;

import java.security.PrivateKey;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ctw.member.service.MemberService;
import kr.or.ctw.member.vo.MemberVO;
import kr.or.ctw.common.LoginSessionScope;
import kr.or.ctw.common.RSAGenerateKey;
import kr.or.ctw.common.error.InvalidPasswordException;

@RequestMapping("/index")
@Controller
public class indexController {

	// 서비스
	@Resource(name = "memberService")
	MemberService service;
	
	
	/**
	 * 메인 로드 하는 화면이지만 
	 * 세션을 지우는 것도 같이 한다.
	 * 
	 * @param req
	 * @return
	 * 
	 * @author 김완수
	 * 2017.04.17
	 * 
	 */
	// 인덱스 화면
	@RequestMapping(value="/main")
	public String index(HttpServletRequest req,HttpSession ses){
	
		ses.removeAttribute("member");	
		ses.removeAttribute("lock");	
		
		return "index_page/index";
		
	}
	
	/**
	 * 로그인폼으로 이동하는 컨트롤러  
	 * Google recaptcha 기능 적용
	 * 
	 * @param session
	 * @return page
	 * @author 문성철
	 * @see loginForm.jsp  
	 * 2017 . 04. 20
	 * 
	 */
	// 로그인 화면
	@RequestMapping(value="/loginForm")
	public String login(){
		
		return "index_page/loginForm";
		
	}
	
	// 소개 화면
	@RequestMapping(value="/introduce")
	public String introduce(){
			
		return "index_page/introduce";
	}
	
	// PPT 화면
	@RequestMapping(value="/presentation")
	public String presentation(){
		
		return "index_page/presentation";
	}
	
	// 지도맵 화면
	@RequestMapping(value="/contact")
	public String contact(){
		
		return "index_page/contact";
	}
	
	// 지도맵 화면
	@RequestMapping(value="/contact2")
	public String contact2(){
		
		return "index_page/contact2";
	}
	
	
	/**
	 * 로그인을 처리하기위한 메서드 
	 * 등급의 레벨을 비교 해서 관리자와 일반 사원으로 나눠서 간다. 
	 * RSA 암호화적용
	 * 
	 * @param vo,req,model
	 * @return page
	 * @author 문성철
	 * @see MainController
	 * 2017 . 04. 20
	 * 
	 */
	// login 처리 	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(Model model,HttpServletRequest req, HttpSession session,Map<String, Object> params,RedirectAttributes rda){
		
		System.out.println("사용자 Login 요청");
		
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
				//throw new InvalidPasswordException("asfdasdf");
				//loginForm 에서 로그인 시도를했으나 틀렸을경우
				System.out.println("메인화면 로그인 실패");
				model.addAttribute("loginCheck", false);
				page =  "index_page/loginForm";			
				
			//로그인 정보가 있을 시 
			}else{
				System.out.println("로그인 정보 확인 성공");
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
	
	/**
	 * 로그아웃을 위해 사용 한다. 
	 * @return
	 * @author 김완수
	 */
	// 로그아웃을 위한 mapping
	@RequestMapping(value = "/indexmain")
	public String indexmain(HttpSession ses) {
		ses.removeAttribute("member");
		ses.removeAttribute("lock");
		return "redirect:/index/main";
		
	}
	
	
	
}
