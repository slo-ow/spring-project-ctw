package kr.or.ctw.vote.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.vote.vo.VoteChoiceVO;

@Repository("votechoiceDao")
public class VoteChoiceDaoImpl implements VoteChoiceDao {

	@Autowired
	private SqlMapClient client;

	// 투표 문항 등록
	@Override
	public int choiceInsert(VoteChoiceVO choicevo) throws SQLException {
		return (int) client.update("votechoice.choiceInsert",choicevo);
	}


	@Override
	public List<VoteChoiceVO> voteResult(int vote_pk) throws SQLException {
		return client.queryForList("votechoice.voteResult",vote_pk);
	}

	
	
	
}
