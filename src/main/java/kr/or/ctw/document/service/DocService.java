package kr.or.ctw.document.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ctw.document.vo.DocVO;
import kr.or.ctw.document.vo.KategorieVO;
import kr.or.ctw.member.vo.MemberVO;

public interface DocService {
		
	/**
	 * 카테고리 리스트를 가져오는 메서드 	
	 * @return List<KategorieVO>
	 * @author 김완수
	 * @date 2017. 4. 26.
	 */
	public List<KategorieVO> kategorieList();
	
	

	/**
	 * 	파일을 저장하는 메서드 
	 * @param req
	 * @param ses
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws SQLException
	 * @author 김완수
	 * @date 2017. 4. 27.
	 */
	public void docFileInsert(HttpServletRequest req, HttpSession ses) throws IllegalStateException, IOException, SQLException;
	
	/**
	 * 파일 리스트를 불러오기 위한 리스트 메서드 	
	 * @param vo
	 * @return
	 * @author 김완수
	 * @date 2017. 4. 27.
	 */
	public List<DocVO> docList(MemberVO vo, HttpServletRequest req);


	/**
	 * 문서를 삭제하기위한 메서드 
	 * 
	 * @Description 
	 * @param doc_pk
	 * @param division
	 * @author 김완수
	 * @since 2017. 5. 2.
	 *
	 */
	public void docDelete(String doc_pk, String division);


	/**
	 * 휴지통에 있는 문서드를 가져오기위한 메서드
	 * 
	 * @Description 
	 * @param vo
	 * @param division
	 * @return
	 * @author 김완수
	 * @since 2017. 5. 2.
	 *
	 */
	public List<DocVO> trashcanDocList(MemberVO vo,String division);

	
	/**
	 * 휴지통에 있는 문서를 완전삭제하기위한 메서드
	 * 
	 * @Description 
	 * @param doc_pk
	 * @param division
	 * @throws SQLException
	 * @author 김완수
	 * @since 2017. 5. 2.
	 *
	 */
	public void trashcanDel(String doc_pk, String division) throws SQLException;
	
	/**
	 * 휴지통에 있는 문서를 복귀하기위한 메서드 
	 * 
	 * @Description 
	 * @param doc_pk
	 * @param division
	 * @author 김완수
	 * @since 2017. 5. 2.
	 *
	 */
	public void trashcanUp(String doc_pk, String division);



	

	
	
}
