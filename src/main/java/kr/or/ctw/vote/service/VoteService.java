package kr.or.ctw.vote.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ctw.vote.vo.VoteChoiceVO;
import kr.or.ctw.vote.vo.VoteRtVO;
import kr.or.ctw.vote.vo.VoteVO;

public interface VoteService {

	// 투표 등록
	public int voteInsert(VoteVO vo) throws SQLException;
	
	// 온라인투표목록 조회
	public List<VoteVO> voteList(Map<String, Object> map) ;
	
	
	// 투표 상세조회
	public VoteVO voteInfo(int vote_pk);

	// 투표율 등록 (투표하기 버튼누르면 수행)
	public int voteRtInsert(VoteVO vo, int choice_fk);

	// 투표율
	public List<VoteRtVO> selectVoteRt(Map<String, Object> map);

	// 투표결과
	public List<VoteChoiceVO> voteResult(int vote_pk);

	// 투표오픈
	int voteOpen(int vote_pk);
	
	// 투표참여 상세조회
	VoteRtVO selectVoteRtInfo(int vote_pk);

	// 투표삭제
	public int voteDelete(int vote_pk);
	
	
}
