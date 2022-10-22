package kr.or.ctw.project.dao;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.project.vo.PrjctPartcptnVO;

@Repository("projectparticipantsDao")
public class ProjectParticipantsDaoImpl implements ProjectParticipantsDao {

	@Autowired
	private SqlMapClient client;

	@Override
	public void projectMemberInsert(PrjctPartcptnVO vo) throws SQLException {
		client.update("prjctpartcptn.projectMemberInsert",vo);
	}

	@Override
	public int acceptProject(Map<String, Object> map) throws SQLException {
		return client.update("prjctpartcptn.acceptProject",map);
	}
	
}
