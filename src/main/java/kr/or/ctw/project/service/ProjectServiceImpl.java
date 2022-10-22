package kr.or.ctw.project.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import kr.or.ctw.member.vo.MemberVO;
import kr.or.ctw.project.dao.ProjectDao;
import kr.or.ctw.project.dao.ProjectParticipantsDao;
import kr.or.ctw.project.vo.PrjctPartcptnVO;
import kr.or.ctw.project.vo.PrjctVO;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
	
	@Resource(name="projectDao")
	ProjectDao projectdao;
	
	@Resource(name="projectparticipantsDao")
	ProjectParticipantsDao participantsdao;

	// 조직원- 자신의 프로젝트조회
	@Override
	public List<PrjctVO> projectList(String mem_id) {
		List<PrjctVO> list =null;
		try {
			list = projectdao.projectList(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void projectInsert(HttpSession ses, List<String> mem_id, PrjctVO prjctVO) throws SQLException {
		
		MemberVO memVO = (MemberVO) ses.getAttribute("member");
		String member_id = memVO.getMem_id();
		prjctVO.setMem_id(member_id);
		int  prjct_pk= projectdao.projectInsert(prjctVO);
		PrjctPartcptnVO vo =  new PrjctPartcptnVO();
		for (int i = 0; i < mem_id.size(); i++) {
			vo.setPrjct_fk(prjct_pk);
			vo.setMem_id(mem_id.get(i));
			participantsdao.projectMemberInsert(vo);
		}
		vo.setPrjct_fk(prjct_pk);
		vo.setMem_id(member_id);
		vo.setPrjct_at("Y");
		participantsdao.projectMemberInsert(vo);
		
		
		
	}

	@Override
	public PrjctVO projectInfo(int prjct_pk) {
		PrjctVO vo = null;
		try {
			vo = projectdao.projectInfo(prjct_pk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int acceptProject(Map<String, Object> map) {
		int res = 0;
		try {
			res = participantsdao.acceptProject(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
}
