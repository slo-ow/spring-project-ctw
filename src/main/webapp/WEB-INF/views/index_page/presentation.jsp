<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!--
	* presentation 소개 페이지
	* @author 문성철 (icet25@naver.com)
	* @version 1.0
	* @since 2017.04.05
-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<!-- 로고 이미지 -->
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath }/resources/indexpage/assets/css/images/logo.png">
<title>Company</title>
	<!-- Link Swiper's CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/swiper/swiper.min.css">

	<!-- styles / API의 기본 style 부분이기 때문에 따로 건드리지않는다. -->
	<style>
		html, body {
			position: relative;
			height: 100%;
		}
		body {
			background: #eee;
			font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
			font-size: 14px;
			color: #000;
			margin: 0;
			padding: 0;
		}
		.swiper-container {
			width: 100%;
			height: 100%;
		}
		.swiper-slide {
			text-align: center;
			font-size: 18px;
			background: #fff;
			/* Center slide text vertically */
			display: -webkit-box;
			display: -ms-flexbox;
			display: -webkit-flex;
			display: flex;
			-webkit-box-pack: center;
			-ms-flex-pack: center;
			-webkit-justify-content: center;
			justify-content: center;
			-webkit-box-align: center;
			-ms-flex-align: center;
			-webkit-align-items: center;
			align-items: center;
		}
		.menu {
			min-width: 100px;
			width: 15%;
			max-width: 320px;
			background-color: #448bde;
			color: #fff;
		}
		.content {
			width: 100%;
		}
		.menu-button {
			position: absolute;
			top: 0px; left: 0px;
			padding: 15px;
			cursor: pointer;
			-webkit-transition: .3s;
			transition: .3s;
			background-color: #2C8DFB;
			/*margin: 14px;
			border-radius: 5px;*/
		}
		.menu-button .bar:nth-of-type(1) {
			margin-top: 0px;
		}
		.menu-button .bar:nth-of-type(3) {
			margin-bottom: 0px;
		}
		.bar {
			position: relative;
			display: block;
			width: 50px;
			height: 5px;
			margin: 10px auto;
			background-color: #fff;
			border-radius: 10px;
			-webkit-transition: .3s;
			transition: .3s;
		}
		.menu-button:hover .bar:nth-of-type(1) {
			-webkit-transform: translateY(1.5px) rotate(-4.5deg);
			-ms-transform: translateY(1.5px) rotate(-4.5deg);
			transform: translateY(1.5px) rotate(-4.5deg);
		}
		.menu-button:hover .bar:nth-of-type(2) {
			opacity: .9;
		}
		.menu-button:hover .bar:nth-of-type(3) {
			-webkit-transform: translateY(-1.5px) rotate(4.5deg);
			-ms-transform: translateY(-1.5px) rotate(4.5deg);
			transform: translateY(-1.5px) rotate(4.5deg);
		}
		.cross .bar:nth-of-type(1) {
			-webkit-transform: translateY(15px) rotate(-45deg);
			-ms-transform: translateY(15px) rotate(-45deg);
			transform: translateY(15px) rotate(-45deg);
		}
		.cross .bar:nth-of-type(2) {
			opacity: 0;
		}
		.cross .bar:nth-of-type(3) {
			-webkit-transform: translateY(-15px) rotate(45deg);
			-ms-transform: translateY(-15px) rotate(45deg);
			transform: translateY(-15px) rotate(45deg);
		}
		.cross:hover .bar:nth-of-type(1) {
			-webkit-transform: translateY(13.5px) rotate(-40.5deg);
			-ms-transform: translateY(13.5px) rotate(-40.5deg);
			transform: translateY(13.5px) rotate(-40.5deg);
		}
		.cross:hover .bar:nth-of-type(2) {
			opacity: .1;
		}
		.cross:hover .bar:nth-of-type(3) {
			-webkit-transform: translateY(-13.5px) rotate(40.5deg);
			-ms-transform: translateY(-13.5px) rotate(40.5deg);
			transform: translateY(-13.5px) rotate(40.5deg);
		}
		/* ul 아래의 자식태그 li 스타일 지정 */
		.uls > li {
			cursor: pointer;
			margin-bottom: 10px;
			text-align: left;
		}
		h1{
			font-family: Phenomena-Bold;
		}

	</style>
