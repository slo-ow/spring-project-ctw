package kr.or.ctw.board.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.board.vo.LbrtyBBSFileVO;

@Repository("filefreeboardDao")
public class FileFreeBoardDaoImpl implements FileFreeBoardDao {

	@Autowired
	private SqlMapClient client;

	//조회
	@Override
	public List<LbrtyBBSFileVO> selectBbsFileInfo(int bbs_f_pk) throws SQLException {
		return client.queryForList("lbrtybbsfile.selectBbsFileInfo", bbs_f_pk);
	}

	//멀티파일업로드
	@Override
	public void insertBbsFileInfo(LbrtyBBSFileVO vo) throws SQLException {
		client.insert("lbrtybbsfile.insertBbsFileInfo", vo);
	}

	//다운로드
	@Override
	public String selectBbsFilenm(String file) throws SQLException {
		return (String) client.queryForObject("lbrtybbsfile.selectBbsFilenm", file);
	}

	//삭제
	@Override
	public int deleteBbsFile(String bbs_f_pk) throws SQLException {
		return client.delete("lbrtybbsfile.deleteBbsFile", bbs_f_pk);
	}
	
}
