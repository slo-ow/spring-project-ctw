package kr.or.ctw.document.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ctw.document.vo.KategorieVO;

/**
 * 문서의 카테고리 
 * @author 김완수
 * @date    2017. 4. 26.
 */
public interface KategorieDao {

	/**
	 * 	카테고리의 목록을 가져온다. 
	 * @return List<KategorieVO>
	 * @throws SQLException
	 * @author 김완수
	 * @date 2017. 4. 26.
	 */
	public List<KategorieVO> listAll() throws SQLException;
	
	
	/**
	 * 	카테고리 추가 
	 * @param vo
	 * @throws SQLException
	 * @author 김완수
	 * @date 2017. 4. 26.
	 */
	public void insert(KategorieVO vo) throws SQLException;
	
	/**
	 * 	카테고리 수정
	 * @param vo
	 * @throws SQLException
	 * @author 김완수
	 * @date 2017. 4. 26.
	 */
	public void update(KategorieVO vo) throws SQLException;
	
	/**
	 * 	카테고리 삭제 
	 * @param vo
	 * @throws SQLException
	 * @author 김완수
	 * @date 2017. 4. 26.
	 */
	public void delete(KategorieVO vo) throws SQLException;
	
	
}
