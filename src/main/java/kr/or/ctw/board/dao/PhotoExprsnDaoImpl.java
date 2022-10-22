package kr.or.ctw.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.board.vo.PhotoExprsnVO;

@Repository("photoexprsnDao")
public class PhotoExprsnDaoImpl implements PhotoExprsnDao {

	@Autowired
	private SqlMapClient client;
	
	@Override
	public int likeInsert(Map<String, Object> map) throws SQLException {
		return (int) client.insert("photoexprsn.likeInsert",map);
	}

	@Override
	public int likedelete(Map<String, Object> map) throws SQLException {
		return client.update("photoexprsn.likedelete",map);
	}

	@Override
	public List<PhotoExprsnVO> photoExprsnList(Map<String, Object> map) throws SQLException {
		return client.queryForList("photoexprsn.photoExprsnList",map);
	}

	@Override
	public List<PhotoExprsnVO> likeCount() throws SQLException {
		return client.queryForList("photoexprsn.likeCount");
	}

}
