package kr.or.ctw.schedule.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.member.vo.MemberVO;
import kr.or.ctw.schedule.vo.FxVO;

@Repository("departmentDao")
public class DepartmentDaoImpl implements FxDao {

	@Autowired
	private SqlMapClient client;

	@Override
	public List<FxVO> scheduleList(MemberVO vo) throws SQLException {
		return client.queryForList("fx.deptFxList",vo);
	}
	@Override
	public int scheduleUpdate(FxVO vo) throws SQLException {
		return client.update("fx.deptFxUpdate",vo);
	}
	@Override
	public void fxInsert(FxVO vo) throws SQLException {
	      client.update("fx.deptFxInsert",vo);
	}
	@Override
	public FxVO fxInfo(int fx_pk) throws SQLException {
		return (FxVO) client.queryForObject("fx.deptInfo", fx_pk);
	}
	@Override
	public void fxDelete(int pk) throws SQLException {
		client.update("fx.deptFxDelete",pk);
	}
	
}
