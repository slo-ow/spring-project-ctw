package kr.or.ctw.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.project.vo.PrjctVO;

@Repository("projectDao")
public class ProjectDaoImpl implements ProjectDao {

	@Autowired
	private SqlMapClient client;

	@Override
	public List<PrjctVO> projectList(String mem_id) throws SQLException {
		return client.queryForList("prjct.projectList",mem_id);
	}

	@Override
	public int projectInsert(PrjctVO prjctVO) throws SQLException {
		
		return (int) client.insert("prjct.projectInsert",prjctVO);
		
	}

	@Override
	public PrjctVO projectInfo(int prjct_pk) throws SQLException {
		return (PrjctVO) client.queryForObject("prjct.projectInfo",prjct_pk);
	}
	
}
