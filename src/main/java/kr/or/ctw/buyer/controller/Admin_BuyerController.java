package kr.or.ctw.buyer.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ctw.buyer.service.BuyerService;
import kr.or.ctw.buyer.vo.BcncVO;

/**
 * @Class Name : Admin_BuyerController.java
 * @Description : 관리자 거래처 Controller
 * @author 문성철 
 * @since  2017.04.24
 * @version 1.0
 */
@RequestMapping("/manager")
@Controller
public class Admin_BuyerController {

	@Resource(name = "buyerService")
	BuyerService service;

	/**
	 * <pre>
	 *	모든 거래처의 현황을 보여주는 메소드
	 * </pre>
	 * @param  
	 * @return list
	 * @author 문성철
	 * @since 2017. 5. 4.
	 * @see departmentbuyer.jsp
	 * 
	 */
	// sidemenu - 거래처관리 - 거래처 현황
	@RequestMapping(value = "/buyer")
	public String companyBuyer(Model model,HttpSession ses) {
		String division ="bcnc";
		List<BcncVO> list = service.listAll(division,ses);	
		// 리스트를 addAttribute로 저장해서 보내준다.
		model.addAttribute("comlist",list);
		return "buyer/companybuyer";
	}

	
	
	
	/**
	 * <pre>
	 *	거래처 요청 내역을 리스트로 가져오는 메소드
	 * </pre>
	 * @param  
	 * @return list
	 * @author 문성철
	 * @since 2017. 5. 4.
	 * @see departmentbuyer.jsp
	 * 
	 */
	// sidemenu - 거래처관리 - 거래처 요청 승인 (게시판)
	@RequestMapping(value = "/debuyer")
	public String departmentBuyer(Model model,HttpSession ses) {
		// 착각해서 임시저장 테이블을 불러옴 
		String division ="bcnc_tmpr";
		List<BcncVO> list = service.listAll(division,ses);	
		// 리스트를 addAttribute로 저장해서 보내준다.
		model.addAttribute("deptlist",list);
		
		return "buyer/departmentbuyer";
	}

	// 거래처 리스트, 보기권한현황도 같이 나옴
	@RequestMapping(value = "/buyerlist",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject buyerList(HttpServletRequest req, HttpSession ses) {
		
		JSONObject json  = new JSONObject();
		String division ="bcnc";
		List<BcncVO> list = service.listAll(division,ses);
		// 리스트의 전체목록을 가져온 이후 map에 넣는다.
		json.put("positions",list);
		
		return json;
	}

	// 거래처 등록폼
	@RequestMapping(value = "/buyerInsertForm")
	public String buyerInsertForm() {

		return "";
	}

	/**
	 * <pre>
	 *	거래처 승인(등록) 메서드
	 * </pre>
	 * @param String 
	 * @return 
	 * @author 문성철
	 * @since 2017. 5. 2.
	 * @see departmentbuyer.jsp
	 * 
	 */
	// 거래처 승인 (등록)
	@RequestMapping(value = "/buyerInsert", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject buyerInsert(@RequestParam int pk,@RequestParam String division) {
		
		JSONObject json = new JSONObject();
		
		System.out.println("\t\t\t거래처 등록요청 글번호 : " + pk);
		System.out.println("\t\t\t거래처 등록 구분 확인   : " + division);
		
		BcncVO vo = new BcncVO(); 
		
		/*// 선택한 글의 상태값을 변경시킨다.
		service.updateStatus(bcnc_pk);*/
		
		// 선택한 글 번호를 넘겨받아서 글에대한 정보를 넘겨받는다
		String division2 = "bcnc_tmpr";
		vo = service.infoBcnc(pk, division2);
		// 선택한 글을 최종(승인) 처리해서 거래처 테이블에 저장한다.
		int res = service.insertBcnc(vo,division);
		System.out.println("\t\t\t거래처 테이블 등록 결과 시퀀스값   : " + res);
		
		json.put("res", res);
		
		return json;
		
	}

	// 거래처 일괄등록
	@RequestMapping(value = "/buyerBatch")
	public String batchInsert() {
		
		return null;
	}
	
	/**
	 * <pre>
	 *	거래처의 상세정보를 가져오는 메서드
	 * </pre>
	 * @param String 
	 * @return vo
	 * @author 문성철
	 * @since 2017. 5. 2.
	 * @see departmentbuyer.jsp
	 * 
	 */
	@RequestMapping(value = "/buyerInfo",method=RequestMethod.POST)
	@ResponseBody
	public BcncVO buyerInfo(@RequestParam String pk,@RequestParam String division) {
		
		BcncVO vo = new BcncVO(); 
		int bcnc_pk = Integer.parseInt(pk);
		
		vo = service.infoBcnc(bcnc_pk, division);
		
		return vo;
	}

	// 거래처 수정
	@RequestMapping(value = "/buyerUpdate")
	public String buyerUpdate() {
		
		return "redirect:/buyerInfo";
	}

	// 거래처 삭제(update)
	@RequestMapping(value = "/buyerDelete")
	public String buyerDelete() {

		return "redirect:/buyerList";
	}

	// 거래처요청리스트 - 조직원한테 거래처 요청을 받음
	@RequestMapping(value = "/buyerReqlist")
	public String buyerRequestList() {

		return "buyerReqlist";
	}

	// 거래처요청 상세보기 - 등록양식을 확인함
	@RequestMapping(value = "/buyerReqinfo")
	public String buyerRequestInfo() {

		return "buyereqinfo";
	}

}
