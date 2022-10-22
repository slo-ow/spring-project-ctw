package kr.or.ctw.vote.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ctw.vote.dao.VoteChoiceDao;
import kr.or.ctw.vote.dao.VoteDao;
import kr.or.ctw.vote.dao.VoteRtDao;
import kr.or.ctw.vote.vo.VoteChoiceVO;
import kr.or.ctw.vote.vo.VoteRtVO;
import kr.or.ctw.vote.vo.VoteVO;

@Service("voteService")
public class VoteServiceImpl implements VoteService {

	@Resource(name="voteDao")
	VoteDao votedao;
	
	@Resource(name="votechoiceDao")
	VoteChoiceDao choicedao;
	
	
	@Resource(name="votertDao")
	VoteRtDao ratedao;


	// 투표 등록
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=SQLException.class)
	public int voteInsert(VoteVO vo) throws SQLException {
		
		
		List<VoteChoiceVO> list = vo.getQuestions();
		int res = 0;
		int res2 = 0;
		
		res = votedao.voteInsert(vo);
		
		int cnt=1;
		for(VoteChoiceVO questions : list){
			VoteChoiceVO choicevo = new VoteChoiceVO();
			choicevo.setChoice_no(cnt);
			choicevo.setChoice_cn(questions.getChoice_cn());
			choicevo.setVote_fk(res);
			res2= choicedao.choiceInsert(choicevo);
			cnt++;
		}
		
		
		
		
		return res;
	}

	
	// 온라인투표목록 조회
	@Override
	public List<VoteVO> voteList(Map<String, Object> map){
		List<VoteVO> list = null;
		try {
			list = votedao.voteList(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}



	// 투표 상세보기
	@Override
	public VoteVO voteInfo(int vote_pk) {
		VoteVO vo = null;
		
		try {
			vo = votedao.voteInfo(vote_pk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}

	
	// 투표율 등록
	@Override
	public int voteRtInsert(VoteVO vo,int choice_fk) {
		int res = 0;
		
		VoteRtVO rtvo = new VoteRtVO();
		rtvo.setVote_fk(vo.getVote_pk());
		rtvo.setMem_id(vo.getMem_id());
		
		// 선택한 번호
		rtvo.setChoice_fk(choice_fk);
		
		try {
			res = ratedao.voteRtInsert(rtvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}


	// 투표율
	@Override
	public List<VoteRtVO> selectVoteRt(Map<String, Object> map) {
		List<VoteRtVO> list = null;
		try {
			list = ratedao.selectVoteRt(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	// 투표결과
	@Override
	public List<VoteChoiceVO> voteResult(int vote_pk) {
		
		List<VoteChoiceVO> list = null;
		try {
			list = choicedao.voteResult(vote_pk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	// 투표오픈
	@Override
	public int voteOpen(int vote_pk) {
		int res = 0;
		try {
			res = votedao.voteOpen(vote_pk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 투표참여 상세조회(X)
	@Override
	public VoteRtVO selectVoteRtInfo(int vote_pk) {
		VoteRtVO vo = null;
		try {
			vo = ratedao.selectVoteRtInfo(vote_pk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}


	// 투표삭제
	@Override
	public int voteDelete(int vote_pk) {
		int res = 0;
		try {
			res = votedao.voteDelete(vote_pk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}


	




	
	
	
	
}
