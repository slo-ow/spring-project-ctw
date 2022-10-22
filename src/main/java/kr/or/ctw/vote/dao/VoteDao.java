package kr.or.ctw.vote.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ctw.vote.vo.VoteVO;

public interface VoteDao {

	// 투표등록
	int voteInsert(VoteVO vo) throws SQLException;

	// 온라인투표목록 조회
	List<VoteVO> voteList(Map<String, Object> map) throws SQLException;
	
	// 투표상세보기
	VoteVO voteInfo(int vote_pk) throws SQLException;

	// 투표오픈
	int voteOpen(int vote_pk) throws SQLException;

	// 투표삭제
	int voteDelete(int vote_pk) throws SQLException;
	
}
