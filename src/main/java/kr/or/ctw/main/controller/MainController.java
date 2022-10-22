package kr.or.ctw.main.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ctw.member.service.MemberService;
import kr.or.ctw.member.vo.MemberVO;
import kr.or.ctw.project.service.ProjectService;
import kr.or.ctw.project.vo.PrjctVO;

@Controller
public class MainController {
	
	// 사원 서비스
	@Resource(name="memberService")
	MemberService mem_service;
	
	// 프로젝트
	@Resource(name="projectService")
	ProjectService pro_service;

	// <관리자 메인>
	 
	// http://localhost:8081/ctw/manager/main
	// 관리자 메인페이지
	@RequestMapping(value="/manager/main")
	public String managerMain(){
		
		return "mainform/managermain";
		
	}
	
	// 메일
	@RequestMapping(value = "/manager/mail")
	public String mail() {
		
		return "mail/list";
		
	}
	
	
	
	// --------------------------------------------
	
	// <사원 메인>
	
	// http://localhost:8081/ctw/member/main
	// 사원 메인페이지
		@RequestMapping(value="/member/main")
		public String memberMain(HttpSession ses,Model model){
			
			try{
				MemberVO vo = (MemberVO) ses.getAttribute("member");
				String mem_id = vo.getMem_id();
				List<PrjctVO> list = pro_service.projectList(mem_id);
				model.addAttribute("projectList", list);
			}catch(Exception e){
				e.printStackTrace();
				return "redirect:/index/main";
			}
			return "mainform/membermain";
		}
		
		// 남여비율차트
		@RequestMapping(value="/member/sexRateChart")
		@ResponseBody
		public List<MemberVO> sexRateChart(){
			List<MemberVO> list = mem_service.sexRateChart();
			return list;
		}
		
		// 부서별 사원수 차트
		@RequestMapping(value="/member/deptChart")
		@ResponseBody
		public List<MemberVO> deptChart(){
			List<MemberVO> list = mem_service.deptChart();
			return list;
		}
		

		
		
		// sidemenu - 메일
		@RequestMapping(value = "/member/mail")
		public String memberMail() {
			
			return "mail/list";
			
		}
		

	
		
		// ------------------------------------------공통 부분 정의 ------------------------------------------------
		
		// 프로필 - 로그아웃 (공통)
		@RequestMapping(value = "/manager/logout")
		public String Admin_Logout(HttpSession ses) {
			//로그아웃과 동시에 세션제거
			ses.removeAttribute("member");
			ses.invalidate();
			return "redirect:/index/main";
		}
		// 프로필 - 로그아웃 (공통)
		@RequestMapping(value = "/member/logout")
		public String Member_Logout(HttpSession ses) {
			//로그아웃과 동시에 세션제거
			ses.removeAttribute("member");
			ses.invalidate();
			return "redirect:/index/main";
		}
				
}
