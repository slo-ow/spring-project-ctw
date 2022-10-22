package kr.or.ctw.noticeboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// 공지게시판
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ctw.member.vo.MemberVO;
import kr.or.ctw.noticeboard.service.NoticeMultiService;
import kr.or.ctw.noticeboard.vo.CmpnyNoticeVO;
import kr.or.ctw.noticeboard.vo.DeptNoticeVO;
import kr.or.ctw.vote.vo.VoteVO;
/**
 * @Class Name : NoticeController.java
 * @Description : 공지사항게시판 Controller
 * @author 박혜윤
 * @since  2017.04.25
 * @version 1.0
 */
@RequestMapping({"/manager","/member"})
@Controller
public class NoticeController {

	@Resource(name="noticemultiService")
	NoticeMultiService service;
	
	
	// sidemenu - 게시판관리 - 게시물관리 - 공지사항게시판(관리자 or 부서장)
	@RequestMapping(value="/notice")
	public String notice(Model model,HttpServletRequest req){
		
		try{
		
		// 로그인한 유저id 가져옴
		HttpSession session = req.getSession();
		
		MemberVO vo = (MemberVO)session.getAttribute("member");
		
		int grade = vo.getGrad_pk();
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		// 관리자 공지사항 게시판
		List<CmpnyNoticeVO> cmpnylist = service.CompanyNoticeList(map);
		model.addAttribute("companylist",cmpnylist);
		
		// 부서장 공지사항 게시판
		List<DeptNoticeVO> deptlist = service.DeptNoticeList(map);
		model.addAttribute("deptlist",deptlist);
			
		if(grade >= 9){
			// 관리자 공지사항 게시판
			return "noticeboard/company/list";
		}else{
			// 부서장 공지사항 게시판
			return "noticeboard/dept/list";
		}
		
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/index/main";
		}
		
	}
	
	
	// sidemenu - 게시판관리 - 게시물관리 - 공지사항게시판(관리자 or 부서장)
/*	@RequestMapping(value="/notice")
	public String notice(){
		
		// 관리자 공지사항 게시판
		return "noticeboard/company/list";
	}
	
	*/
	
	//	ajax 공지사항게시판	
	@RequestMapping(value="/noticeList",method = RequestMethod.POST)
	@ResponseBody
	public List<CmpnyNoticeVO> voteList() {
		
		Map<String,Object> map = new HashMap<String,Object>();
		List<CmpnyNoticeVO> list = service.CompanyNoticeList(map);
		

		return list;
		
	}
	
	
	// 회사 공지글 작성
	@RequestMapping(value="/companynoticeInsert")
	public String companynoticeInsert(Model model,HttpServletRequest req,CmpnyNoticeVO vo){
		
		int res = service.companyNoticeInsert(vo);
		System.out.println("등록"+res);
		return "redirect:/manager/notice";
	}
	
	
	//deptnoticeInsert
	// 부서 공지글 작성
	@RequestMapping(value="/deptnoticeInsert")
	public String deptnoticeInsert(Model model,HttpServletRequest req,DeptNoticeVO vo){
		
		int res = service.deptnoticeInsert(vo);
		
		return "redirect:/manager/notice";
		
	}
	
	/**
	 * 
	 * <pre>
	 *	회사 공지사 상세보기
	 * </pre>
	 * @param notice_pk
	 * @return String
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 8.
	 *
	 */
	@RequestMapping(value="/companyInfo")
	public String noticeInfo(@RequestParam int notice_pk, Model model){
		
		// 부서장 공지사항 게시판
		CmpnyNoticeVO vo = service.selectInfo(notice_pk);
		model.addAttribute("vo",vo);
		
		// 조회수 증가
		service.updateHits(notice_pk);
		
			
		// 부서 공지사항 게시판
		return "noticeboard/company/info";
		
		
	}
	
	/**
	 * <pre>
	 *	부서 공지사 상세보기
	 * </pre>
	 * @param notice_pk
	 * @return String
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 8.
	 *
	 */
	@RequestMapping(value="/deptInfo")
	public String deptInfo(@RequestParam int notice_pk, Model model){
		
		// 부서장 공지사항 게시판
		DeptNoticeVO vo = service.deptSelectInfo(notice_pk);
		model.addAttribute("vo",vo);
		
		// 조회수 증가
		service.deptUpdateHits(notice_pk);
	
			
		// 부서 공지사항 게시판
		return "noticeboard/dept/info";
		
		
	}
	
