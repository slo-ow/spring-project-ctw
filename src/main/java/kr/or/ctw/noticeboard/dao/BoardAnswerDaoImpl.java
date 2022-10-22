package kr.or.ctw.noticeboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("boardanswerDao")
public class BoardAnswerDaoImpl implements BoardAnswerDao {

	@Autowired
	private SqlMapClient client;
	
}
