package kr.or.ctw.personalFolder.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ctw.personalFolder.service.PersonalFolderService;

// 개인 업무 폴더
@RequestMapping("/member")
@Controller
public class PersonalFolderController {

	@Resource(name="personalfolderService")
	PersonalFolderService service;
	
	// sidemenu -My Page - 개인업무관리 (personalfolder)
	@RequestMapping(value="/mywork")
	public String workList(){
		
		return "personalfolder/list";
		
	}
	
}
