package kr.or.ctw.noticeboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ctw.noticeboard.service.ManagementService;
import kr.or.ctw.noticeboard.vo.BBSActvtyVO;
/**
 * @Class Name :ManagementController.java
 * @Description : 관리자 게시판 관리 Controller
 * @author 박혜윤
 * @since  2017.04.25
 * @version 1.0
 */
/**
 * 
 * <pre>
 * 	게시판의 활성/비활성화 관리 
 * </pre>
 * @author 박혜윤
 * @since 2017. 4. 25.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자         수정내용
 * --------     --------    ----------------------
 * 2017. 4. 25.     박혜윤       최초작성
 * 2017. 5. 1.      박혜윤       /activation 추가
 * Copyright (c) 2017 by CTW All right reserved
 * </pre>
 */
// 게시판 관리
@RequestMapping({"/manager","/member"})
@Controller
public class ManagementController {

	@Resource(name = "managementService")
	ManagementService service;

	// sidemenu - 게시판관리 - 게시판관리
	// 게시판 내역보기
	@RequestMapping(value = "/manageList")
	public String boardList(Model model) {
		
		List<BBSActvtyVO> list = service.boardList();
		model.addAttribute("list",list);
		return "noticeboard/manage/listForm";
	}


	/**
	 * @Description  활성/비활성화 셋팅
	 * @param 
	 * @return json
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 1.
	 *
	 */
	@RequestMapping(value = "/activation", method = RequestMethod.POST)
	@ResponseBody
	public int activation(@RequestParam String actvty_pk, String actvty_nm) {
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("actvty_pk",actvty_pk );
		map.put("actvty_nm",actvty_nm );
		int res = service.activationUpdate(map);
		return res;
	}
	
	/**
	 * @Description  게시판 내역 리스트
	 * @param 
	 * @return json
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 1.
	 *
	 */
	@RequestMapping(value = "/baordListAll")
	@ResponseBody
	public List<BBSActvtyVO> boardListAjax() {
		
		List<BBSActvtyVO> list = service.boardList();
		return list;
	}
	



}
