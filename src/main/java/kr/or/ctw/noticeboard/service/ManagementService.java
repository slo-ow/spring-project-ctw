package kr.or.ctw.noticeboard.service;

import java.util.List;
import java.util.Map;

import kr.or.ctw.noticeboard.vo.BBSActvtyVO;


public interface ManagementService {

	
	/**
	 * <pre>
	 *  게시판 내역 리스트
	 * </pre>
	 * @param 
	 * @return List<BBSActvtyVO>
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 1.
	 *
	 */
	public List<BBSActvtyVO> boardList();
	
	/**
	 * <pre>
	 *  활성/비활성화 셋팅
	 * </pre>
	 * @param map
	 * @return int
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 1.
	 *
	 */
	public int activationUpdate(Map<String, Object> map);

	


	
}
