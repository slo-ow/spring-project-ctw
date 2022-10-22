package kr.or.ctw.board.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.board.vo.PhotoFileVO;

@Repository("filegalleryboardDao")
public class FileGalleryBoardDaoImpl implements FileGalleryBoardDao {

	@Autowired
	private SqlMapClient client;

	// 이미지 저장
	@Override
	public void photoFileInsert(PhotoFileVO imgvo) throws SQLException {
		client.update("photofile.photoFileInsert", imgvo);
	}

	@Override
	public List<PhotoFileVO> selectInfo(int photo_pk) throws SQLException {
		return client.queryForList("photofile.selectInfo",photo_pk);
	}
	
	
}
