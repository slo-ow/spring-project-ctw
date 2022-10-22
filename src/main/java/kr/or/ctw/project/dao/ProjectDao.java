package kr.or.ctw.project.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ctw.project.vo.PrjctVO;

// 프로젝트 파일, 진행, 진행사원 관련
public interface ProjectDao {

	/**
	 * <pre>
	 *	프로젝트 조회
	 * </pre>
	 * @param 
	 * @return List<PrjctVO>
	 * @author 박혜윤
	 * @param mem_id 
	 * @see 
	 * @since 2017. 5. 9.
	 *
	 */
	List<PrjctVO> projectList(String mem_id) throws SQLException;

	
	/**
	 * 프로젝트 insert
	 * 
	 * @Description 
	 * @param prjctVO
	 * @return
	 * @throws SQLException
	 * @author 김완수
	 * @since 2017. 5. 10.
	 *
	 */
	int projectInsert(PrjctVO prjctVO) throws SQLException;
	
	/**
	 * <pre>
	 *	프로젝트 상세보기
	 * </pre>
	 * @param prjct_pk
	 * @return PrjctVO
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 10.
	 *
	 */
	public PrjctVO projectInfo(int prjct_pk) throws SQLException;
	
}
