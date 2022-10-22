package kr.or.ctw.board.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.or.ctw.board.vo.LbrtyBBSAnsVO;
import kr.or.ctw.board.vo.LbrtyBBSFileVO;
import kr.or.ctw.board.vo.LbrtyBBSVO;
import kr.or.ctw.member.vo.MemberVO;

public interface FreeMultiService {
	//저장
	public int insertBbs(MemberVO memVO, HttpServletRequest req);

	//조회
	public List<LbrtyBBSVO> listAll();

	//수정
	public int updateBbs(LbrtyBBSVO vo);

	//삭제
	public int deleteBbs(int bbs_pk);

	//상세조회, 검색
	public LbrtyBBSVO selectBbs(int pk);

	//조회수 증가
	public void upCntBbs(int pk);



	//파일조회
	public List<LbrtyBBSFileVO> selectBbsFileInfo(int bbs_f_pk);

	//파일 저장(멀티파일업로드)
	public int insertBbsFileInfo(LbrtyBBSFileVO vo);

	//파일 원본이름찾기(다운로드)
	public String selectBbsFilenm(String file);

	//파일 삭제
	public int deleteBbsFile(String bbs_f_pk);

	//댓글 저장
	public int freeInsertAnswer(LbrtyBBSAnsVO vo);

	//댓글 조회
	public List<LbrtyBBSAnsVO> freeListAnswer(int bbs_ans_pk);

	//댓글 삭제
	public int freeDeleteAnswer(int bbs_ans_pk);


}
