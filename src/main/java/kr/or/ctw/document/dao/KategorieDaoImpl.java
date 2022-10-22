package kr.or.ctw.document.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.document.vo.KategorieVO;

@Repository("KategorieDao")
public class KategorieDaoImpl  implements KategorieDao{
	
	@Autowired
	private SqlMapClient client;

	@Override
	public List<KategorieVO> listAll() throws SQLException {
		return client.queryForList("doc.kateGorieList");
	}

	@Override
	public void insert(KategorieVO vo) throws SQLException {
		
	}

	@Override
	public void update(KategorieVO vo) throws SQLException {
		
	}

	@Override
	public void delete(KategorieVO vo) throws SQLException {
		
	}
	
}
