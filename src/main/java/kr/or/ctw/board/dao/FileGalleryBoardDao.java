package kr.or.ctw.board.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ctw.board.vo.PhotoFileVO;

// 갤러리게시판의 파일관련  crud
public interface FileGalleryBoardDao {

	// 이미지 저장
	void photoFileInsert(PhotoFileVO imgvo) throws SQLException;

	/**
	 * <pre>
	 *	해당하는 게시물의 이미지파일의 상세 정보
	 * </pre>
	 * @param int
	 * @return List<PhotoFileVO>
	 * @author 박혜윤
	 * @throws SQLException 
	 * @see 
	 * @since 2017. 5. 4.
	 */
	List<PhotoFileVO> selectInfo(int photo_pk) throws SQLException;

}
