package kr.or.ctw.personalFolder.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("memdtaDao")
public class MemDtaDaoImpl implements MemDtaDao {

	@Autowired
	private SqlMapClient client;
	
}
