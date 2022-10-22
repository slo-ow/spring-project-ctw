<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.or.ctw.common.RSAGenerateKey"%>
<%@ page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	RSAGenerateKey keyGenerate = new RSAGenerateKey();
	Map<String,String> keyMap = keyGenerate.getGeneratePairKey(session);
%>
<c:set var="keyMap" value="<%=keyMap %>"></c:set>
<!DOCTYPE html>
<html>
<!--
	* @Description : 로그인 페이지 / Google recaptcha 기능 적용, 사용자 정보 암호화 적용, 엔터입력 로그인 기능
	* @author 문성철 (icet25@naver.com)
	* @version 1.2
	* @since 2017.04.20
-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 로고 이미지 -->
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath }/resources/indexpage/assets/css/images/logo.png">
<!-- custom css -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/loginForm/css/custom.css">
<!--  Google recaptcha -->
<script  src="https://code.jquery.com/jquery-3.2.1.js"></script>
<!-- sweetalert -->
<script src="${pageContext.request.contextPath }/resources/sweetalert/js/sweetalert2.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/sweetalert/css/sweetalert2.min.css">

<title>Company Where This Ware Login</title>
</head>
<!--  로그인의 결과값이 false와 같을경우 로그인 실패 -->
<c:if test="${loginCheck eq false }">
<script type="text/javascript">
$(function(){
	//새로 고침 방지 기능 실행
	function LockF5(){
	 if (event.keyCode == 116) {
	  event.keyCode = 0;
	  return false;
	 }
	}
	document.onkeydown = LockF5;
	//alert
	console.log("로그인 실패");
	swal({
		  title: "로그인 실패",
		  text: "아이디 또는 비밀번호를 확인해주세요",
		  type: "error",
		  confirmButtonText: "닫기"
	});
})
</script>
</c:if>
<script type="text/javascript">
$(function(){  	 
	/* $("#login").click(function(){	
		if (typeof(grecaptcha) != 'undefined') {	
			if (grecaptcha.getResponse() == "") {	
				alert("자동로그인/스팸방지코드를 체크 해주세요.");	
			    return;	
			}else{
				//암호화 시작
				var rsa = new RSAKey();
				rsa.setPublic('${keyMap["publicKeyModulus"]}', '${keyMap["publicKeyExponent"]}');	
				var idEncryptValue = rsa.encrypt($('#mem_id').val());			
				var pwdEncryptValue = rsa.encrypt($('#mem_pass').val());
				//암호화 끝
				
				//동적으로 폼 생성 시작
				$frm = $('<form action="login" method="post"></form>');
				$frm.append('<input type="hidden" name="mem_id" value="' + idEncryptValue + '"/>');
				$frm.append('<input type="hidden" name="mem_pass" value="' + pwdEncryptValue + '"/>');
				$(document.body).append($frm);
				$frm.submit();
			}	
		 }
	}) */
	$("#login").click(function(){
		//로그인폼 유효성검사		
		var id = $("#mem_id").val();
		var pass = $("#mem_pass").val();
		if(id==""||pass==""){
			swal({
				  title: "로그인 실패",
				  text: "아이디 또는 비밀번호를 확인해주세요",
				  type: "error",
				  confirmButtonText: "닫기"
			});
			return;
		}
		
		//암호화 시작
		var rsa = new RSAKey();
		rsa.setPublic('${keyMap["publicKeyModulus"]}', '${keyMap["publicKeyExponent"]}');	
		var idEncryptValue = rsa.encrypt($('#mem_id').val());			
		var pwdEncryptValue = rsa.encrypt($('#mem_pass').val());
		//암호화 끝
		
		//동적으로 폼 생성 시작
		$frm = $('<form action="login" method="post"></form>');
		$frm.append('<input type="hidden" name="mem_id" value="' + idEncryptValue + '"/>');
		$frm.append('<input type="hidden" name="mem_pass" value="' + pwdEncryptValue + '"/>');
		$(document.body).append($frm);
		$frm.submit();
	})
	
	//enterKey 입력이벤트발생 , 엔터입력 로그인 
	$("input[name=mem_pass]").keydown(function (key) {
		if(key.keyCode == 13){ // 입력된 이벤트 키가 13 이면 실행 (엔터키코드 13)
			//로그인폼 유효성검사		
			var id = $("#mem_id").val();
			var pass = $("#mem_pass").val();
			if(id==""||pass==""){				
				return;
			}
			
			//암호화 시작
			var rsa = new RSAKey();
			rsa.setPublic('${keyMap["publicKeyModulus"]}', '${keyMap["publicKeyExponent"]}');	
			var idEncryptValue = rsa.encrypt($('#mem_id').val());			
			var pwdEncryptValue = rsa.encrypt($('#mem_pass').val());
			//암호화 끝
			
			//동적으로 폼 생성 시작
			$frm = $('<form action="login" method="post"></form>');
			$frm.append('<input type="hidden" name="mem_id" value="' + idEncryptValue + '"/>');
			$frm.append('<input type="hidden" name="mem_pass" value="' + pwdEncryptValue + '"/>');
			$(document.body).append($frm);
			$frm.submit();
		}
	});
	
});
 
  var onloadCallback = function() {
    grecaptcha.render('html_element', {
      'sitekey' : '6LfTLxoUAAAAAILA9vSl49gdQ-eR-5w1aEfr8ALb'
    });
  };     
</script>

<body class="hold-transition login-page">
<div class="vid-container">
  <video class="bgvid" autoplay="autoplay" muted="muted" preload="auto" loop>
      <source src="${pageContext.request.contextPath}/resources/video/login.mp4" type="video/mp4">
  </video>
  <div class="inner-container">
    <div class="box">
      <h1>Company Ware</h1>
      <input type="text" placeholder="Your code (ID)" name="mem_id" id="mem_id"/>
      <input type="password" placeholder="Password" name="mem_pass" id="mem_pass"/>
      <!-- recaptcha -->
      <!-- <div class="checkbox icheck" style="margin-left: 35px;">
      	<div id="html_element"></div>
        <div class="g-recaptcha" data-sitekey="6LfTLxoUAAAAAILA9vSl49gdQ-eR-5w1aEfr8ALb"></div>
      </div> -->
      <button id="login">Sign In</button>
      <button onclick="history.back(-1)">Return</button>
    </div>
  </div>
</div>
<script src="${pageContext.request.contextPath}/resources/rsa/jsbn.js"></script>
<script src="${pageContext.request.contextPath}/resources/rsa/rsa.js"></script>
<script src="${pageContext.request.contextPath}/resources/rsa/prng4.js"></script>
<script src="${pageContext.request.contextPath}/resources/rsa/rng.js"></script>
<!-- Google recaptcha -->
 <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit" async defer></script>
 <!-- iCheck -->
<script src="${pageContext.request.contextPath }/resources/plugins/iCheck/icheck.min.js"></script>
<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
  });
</script>
</body>
</html>