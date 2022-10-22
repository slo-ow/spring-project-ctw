<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%--
* @Description 유저프로필 - lockscreen 잠금화면
* @author 문성철
* @version 1.0
* @since 2017. 5. 2.
*
* [[개정이력(Modification Information)]]
*  수정일          수정자          수정내용
* ----------    ---------   -------------
* 2017. 5. 2.      작성자명      최초작성
* Copyright (c) 2017 by CTW All right reserved
 --%>
<html>
<head>
<%@ include file="head.jsp"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<%@ page import="kr.or.ctw.common.RSAGenerateKey"%>
<%@ page import="java.util.Map"%>
<%
	RSAGenerateKey keyGenerate = new RSAGenerateKey();
	Map<String,String> keyMap = keyGenerate.getGeneratePairKey(session);
%>
<c:set var="keyMap" value="<%=keyMap %>"></c:set>
<!-- Lock custom css -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/mainform/css/lock.css" />
<!-- Lock custom js -->
<script src="${pageContext.request.contextPath}/resources/mainform/js/lock.js"></script>
<script>
$(function(){
	
	//enterKey 입력이벤트발생 , 엔터입력 로그인 
	$("#mem_pass").keydown(function (key) {
		
		if(key.keyCode == 13){ // 입력된 이벤트 키가 13 이면 실행 (엔터키코드 13)
			console.log('로그인 요청');
		
			//로그인폼 유효성검사		
			var id = $("#mem_id").val();
			var pass = $("#mem_pass").val();
		
			//암호화 시작
			var rsa = new RSAKey();
			rsa.setPublic('${keyMap["publicKeyModulus"]}', '${keyMap["publicKeyExponent"]}');	
			var idEncryptValue = rsa.encrypt($('#mem_id').val());			
			var pwdEncryptValue = rsa.encrypt($('#mem_pass').val());
			//암호화 끝
			
			//동적으로 폼 생성 시작
			$frm = $('<form action="unlock" method="post"></form>');
			$frm.append('<input type="hidden" name="mem_id" value="' + idEncryptValue + '"/>');
			$frm.append('<input type="hidden" name="mem_pass" value="' + pwdEncryptValue + '"/>');
			$(document.body).append($frm);
			$frm.submit();
			
		}
	})
	
})
</script>
</head>
<body class="hold-transition">
<div class="vid-container">
  <video class="bgvid" autoplay="autoplay" loop="loop" preload="auto" muted="muted">
      <source src="${pageContext.request.contextPath}/resources/video/lock.mp4" type="video/mp4">
  </video>
  <div class="inner-container">
    <div class="box">
      <h1>LOCK - ON</h1>
      <h2>Company Ware</h2>
      <div class="custom_footer">Copyright © 2017 <b><a href="#">CompanyWare</a></b>&nbsp;All rights reserved</div>
    </div>
   	<!-- lock body -->
	<div class="custom_lock">
		<!-- START LOCK SCREEN ITEM -->
		<div class="lockscreen-item">
			<!-- lockscreen image -->
			<div class="lockscreen-image">
				<img src="${pageContext.request.contextPath}/resources/mainform/image/lock.png" alt="User Image">
			</div>
			<!-- lockscreen image end -->
			<!-- form -->
			<div class="lockscreen-credentials">
				<div class="input-group">
					<input type="hidden" name="mem_id" id="mem_id" value="${member.mem_id}">
					<input type="password" name="mem_pass" id="mem_pass" class="form-control" placeholder="password">
					<div class="input-group-btn">
						<button type="button" class="btn">
							<i class="fa fa-arrow-right text-muted"></i>
						</button>
					</div>
				</div>
			</div>
			<!-- form end -->
		</div>		
	</div>
	<!-- lock body end -->
  </div>
</div>
<script src="${pageContext.request.contextPath}/resources/rsa/jsbn.js"></script>
<script src="${pageContext.request.contextPath}/resources/rsa/rsa.js"></script>
<script src="${pageContext.request.contextPath}/resources/rsa/prng4.js"></script>
<script src="${pageContext.request.contextPath}/resources/rsa/rng.js"></script>
</body>
</html>