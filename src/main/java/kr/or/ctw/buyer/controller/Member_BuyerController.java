package kr.or.ctw.buyer.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ctw.buyer.service.BuyerService;
import kr.or.ctw.buyer.vo.BcncVO;

/**
 * @Class Name : Member_BuyerController.java
 * @Description : 조직원 거래처 Controller
 * @author 문성철 
 * @since  2017.04.24
 * @version 1.0
 */
// <조직원>
@RequestMapping("/member")
@Controller
public class Member_BuyerController {


	@Resource(name="buyerService")
	BuyerService service;


	// sidemenu - 개인거래처 - 거래처 조회
	@RequestMapping(value="/buyer",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject buyer(HttpServletRequest req, HttpServletResponse res, Model model,HttpSession ses){

		JSONObject json  = new JSONObject();
		String division ="bcnc_tmpr";
		List<BcncVO> list = service.listAll(division,ses);
		// 리스트의 전체목록을 가져온 이후 map에 넣는다.
		json.put("positions",list); 	

		return json;
	}




	// sidemenu - 개인거래처 - 거래처 요청
	@RequestMapping(value = "/buyerReq")
	public String buyerRequest(HttpServletRequest req, HttpServletResponse res, Model model,HttpSession ses) {

		//인코딩 설정
		res.setContentType("text/html;charset=UTF-8");

		List<BcncVO> list = null;
		String division = "";
		// 서비스를 변환 해서 division,ses 추가 되었습니다. 
		list = service.listAll(division,ses);

		model.addAttribute("list", list);
		return "buyer/requestbuyer";
	}

	
	/**
	 * <pre>
	 *	조직원 거래처 등록 요청
	 * </pre>
	 * @param BcncVO
	 * @return int
	 * @author 문성철
	 * @since 2017. 5. 2.
	 * @see requestbuyer.jsp
	 * 
	 */
	@RequestMapping(value="/addRequest", method=RequestMethod.POST)
	@ResponseBody
	public String buyerRequestInsert(BcncVO vo){
		
		System.out.println("******************** 거래처 등록요청 ********************");
		System.out.println("거래처 대표명    : " + vo.getBcnc_rprsntv());
		System.out.println("거래처 이름       :" +  vo.getBcnc_nm());
		System.out.println("거래처 전화번호 : " + vo.getBcnc_telno());
		System.out.println("거래처 주소       : " + vo.getBcnc_addr());
		System.out.println("담당사원 아이디 : " + vo.getMem_id());
		System.out.println("담당사원 이름    : " + vo.getBcnc_mem_name());
		System.out.println("담당사원 번호    : " + vo.getBcnc_mem_telno());
		System.out.println("담당사원 부서    : " + vo.getDept_pk());
		System.out.println("******************** 거래처 등록요청 ********************");
		
		//구분자 (임시거래처)
		String division = "bcnc_tmpr";
		
		int result = 0;
		result = service.insertBcnc(vo,division);
		
		String status;

		if(result > 0){//성공
			status = "DB 200 Succes";
		}else{//실패
			status = "DB 500 Error";
		}
		
		return status;
	}

}
