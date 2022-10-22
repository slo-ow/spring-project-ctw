package kr.or.ctw.common.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <pre>
 * 잠금 화면 여부를 판단하는 LockInterceptor 클래스 
 * </pre>
 * @author 문성철
 * @since 2017. 5. 1.
 * @version 1.0
 * @see
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일            수정자               수정내용
 * --------     --------    ----------------------
 * 2017. 5. 1.      작성자명       최초작성
 * Copyright (c) 2017 by DDIT All right reserved
 * </pre>
 */
public class LockInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(LockInterceptor.class);

	// 지정된 컨트롤러의 동작 이전에 가로채는 역할로 사용
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		System.out.println("pre handle.............지정된 컨트롤러의 동작 이전에 가로채는 역할로 사용");
		
		HttpSession ses = request.getSession();
		System.out.println("\t\t\t잠금화면 세션 확인  : " + ses.getAttribute("lock"));
		if(ses.getAttribute("lock")!=null){ // 세션이 null 이 아니라면 잠금상태를 의미함.
			
			// java에서 sweetAlert API를 사용하기위해 공통모듈로 구현해놓은 클래스를 호출한다.			
			response.setContentType("text/html; charset=UTF-8");

			// 잠금상태라면 == 잠금화면으로 Redirect 시킨다.
			response.sendRedirect("/ctw/lock/lockscreen");
			return false;
		}else{
			System.out.println("\t\t\t잠금상태 OFF");
			return true;
		}
		
	}
	
	/*
	// 지정된 컨트롤러의 동작 이후에 처리, SpringMVC의  Front Controller인 DispatcherServlet 이 화면을 처리하기 전에 동작
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			
		System.out.println("post handle...........지정된 컨트롤러의 동작 이후에 처리, SpringMVC의  Front Controller인 DispatcherServlet 이 화면을 처리하기 전에 동작");
		
	}*/

	/*
	// DispatcherServlet의 화면 처리가 완료된 상태에서 처리
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		// Object handler 는 현재 실행하려는 메소드 자체를 의미함. 
		// 이를 활용하면 현재 실행되는 컨트롤러를 파악하거나, 추가적인 메소드를 실행하는 등의 작업이 가능하다.
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod(); 
		logger.info("\n afterCompletion을 호출한 메소드 : " + methodObj);
		
		
		HttpSession ses = request.getSession();
		System.out.println("\t\t\t잠금화면 세션 확인  : " + ses.getAttribute("lock"));
		if(ses.getAttribute("lock")!=null){ // 세션이 null 이 아니라면 잠금상태를 의미함.
			
			// java에서 sweetAlert API를 사용하기위해 공통모듈로 구현해놓은 클래스를 호출한다.			
			response.setContentType("text/html; charset=UTF-8");

			// 잠금상태라면 == 잠금화면으로 Redirect 시킨다.
			response.sendRedirect("/ctw/lock/lockscreen");
		}else{
			System.out.println("\t\t\t잠금상태 OFF");
		}
		
		
	}
	*/
}
