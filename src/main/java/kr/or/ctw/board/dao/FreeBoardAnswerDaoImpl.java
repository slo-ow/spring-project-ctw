package kr.or.ctw.board.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.board.vo.LbrtyBBSAnsVO;

@Repository("freeboardanswerDao")
public class FreeBoardAnswerDaoImpl implements FreeBoardAnswerDao {

	@Autowired
	private SqlMapClient client;

	@Override
	public int freeInsertAnswer(LbrtyBBSAnsVO vo) throws SQLException {
		return (int) client.insert("lbrtybbsans.freeInsertAnswer", vo);
	}

	@Override
	public List<LbrtyBBSAnsVO> freeListAnswer(int bbs_ans_pk) throws SQLException {
		return client.queryForList("lbrtybbsans.freeListAnswer", bbs_ans_pk);
	}

	@Override
	public int freeDeleteAnswer(int bbs_ans_pk) throws SQLException {
		return client.delete("lbrtybbsans.freeDeleteAnswer", bbs_ans_pk);
	}
}
