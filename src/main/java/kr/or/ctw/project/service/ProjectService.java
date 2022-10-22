package kr.or.ctw.project.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import kr.or.ctw.project.vo.PrjctVO;

// 프로젝트와 참여자테이블
public interface ProjectService {

	/**
	 * <pre>
	 *	자신의 프로젝트 조회
	 * </pre>
	 * @param 
	 * @return List<PrjctVO>
	 * @author 박혜윤
	 * @param mem_id 
	 * @see 
	 * @since 2017. 5. 9.
	 *
	 */
	List<PrjctVO> projectList(String mem_id);
	
	
	/**
	 * 프로젝트와 참여자 insert
	 * 
	 * @Description 
	 * @param ses
	 * @param mem_id
	 * @param prjctVO
	 * @author 김완수
	 * @throws SQLException 
	 * @since 2017. 5. 10.
	 *
	 */
	void projectInsert(HttpSession ses, List<String> mem_id, PrjctVO prjctVO) throws SQLException;

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
	public PrjctVO projectInfo(int prjct_pk);


	/**
	 * 
	 * <pre>
	 *	참여자 상태 수락
	 * </pre>
	 * @param map
	 * @return int
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 10.
	 *
	 */
	int acceptProject(Map<String, Object> map);


	
	
}
