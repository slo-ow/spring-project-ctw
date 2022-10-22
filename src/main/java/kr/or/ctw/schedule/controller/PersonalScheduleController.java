package kr.or.ctw.schedule.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ctw.member.vo.MemberVO;
import kr.or.ctw.schedule.service.FxService;
import kr.or.ctw.schedule.vo.FxVO;


/**
 * 
 * 컨트롤러 
 * <pre>
 * 
 * </pre>
 * @author 김완수
 * @since 2017. 5. 2.
 * @version 1.0
 * @see kr.or.ctw.schedule.controller PersonalScheduleController
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일             수정자        수정내용
 * --------------     --------    -----------
 * 2017. 5. 2.     	  김완수        최초작성
 * Copyright (c) 2017 by DDIT All right reserved
 * </pre>
 */
@RequestMapping("/member")
@Controller
public class PersonalScheduleController {


		@Resource(name="fxService")
		FxService fxService;
		
		/**
		 * @Description 페이지 전환
		 * @param model
		 * @return
		 * @author 김완수
		 * @since 2017. 5. 2.
		 *
		 */
		@RequestMapping(value="/myschedule")
		public String scheduleList(Model model){
			return "schedule/personal/list";
		}
		/**
		 * 일정을 여러개 리스트 값을 보내야 되기 때문에 map형태로 반환한다. 
		 * Map<String,List<T>>
		 * @author 김완수 
		 * @param HttpSession
		 * @return Map<String,List<T>>
		 * 2017.04.20
		 * 
		 */
		@RequestMapping(value="/memFxList",method=RequestMethod.POST)
		@ResponseBody
		public Map<String,List<FxVO>> memFxList(HttpSession ses){
			MemberVO vo = (MemberVO) ses.getAttribute("member");
			List<FxVO> plist = null;
			List<FxVO> dlist = null;
			List<FxVO> clist = null;
			Map<String, String> map= null;
			// 각, 부서, 사원, 전체 리스트를 가져온다. 
			dlist =	fxService.deptList(vo);
			plist =	fxService.memList(vo);
			clist =   fxService.cmpnyList(vo);
			// map으로 각각 가져온 list를 담아준다 
			Map<String,List<FxVO>> map2 = new HashMap<>();
			map2.put("mem", plist);
			map2.put("dept", dlist);
			map2.put("cmpny", clist);
			return map2;
		}
		/**
		 * 해당 id의 기본키를 가져와서 구분기호와 함께 서비스로 넘겨준다 
		 * (일정의 상세보기 날짜와 시간, 내용등을 보여준다. 
		 * @param pk
		 * @param division
		 * @return vo
		 * @author 김완수
		 * 2017.04.24
		 * 
		 */
	   @RequestMapping(value="/fxInfo",method=RequestMethod.POST)
	   @ResponseBody
	   public FxVO scheduleInfo(@RequestParam String pk,@RequestParam String division){
		  FxVO vo = null;
		  vo = fxService.scheduleInfo(pk, division);
	      return vo;
	   }
	   /**
	    * 일정을 수정하는 메서드 
	    * 파라메터를 vo타입으로 받고 구분기호받아 서비스 단에서 처리한다. 
	    * @param vo
	    * @param division
	    * @return 컨트롤러 주소
	    * @author 김완수 
	    * 2017.04.24 
	    * 
	    * 
	    */
	   @RequestMapping(value="/fxupdate",method = RequestMethod.POST)
	   public String scheduleUpdate(FxVO vo,@RequestParam String division){
		   	fxService.scheduleUpdate(vo,division);
	      return "redirect:/member/myschedule";
	   }
	   /**
	    * 일정을 삭제하는 메서드 
	    * pk를 받아 구분기호와 함께 서비스로 넘겨준다. 
	    * @param pk
	    * @param division
	    * @return 컨트롤러 주소
	    * @author 김완수
	    * 
	    * 2017.04.24
	    */
	   @RequestMapping(value="/fxDelete",method = {RequestMethod.GET, RequestMethod.POST})
	   public String scheduleDelete(@RequestParam String pk,@RequestParam String division){
		   int fx_pk = Integer.parseInt(pk);
		   fxService.scheduleDelete(fx_pk,division);
	      return "redirect:/member/myschedule";

	   }

		/**
		 * 일정을 등록 하는 메서드이다
		 * 구분기호를 받아 서비스에서 처리한다. 
		 * @param vo
		 * @param division
		 * @param ses
		 * @return 컨트롤러 주소
		 * @author 김완수
		 * 2017.04.24
		 */
		@RequestMapping(value="/fxinsert",method=RequestMethod.POST)
		public String scheduleInsert(FxVO vo,@RequestParam String division,HttpSession ses){
			MemberVO memVO = (MemberVO) ses.getAttribute("member");
			vo.setMem_id(memVO.getMem_id());
				fxService.fxInsert(vo,division);
			return "redirect:/member/myschedule";
		}
		

	
	
}
