package kr.or.ctw.schedule.service;

import java.util.List;

import kr.or.ctw.member.vo.MemberVO;
import kr.or.ctw.schedule.vo.FxVO;


/**
 * 
 * 
 * <pre>
 * 
 * </pre>
 * @author 김완수
 * @since 2017. 5. 1.
 * @version 1.0
 * @see kr.or.ctw.schedule.service FxService
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일             수정자        수정내용
 * --------------     --------    -----------
 * 2017. 5. 1.     	  김완수        최초작성
 * Copyright (c) 2017 by DDIT All right reserved
 * </pre>
 */
public interface FxService {
	
	/**
	 * 정보보기 메서드 
	 * @param vo
	 * @param division
	 * @return vo
	 * @author 김완수
	 * 2017.04.24
	 */
	public FxVO scheduleInfo(String pk , String division);
	
	/**
	 * delete 메서드 
	 * @param vo
	 * @param division
	 * @author 김완수
	 * 2017.04.24
	 */
	public void scheduleDelete(int pk, String division);
	
	/**
	 * 
	 * 
	 * @Description 
	 * @param vo
	 * @param division
	 * @author 김완수
	 * @since 2017. 5. 1.
	 *
	 */
	public void scheduleUpdate(FxVO vo, String division);
	
	/**
	 * insert 메서드 
	 * @param vo
	 * @param division
	 * @author 김완수
	 * 2017.04.24
	 */
	public void fxInsert(FxVO vo, String division);
	
	/**
	 * 부서 일정리스트 
	 * @param vo
	 * @return List<vo>
	 * @author 김완수
	 * 2017.04.24
	 */
	public List<FxVO> deptList(MemberVO vo);
	
	/**
	 * 사원 일정리스트 
	 * @param vo
	 * @return List<vo>
	 * @author 김완수
	 * 2017.04.24
	 */
	public List<FxVO> memList(MemberVO vo);
	
	/**
	 * 회사 일정리스트 
	 * @param vo
	 * @return List<vo>
	 * @author 김완수
	 * 2017.04.24
	 */
	public List<FxVO> cmpnyList(MemberVO vo);
	
}
