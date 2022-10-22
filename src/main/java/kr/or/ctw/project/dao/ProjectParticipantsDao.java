package kr.or.ctw.project.dao;

import java.sql.SQLException;
import java.util.Map;

import kr.or.ctw.project.vo.PrjctPartcptnVO;

public interface ProjectParticipantsDao {

	/**
	 * 프로젝트 참여자 insert
	 * 
	 * @Description 
	 * @param vo
	 * @author 김완수
	 * @throws SQLException 
	 * @since 2017. 5. 10.
	 *
	 */
	void projectMemberInsert(PrjctPartcptnVO vo) throws SQLException;

	/**
	 * <pre>
	 *	프로젝트 수락시 참여자 상태 변화
	 * </pre>
	 * @param map
	 * @return int
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 10.
	 *
	 */
	int acceptProject(Map<String, Object> map) throws SQLException;

}
