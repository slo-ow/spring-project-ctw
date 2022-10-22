package kr.or.ctw.mssage.dao;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.or.ctw.mssage.vo.MssFileVO;
import kr.or.ctw.mssage.vo.MssVO;
/**
 * 
 * 
 * 
 * <pre>
 * 
 * </pre>
 * @author 김완수
 * @since 2017. 5. 13.
 * @version 1.0
 * @see kr.or.ctw.mssage.dao
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자        수정내용
 * --------------     --------    -----------
 * 2017. 5. 13.     	        김완수        최초작성
 * Copyright (c) 2017 by DDIT All right reserved
 * </pre>
 */
public interface MssDao {
	
	/**
	 * 쪽지의 리스트를 가져오는 메서드
	 * @param mem_id
	 * @return
	 * @throws SQLException
	 * @author 김완수
	 * @date 2017. 5. 13.
	 */
	public List<MssVO> mssList(String mem_id) throws SQLException;

	
	/**
	 * 쪽지의 정보를 가져오는 메서드
	 * @param mvo
	 * @return
	 * @throws SQLException
	 * @author 김완수
	 * @date 2017. 5. 13.
	 */
	public int mssInsert(MssVO mvo) throws SQLException;
	
	/**
	 * 쪽지를 삭제하는메서드
	 * @param mss_pk
	 * @throws SQLException
	 * @author 김완수
	 * @date 2017. 5. 13.
	 */
	public void mssDeleteCheck(int mss_pk) throws SQLException;
	
	/**
	 * 쪽지를 완전삭제하는 메서드
	 * @param mvo
	 * @throws SQLException
	 * @author 김완수
	 * @date 2017. 5. 13.
	 */
	public void mssDelete(MssVO mvo) throws SQLException;
	
	/**
	 * 쪽지의 정보를 가져오기 위한 메서드
	 * @param mss_pk
	 * @return
	 * @throws SQLException
	 * @author 김완수
	 * @date 2017. 5. 13.
	 */
	public MssVO mssInfo(int mss_pk) throws SQLException;

	/**
	 * 쪽지에 첨부된 파일을 저장하는 메서드 
	 * @param mfvo
	 * @throws SQLException
	 * @author 김완수
	 * @date 2017. 5. 13.
	 */
	public void mssFileInsert(MssFileVO mfvo) throws SQLException;

	//public void mmsCheck(int mss_pk) throws SQLException;
	
	
}
