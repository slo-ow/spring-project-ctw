package kr.or.ctw.common.test;

import java.sql.SQLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("countDao")
public class CountDaoImpl implements CountDao{
	
	@Autowired
	private SqlMapClient client ;

	
	@Override
	public int testCal() throws SQLException {
		
		return (int) client.queryForObject("fx.testCnt");
		
	}




}
