package kr.or.ctw.common.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorpageHandler {

	// 400에러 페이지
	@RequestMapping(value="/error400")
	public String error400(){
		return "error_page/error400";
		
	}
	
	
	// 404에러 페이지
	@RequestMapping(value="/error404")
	public String error404(){
		return "error_page/error404";
		
	}
	
	
	// 405에러 페이지
	@RequestMapping(value="/error405")
	public String error405(){
		return "error_page/error405";
		
	}
	
	
	// 500에러 페이지
	@RequestMapping(value="/error500")
	public String error500(){
		return "error_page/error500";
		
	}
	
	
	
	// 502에러 페이지
	@RequestMapping(value="/error502")
	public String error502(){
		return "error_page/error502";
		
	}
	
}
