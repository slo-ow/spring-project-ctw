package kr.or.ctw.member.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ctw.member.dao.MemberDao;
import kr.or.ctw.member.poiutil.ExcelRead;
import kr.or.ctw.member.poiutil.ReadOption;
import kr.or.ctw.member.vo.DeptVO;
import kr.or.ctw.member.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Resource(name="memberDao")
	private MemberDao dao;

	
	@Override
	public void memberInsert(MemberVO vo) {
		try {
			dao.memberInsert(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public MemberVO login(Map<String,Object> params) {
		MemberVO vo = null;
		try {
			vo=dao.login(params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public List<MemberVO> memberSelect() {
		List<MemberVO>list = null;
		try {
			list = dao.memberSelect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public MemberVO detailView(String id) {
		MemberVO vo = null;
		try {
			vo = dao.detailView(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public void updateMamber(MemberVO vo) {
		
		try {
			dao.updateMamber(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<DeptVO> deptList() {
		List<DeptVO> list= null;
		try {
			list = dao.deptList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<MemberVO> deptMemList(int dept_pk) {
		List<MemberVO> list= null;
		try {
			list = dao.deptMemList(dept_pk);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<MemberVO> sexRateChart() {
		List<MemberVO> list = null;
		try {
			list = dao.sexRateChart();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<MemberVO> deptChart() {
		List<MemberVO> list = null;
		try {
			list = dao.deptChart();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 일괄등록
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor=SQLException.class)
	public int massiveInsert(File destFile) throws SQLException {
		int res = 0; 
		ReadOption readOption = new ReadOption();
		  readOption.setFilePath(destFile.getAbsolutePath());
		  readOption.setOutputColumns("A","B","C","D","E","F","G","H","I","J","K","L");
		  readOption.setStartRow(2);
		  
		  List<Map<String, String>> excelContent = ExcelRead.read(readOption);
		  
		  MemberVO vo = null;
		  for(Map<String, String> article : excelContent){
		   
		   vo = new MemberVO();
		   vo.setMem_id(article.get("A"));
		   vo.setMem_nm(article.get("B"));
		   vo.setMem_pass(article.get("C"));
		   vo.setMem_telno(article.get("D"));
		   vo.setMem_date(article.get("E"));
		   vo.setMem_email(article.get("F"));
		   vo.setMem_addr(article.get("G"));
		   vo.setMem_brthdy(article.get("H"));
		   vo.setGrad_nm(article.get("I"));
		   vo.setDept_nm(article.get("J"));
		   vo.setClsf_nm(article.get("K"));
		   vo.setMem_sex(article.get("L"));
		   
		   res = dao.massiveInsert(vo);
		  }
		return res;
	}
	
}
