package kr.or.ctw.document.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ctw.document.service.DocService;
import kr.or.ctw.document.vo.DocVO;
import kr.or.ctw.document.vo.KategorieVO;
import kr.or.ctw.member.vo.MemberVO;

/**
 * 문서페이지 컨트롤러
 * 
 * <pre>
 * 
 * </pre>
 * @author 김완수
 * @since 2017. 5. 2.
 * @version 1.0
 * @see kr.or.ctw.document.controller DocController
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일             수정자        수정내용
 * --------------     --------    -----------
 * 2017. 5. 2.     	  김완수        최초작성
 * Copyright (c) 2017 by DDIT All right reserved
 * </pre>
 */
@RequestMapping({"/manager","/member"})
@Controller
public class DocController {

	@Resource(name = "docService")
	DocService service;

	
	
	
	/**
	 * 문서의 카테고리의 목록을 가져오는 메서드
	 * 
	 * @Description 
	 * @return List<T>
	 * @author 김완수
	 * @since 2017. 5. 2.
	 *
	 */
	@RequestMapping(value = "/kategoriList",method=RequestMethod.POST)
	@ResponseBody
	public List<KategorieVO> kategorieList() {
		List<KategorieVO> list =null;
		list = service.kategorieList();
		return list;
	}
	
	
	/**
	 * 	부서문서페이지 이동 
	 * @return
	 * @author 김완수
	 * @date 2017. 4. 26.
	 */
	@RequestMapping(value = "/departmentdoc")
	public String deptDoc() {
		return "document/departmentdoc/managementlist";

	}
	
	
	
	
	/**
	 * 	관리자문서페이지 이동 
	 * @return
	 * @author 김완수
	 * @date 2017. 4. 26.
	 */
	@RequestMapping(value = "/managerdoc")
	public String comDoc() {
		return "document/managerdoc/managementlist";
		
	}
	
	/**
	 * 	사원문서페이지 이동 
	 * @return
	 * @author 김완수
	 * @date 2017. 4. 26.
	 */
	@RequestMapping(value = "/referenceroom")
	public String memDoc() {
		StackTraceElement[] a = new Throwable().getStackTrace();
		
		for (int i = a.length-1; i>0; i--) {
			System.out.print("클래스 - " + a[i].getClassName());
            System.out.print(", 메소드 - "+a[i].getMethodName());
            System.out.print(", 라인 - "+a[i].getLineNumber());
            System.out.print(", 파일 - "+a[i].getFileName());
            System.out.println();


		}
		return "document/referenceroom/list";
		
	}
	
	
	/**
	 * 휴지통으로 가는 매핑메서드 
	 * 
	 * @Description 
	 * @param model
	 * @return
	 * @author 김완수
	 * @since 2017. 5. 2.
	 *
	 */
	@RequestMapping(value = "/trashcanCoList")
	public String comDocTrash(Model model) {
		model.addAttribute("division", "com");
		return "document/trashcan/colist";
	}
	@RequestMapping(value = "/trashcanDeList")
	public String deptDocTrash(Model model) {
		model.addAttribute("division", "dept");
		return "document/trashcan/colist";
	}
	@RequestMapping(value = "/dwasdqwdqwd")
	public String memDocTrash(Model model) {
		model.addAttribute("division", "mem");
		return "document/referenceroom/list";
	}
	// 휴지동 매핑 --끝-- 
	
	
	
	/**
	 * 문서를 저장하기위한 메서드 
	 * 
	 * @Description 
	 * @param req
	 * @param ses
	 * @return
	 * @author 김완수
	 * @since 2017. 5. 2.
	 *
	 */
	@RequestMapping(value = "/docSave",method=RequestMethod.POST)
	public String  docSave(HttpServletRequest req,HttpSession ses) {
		String division = req.getParameter("division");
		try {
			service.docFileInsert(req,ses);
		} catch (IllegalStateException | IOException | SQLException e) {
			e.printStackTrace();
		}
		if (division.equals("com")) {
			return "redirect:/manager/managerdoc";
		}else if(division.equals("dept")){
			return "redirect:/manager/departmentdoc";
		}else{
			return "redirect:/member/referenceroom";
		}
	}
	
