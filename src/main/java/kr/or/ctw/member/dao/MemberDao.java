package kr.or.ctw.member.dao;

import java.io.File;
import java.lang.reflect.Member;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ctw.member.vo.ClsfVO;
import kr.or.ctw.member.vo.DeptVO;
import kr.or.ctw.member.vo.MemberVO;

public interface MemberDao {


	
	// 조직원 등록
	public void memberInsert(MemberVO vo) throws SQLException;
	
	// 조직원 전체검색
	public List<MemberVO> memberSelect() throws SQLException;
	
	// 조직원 상세보기(선택검색)
	public MemberVO detailView(String id) throws SQLException;
	
	// 조직원 정보수정
	public void updateMamber(MemberVO vo)throws SQLException;
	
	// 조직원 일괄등록
	public int massiveInsert(MemberVO vo)throws SQLException;
	
	
	
	
	
	// 직위 리스트
	public List<ClsfVO> positionList();
	
	// 직위추가
	public int positionInsert(ClsfVO vo);
	
	// 직위수정
	public int positionUpdate(int pk);
	
	// 직위삭제
	public int positionDelete(int pk);
	
	
	
	
	// 조직원리스트 목록
	public List<MemberVO> memberList(Map<String, Object> map);

	
	// 조직원상세보기
	public MemberVO MemberInfo(int pk);
	
	
	// 조직원일괄등록기능 
	
	
	
	
	// 부서등록
	public int departmentInsert(DeptVO vo);
	
	// 부서이름수정하기
	public int departmentUpdate(DeptVO vo);
	
	// 부서삭제하기
	public int departmentDelete(int pk);
	
	
	// 조직도보기 ?
	public DeptVO organizationChart();

	
	// 로그인
	public MemberVO login(Map<String,Object> params) throws SQLException ;

	/**
	 * 부서의 리스트를 가져오기위한 메서드
	 * 
	 * @Description 
	 * @author 김완수
	 * @since 2017. 5. 9.
	 *
	 */
	public List<DeptVO> deptList() throws SQLException;
	/**
	 * 부서의 일치하는 회원 리스트를 가져오는 메서드
	 * 
	 * @Description 
	 * @param dept_pk
	 * @return
	 * @author 김완수
	 * @since 2017. 5. 9.
	 *
	 */
	public List<MemberVO> deptMemList(int dept_pk) throws SQLException;

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
	public List<MemberVO> sexRateChart() throws SQLException;
	
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
	public List<MemberVO> deptChart() throws SQLException;
	
}
