package kr.or.ctw.noticeboard.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ctw.noticeboard.dao.BoardAnswerDao;
import kr.or.ctw.noticeboard.dao.FileBoardDao;
import kr.or.ctw.noticeboard.vo.BBSActvtyVO;
import kr.or.ctw.noticeboard.dao.BoardDao;
import kr.or.ctw.noticeboard.dao.BoardMangerDao;

@Service("managementService")
public class ManagementServiceImpl implements ManagementService {
	
	@Resource(name="boardmanagerDao")
	BoardMangerDao managedao;
	
	@Resource(name="boardDao")
	BoardDao dao;
	
	@Resource(name="fileboardDao")
	FileBoardDao filedao;
	
	@Resource(name="boardanswerDao")
	BoardAnswerDao ansdao;

	// 활성화
	@Override
	public int activationUpdate(Map<String, Object> map) {
		int res = 0;
		try {
			res = managedao.activationUpdate(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<BBSActvtyVO> boardList() {
		List<BBSActvtyVO> list = null;
		try {
			list = managedao.boardList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	


}
