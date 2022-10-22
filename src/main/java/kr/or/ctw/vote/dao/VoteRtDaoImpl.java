package kr.or.ctw.vote.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.vote.vo.VoteChoiceVO;
import kr.or.ctw.vote.vo.VoteRtVO;


@Repository("votertDao")
public class VoteRtDaoImpl implements VoteRtDao {

	@Autowired
	private SqlMapClient client;

	@Override
	public int voteRtInsert(VoteRtVO vo) throws SQLException {
		return (int) client.insert("votert.voteRtInsert",vo);
	}
	
	// 투표율
	@Override
	public List<VoteRtVO> selectVoteRt(Map<String, Object> map) throws SQLException {
		return client.queryForList("votert.selectVoteRt",map);
	}

	// 투표참여 상세조회(X)
	@Override
	public VoteRtVO selectVoteRtInfo(int vote_pk) throws SQLException {
		return (VoteRtVO) client.queryForObject("votert.selectVoteRtInfo",vote_pk);
	}
	
}
