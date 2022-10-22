package kr.or.ctw.buyer.service;


import java.util.List;

import javax.servlet.http.HttpSession;

import kr.or.ctw.buyer.vo.BcncVO;
import kr.or.ctw.member.vo.MemberVO;

/**
 * <pre>
 * 거래처와 거래처 임시 저장을 같이 처리하는 서비스 
 * </pre>
 * @author 문성철
 * @since 2017. 5. 2.
 * @version 1.0
 * @see
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일          수정자            수정내용
 * --------     --------      -------------
 * 2017. 5. 2.    작성자명          최초작성
 * Copyright (c) 2017 by CTW All right reserved
 * </pre>
 */ 
public interface BuyerService {
	
	/**
	 * <pre>
	 *	거래처 or 임시거래처 등록 / division으로 구분함.
	 * </pre>
	 * @param 
	 * @return 
	 * @author 문성철
	 * @since 2017. 5. 2.
	 * @see Member_BuyerController.java
	 * 
	 */
	public int insertBcnc(BcncVO vo,String division);
	
	/**
	 * 	임시거래처와 거래처의 리스트를 가저오는 메소드
	 * @param division
	 * @param ses
	 * @return List<vo>
	 * @author 김완수
	 * @date 2017. 4. 26.
	 */
	List<BcncVO> listAll(String division, HttpSession ses);
	
	
	//삭제
	public int deleteBcnc(int num,String division);
	
	//수정
	public int updateBcnc(BcncVO vo,String division);

	
	/**
	 * 	거래처와 임시거래처 정보를 가져오는 메소드 
	 * 
	 * @param bcnc_pk
	 * @param division
	 * @return vo
	 * @author 김완수
	 * @date 2017. 4. 26.
	 */
	public BcncVO infoBcnc(int bcnc_pk,String division);
	
	/**
	 * 	거래처 승인 이후 승인여부의 상태값을 변경하는 메소드 
	 * 
	 * @param bcnc_pk
	 * @return 
	 * @author 문성철
	 * @date 2017. 5. 4.
	 */
	public void updateStatus(int bcnc_pk);
}
