package kr.or.ctw.noticeboard.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.noticeboard.vo.DeptNoticeVO;

@Repository("deptnoticeboardDao")
public class DeptNoticeBoardDaoImpl implements DeptNoticeBoardDao {

	@Autowired
	private SqlMapClient client;
	
	// 부서 공지글 등록
	@Override
	public int deptnoticeInsert(DeptNoticeVO vo) throws SQLException {
		return (int) client.insert("deptnotice.deptnoticeInsert",vo);
	}
	
   // 부서공지글 조회
   @Override
   public List<DeptNoticeVO> DeptNoticeList(Map<String, Object> map) throws SQLException {
      return client.queryForList("deptnotice.DeptNoticeList",map);
   }

	@Override
	public DeptNoticeVO deptSelectInfo(int notice_pk) throws SQLException {
		return (DeptNoticeVO) client.queryForObject("deptnotice.deptSelectInfo",notice_pk);
	}
	
	@Override
	public void deptUpdateHits(int notice_pk) throws SQLException {
		client.update("deptnotice.deptUpdateHits",notice_pk);
	}

	@Override
	public int deptDel(int notice_pk) throws SQLException {
		return client.update("deptnotice.deptDel",notice_pk);
	}

	@Override
	public int deptUpdate(DeptNoticeVO vo) throws SQLException {
		return client.update("deptnotice.deptUpdate",vo);
	}
}
