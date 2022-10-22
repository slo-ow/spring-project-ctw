package kr.or.ctw.board.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ctw.board.vo.LbrtyBBSFileVO;

// 자유게시판의 파일관련 crud
public interface FileFreeBoardDao {
	
	//조회
	public List<LbrtyBBSFileVO> selectBbsFileInfo(int bbs_f_pk) throws SQLException;
	
	//저장(멀티파일업로드)
	public void insertBbsFileInfo(LbrtyBBSFileVO vo) throws SQLException;
	
	//파일 원본이름찾기(다운로드)
	public String selectBbsFilenm(String file) throws SQLException;
	
	//파일 삭제
	public int deleteBbsFile(String bbs_f_pk) throws SQLException;
	

}
