package kr.or.ctw.vote.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.vote.vo.VoteChoiceVO;
import kr.or.ctw.vote.vo.VoteVO;

@Repository("voteDao")
public class VoteDaoImpl implements VoteDao {

	@Autowired
	private SqlMapClient client;

	// 투표등록
	@Override
	public int voteInsert(VoteVO vo) throws SQLException {
		return (int) client.insert("vote.voteInsert", vo);
	}

	// 온라인투표목록 조회
	@Override
	public List<VoteVO> voteList(Map<String, Object> map) throws SQLException {
		return client.queryForList("vote.voteList",map);
	}

	// 투표상세보기
	@Override
	public VoteVO voteInfo(int vote_pk) throws SQLException {
		return (VoteVO) client.queryForObject("vote.voteInfo",vote_pk);
	}

	// 투표오픈
	@Override
	public int voteOpen(int vote_pk) throws SQLException {
		return client.update("vote.voteOpen",vote_pk);
	}

	// 투표삭제
	@Override
	public int voteDelete(int vote_pk) throws SQLException {
		return client.update("vote.voteDelete",vote_pk);
	}
	
}
