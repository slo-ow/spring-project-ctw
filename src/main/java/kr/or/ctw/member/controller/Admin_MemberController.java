package kr.or.ctw.member.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ctw.member.service.MemberService;
import kr.or.ctw.member.vo.MemberVO;


/**
 * 
 * 
 * <pre>
 * Manager Controller
 * </pre>
 * @author 박창현
 * @since 2017. 4. 28.
 * @version 1.0
 * @see 
 *
 * [[개정이력(Modification Information)]]
 * 수정일             수정자      수정내용
 * --------------     --------    -----------
 * 2017. 4. 28.              
 * Copyright (c) 2017 by DDIT All right reserved
 *
 */
//@RequestMapping({"/manager","/member"})
@RequestMapping("/manager")
@Controller
public class Admin_MemberController {

	// 서비스
	@Resource(name = "memberService")
	MemberService service;


	/**
	 * 
	 * 
	 * @Description 조직원 등록 Controller
	 * @param 
	 * @return insertForm.jsp(ViewPage)
	 * @author 박창현
	 * @see 
	 * @since 2017. 4. 28.
	 *
	 */
	@RequestMapping(value = "/organizationInsertForm")
	public String memberInsertForm() {

		return "member/organization/memberinsert";

	}

	/**
	 * 
	 * 
	 * @Description ViewPage(insertForm.jsp)에서 넘겨받은 값으로
	 * 				insert쿼리를 진행하는 Controller 
	 * @param MemberVO
	 * @return memberList(Controller) 
	 * @author 박창현
	 * @see 
	 * @since 2017. 4. 28.
	 *
	 */
	@RequestMapping(value ="/memberInsert", method=RequestMethod.POST)
	public String memberInsert(MemberVO vo) {
		

		service.memberInsert(vo);
		
	
		return "redirect:/manager/memberList";
	}

	/**
	 * 
	 * 
	 * @Description 조직원 현황을 Select쿼리하여 Model에 담은뒤
	 * 				ViewPage(memberlist.jsp)로 이동시켜주는 Controller
	 * @param 
	 * @return memberlist.jsp(ViewPage)
	 * @author 박창현
	 * @see 
	 * @since 2017. 4. 28.
	 *
	 */
	@RequestMapping(value = "/memberList")
	public String memberList(Model model) {

		List<MemberVO>list = new ArrayList<>();
		
		list = service.memberSelect();
		
		model.addAttribute("list",list);
		
		return "member/organization/memberlist";
	

	}

	/**
	 * 
	 * 
	 * @Description orgChart.js(조직도 ViewPage)에서 Ajax방식으로 데이터베이스 값을 얻어오는 Controller
	 * @param 
	 * @author 박창현
	 * @since 2017. 5. 15.
	 *
	 */
	@RequestMapping(value = "/ajaxList", method=RequestMethod.POST)
	@ResponseBody
	public List<MemberVO> ajaxList(MemberVO vo, Model model) {
		
		List<MemberVO>list = new ArrayList<>();
		
		list = service.memberSelect();
		
		return list;
		
	}

	/**
	 * 조직원 선택조회(상세보기) 
	 * 선택한 조직원의 상세정보를 확인할 수 있는 Controller  
	 * @return memberlist.jsp(ViewPage)
	 * 
	 * @author 박창현
	 * 2017.04.24
	 */
	@RequestMapping(value = "/selectMember")
	@ResponseBody
	public MemberVO selectMember(MemberVO vo, Model model, HttpServletRequest reqest, HttpServletResponse response) {
		
		String id = reqest.getParameter("id");
		
		System.out.println("id=" + id);
		
		vo = service.detailView(id);
		
		model.addAttribute("vo",vo);
		
		return vo;	

	}
	
