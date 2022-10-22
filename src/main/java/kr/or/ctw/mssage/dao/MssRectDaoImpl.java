package kr.or.ctw.mssage.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.mssage.vo.MssFileVO;
import kr.or.ctw.mssage.vo.MssVO;

@Repository("rectDao")
public class MssRectDaoImpl implements MssDao{

	@Autowired
    private SqlMapClient client;
	
	@Override
	public List<MssVO> mssList(String mem_id) throws SQLException {
		return client.queryForList("mss.rectList", mem_id);
	}


	@Override
	public void mssDeleteCheck(int mss_pk) throws SQLException {
			client.update("mss.rectDelUpdate",mss_pk);
	}

	@Override
	public void mssDelete(MssVO mvo) throws SQLException {
			client.update("mss.mssDelete",mvo);
	}

	@Override
	public MssVO mssInfo(int mss_pk) throws SQLException {
		client.update("mss.mssCheck",mss_pk);
		System.out.println("dao들어오나요 ?");
		return (MssVO) client.queryForObject("mss.mssRecpInfo",mss_pk);
	}

	@Override
	public int mssInsert(MssVO mvo) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void mssFileInsert(MssFileVO mfvo) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
