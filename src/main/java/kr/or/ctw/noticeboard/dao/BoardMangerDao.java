package kr.or.ctw.noticeboard.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ctw.noticeboard.vo.BBSActvtyVO;

public interface BoardMangerDao {

	/**
	 * @Description 게시판 내역
	 * @param 
	 * @return List<BBSActvtyVO>
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 1.
	 *
	 */
	List<BBSActvtyVO> boardList() throws SQLException;

	
	/**
	 * @Description 활성/비활성 셋팅
	 * @param map
	 * @return int
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 1.
	 *
	 */
	int activationUpdate(Map<String, Object> map) throws SQLException;


}