	// 회사 게시물 삭제
	@RequestMapping(value="/companyDel")
	public String companyDel(@RequestParam int notice_pk, Model model){
		
		// 부서장 공지사항 게시판
		int res = service.companyDel(notice_pk);
		
			
		// 부서 공지사항 게시판
		return "redirect:/manager/notice";
		
		
	}
	
	// 부서 게시물 삭제
	@RequestMapping(value="/deptDel")
	public String deptDel(@RequestParam int notice_pk, Model model){
		
		// 부서장 공지사항 게시판
		int res = service.deptDel(notice_pk);
		
			
		// 부서 공지사항 게시판
		return "redirect:/manager/notice";
		
		
	}
	
	/**
	 * <pre>
	 *	회사공지글 수정 폼
	 * </pre>
	 * @return String
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 11.
	 *
	 */
	@RequestMapping(value="/companyUpdateForm", method =RequestMethod.POST)
	public String companyUpdateForm(CmpnyNoticeVO vo, Model model){
		
		model.addAttribute("vo",vo);
		return "noticeboard/company/updateForm";
		
	}
	
	/**
	 * <pre>
	 *	회사 공지글 수정
	 * </pre>
	 * @param vo
	 * @param model
	 * @return
	 * @return String
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 11.
	 *
	 */
	@RequestMapping(value="/companyUpdate", method =RequestMethod.POST)
	public String companyUpdate(CmpnyNoticeVO vo, Model model, RedirectAttributes red){
		
		System.out.println("여기업데이트");
		int res = service.companyUpdate(vo);
		
		red.addAttribute("notice_pk",vo.getNotice_pk());
		return "redirect:/manager/companyInfo";
		
		
	}
	
	/**
	 * <pre>
	 *	부서공지글 수정 폼
	 * </pre>
	 * @return String
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 11.
	 *
	 */
	@RequestMapping(value="/deptUpdateForm", method =RequestMethod.POST)
	public String deptUpdateForm(CmpnyNoticeVO vo, Model model){
		
		model.addAttribute("vo",vo);
		return "noticeboard/dept/updateForm";
		
	}
	
	/**
	 * <pre>
	 *	회사 공지글 수정
	 * </pre>
	 * @param vo
	 * @param model
	 * @return
	 * @return String
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 11.
	 *
	 */
	@RequestMapping(value="/deptUpdate", method =RequestMethod.POST)
	public String deptUpdate(DeptNoticeVO vo, Model model, RedirectAttributes red){
		
		int res = service.deptUpdate(vo);
		
		red.addAttribute("notice_pk",vo.getNotice_pk());
		return "redirect:/manager/deptInfo";
		
		
	}
	
	//----------------<조직원>--------------------
	
	/**
	 * 
	 * @Description 조직원페이지의 커뮤니티게시판의 전체공지사항
	 * 				관리자가 작성한 공지사항을 조회할 수 있다.
	 * @param 
	 * @return 
	 * @author 
	 * @see 
	 * @since 2017. 4. 25
	 *
	 */
	// sidemenu -커뮤니티게시판 - 전체공지사항(관리자가 작성) 조회
	@RequestMapping(value="/companynotice")
	public String companynotice(Model model,HttpServletRequest req){
		
		// 로그인한 유저id 가져옴
		HttpSession session = req.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("member");
		
		int grade = vo.getGrad_pk();
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		// 관리자 공지사항 게시판
		List<CmpnyNoticeVO> cmpnylist = service.CompanyNoticeList(map);
		model.addAttribute("companylist",cmpnylist);
		
	
		// 회사전체 공지사항 게시판
		return "noticeboard/company/list";
	
	}
	
	// 부서공지사항(부서장이 작성) 조회
	@RequestMapping(value="/deptnotice")
	public String deptnotice(Model model,HttpServletRequest req){
		
		// 로그인한 유저id 가져옴
		HttpSession session = req.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("member");
		
		int grade = vo.getGrad_pk();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dept_pk", vo.getDept_pk());
		// 부서장 공지사항 게시판
		List<DeptNoticeVO> deptlist = service.DeptNoticeList(map);
		model.addAttribute("deptlist",deptlist);
			
		// 부서 공지사항 게시판
		return "noticeboard/dept/list";
		
		
	}

	//----------- <공통부분>---------------
	

	
	
	
	
}
