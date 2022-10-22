package kr.or.ctw.mssage.controller;

import java.io.File;
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

import kr.or.ctw.member.vo.MemberVO;
import kr.or.ctw.mssage.service.MssService;
import kr.or.ctw.mssage.vo.MssVO;
/**
 * 
 * 
 * 
 * <pre>
 * 쪽지 메인 컨트롤러 
 * </pre>
 * @author 김완수
 * @since 2017. 5. 13.
 * @version 1.0
 * @see kr.or.ctw.mssage.controller
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자        수정내용
 * --------------     --------    -----------
 * 2017. 5. 13.     	        김완수        최초작성
 * Copyright (c) 2017 by DDIT All right reserved
 * </pre>
 */
@RequestMapping({"/manager","/member"})
@Controller
public class MssController {

	// 쪽지 서비스 연결
	@Resource(name="mssService")
	MssService service;
	
	/**
	 * 쪽지 발신자 페이지
	 * @param model
	 * @param ses
	 * @return
	 * @author 김완수
	 * @date 2017. 5. 13.
	 */
	@RequestMapping(value = "/sendnote")
	public String sendNote(Model model, HttpSession ses) {
		
		String division = "dspt";
		MemberVO vo = (MemberVO) ses.getAttribute("member");
		String mem_id = vo.getMem_id();
		List<MssVO> list = service.mssList(mem_id, division);
		
		model.addAttribute("mssList", list);
		
		return "note/sendlist";
		
	}
	
	/**
	 * 쪽지 수신자 페이지
	 * @param model
	 * @param ses
	 * @return
	 * @author 김완수
	 * @date 2017. 5. 13.
	 */
	@RequestMapping(value = "/receivenote")
	public String receiveNote(Model model, HttpSession ses) {
		String division = "recp";
		MemberVO vo = (MemberVO) ses.getAttribute("member");
		String mem_id = vo.getMem_id();
		List<MssVO> list = service.mssList(mem_id, division);
		
		model.addAttribute("mssList", list);
		
		return "note/receivelist";
		
	}
	
	/**
	 * 쪽지를 저장하는 부분
	 * @param req
	 * @param ses
	 * @return
	 * @author 김완수
	 * @date 2017. 5. 13.
	 */
	@RequestMapping(value = "/mssInsert")
	public String mssInsert(HttpServletRequest req,HttpSession ses) {
		MemberVO vo = (MemberVO) ses.getAttribute("member");
		
		String mem_id = vo.getMem_id();
		service.mssInsert(req,mem_id);
//		쪽지를 저장하고 발신자 페이지로 이동한다. 
		if(vo.getClsf_pk() >= 5){
			return "redirect:/manager/sendnote";
		}else{
			return "redirect:/member/sendnote";
		}
		
	}
	
	/**
	 * 쪽지를 삭제하는 부분
	 * @param ses
	 * @param mss_pk
	 * @param division
	 * @return
	 * @author 김완수
	 * @date 2017. 5. 13.
	 */
	@RequestMapping(value = "/mssDelete")
	public String mssDelete(HttpSession ses,@RequestParam int mss_pk,@RequestParam String division) {
		MemberVO vo = (MemberVO) ses.getAttribute("member");
		
		System.out.println(mss_pk);
		System.out.println(division);
		
		// 구분기호를 받아 해당 페이지로 이동 할 수 있게 처리함
		service.mssDeleteCheck(mss_pk, division);
		if (division.equals("dspt")) {
		   if(vo.getClsf_pk() >= 5){
				return "redirect:/manager/sendnote";
			}else{
				return "redirect:/member/sendnote";
			}
		}else{
			   if(vo.getClsf_pk() >= 5){
					return "redirect:/manager/receivenote";
				}else{
					return "redirect:/member/receivenote";
				}
		}
		
		
	}
	
	/**
	 * 쪽지 정보를 가져오는 부분
	 * @param mss_pk
	 * @param division
	 * @return
	 * @author 김완수
	 * @date 2017. 5. 13.
	 */
	@RequestMapping(value="/mssInfo", method=RequestMethod.POST)
	@ResponseBody
	public MssVO mssInfo(@RequestParam int mss_pk,@RequestParam String division){
		
		MssVO vo = new MssVO();
		
		vo = service.mssInfo(mss_pk, division);
	
		return vo;
	}
	
	/**
	 * 쪽지에 첨부된 파일을 다운로드 하기위한 부분
	 * @param req
	 * @return
	 * @throws Exception
	 * @author 김완수
	 * @date 2017. 5. 13.
	 */
    @RequestMapping(value="mssFileDown")
    public ModelAndView ModelAndView(HttpServletRequest req) throws Exception{
		// 사용자가 파일을 다운로드 할때 실제 저장된 이름으로 다운로드를 하면 이름이 이상하기 때문에 
		// real_name이라는 파마미터로 처리해서 해주면 사용자가 저장한 이름으로 다운로드가 된다. 
    	
		String file_name = req.getParameter("file_nm");
		// 파일이저장되어있는 주소와 파일명을 보내준다.
		String path = "d:/mssage_file/"+file_name;
		// 실제로 다운로드할 파일을 설정 
		File downloadFile = new File(path);
		if(!downloadFile.canRead()){
		throw new Exception("File can't read(파일을 찾을 수 없습니다)");
		}
		// 첫번째 해당뷰컨트롤러로 간다.  
		return new ModelAndView("fileDownloadView", "downloadFile",downloadFile);
    }
    
	

}
