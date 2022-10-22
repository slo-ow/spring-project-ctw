package kr.or.ctw.board.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ctw.board.vo.LbrtyBBSAnsVO;

// 자유게시판의 댓글  crud
public interface FreeBoardAnswerDao {

	//댓글 저장
	public int freeInsertAnswer(LbrtyBBSAnsVO vo) throws SQLException;
	
	//댓글 조회
	public List<LbrtyBBSAnsVO> freeListAnswer(int bbs_ans_pk) throws SQLException;
	
	//댓글 삭제
	public int freeDeleteAnswer(int bbs_ans_pk) throws SQLException;
	
}
