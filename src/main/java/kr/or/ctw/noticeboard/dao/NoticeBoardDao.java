package kr.or.ctw.noticeboard.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ctw.noticeboard.vo.CmpnyNoticeVO;

// 회사의 공지게시판
public interface NoticeBoardDao {

	// 회사 공지글 조회
	List<CmpnyNoticeVO> CompanyNoticeList(Map<String, Object> map) throws SQLException;

	// 회사 공지글 등록
	int companyNoticeInsert(CmpnyNoticeVO vo) throws SQLException;

	// 회사공지글 상세보기
	CmpnyNoticeVO selectInfo(int notice_pk) throws SQLException;


	/**
	 * <pre>
	 *	조회수증가
	 * </pre>
	 * @param notice_pk
	 * @return void
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 7.
	 */
	void updateHits(int notice_pk) throws SQLException;

	/**
	 * <pre>
	 *	회사게시물 삭제
	 * </pre>
	 * @param notice_pk
	 * @return int
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 8.
	 */
	int companyDel(int notice_pk) throws SQLException;

	/**
	 * <pre>
	 *	회사공지글 수정
	 * </pre>
	 * @param vo
	 * @return
	 * @throws SQLException
	 * @return int
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 11.
	 *
	 */
	int companyUpdate(CmpnyNoticeVO vo) throws SQLException;

}
