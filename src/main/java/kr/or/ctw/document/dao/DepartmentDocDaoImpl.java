package kr.or.ctw.document.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.document.vo.DocVO;
import kr.or.ctw.member.vo.MemberVO;

@Repository("deptDocDao")
public class DepartmentDocDaoImpl implements DocDao {

	@Autowired
	private SqlMapClient client;

	@Override
	public int insertDoc(DocVO vo) throws SQLException {
		return client.update("doc.insertDept",vo);
	}
	@Override
	public List<DocVO> docList(DocVO vo) throws SQLException {
		return client.queryForList("doc.deptList", vo);
	}
	@Override
	public void docDelete(String doc_pk) throws SQLException {
		client.update("doc.deptDelete",doc_pk);
	}
	@Override
	public List<DocVO> trashcanDocList(MemberVO vo) throws SQLException {
		return client.queryForList("doc.deptTrashcan",vo);
	}
	@Override
	public void trashcanDocDel(String doc_pk) throws SQLException {
		client.update("doc.deptTrashcanDel",doc_pk);		
	}
	@Override
	public void trashcanDocUP(String doc_pk) throws SQLException {
		client.update("doc.deptTrashcanUp",doc_pk);		
		
	}
	
}