	/**
	 * 문서를 휴지통으로 보내기위한 메서드
	 * 
	 * @Description 
	 * @param doc_pk
	 * @param division
	 * @return
	 * @author 김완수
	 * @since 2017. 5. 2.
	 *
	 */
	@RequestMapping(value = "/docDelete")
	public String  docDelete(@RequestParam String doc_pk,@RequestParam String division) {
		service.docDelete(doc_pk,division);
		if (division.equals("com")) {
			return "redirect:/manager/managerdoc";
		}else if(division.equals("dept")){
			return "redirect:/manager/departmentdoc";
		}else{
			return "redirect:/member/referenceroom";
		}
	}
	
	/**
	 * 문서를 완전 삭제하기위한 메서드 
	 * 
	 * @Description 
	 * @param doc_pk
	 * @param division
	 * @return
	 * @author 김완수
	 * @since 2017. 5. 2.
	 *
	 */
	@RequestMapping(value = "/trashcanDocDel")
	public String  trashcanDel(@RequestParam String doc_pk,@RequestParam String division) {
		try {
			service.trashcanDel(doc_pk,division);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (division.equals("com")) {
			return "redirect:/manager/trashcanCoList";
		}else if(division.equals("dept")){
			return "redirect:/manager/trashcanDeList";
		}else{
			return "redirect:/member/referenceroom";
		}
	}
	
	/**
	 * 문서를 휴지통에서 원래 페이지로 복구시키는 메서드
	 * 
	 * @Description 
	 * @param doc_pk
	 * @param division
	 * @return
	 * @author 김완수
	 * @since 2017. 5. 2.
	 *
	 */
	@RequestMapping(value = "/trashcanDocUp")
	public String  trashcanUp(@RequestParam String doc_pk,@RequestParam String division) {
	
			service.trashcanUp(doc_pk, division);

		if (division.equals("com")) {
			return "redirect:/manager/trashcanCoList";
		}else if(division.equals("dept")){
			return "redirect:/manager/trashcanDeList";
		}else{
			return "redirect:/member/referenceroom";
		}
	}

	
	/**
	 * 문서의 리스트를 가져오는 메서드 
	 * 
	 * @Description 
	 * @param ses
	 * @param req
	 * @return List<T>
	 * @author 김완수
	 * @since 2017. 5. 2.
	 *
	 */
	@RequestMapping(value = "/docList",method=RequestMethod.POST)
	@ResponseBody
	public List<DocVO>  docList(HttpSession ses,HttpServletRequest req) {
		List<DocVO> list = null;
		MemberVO vo =(MemberVO) ses.getAttribute("member");
		list = service.docList(vo, req);
		return list;
	}
	
	/**
	 * 휴지통에 있는 문서들을 가져오는 메서드
	 * 
	 * @Description 
	 * @param division
	 * @param ses
	 * @return
	 * @author 김완수
	 * @since 2017. 5. 2.
	 *
	 */
	@RequestMapping(value = "/trashcanDocList",method=RequestMethod.POST)
	@ResponseBody
	public List<DocVO>  trashcanDocList(@RequestParam String division,HttpSession ses) {
		List<DocVO> list = null;
		MemberVO vo = (MemberVO) ses.getAttribute("member");
		list = service.trashcanDocList(vo,division);
		return list;
	}
	
	
	
	
	/**
	 * 파일을 다운로드를 위한 메서드 	
	 * @param req
	 * @return
	 * @throws Exception
	 * @author 김완수
	 * @date 2017. 4. 28.
	 */
	@RequestMapping(value="download/docFile") //downlist.jsp 에서 링크
	public ModelAndView ModelAndView(  
											HttpServletRequest req
											) throws Exception{
			// 사용자가 파일을 다운로드 할때 실제 저장된 이름으로 다운로드를 하면 이름이 이상하기 때문에 
			// real_name이라는 파마미터로 처리해서 해주면 사용자가 저장한 이름으로 다운로드가 된다. 
			String doc_pk = req.getParameter("doc_pk");
			// 파일이저장되어있는 주소와 파일명을 보내준다.
			String path = "d:/doc/"+doc_pk;
	        // 실제로 다운로드할 파일을 설정 
		    File downloadFile = new File(path);
		    if(!downloadFile.canRead()){
		        throw new Exception("File can't read(파일을 찾을 수 없습니다)");
		    }
		    // 첫번째 해당뷰컨트롤러로 간다.  
		    return new ModelAndView("fileDownloadView", "downloadFile",downloadFile);
	}
	

	
	

	
	
}
