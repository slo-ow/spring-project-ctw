package kr.or.ctw.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ctw.board.service.FreeMultiService;
import kr.or.ctw.board.vo.LbrtyBBSAnsVO;
import kr.or.ctw.board.vo.LbrtyBBSFileVO;
import kr.or.ctw.board.vo.LbrtyBBSVO;
import kr.or.ctw.member.vo.MemberVO;

// <관리자>
// 자유게시판
@RequestMapping({"/manager","/member"})
@Controller
public class FreeBoardController extends AbstractView implements ApplicationContextAware{

	@Resource(name = "freemultiService")
	FreeMultiService service;


	// sidemenu - 게시판관리 - 게시물관리 - 자유게시판
	// 게시물 조회
	@RequestMapping(value = "/free")
	public String freeBoard(HttpServletRequest req, HttpServletResponse res, Model model) {

		//인코딩설정
		res.setContentType("text/html;charset=UTF-8");

		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<LbrtyBBSVO> list = null;

		list = service.listAll();

		model.addAttribute("list", list);


		return "board/free/list";

	}

	//게시물 작성, 멀티파일 업로드
	@RequestMapping(value="/freeinsert", method=RequestMethod.POST)
	public String finsert(HttpServletRequest req, HttpSession ses){

		MemberVO vo = (MemberVO) ses.getAttribute("member");
		service.insertBbs(vo, req);

		return "redirect:/manager/free";
	}

	//게시물 상세보기
	@RequestMapping(value="/freeInfo")
	public String fboardInfo(@RequestParam int bbs_pk,LbrtyBBSVO vo, Model model, HttpServletRequest req, HttpServletResponse res){

		//인코딩설정
		res.setContentType("text/html;charset=UTF-8");

		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		vo = service.selectBbs(bbs_pk);
		List<LbrtyBBSFileVO> list = null;
		
		list = service.selectBbsFileInfo(bbs_pk);

		//게시글 조회수 증가
		service.upCntBbs(bbs_pk);
		
		
		model.addAttribute("vo", vo);
		model.addAttribute("list", list);
		
		return "/board/free/detailform";
	}



	//게시물 수정폼
	@RequestMapping(value="/freeUpdate",method=RequestMethod.POST)
	public String freeselect(Model model, LbrtyBBSVO vo){

		service.updateBbs(vo);
		
		model.addAttribute("vo", vo);

		return "/board/free/updateform";

	}
	//게시물 수정
	@RequestMapping(value="/fUpdate", method=RequestMethod.POST)
	public String updateBbs(LbrtyBBSVO vo, Model model, HttpServletRequest req,RedirectAttributes rea){
		
		HttpSession ses  =req.getSession();
		MemberVO sesvo = (MemberVO) ses.getAttribute("member");
		
		int grade = sesvo.getGrad_pk();
		
		int res = service.updateBbs(vo);
		
		rea.addAttribute("bbs_pk",vo.getBbs_pk());
		
		if(grade >= 5){
			return "redirect:/manager/freeInfo";
		}else{
			return "redirect:/member/freeInfo";
			
		}
		
	}

	//게시물 삭제
	@RequestMapping(value="/freeDelete")
	public String freedelete(@RequestParam int bbs_pk, Model model){
		
		int res = service.deleteBbs(bbs_pk);
		
		return "redirect:/manager/free";
		
	}
	

	


	//파일 다운로드
	@RequestMapping(value="download/freefiledown")
	protected ModelAndView modelAndView(HttpServletRequest req) throws Exception{
		
		//자유게시판 파일 다운로드받을때, 다운로드받을 이름
		String bbs_f_name = req.getParameter("bbs_f_pk");
		System.out.println("bbs_f_name===="+bbs_f_name);
		
		//파일이 저장되어있는 주소와 파일명
		String path = "d:/freeBoard/"+bbs_f_name;
		System.out.println("path === "+path);
		//실제 다운로드 할 파일을 설정
	    File downloadFile = new File(path);
	    if(!downloadFile.canRead()){
	        throw new Exception("File can't read(파일을 찾을 수 없습니다)");
	    }
	    // 첫번째 해당뷰컨트롤러로 간다.  
	    return new ModelAndView("fileDownloadView", "downloadFile",downloadFile);
		
	}
	
	
	//파일 저장 주소 및 파일명 가져오기
	private File getFile(String fname) {
		// 실제 웹폴더 접근
		// 다운로드 파일을 찾아서 File객체로 리턴
		String path = "D:\\FinalProjectSetting\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\LMS\\filesave\\" + fname;
		File f = new File(path);
		
		return f;
	}

	//파일 삭제
	@RequestMapping(value="/filedelete")
	public String filefreedelete(HttpServletRequest req, LbrtyBBSVO vo, LbrtyBBSFileVO filevo, RedirectAttributes redirectAttr){
		String bbs_f_pk = filevo.getBbs_f_pk();
		service.deleteBbsFile(bbs_f_pk);
		
		return "";
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> arg0, HttpServletRequest arg1, HttpServletResponse arg2)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	//댓글저장
	@RequestMapping(value="/answersave", method=RequestMethod.POST)
	public String answersave(HttpServletRequest req, LbrtyBBSAnsVO vo, RedirectAttributes reAttr){
		
		int anspk = vo.getBbs_ans_pk();
		
		int lbansvo = service.freeInsertAnswer(vo);
		reAttr.addAttribute(vo);
		
		return "redirect:/";
		
	}


}
