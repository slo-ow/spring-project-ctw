package kr.or.ctw.member.dao;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.member.vo.ClsfVO;
import kr.or.ctw.member.vo.DeptVO;
import kr.or.ctw.member.vo.MemberVO;

@Repository("memberDao")
public class MemberDaoImpl implements MemberDao{
	
	@Autowired
	private SqlMapClient client;

	
	
	// 조직원등록
	@Override
	public void memberInsert(MemberVO vo) throws SQLException {
		client.insert("member.memberInsert",vo);
		
	}
	
	// 조직원 일괄검색
	@Override
	public List<MemberVO> memberSelect() throws SQLException {
		return client.queryForList("member.memberSelect");
	}

	// 조직원 선택검색(세부보기)
	@Override
	public MemberVO detailView(String id) throws SQLException {
		return (MemberVO) client.queryForObject("member.detailView",id);
	}

	// 조직원 정보수정
	@Override
	public void updateMamber(MemberVO vo) throws SQLException {
		client.update("member.updateMamber",vo);
		
	}
	
	// 조직원 일괄등록
	@Override
	public int massiveInsert(MemberVO vo) throws SQLException {
		return client.update("member.massiveInsert",vo);
	}
	


	//--------------------------
	

	@Override
	public List<ClsfVO> positionList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int positionInsert(ClsfVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int positionUpdate(int pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int positionDelete(int pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> memberList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
	



	@Override
	public MemberVO MemberInfo(int pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int departmentInsert(DeptVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int departmentUpdate(DeptVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int departmentDelete(int pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DeptVO organizationChart() {
		// TODO Auto-generated method stub
		return null;
	}


	// 로그인 
	@Override
	public MemberVO login(Map<String, Object> params) throws SQLException {
		
		return (MemberVO) client.queryForObject("member.login",params);
	}

	@Override
	public List<DeptVO> deptList() throws SQLException {
		return client.queryForList("member.deptList");
	}

	@Override
	public List<MemberVO> deptMemList(int dept_pk) throws SQLException {
		return client.queryForList("member.deptMemList",dept_pk);
	}

	// 남녀성비
	@Override
	public List<MemberVO> sexRateChart() throws SQLException {
		return client.queryForList("membermain.sexRateChart");
	}

	// 부서별 사원수
	@Override
	public List<MemberVO> deptChart() throws SQLException {
		return client.queryForList("membermain.deptChart");
	}






















	
	
	
	
	
}
