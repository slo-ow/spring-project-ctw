package kr.or.ctw.mssage.service;

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
 * @see kr.or.ctw.mssage.service
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자        수정내용
 * --------------     --------    -----------
 * 2017. 5. 13.     	        김완수        최초작성
 * Copyright (c) 2017 by DDIT All right reserved
 * </pre>
 */
public interface MssService {
	/**
	 * 쪽지리스트를 가져오기 위한 메서드 
	 * @param mem_id
	 * @param division
	 * @return
	 * @author 김완수
	 * @date 2017. 5. 13.
	 */
	public List<MssVO> mssList(String mem_id,String division);
	
	/**
	 * 쪽지를 삭제하기위한 메서드 
	 * @param mss_pk
	 * @param division
	 * @author 김완수
	 * @date 2017. 5. 13.
	 */
	public void mssDeleteCheck(int mss_pk,String division);
	
	/**
	 * 쪽지를 완전 삭제하기 위한 메서드 
	 * @param mvo
	 * @param division
	 * @author 김완수
	 * @date 2017. 5. 13.
	 */
	public void mssDelete(MssVO mvo,String division);
	
	/**
	 * 쪽지의 정보를 자여오기 위한 메서드 
	 * @param mss_pk
	 * @param division
	 * @return
	 * @author 김완수
	 * @date 2017. 5. 13.
	 */
	public MssVO mssInfo(int mss_pk,String division);
	
	/**
	 * 쪽지의 확인 여부를 확인하기위한 메서드
	 * @param mss_pk
	 * @author 김완수
	 * @date 2017. 5. 13.
	 */
	public void mmsCheck(int mss_pk);
	/**
	 * 쪽지를 저장하기 위한 메서드
	 * @param req
	 * @param mem_id
	 * @author 김완수
	 * @date 2017. 5. 13.
	 */
	public void mssInsert(HttpServletRequest req, String mem_id);


	
}
