package kr.or.ctw.project.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ctw.project.dao.ProjectFileDao;

@Service("projectfileService")
public class ProjectFileServiceImpl implements ProjectFileService {
	
	@Resource(name="projectfileDao")
	ProjectFileDao fileDao;
	
	
}
