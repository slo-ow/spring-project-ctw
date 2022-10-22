package kr.or.ctw.document.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ctw.document.vo.DocVO;
import kr.or.ctw.member.vo.MemberVO;

/**
 * 데이터를 저장할때 테이블 컬럼명이 똑같아서 
 * 하나의 DAO로 처리한다. 
 * @author 김완수
 * @date    2017. 4. 26.
 */
public interface DocDao {
	
	/**
	 * 파일을 insert하기위한 메서드 	
	 * @param vo
	 * @return int 
	 * @throws SQLException
	 * @author pc05
	 * @date 2017. 4. 27.
	 */
	public int insertDoc(DocVO vo) throws SQLException;
	
	
	/**
	 * 파일리스트를 가지고오기위한 메서드 	
	 * @param vo
	 * @return List<DocVO>
	 * @throws SQLException
	 * @author pc05
	 * @date 2017. 4. 27.
	 */
	public List<DocVO> docList(DocVO vo) throws SQLException;

	/**
	 * 문서를 휴지통으로 보내는 메서드
	 * 
	 * @Description 
	 * @param doc_pk
	 * @throws SQLException
	 * @author 김완수
	 * @since 2017. 5. 2.
	 *
	 */
	public void docDelete(String doc_pk) throws SQLException;

	/**
	 * 휴지통리스트를 가져오기 위한 메서드 
	 * 
	 * @Description 
	 * @param vo
	 * @return
	 * @throws SQLException
	 * @author 김완수
	 * @since 2017. 5. 2.
	 *
	 */
	public List<DocVO> trashcanDocList(MemberVO vo) throws SQLException;

	/**
	 * 저장되어있는 문서를 완전 삭제하기위한 메서드 
	 * 
	 * @Description 
	 * @param doc_pk
	 * @throws SQLException
	 * @author 김완수
	 * @since 2017. 5. 2.
	 *
	 */
	public void trashcanDocDel(String doc_pk) throws SQLException; 
	/**
	 * 문서를 다시 휴지통이 아니라 그냥 나오는 리스트로 복귀 시키는 메서드
	 * 
	 * @Description 
	 * @param doc_pk
	 * @throws SQLException
	 * @author 김완수
	 * @since 2017. 5. 2.
	 *
	 */
	public void trashcanDocUP(String doc_pk) throws SQLException; 
}
