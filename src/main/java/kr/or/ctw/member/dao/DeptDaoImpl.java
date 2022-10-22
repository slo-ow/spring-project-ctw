package kr.or.ctw.member.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("deptDao")
public class DeptDaoImpl implements DeptDao {

	@Autowired
	private SqlMapClient client;
	
}
