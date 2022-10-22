package kr.or.ctw.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * @Class Name : LoginSessionScope.java
 * @Description : 로그인 유저들의 세션관리 객체
 * @author 문성철 
 * @since  2017.04.20
 * @version 1.0
 * @see
 */

public class LoginSessionScope {
	public static Map<String, HttpSession> connectUserList = new HashMap<String, HttpSession>();
	
	public static void addConnectUser(String id, HttpSession session){
		connectUserList.put(id, session);
	}
	
	public static void removeDisconnectUser(String id){
		connectUserList.remove(id);
	} 
	
	
}
