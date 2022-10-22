package kr.or.ctw.noticeboard.service;

import java.util.List;
import java.util.Map;

import kr.or.ctw.noticeboard.vo.CmpnyNoticeVO;
import kr.or.ctw.noticeboard.vo.DeptNoticeVO;

public interface NoticeMultiService {

	// 회사 공지글 조회
	List<CmpnyNoticeVO> CompanyNoticeList(Map<String, Object> map);

	// 부서 공지글 조회
	List<DeptNoticeVO> DeptNoticeList(Map<String, Object> map);

	// 회사 공지글 등록
	int companyNoticeInsert(CmpnyNoticeVO vo);

	
	// 부서 공지글 등록
	int deptnoticeInsert(DeptNoticeVO vo);

	// 회사공지글 상세보기
	CmpnyNoticeVO selectInfo(int notice_pk);

	
	/**
	 * <pre>
	 *	전체공지사항 조회수증가
	 * </pre>
	 * @param notice_pk
	 * @return void
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 7.
	 */
	void updateHits(int notice_pk);

	/**
	 * <pre>
	 *	부서게시판 상세보기
	 * </pre>
	 * @param notice_pk
	 * @return void
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 7.
	 */
	DeptNoticeVO deptSelectInfo(int notice_pk);

	/**
	 * <pre>
	 *	부서게시판 조회수증가
	 * </pre>
	 * @param notice_pk
	 * @return void
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 7.
	 */
	void deptUpdateHits(int notice_pk);

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
	int companyDel(int notice_pk);

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
	int deptDel(int notice_pk);

	/**
	 * 
	 * <pre>
	 *	회사 공지글 수정
	 * </pre>
	 * @param vo
	 * @return int
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 11.
	 *
	 */
	int companyUpdate(CmpnyNoticeVO vo);

	/**
	 * 
	 * <pre>
	 *	부서 공지글 수정
	 * </pre>
	 * @param vo
	 * @return int
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 11.
	 *
	 */
	int deptUpdate(DeptNoticeVO vo);
	
	

}
