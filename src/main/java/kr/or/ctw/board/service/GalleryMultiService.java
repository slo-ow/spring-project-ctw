package kr.or.ctw.board.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ctw.board.vo.PhotoExprsnVO;
import kr.or.ctw.board.vo.PhotoFileVO;
import kr.or.ctw.board.vo.PhotoVO;
import kr.or.ctw.common.vo.pagingVO;

public interface GalleryMultiService {

		// 갤러리 등록
		int potoInsert(PhotoVO vo) throws IllegalStateException, IOException, SQLException;
		
		// 갤러리 조회  
		List<PhotoVO> potoList(Map<String, Object> map);

		/**
		 * @Description 좋아요 눌렀을 때, photo_exprsn테이블의 인서트
		 * @param vo
		 * @return int
		 * @author 박혜윤
		 * @see 
		 * @since 2017. 5. 1.
		 *
		 */
		int likeInsert(Map<String, Object> map);

		/**
		 * @Description 좋아요 취소, photo_exprsn테이블의 삭제처리
		 * @param  int
		 * @return int
		 * @author 박혜윤
		 * @see 
		 * @since 2017. 5. 1.
		 *
		 */
		int likedelete(Map<String, Object> map);

		/**
		 * @Description PhotoExprsn테이블(게시물의 좋아요) 리스트 가져오기
		 * @param  map
		 * @return List<PhotoExprsnVO>
		 * @author 박혜윤
		 * @see 
		 * @since 2017. 5. 1.
		 *
		 */
		List<PhotoExprsnVO> photoExprsnList(Map<String, Object> map);

		/**
		 * <pre>
		 *	좋아요 개수
		 * </pre>
		 * @param int
		 * @return List<PhotoExprsnVO>
		 * @author 박혜윤
		 * @see 
		 * @since 2017. 5. 2.
		 */
		List<PhotoExprsnVO> likeCount();

		/**
		 * <pre>
		 *	페이징처리된 모든 컬럼을 셀렉트
		 * </pre>
		 * @param int
		 * @return List<PhotoExprsnVO>
		 * @author 박혜윤
		 * @see 
		 * @since 2017. 5. 3.
		 */
		List<PhotoVO> listAll(pagingVO page);

		/**
		 * <pre>
		 *	해당하는 게시물의 이미지파일의 상세 정보
		 * </pre>
		 * @param int
		 * @return List<PhotoFileVO>
		 * @author 박혜윤
		 * @see 
		 * @since 2017. 5. 4.
		 */
		List<PhotoFileVO> selectInfo(int photo_pk);

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
		int photoDelete(int photo_pk);
		
}