</head>
<body>
	<!-- Swiper -->
	<div class="swiper-container">
		<div class="swiper-wrapper">
			<div class="swiper-slide menu">
					<ul class="uls">
						<!-- 메뉴의 list 부분 -->
						<li onclick="home()">Home</li>
						<li onclick="login()">Log In</li>
						<li onclick="introduce()">Introduce</li>
						<li onclick="presentation()">presentation</li>
						<li onclick="contact()">Contact Us</li>
					</ul>
			</div>
			<!-- 상단 三 모양 아이콘 / 수정하지 말것 -->
			<div class="swiper-slide content">
				<div class="menu-button">
					<div class="bar"></div>
					<div class="bar"></div>
					<div class="bar"></div>
				</div>
				<!-- 슬라이드의 첫 시작 페이지가 보여지는 부분 -->
				<h1>Company Where This Ware</h1>
			</div>
			<!--
					슬라이드의 이미지 list 부분, 보여주고 싶은 개수만큼
					아래와같이 <div></div> 태그를 늘려주면 된다.
			-->
			<div class="swiper-slide"><img src="${pageContext.request.contextPath }/resources/swiper/images/1.jpg"/></div>
			<div class="swiper-slide"><img src="${pageContext.request.contextPath }/resources/swiper/images/2.jpg"/></div>
			<div class="swiper-slide"><img src="${pageContext.request.contextPath }/resources/swiper/images/3.jpg"/></div>
			<div class="swiper-slide"><img src="${pageContext.request.contextPath }/resources/swiper/images/4.jpg"/></div>
			<div class="swiper-slide"><img src="${pageContext.request.contextPath }/resources/swiper/images/5.jpg"/></div>
			<div class="swiper-slide"><img src="${pageContext.request.contextPath }/resources/swiper/images/6.jpg"/></div>
			<div class="swiper-slide"><img src="${pageContext.request.contextPath }/resources/swiper/images/7.jpg"/></div>
			<div class="swiper-slide"><img src="${pageContext.request.contextPath }/resources/swiper/images/8.jpg"/></div>
			<div class="swiper-slide"><img src="${pageContext.request.contextPath }/resources/swiper/images/9.jpg"/></div>
		</div>
			 <!-- Add Pagination -->
			 <div class="swiper-pagination"></div>
			 <!-- Add Arrows -->
			 <div class="swiper-button-next"></div>
			 <div class="swiper-button-prev"></div>
	</div>

	<!-- Swiper JS -->
	<script src="${pageContext.request.contextPath }/resources/swiper/swiper.min.js"></script>

	<!-- Initialize Swiper / 기존 js 수정 하지말것 -->
	<script>
		var toggleMenu = function(){
			if (swiper.previousIndex == 0)
				swiper.slidePrev()
		}
		, menuButton = document.getElementsByClassName('menu-button')[0]
		, swiper = new Swiper('.swiper-container', {
			slidesPerView: 'auto'
			, initialSlide: 1
			, resistanceRatio: .00000000000001
			, keyboardControl: true
			, zoom: true
			, pagination: '.swiper-pagination'
			, paginationType: 'progress'
			, onSlideChangeStart: function(slider) {
				if (slider.activeIndex == 0) {
					menuButton.classList.add('cross')
					menuButton.removeEventListener('click', toggleMenu, false)
				} else
					menuButton.classList.remove('cross')
			}
			, onSlideChangeEnd: function(slider) {
				if (slider.activeIndex == 0)
					menuButton.removeEventListener('click', toggleMenu, false)
				else
					menuButton.addEventListener('click', toggleMenu, false)
			}
		})

		/* 메뉴의 list --> li 태그 클릭 동작 기능 */
		
		function home(){
			location.href='index';
		}
		function login(){
			location.href='login';
		}
		function introduce(){
			location.href='introduce';
		}
		function presentation(){
			location.href='presentation';
		}
		function contact(){
			location.href='contact';
		}
		
	</script>
</body>
</html>