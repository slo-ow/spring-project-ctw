package kr.or.ctw.noticeboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("fileboardDao")
public class FileBoardDaoImpl implements FileBoardDao {

	@Autowired
	private SqlMapClient client;
}
