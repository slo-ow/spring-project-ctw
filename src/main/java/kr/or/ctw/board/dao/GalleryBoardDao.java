package kr.or.ctw.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ctw.board.vo.PhotoVO;
import kr.or.ctw.common.vo.pagingVO;

// 갤러리게시판의  crud
public interface GalleryBoardDao {

	
	// 사진등록
	int potoInsert(PhotoVO vo) throws SQLException;

	// 갤러리 조회 
	List<PhotoVO> potoList(Map<String, Object> map) throws SQLException;

	List<PhotoVO> listAll(pagingVO page) throws SQLException;
	
	/**
	 * <pre>
	 *	 포토게시물 삭제
	 * </pre>
	 * @param photo_pk
	 * @return int
	 * @author 박혜윤
	 * @see 
	 * @since 2017. 5. 9.
	 *
	 */
	int photoDelete(int photo_pk) throws SQLException;

}
