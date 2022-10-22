package kr.or.ctw.document.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.document.vo.DocVO;
import kr.or.ctw.member.vo.MemberVO;

@Repository("memDocDao")
public class MemDocDaoImpl implements DocDao{

	@Autowired
	private SqlMapClient client;

	@Override
	public int insertDoc(DocVO vo) throws SQLException {
		return client.update("doc.insertMem",vo);
	}
	@Override
	public List<DocVO> docList(DocVO vo) throws SQLException {
		return client.queryForList("doc.memList", vo);
	}
	@Override
	public void docDelete(String doc_pk) throws SQLException {
		client.update("doc.memDelete",doc_pk);
	}
	@Override
	public List<DocVO> trashcanDocList(MemberVO vo) throws SQLException {
		return client.queryForList("doc.memTrashcan");
	}
	@Override
	public void trashcanDocDel(String doc_pk) throws SQLException {
		client.update("doc.memTrashcanDel",doc_pk);		
	}
	@Override
	public void trashcanDocUP(String doc_pk) throws SQLException {
		client.update("doc.memTrashcanUp",doc_pk);		
	}
	
	
}
