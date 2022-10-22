package kr.or.ctw.schedule.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ctw.member.vo.MemberVO;
import kr.or.ctw.schedule.vo.FxVO;
/**
 * 테이블의 컬럼명을 똑같이 만들었기 때문에 
 * interface객체를 하나만 만들어서 각각 다른 테이블을 연결하는 
 * DAOImpl에 implements해서 사용한다.
 * @author 김완수
 * 2017.04.20
 *
 */
public interface FxDao {
	
	/**
	 * 일정리스트를 가져온다 
	 * @param vo
	 * @return List<T>
	 * @throws SQLException
	 * @author 김완수 
	 * 2017.04.24
	 */
	public List<FxVO> scheduleList(MemberVO vo) throws SQLException;
	
	/**
	 * 일정을 수정한다. 
	 * @param vo
	 * @return int
	 * @throws SQLException
	 * @author 김완수 
	 * 2017.04.24
	 */
	public int scheduleUpdate(FxVO vo) throws SQLException;

	/**
	 * 일정을 저장한다. 
	 * @param vo
	 * @throws SQLException
	 * @author 김완수 
	 * 2017.04.24
	 */
	void fxInsert(FxVO vo) throws SQLException;

	/**
	 * 일정의 상세정보를 가져온다. 
	 * @param fx_pk
	 * @return vo
	 * @throws SQLException
	 * @author 김완수 
	 * 2017.04.24
	 */
	public FxVO fxInfo(int fx_pk)  throws SQLException;

	/**
	 * 일정을 삭제 한다. 
	 * @param pk
	 * @throws SQLException
	 * @author 김완수 
	 * 2017.04.24
	 */
	public void fxDelete(int pk) throws SQLException;

	
	
}
