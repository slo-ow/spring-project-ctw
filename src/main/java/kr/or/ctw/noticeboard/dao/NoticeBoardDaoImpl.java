package kr.or.ctw.noticeboard.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.noticeboard.vo.CmpnyNoticeVO;

// 회사의 공지게시판
@Repository("noticeboardDao")
public class NoticeBoardDaoImpl implements NoticeBoardDao {

	@Autowired
	private SqlMapClient client;

	// 회사 공지글 조회
	@Override
	public List<CmpnyNoticeVO> CompanyNoticeList(Map<String, Object> map) throws SQLException {
		return client.queryForList("cmpnynotice.CompanyNoticeList",map);
	}

	// 회사 공지글 등록
	@Override
	public int companyNoticeInsert(CmpnyNoticeVO vo) throws SQLException {
		return (int) client.insert("cmpnynotice.companyNoticeInsert",vo);
	}

	// 상세보기
	@Override
	public CmpnyNoticeVO selectInfo(int notice_pk) throws SQLException {
		return (CmpnyNoticeVO) client.queryForObject("cmpnynotice.selectInfo", notice_pk);
	}
	
	// 조회수 증가
	@Override
	public void updateHits(int notice_pk) throws SQLException {
		client.update("cmpnynotice.updateHits",notice_pk);
	}

	@Override
	public int companyDel(int notice_pk) throws SQLException {
		return client.update("cmpnynotice.companyDel",notice_pk);
	}

	// 회사 공지글 수정
	@Override
	public int companyUpdate(CmpnyNoticeVO vo) throws SQLException {
		return client.update("cmpnynotice.companyUpdate",vo);
	}
	
}
