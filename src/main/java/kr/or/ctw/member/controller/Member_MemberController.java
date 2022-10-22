package kr.or.ctw.member.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ctw.member.service.MemberService;
import kr.or.ctw.member.vo.MemberVO;

@RequestMapping("/member")
@Controller
public class Member_MemberController {

	// 서비스
	@Resource(name="memberService")
	MemberService service;
	
	
	
	
	
	@RequestMapping(value = "/memberOrgChart")
	public String myInfo() {
		return "member/organization/memberOrgChart";
	}
	

	/**
	 * 
	 * 
	 * @Description memorgChart.js(조직도 ViewPage)에서 Ajax방식으로 데이터베이스 값을 얻어오는 Controller
	 * @param 
	 * @author 박창현
	 * @since 2017. 5. 15.
	 *
	 */
	@RequestMapping(value = "/memAjaxList", method=RequestMethod.POST)
	@ResponseBody
	public List<MemberVO> ajaxList(MemberVO vo) {
		
		List<MemberVO>list = new ArrayList<>();
		
		list = service.memberSelect();
		
		return list;
		
	}


	
	
	

}
