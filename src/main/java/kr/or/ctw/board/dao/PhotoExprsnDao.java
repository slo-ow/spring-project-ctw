package kr.or.ctw.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ctw.board.vo.PhotoExprsnVO;

public interface PhotoExprsnDao {

	/**
	 * @Description 좋아요 눌렀을 때, photo_exprsn테이블의 인서트
	 * @param vo
	 * @return int
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 1.
	 *
	 */
	int likeInsert(Map<String, Object> map) throws SQLException;

	
	/**
	 * @Description 좋아요 취소했을 때, photo_exprsn테이블의 삭제
	 * @param int
	 * @return int
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 1.
	 *
	 */
	int likedelete(Map<String, Object> map) throws SQLException;


	/**
	 * @Description PhotoExprsn테이블(게시물의 좋아요) 리스트 가져오기
	 * @param  map
	 * @return List<PhotoExprsnVO>
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 1.
	 *
	 */
	List<PhotoExprsnVO> photoExprsnList(Map<String, Object> map) throws SQLException;

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param int
	 * @return List<PhotoExprsnVO>
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 2.
	 */
	List<PhotoExprsnVO> likeCount() throws SQLException;



}
