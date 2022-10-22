package kr.or.ctw.noticeboard.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.noticeboard.vo.BBSActvtyVO;


@Repository("boardmanagerDao")
public class BoardMangerDaoImpl implements BoardMangerDao{

	@Autowired
	private SqlMapClient client;

	@Override
	public List<BBSActvtyVO> boardList() throws SQLException {
		return client.queryForList("bbsactvty.boardList");
	}
	
	
	@Override
	public int activationUpdate(Map<String, Object> map) throws SQLException {
		return client.update("bbsactvty.activationUpdate",map);
	}

	
}
