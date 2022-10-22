package kr.or.ctw.vote.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ctw.vote.vo.VoteChoiceVO;

public interface VoteChoiceDao {

	// 투표 문항 등록
	int choiceInsert(VoteChoiceVO choicevo) throws SQLException;
	
	// 투표결과
	List<VoteChoiceVO> voteResult(int vote_pk) throws SQLException;
}
