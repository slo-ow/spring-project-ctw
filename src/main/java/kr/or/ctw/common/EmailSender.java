package kr.or.ctw.common;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 메일 발송을 위한 컨트롤러 
 * 
 * <pre>
 * 
 * </pre>
 * @author 김완수
 * @since 2017. 5. 2.
 * @version 1.0
 * @see kr.or.ctw.common EmailSender
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일             수정자        수정내용
 * --------------     --------    -----------
 * 2017. 5. 2.     	  김완수        최초작성
 * Copyright (c) 2017 by DDIT All right reserved
 * </pre>
 */
@RequestMapping({"/manager","/member"})
@Controller
public class EmailSender {
	  @Autowired
	  private JavaMailSender mailSender;
	 
	  // mailSending 코드
	  @RequestMapping(value = "/mail/mailSending",method=RequestMethod.POST)
	  @ResponseBody
	  public JSONObject mailSending(HttpServletRequest request) {
		JSONObject json =new JSONObject();
	    String setfrom = "wansoo0243@gmail.com";   //이 부분에 자신의 구글 메일 주소를 넣어준다. 계정설정은 root-context.xml 에서 할것   
	    String tomail  = request.getParameter("tomail");     // 받는 사람 이메일
	    String title   = request.getParameter("title");      // 제목
	    String content = request.getParameter("content");    // 내용
	   
	    try {
	      MimeMessage message = mailSender.createMimeMessage();
	      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	 
	      messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
	      messageHelper.setTo(tomail);     // 받는사람 이메일
	      messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
	      messageHelper.setText(content);  // 메일 내용
	      //messageHelper.setFileTypeMap(fileTypeMap);
	      mailSender.send(message);
	      json.put("check", "1");
	    } catch(Exception e){
	      json.put("check", "2");
	    }
	   
	    return json;
	  }

	
	
	
}
