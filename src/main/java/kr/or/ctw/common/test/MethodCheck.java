package kr.or.ctw.common.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MethodCheck implements ServletContextListener {

	
   @Resource(name="countService")
   CountService service;


@Override
public void contextDestroyed(ServletContextEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void contextInitialized(ServletContextEvent arg0) {
		
		

		
        // 실행간격 지정(3초)
        int sleepSec = 3 ;
         
        // 시간 출력 포맷
        final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
 
         
        // 주기적인 작업을 위한
        final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

    	
  
        exec.scheduleAtFixedRate(new Runnable(){
             int test = 0;
			public void run(){
                try {
 
                    Calendar cal = Calendar.getInstance() ;
                    test = service.testCal();
                    System.out.println("test = "+test);
            		System.out.println("자동으로 실행이 되었나요 ??");
                     
                    // 콘솔에 현재 시간 출력
                    System.out.println(fmt.format(cal.getTime())) ;
                     
                } catch (Exception e) {
                     
                    e.printStackTrace();
                     
                     
                  /*  // 에러 발생시 Executor를 중지시킨다
                    exec.shutdown() ;*/
                }
            }
        }, 0, sleepSec, TimeUnit.SECONDS);


	}

	
	
	
}
