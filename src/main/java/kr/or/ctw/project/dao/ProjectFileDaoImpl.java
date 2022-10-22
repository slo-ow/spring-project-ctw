package kr.or.ctw.project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("projectfileDao")
public class ProjectFileDaoImpl implements ProjectFileDao {
	
	@Autowired
	private SqlMapClient client;
	
}
