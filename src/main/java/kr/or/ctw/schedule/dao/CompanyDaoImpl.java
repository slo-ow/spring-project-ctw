package kr.or.ctw.schedule.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.member.vo.MemberVO;
import kr.or.ctw.schedule.vo.FxVO;

@Repository("companyDao")
public class CompanyDaoImpl implements FxDao {

	@Autowired
	private SqlMapClient client;

	@Override
	public List<FxVO> scheduleList(MemberVO vo) throws SQLException {
		return client.queryForList("fx.cmpnyFxList");
	}
	@Override
	public int scheduleUpdate(FxVO vo) throws SQLException {
		return client.update("fx.comFxUpdate",vo);
	}
	@Override
	public void fxInsert(FxVO vo) throws SQLException {
		client.update("fx.comFxInsert",vo);
	}
	@Override
	public FxVO fxInfo(int fx_pk) throws SQLException {
		return (FxVO) client.queryForObject("fx.comInfo", fx_pk);
	}
	@Override
	public void fxDelete(int pk) throws SQLException {
		client.update("fx.comFxDelete",pk);
	}
}
