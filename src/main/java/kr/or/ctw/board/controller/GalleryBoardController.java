package kr.or.ctw.board.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.map.util.JSONPObject;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ctw.board.service.GalleryMultiService;
import kr.or.ctw.board.vo.PhotoExprsnVO;
import kr.or.ctw.board.vo.PhotoFileVO;
import kr.or.ctw.board.vo.PhotoVO;
import kr.or.ctw.common.vo.pagingVO;

// <관리자>
// 갤러리 게시판
/**
 * <pre>
 * 
 * </pre>
 * @author 작성자명
 * @since 2017. 5. 1.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자         수정내용
 * --------     --------    ----------------------
 * 2017. 5. 1.   박혜윤       최초작성
 * Copyright (c) 2017 by CTW All right reserved
 * </pre>
 */
@RequestMapping({"/manager","/member"})
@Controller
public class GalleryBoardController {

	@Resource(name="gallerymultiService")
	GalleryMultiService service;
	

	// sidemenu - 게시판관리 - 게시물관리 - 갤러리게시판
	@RequestMapping(value="/gallery")
	public String galleryBoard(){
		return "board/gallery/list";
		
	}
	
	// 사진등록 potoInsert
	@RequestMapping(value="/potoInsert", method = RequestMethod.POST)
	public String potoInsert(PhotoVO vo){
		
		try {
			int res = service.potoInsert(vo);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "redirect:/manager/gallery";
		
	}
	
	// 사진 조회 potoList
	@RequestMapping(value="/pagingMove")
	@ResponseBody
	public JSONObject pageMove(PhotoVO vo, pagingVO page){
		
		page.setBlockSize(5);
		page.setScreenSize(12);
		JSONObject pageResult = new JSONObject();
		
		Map<String, Object> map = new HashMap<String,Object>();
		List<PhotoVO> count = service.potoList(map);
		
		int totalRecords = count.size();
		page.setTotalRecords(totalRecords);
		List<PhotoVO> list = service.listAll(page);
		
		page.setDataList(list);
		pageResult.put("page", page);
		
		return pageResult;
		
		
	}
	
	
	// 좋아요 눌렀을때 photo_exprsn테이블 인서트
	@ResponseBody
	@RequestMapping(value="/likeUp")
	public int likeInsert(@RequestParam String mem_id, int photo_fk){
		int res = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mem_id",mem_id );
		map.put("photo_fk",photo_fk );
		res = service.likeInsert(map);
		return res;
		
	}
	
	// 좋아요취소 - delete
	@ResponseBody
	@RequestMapping(value="/likedelete",method = RequestMethod.POST)
	public int likedelete(@RequestParam String mem_id, int photo_fk){
		int res = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mem_id",mem_id );
		map.put("photo_fk",photo_fk );
		res = service.likedelete(map);
		return res;
		
	}
	
	
	
	//	likeProcess
	@RequestMapping(value="/likeProcess")
	@ResponseBody
	public JSONObject likeProcess(@RequestParam String mem_id, int photo_fk){
		JSONObject obj = new JSONObject();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("photo_fk", photo_fk);
		map.put("mem_id", mem_id);
		List<PhotoExprsnVO> list = service.photoExprsnList(map);
		
		System.out.println("흠  " +photo_fk);
		System.out.println("아이디  "+mem_id);
		
		if(list.size() == 0){
			service.likeInsert(map);
			obj.put("test", "좋아요");
		}else{
			service.likedelete(map);
			obj.put("test", "좋아요취소");
		}
		
		return obj;
		
	}
	
	
	// 사진 리스트 selectInfo
	@ResponseBody
	@RequestMapping(value="/selectInfo",method = RequestMethod.POST)
	public List<PhotoFileVO> selectInfo(@RequestParam int photo_pk){
		
		List<PhotoFileVO> list = service.selectInfo(photo_pk);
		System.out.println("++++사진리스트+++++"+list.size());
		
		return list;
		
	}
	
	
	// 삭제
	@ResponseBody
	@RequestMapping(value="/photoDelete",method = RequestMethod.POST)
	public int photoDelete(@RequestParam int photo_pk){
		
		int res = service.photoDelete(photo_pk);
		System.out.println(res+"res값은?");
		return res;
		
	}
	
	
	
}
