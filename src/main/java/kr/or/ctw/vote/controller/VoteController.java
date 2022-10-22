package kr.or.ctw.vote.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ctw.member.service.MemberService;
import kr.or.ctw.member.vo.MemberVO;
import kr.or.ctw.vote.service.VoteService;
import kr.or.ctw.vote.vo.VoteChoiceVO;
import kr.or.ctw.vote.vo.VoteRtVO;
import kr.or.ctw.vote.vo.VoteVO;

// 온라인투표
@RequestMapping({"/manager","/member"})
@Controller
public class VoteController {

	@Resource(name = "voteService")
	VoteService service;
	
	@Resource(name = "memberService")
	MemberService m_service;
	
	// sidemenu -온라인투표 - 투표참여
	@RequestMapping(value = "/vote")
	public String voteParticipation(Model model, HttpServletRequest req) {
		
		try{
		// 로그인한 유저id 가져옴
		HttpSession session = req.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("member");
		String id = vo.getMem_id();
		System.out.println("아이디 - "+id);
		
		List<VoteVO> list = null;
		Map<String,Object> map = new HashMap<String,Object>();
		list = service.voteList(map);
		
		// 자신이 한 투표를 조회 (중복투표방지)
		map.put("mem_id", id);		
		List<VoteRtVO> rtlist = service.selectVoteRt(map);
		
		model.addAttribute("votelist",list);
		model.addAttribute("rtlist",rtlist);
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/index/main";
		}
		
		return "vote/list";
		
	}
	
	//	투표리스트
	@RequestMapping(value = "/voteList",method = RequestMethod.POST)
	@ResponseBody
	public List<VoteVO> voteList() {
		
		List<VoteVO> list = null;
		Map<String,Object> map = new HashMap<String,Object>();
		list = service.voteList(map);
		
		return list;
		
	}
	
	// 투표상세보기
	@RequestMapping(value = "/voteinfo")
	public String voteInfo(Model model,@RequestParam int vote_pk) {
		
		System.out.println(vote_pk);

		
		VoteVO vo = service.voteInfo(vote_pk);
		
		model.addAttribute("vote",vo);
		
		return "vote/voteinfo";

	}
	
	
	// sidemenu -온라인투표 - 나의투표함
	@RequestMapping(value = "/myvote")
	public String myVote(Model model,HttpServletRequest req) {
		
		try{
			// 세션가져오기
			HttpSession session = req.getSession();
			MemberVO vo = (MemberVO)session.getAttribute("member");
	//		System.out.println("세션확인 : " + vo.getMem_id());
			
			// 로그인한 유저의 리스트만 불러오기
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("mem_id", vo.getMem_id());
			List<VoteVO> list = null;
			
			list = service.voteList(map);
			model.addAttribute("votelist",list);
		
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/index/main";
		}

		return "vote/mylist";
	}

	// 투표등록
	@RequestMapping(value = "/voteInsert", method = RequestMethod.POST)
	public String voteInsert(VoteVO vo,HttpSession ses) {

		
		int res = 0;
		try {
			res = service.voteInsert(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(res);
		

		return "redirect:/manager/vote";

	}
	
	
	// 투표참여
	@RequestMapping(value = "/voteChoiceInsert")
	public String voteParticipation(VoteVO vo,HttpSession ses,@RequestParam int choice_fk) {
		
		
		int res = service.voteRtInsert(vo,choice_fk);
		
		return "redirect:/manager/vote";
		
	}

	// 투표율 가져오기
	@RequestMapping(value = "/voteChartList")
	@ResponseBody
	public List<VoteChoiceVO> voteRate(@RequestParam int vote_pk) {
		
		List<VoteChoiceVO> list = service.voteResult(vote_pk);
		
		return list;
		
	}
	
	// 투표오픈
	@RequestMapping(value = "/voteOpen")
	@ResponseBody
	public int voteOpen(@RequestParam int vote_pk) {
		
		int res = service.voteOpen(vote_pk);
		
		return res;
		
	}
	
	// 투표삭제
	@RequestMapping(value = "/voteDelete")
	@ResponseBody
	public int voteDelete(@RequestParam int vote_pk) {
		
		int res = service.voteDelete(vote_pk);
		
		return res;
		
	}
	
	
}
