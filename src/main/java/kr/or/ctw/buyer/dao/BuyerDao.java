package kr.or.ctw.buyer.dao;

import java.sql.SQLException;

import java.util.List;

import kr.or.ctw.buyer.vo.BcncVO;
import kr.or.ctw.member.vo.MemberVO;

/**
 * <pre>
 *  거래처 Data Access Object
 * </pre>
 * @author 문성철
 * @since 2017. 5. 2.
 * @version 1.0
 * @see BuyerService.java
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일          수정자            수정내용
 * --------     --------      -------------
 * 2017. 5. 2.    작성자명          최초작성
 * Copyright (c) 2017 by CTW All right reserved
 * </pre>
 */ 
public interface BuyerDao {

	/**
	 * <pre>
	 *	거래처 or 임시거래처 등록 / division으로 구분함.
	 * </pre>
	 * @param 
	 * @return 
	 * @author 문성철
	 * @since 2017. 5. 2.
	 * @see 
	 * 
	 */
	//저장
	public int insertBcnc(BcncVO vo) throws SQLException;
	
	/**
	 * 	임시거래처와 거래처의 리스트를 가져오는 메서드 
	 * @param vo
	 * @return List<vo>
	 * @throws SQLException
	 * @author 김완수
	 * @date 2017. 4. 26.
	 */
	public List<BcncVO> listAll(MemberVO vo) throws SQLException;
	
	//삭제
	public int deleteBcnc(int num) throws SQLException;
	
	//수정
	public int updateBcnc(BcncVO vo) throws SQLException;
	
	/**
	 * <pre>
	 *	임시거래처와 거래처의 리스트를 가져오는 메서드
	 * </pre>
	 * @param bcnc_pk
	 * @return vo
	 * @author 문성철
	 * @since 2017. 5. 2.
	 * @see 
	 * 
	 */
	public BcncVO infoBcnc(int bcnc_pk) throws SQLException;
	
	
	/**
	 * 	거래처 승인 이후 승인여부의 상태값을 변경하는 메소드 
	 * 
	 * @param bcnc_pk
	 * @return 
	 * @author 문성철
	 * @date 2017. 5. 4.
	 */
	public void updateStatus(int bcnc_pk) throws SQLException;
}
