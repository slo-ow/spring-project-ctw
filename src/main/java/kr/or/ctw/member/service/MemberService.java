package kr.or.ctw.member.service;



import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ctw.member.vo.DeptVO;
import kr.or.ctw.member.vo.MemberVO;

public interface MemberService {

	/**
	 * 
	 * @Description 조직원 등록 
	 * @return 
	 * @author 박창현
	 * @since 2017. 5. 11.
	 *
	 */
	public void memberInsert(MemberVO vo);
	
	/**
	 * 
	 * @Description 조직원 일괄검색
	 * @return 
	 * @author 박창현
	 * @since 2017. 5. 11.
	 *
	 */
	public List<MemberVO> memberSelect();
	

	/**
	 * 
	 * @Description 조직원 세부보기(세부조회) 
	 * @param 
	 * @return 
	 * @author 박창현
	 * @since 2017. 5. 11.
	 *
	 */
	public MemberVO detailView(String id);
	
	/**
	 * @Description 조직원 정보를 수정하는 메서드 
	 * @return 
	 * @author 박창현 
	 * @since 2017. 5. 11.
	 *
	 */
	public void updateMamber(MemberVO vo);
	
	/**
	 * 
	 * @Description EXCEl 파일을 이용하여 일괄등록 하는 메서드
	 * @return 
	 * @author 박창현 
	 * @see 
	 * @since 2017. 5. 11.
	 *
	 */
	public int massiveInsert(File destFile) throws SQLException;
	
	
	
	
	// 로그인 처리
	public MemberVO login(Map<String,Object> params);

	
	/**
	 * 부서의 리스트를 가져오는 메서드 
	 * 
	 * @Description 
	 * @return
	 * @author 김완수
	 * @since 2017. 5. 9.
	 *
	 */
	public List<DeptVO> deptList();

	/**
	 * 부서별 사원리스트
	 * 
	 * @Description 
	 * @param dept_pk
	 * @return
	 * @author 김완수
	 * @since 2017. 5. 9.
	 *
	 */
	public List<MemberVO> deptMemList(int dept_pk);

	/**
	 * <pre>
	 *	사원 성별 비율
	 * </pre>
	 * @param 
	 * @return List<MemberVO>
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 9.
	 *
	 */
	public List<MemberVO> sexRateChart();

	/**
	 * <pre>
	 *	부서별 사원수 차트
	 * </pre>
	 * @param 
	 * @return List<MemberVO>
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 9.
	 *
	 */
	public List<MemberVO> deptChart();
	
}
