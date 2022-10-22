package kr.or.ctw.noticeboard.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ctw.member.vo.MemberVO;
import kr.or.ctw.noticeboard.dao.BoardAnswerDao;
import kr.or.ctw.noticeboard.dao.BoardDao;
import kr.or.ctw.noticeboard.dao.DeptNoticeBoardDao;
import kr.or.ctw.noticeboard.dao.NoticeBoardDao;
import kr.or.ctw.noticeboard.vo.CmpnyNoticeVO;
import kr.or.ctw.noticeboard.vo.DeptNoticeVO;

@Service("noticemultiService")
public class NoticeMultiServiceImpl implements NoticeMultiService {

	// 회사 공지
	@Resource(name="noticeboardDao")
	NoticeBoardDao boarddao;
		
	
	// 부서공지
	@Resource(name="deptnoticeboardDao")
	DeptNoticeBoardDao deptdao;
	

	
	

	// 회사 공지글 조회
	@Override
	public List<CmpnyNoticeVO> CompanyNoticeList(Map<String, Object> map) {
		List<CmpnyNoticeVO> list = null;
		try {
			list = boarddao.CompanyNoticeList(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 부서 공지글 조회
	@Override
	public List<DeptNoticeVO> DeptNoticeList(Map<String, Object> map) {
		List<DeptNoticeVO> list = null;
		try {
			list = deptdao.DeptNoticeList(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 회사 공지글 등록
	@Override
	public int companyNoticeInsert(CmpnyNoticeVO vo) {
		int res = 0;
		try {
			res = boarddao.companyNoticeInsert(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 부서 공지글 등록
	@Override
	public int deptnoticeInsert(DeptNoticeVO vo) {
		int res = 0;
		try {
			
			// 부서장의 부서코드
			vo.setDept_pk(vo.getDept_pk());
			
			res = deptdao.deptnoticeInsert(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 회사공지글 상세보기
	@Override
	public CmpnyNoticeVO selectInfo(int notice_pk) {
		CmpnyNoticeVO vo = null;
		try {
			vo = boarddao.selectInfo(notice_pk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	// 조회수 증가
	@Override
	public void updateHits(int notice_pk) {
		try {
			boarddao.updateHits(notice_pk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 부게시물 상세보기
	@Override
	public DeptNoticeVO deptSelectInfo(int notice_pk) {
		DeptNoticeVO vo = null;
		try {
			vo = deptdao.deptSelectInfo(notice_pk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	// 부서 조회수 증가
	@Override
	public void deptUpdateHits(int notice_pk) {
		try {
			deptdao.deptUpdateHits(notice_pk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 회사 게시물 삭제
	@Override
	public int companyDel(int notice_pk) {
		int res = 0;
		try {
			res = boarddao.companyDel(notice_pk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 부서 게시물 삭제
	@Override
	public int deptDel(int notice_pk) {
		int res = 0;
		try {
			res = deptdao.deptDel(notice_pk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 회사공지글 수정
	@Override
	public int companyUpdate(CmpnyNoticeVO vo) {
		int res = 0;
		try {
			res = boarddao.companyUpdate(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 부서공지글 수정
	@Override
	public int deptUpdate(DeptNoticeVO vo) {
		int res = 0;
		try {
			res = deptdao.deptUpdate(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
}
