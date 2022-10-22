package kr.or.ctw.vote.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ctw.vote.vo.VoteRtVO;

public interface VoteRtDao {

	// 투표율저장
	int voteRtInsert(VoteRtVO rtvo) throws SQLException;

	// 투표율
	List<VoteRtVO> selectVoteRt(Map<String, Object> map) throws SQLException;
	
	// 투표참여 상세조회
	VoteRtVO selectVoteRtInfo(int vote_pk) throws SQLException;
	
}
