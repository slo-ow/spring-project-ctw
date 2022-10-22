package kr.or.ctw.noticeboard.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ctw.noticeboard.vo.DeptNoticeVO;

public interface DeptNoticeBoardDao {

	// 부서 공지글 등록
	int deptnoticeInsert(DeptNoticeVO vo) throws SQLException;
 
	// 부서 공지글 조회
	List<DeptNoticeVO> DeptNoticeList(Map<String, Object> map) throws SQLException;
	// 부서 게시판 상세보기
	DeptNoticeVO deptSelectInfo(int notice_pk) throws SQLException;

	// 부서게시판 조회수 증가
	void deptUpdateHits(int notice_pk) throws SQLException;

	/**
	 * <pre>
	 *	부서게시물 삭제
	 * </pre>
	 * @param notice_pk
	 * @return int
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 8.
	 */
	int deptDel(int notice_pk) throws SQLException;

	/**
	 * <pre>
	 *부서게시물 수정
	 * </pre>
	 * @param vo
	 * @return
	 * @return int
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 11.
	 *
	 */
	int deptUpdate(DeptNoticeVO vo) throws SQLException;

}
