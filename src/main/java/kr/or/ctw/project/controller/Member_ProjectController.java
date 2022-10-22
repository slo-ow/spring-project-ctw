package kr.or.ctw.project.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ctw.member.service.MemberService;
import kr.or.ctw.member.vo.DeptVO;
import kr.or.ctw.member.vo.MemberVO;
import kr.or.ctw.project.service.ProjectFileService;
import kr.or.ctw.project.service.ProjectService;
import kr.or.ctw.project.vo.PrjctPartcptnVO;
import kr.or.ctw.project.vo.PrjctVO;


//<조직원> 협업프로젝트
@RequestMapping({"/manager","/member"})
@Controller
public class Member_ProjectController {

	@Resource(name="projectService")
	ProjectService service;
	
	@Resource(name="projectfileService")
	ProjectFileService workservice;

	@Resource(name="memberService")
	MemberService memberService;
	
	
	// sidemenu - 프로젝트
	@RequestMapping(value = "/project")
	public String project(HttpSession ses,Model model) {
		MemberVO vo = (MemberVO) ses.getAttribute("member");
		String mem_id = vo.getMem_id();
		List<PrjctVO> list = service.projectList(mem_id);
		model.addAttribute("projectList", list);
		return "project/member/list";
		
	}

	
	/**
	 * 사원의 리스트 
	 * 
	 * @Description 
	 * @return
	 * @author 김완수
	 * @since 2017. 5. 10.
	 *
	 */
	@RequestMapping(value = "/memList",method=RequestMethod.POST)
	@ResponseBody
	public List<MemberVO> memList() {
		
		List<MemberVO> list =memberService.memberSelect();
		return list;
		
	}
	
	/**
	 * 부서리스트
	 * 
	 * @Description 
	 * @return
	 * @author 김완수
	 * @since 2017. 5. 10.
	 *
	 */
	@RequestMapping(value = "/deptList",method=RequestMethod.POST)
	@ResponseBody
	public List<DeptVO> deptList() {
		
		List<DeptVO> list =memberService.deptList();
		return list;
		
	}
	/**
	 * 부서에 해당하는 사원의 리스트
	 * 
	 * @Description 
	 * @param dept_pk
	 * @return
	 * @author 김완수
	 * @since 2017. 5. 10.
	 *
	 */
	@RequestMapping(value = "/memDeptList",method=RequestMethod.POST)
	@ResponseBody
	public List<MemberVO> memDeptList(@RequestParam int dept_pk) {
		
		List<MemberVO> list =memberService.deptMemList(dept_pk);
		return list;
		
	}
	
	/**
	 * 프로젝트를 생성하는 메서드
	 * 
	 * @Description 
	 * @param ses
	 * @param mem_id
	 * @param prjctVO
	 * @return
	 * @author 김완수
	 * @since 2017. 5. 10.
	 *
	 */
	@RequestMapping(value="/projectInsert",method=RequestMethod.POST)
	public String projectInsert(HttpSession ses,@RequestParam List<String> mem_id,PrjctVO prjctVO){
	MemberVO memVO = (MemberVO) ses.getAttribute("member");
	System.out.println("size == "+mem_id.size());
		for (int i = 0; i < mem_id.size(); i++) {
			System.out.println("mem_id=="+mem_id.get(i));
		}
		
		System.out.println("prjct_nm = " +prjctVO.getPrjct_nm());
		System.out.println("getPrjct_cn = " +prjctVO.getPrjct_cn());
		System.out.println("getPrjct_begin = " +prjctVO.getPrjct_begin());
		System.out.println("getPrjct_end = " +prjctVO.getPrjct_end());
		try {
			service.projectInsert(ses,mem_id,prjctVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(memVO.getGrad_pk() >=5){
			return "redirect:/manager/project";
		}else{
			return "redirect:/member/project";
		}
	
	
	}
	
	/**
	 * <pre>
	 *	프로젝트 상세보기
	 * </pre>
	 * @param prjct_pk
	 * @return PrjctVO
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 10.
	 *
	 */
	@RequestMapping(value = "/projectInfo",method=RequestMethod.POST)
	@ResponseBody
	public PrjctVO projectInfo(@RequestParam int prjct_pk) {
		
		PrjctVO vo =service.projectInfo(prjct_pk);
		return vo;
		
	}
	
	/**
	 * <pre>
	 *	프로젝트 요청 수락시 상태변화
	 * </pre>
	 * @param vo
	 * @return int
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 10.
	 *
	 */
	@RequestMapping(value = "/acceptProject",method=RequestMethod.POST)
	@ResponseBody
	public int acceptProject(PrjctPartcptnVO vo) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("mem_id", vo.getMem_id());
		map.put("prjct_fk", vo.getPrjct_fk());
		int res =service.acceptProject(map);
		return res;
		
	}
	
	/**
	 * <pre>
	 *	프로젝트 요청 수락시 프로젝트 방(Room)으로 이동
	 * </pre>
	 * @param 
	 * @return 
	 * @author 문성철
	 * @see   project_Room.jsp,project - js - custom.js
	 * @since 2017. 5. 11.
	 *
	 */
	@RequestMapping(value = "/joinProject",method=RequestMethod.GET)
	public String joinProject() {
		
		// 프로젝트 초대 승인 -> 프로젝트 Room 으로 페이지 이동
		
		return "project/project_Room";		
	}
	
}
