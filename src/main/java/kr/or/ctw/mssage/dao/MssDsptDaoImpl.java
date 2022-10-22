package kr.or.ctw.mssage.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.mssage.vo.MssFileVO;
import kr.or.ctw.mssage.vo.MssVO;

@Repository("dsptDao")
public class MssDsptDaoImpl implements MssDao{
	
	@Autowired
    private SqlMapClient client;
	
	
	@Override
	public List<MssVO> mssList(String mem_id) throws SQLException {
		return client.queryForList("mss.dsptList", mem_id);
	}

	@Override
	public int mssInsert(MssVO mvo) throws SQLException {
			
	
			return (int) client.insert("mss.mssInsert",mvo);
	}

	@Override
	public void mssDeleteCheck(int mss_pk) throws SQLException {
			client.update("mss.dsptDelUpdate",mss_pk);
	}

	@Override
	public void mssDelete(MssVO mvo) throws SQLException {
			client.update("mss.mssDelete",mvo);
	}

	@Override
	public MssVO mssInfo(int mss_pk) throws SQLException {
		
		return (MssVO) client.queryForObject("mss.mssDsptInfo",mss_pk);
	}

	@Override
	public void mssFileInsert(MssFileVO mfvo) throws SQLException {
		client.update("mss.mssFileInsert",mfvo);
		
	}

}