	/**
	 * 
	 * 조직원 정보 불러오기
	 * UPDATE를 위해 필요한 정보를 SELECT
	 * 
	 * @return memberupdate.jsp(ViewPage)
	 * 
	 * @author 박창현
	 */
	@RequestMapping(value="/selectUpdate",method=RequestMethod.GET)
	public String selectUpdate( Model model, @RequestParam String mem_id){
		MemberVO vo = service.detailView(mem_id);
		System.out.println("넘어온grad=" + vo.getGrad_pk());

		System.out.println(vo.getMem_addr());
		model.addAttribute("vo",vo);
		
		return "member/organization/memberupdate";
	}
	
	
	/**
	 * 
	 * 조직원 수정
	 * 선택한 조직원의 정보를 수정할 수 있는 Controller
	 * @return memberupdate.jsp(ViewPage)
	 * 
	 * @author 박창현
	 * 2017.04.25
	 */
	@RequestMapping(value = "/memberUpdate", method=RequestMethod.POST)
	public String memberUpdate(MemberVO vo) {

		service.updateMamber(vo);
	
		return "redirect:/manager/memberList";
	}
	
	/**
	 * 
	 * @Description View페이지(memberinsert.jsp)에서 Ajax로 넘겨준 값을 처리하는 메서드
	 * @param 
	 * @return 
	 * @author 박창현
	 * @since 2017. 5. 11.
	 *
	 */
    @RequestMapping(value = "/excelUploadAjax", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject excelUploadAjax(HttpServletRequest request){
    	
    	JSONObject obj = new JSONObject();
    	
    	MultipartHttpServletRequest multReq = (MultipartHttpServletRequest) request;
    	
    	MultipartFile excelFile = multReq.getFile("excelFile");
        System.out.println("엑셀 파일 업로드 컨트롤러");
        if(excelFile==null || excelFile.isEmpty()){
            throw new RuntimeException("엑셀파일을 선택 해 주세요.");
        }
        File uploadFile = new File("D:\\"+excelFile.getOriginalFilename());
        try{
            excelFile.transferTo(uploadFile);
        }catch(IOException e){
            throw new RuntimeException(e.getMessage(),e);
        }
        
        int res = 0;
		try {
			res = service.massiveInsert(uploadFile);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        uploadFile.delete();
        
        obj.put("res", res);
/*        ModelAndView view = new ModelAndView();
        view.setViewName("");*/
        return obj;
        
//        return "redirect:/manager/memberList";
    }	
	
    /**
     * @Description 일괄등록이 성공한 후에, 조직원 List로 보내주는 메서드
     * @return 
     * @author 박창현
     * @since 2017. 5. 15.
     *
     */
    @RequestMapping(value="massiveSuccess")
    public String massiveSuccess(){
    	
    	return "redirect:/manager/memberList";
    	
    }
    
    /**
     * 
     * 
     * @Description 등록양식 파일을 다운로드 할 수 있는 메서드
     * @param 
     * @return 
     * @author 박창현
     * @since 2017. 5. 15.
     *
     */
    @RequestMapping(value="download/massiveDown")
    public ModelAndView ModelAndView(HttpServletRequest req) throws Exception{
		// 사용자가 파일을 다운로드 할때 실제 저장된 이름으로 다운로드를 하면 이름이 이상하기 때문에 
		// real_name이라는 파마미터로 처리해서 해주면 사용자가 저장한 이름으로 다운로드가 된다. 
		String doc_pk = "등록양식.xlsx";
		// 파일이저장되어있는 주소와 파일명을 보내준다.
		String path = "d:/excelDoc/"+doc_pk; 
		// 실제로 다운로드할 파일을 설정 
		File downloadFile = new File(path);
		if(!downloadFile.canRead()){
		throw new Exception("File can't read(파일을 찾을 수 없습니다)");
		}
		// 첫번째 해당뷰컨트롤러로 간다.  
		return new ModelAndView("fileDownloadView", "downloadFile",downloadFile);
    }
    
    
	
    /**
     * 
     * 
     * @Description 조직도 ViewPage를 출력하기 위한 메서드
     * @param 
     * @return 
     * @author 박창현 
     * @since 2017. 5. 15.
     *
     */
 	@RequestMapping(value = "/orgChart")
	public String orgChart() {
		return "member/organization/orgchart";

	}


}
