package kr.or.ctw.board.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ctw.board.vo.LbrtyBBSVO;

// 자유게시판 게시글  crud
public interface FreeBoardDao {
	
	//저장
	public int insertBbs(LbrtyBBSVO vo) throws SQLException;
	
	//조회
	public List<LbrtyBBSVO> listAll() throws SQLException;
	
	//수정
	public int updateBbs(LbrtyBBSVO vo) throws SQLException;
	
	//삭제
	public int deleteBbs(int bbs_pk) throws SQLException;
	
	//상세조회, 검색
	public LbrtyBBSVO selectBbs(int pk) throws SQLException;
	
	//조회수증가
	public void upCntBbs(int pk) throws SQLException;

}
