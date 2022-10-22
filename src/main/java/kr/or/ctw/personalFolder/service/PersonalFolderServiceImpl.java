package kr.or.ctw.personalFolder.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ctw.personalFolder.dao.MemDtaDao;
import kr.or.ctw.personalFolder.dao.MemDtaFolDao;

@Service("personalfolderService")
public class PersonalFolderServiceImpl implements PersonalFolderService {

	@Resource(name="memdtaDao")
	MemDtaDao memdao;
	
	@Resource(name="memdtafolDao")
	MemDtaFolDao foldao;
	
	
}
