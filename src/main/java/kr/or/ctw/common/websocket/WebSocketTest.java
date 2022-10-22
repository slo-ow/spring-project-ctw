package kr.or.ctw.common.websocket;


import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;



@ServerEndpoint("/websocket")
public class WebSocketTest{
	static  HttpSession ses;
	private static final Set<Session> sessions = (Set<Session>) ses.getAttribute("member");
		//Collections.synchronizedSet(new java.util.HashSet<Session>());
	@OnOpen
	public void handleOpen(Session ses){
		
		 System.out.println("Open session id : " + ses.getId());
	        
	        try {
	            final Basic basic = ses.getBasicRemote();
	            basic.sendText("Connection Established");
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
	        
	        sessions.add(ses);
		System.out.println("연결되나요? ");
	}

	
	 private void sendAllSessionToMessage(Session self, String message){
	        try {
	            for( Session session : sessions ){
	                if( ! self.getId().equals(session.getId()) )
	                    session.getBasicRemote().sendText("All : " + message);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
     * 웹 소켓으로부터 메시지가 오면 호출되는 이벤트
     * @param message
     * @return
     */
    @OnMessage
    public void handleMessage(String message,Session session){
    	System.out.println("Message from " + session.getId() + ": " + message);
        try {
            final Basic basic = session.getBasicRemote();
            basic.sendText("to : " + message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        sendAllSessionToMessage( session, message );

    }



    
    
    /**
     * 웹 소켓이 닫히면 호출되는 이벤트
     */
    @OnClose
    public void handleClose(){
        System.out.println("client is now disconnected...");
    }
    
    
    /**
     * 웹 소켓이 에러가 나면 호출되는 이벤트
     * @param t
     */
    @OnError
    public void handleError(Throwable t){
        t.printStackTrace();
    }


	
}
