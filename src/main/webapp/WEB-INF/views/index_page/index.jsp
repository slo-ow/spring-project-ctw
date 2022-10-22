<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!--
	* @Description : index 메인페이지
	* @author 문성철 (icet25@naver.com)
	* @version 1.2
	* @since 2017.04.20
-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 로고 이미지 -->
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath }/resources/indexpage/assets/css/images/logo.png">
<title>Company</title>
<!-- custom css -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/indexpage/css/custom.css" />
</head>
<body>
<video poster="${pageContext.request.contextPath}/resources/video/main.png" id="bgvid" autoplay="autoplay" loop="loop" preload="auto" muted="muted">
	<source src="${pageContext.request.contextPath}/resources/video/main.mp4" type="video/mp4">
</video>
<div id="polina">
<h1>company ware</h1>
<p>Copyright 2017. CompanyWare All rights reserved.
<p><a rel="nofollow" rel="noreferrer"href="loginForm">LOGIN</a></p>
<p><a rel="nofollow" rel="noreferrer"href="introduce">INTRODUCE</a></p>
<p><a rel="nofollow" rel="noreferrer"href="presentation">PRESENTATION</a></p>
<p><a rel="nofollow" rel="noreferrer"href="contact">CONTACT US</a></p>
<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur porta dictum turpis, eu mollis justo gravida ac. Proin non eros blandit, rutrum est a, cursus quam. Nam ultricies, velit ac suscipit vehicula, turpis eros sollicitudin lacus, at convallis mauris magna non justo. Etiam et suscipit elit. Morbi eu ornare nulla, sit amet ornare est. Sed vehicula ipsum a mattis dapibus. Etiam volutpat vel enim at auctor.</p>
<p>Aenean pharetra convallis pellentesque. Vestibulum et metus lectus. Nunc consectetur, ipsum in viverra eleifend, erat erat ultricies felis, at ultricies mi massa eu ligula. Suspendisse in justo dapibus metus sollicitudin ultrices id sed nisl.</p>
<button>Pause</button>
</div>
</body>
<script src="${pageContext.request.contextPath }/resources/indexpage/js/custom.js"></script>
</html>