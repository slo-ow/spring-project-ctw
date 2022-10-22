package kr.or.ctw.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.board.vo.PhotoVO;
import kr.or.ctw.common.vo.pagingVO;

@Repository("galleryboardDao")
public class GalleryBoardDaoImpl implements GalleryBoardDao {

	@Autowired
	private SqlMapClient client;

	// 사진등록
	@Override
	public int potoInsert(PhotoVO vo) throws SQLException {
		return (int) client.insert("photo.potoInsert",vo);
	}

	
	// 갤러리 조회
	@Override
	public List<PhotoVO> potoList(Map<String, Object> map) throws SQLException {
		return client.queryForList("photo.potoList",map);
	}
	
	@Override
	public List<PhotoVO> listAll(pagingVO page) throws SQLException {
		return client.queryForList("photo.listAll",page);
	}


	@Override
	public int photoDelete(int photo_pk) throws SQLException {
		return client.update("photo.photoDelete",photo_pk);
	}



	
}
