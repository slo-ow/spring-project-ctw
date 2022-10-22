package kr.or.ctw.personalFolder.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("memdtafolDao")
public class MemDtaFolDaoImpl implements MemDtaFolDao {

	@Autowired
	private SqlMapClient client;
	
}
