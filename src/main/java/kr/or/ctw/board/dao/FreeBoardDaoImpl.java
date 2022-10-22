package kr.or.ctw.board.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.board.vo.LbrtyBBSVO;

@Repository("freeboardDao")
public class FreeBoardDaoImpl implements FreeBoardDao {

	@Autowired
	private SqlMapClient client;

	//게시글작성
	@Override
	public int insertBbs(LbrtyBBSVO vo) throws SQLException {
		return (int) client.insert("lbrtybbs.insertBbs",vo);
	}

	//게시글조회
	@Override
	public List<LbrtyBBSVO> listAll() throws SQLException {
		return client.queryForList("lbrtybbs.listAll");
	}

	//상세조회
	@Override
	public LbrtyBBSVO selectBbs(int pk) throws SQLException {
		return (LbrtyBBSVO) client.queryForObject("lbrtybbs.selectBbs", pk);
	}

	// 정보수정
	@Override
	public int updateBbs(LbrtyBBSVO vo) throws SQLException {
		return client.update("lbrtybbs.updateBbs", vo);
		
	}
	
	//게시글 삭제
	@Override
	public int deleteBbs(int bbs_pk) throws SQLException {
		return client.delete("lbrtybbs.deleteBbs",bbs_pk);
	}

	//조회수증가
	@Override
	public void upCntBbs(int pk) throws SQLException {
		client.update("lbrtybbs.upCntBbs", pk);
		
	}


	
}
