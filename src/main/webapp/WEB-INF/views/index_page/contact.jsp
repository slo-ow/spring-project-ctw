<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!--
	* Contact Us 회사 소개 Google Map 페이지
	* @author 문성철 (icet25@naver.com)
	* @version 1.0
	* @since 2017.04.05
-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Company</title>
<!-- 로고 이미지 -->
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath }/resources/indexpage/assets/css/images/logo.png">
<!-- CSS 커스터마이징 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/indexpage/assets/css/custom.css" />
<!-- JS 커스터마이징 -->
<script src="${pageContext.request.contextPath }/resources/indexpage/assets/js/custom.js"></script>
<link rel="stylesheet"	href="${pageContext.request.contextPath }/resources/indexpage/assets/css/main.css" />
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
<style>
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}

#map {
	height: 100%;
}

#floating-panel {
	position: absolute;
	top: 10px;
	left: 25%;
	z-index: 5;
	background-color: #fff;
	padding: 5px;
	border: 1px solid #999;
	text-align: center;
	font-family: 'Roboto', 'sans-serif';
	line-height: 30px;
	padding-left: 10px;
}

#floating-panel {
	margin-left: -52px;
}

</style>
<script>
		var neighborhoods = [ {
			//성철
			lat : 36.309753,
			lng : 127.396324
		}, {
			//혜윤
			lat : 36.351225,
			lng : 127.428482
		}, {
			//완수
			lat : 36.348891,
			lng : 127.373962
		}, {
			//선아
			lat : 36.502011,
			lng : 127.252319
		},{
			//창현
			lat : 36.373012,
			lng : 127.425591
		}];

		var map;
		var contentString = [];
		var i = 0;
		
		//첫번째 창 내용
		contentString[0] = '<div id="content">'+
	      '<div id="siteNotice">'+
	      '</div>'+
	      '<h1 id="firstHeading" class="firstHeading">TA</h1>'+
	      '<div id="bodyContent">'+
	      '<p>' +	      
	      '<img src="${pageContext.request.contextPath }/resources/indexpage/assets/css/images/user.png"/><br>'+
	      '<b>TA : </b>문성철</p>'+
	      '</div>'+
	      '</div>';
		//두번째 창 내용
		contentString[1] = '<div id="content">'+
	      '<div id="siteNotice">'+
	      '</div>'+
	      '<h1 id="firstHeading" class="firstHeading">AA</h1>'+
	      '<div id="bodyContent">'+
	      '<p>' +	      
	      '<img src="${pageContext.request.contextPath }/resources/indexpage/assets/css/images/user.png"/><br>'+
	      '<b>TA : </b>박혜윤</p>'+
	      '</div>'+
	      '</div>';
		//세번째 창 내용
		contentString[2] = '<div id="content">'+
	      '<div id="siteNotice">'+
	      '</div>'+
	      '<h1 id="firstHeading" class="firstHeading">DA</h1>'+
	      '<div id="bodyContent">'+
	      '<p>' +	      
	      '<img src="${pageContext.request.contextPath }/resources/indexpage/assets/css/images/user.png"/><br>'+
	      '<b>TA : </b>김완수</p>'+
	      '</div>'+
	      '</div>';
		//네번째 창 내용
		contentString[3] = '<div id="content">'+
	      '<div id="siteNotice">'+
	      '</div>'+
	      '<h1 id="firstHeading" class="firstHeading">BA</h1>'+
	      '<div id="bodyContent">'+
	      '<p>' +	      
	      '<img src="${pageContext.request.contextPath }/resources/indexpage/assets/css/images/user.png"/><br>'+
	      '<b>TA : </b>김선아</p>'+
	      '</div>'+
	      '</div>';
		//다섯번째 창 내용
		contentString[4] = '<div id="content">'+
	      '<div id="siteNotice">'+
	      '</div>'+
	      '<h1 id="firstHeading" class="firstHeading">PL</h1>'+
	      '<div id="bodyContent">'+
	      '<p>' +	      
	      '<img src="${pageContext.request.contextPath }/resources/indexpage/assets/css/images/user.png"/><br>'+
	      '<b>TA : </b>박창현</p>'+
	      '</div>'+
	      '</div>';
		
	      
		//페이지가 로드되는순간 표시되는 지도의 위도,경도를 설정
		function initMap() {
			map = new google.maps.Map(document.getElementById('map'), {
				zoom : 10,
				center : {
					lat : 36.339900,
					lng : 127.395665
				}
			});
			//마커의 개수만큼 반복분을 실행
			for (var i = 0; i < neighborhoods.length; i++) {
				addMarker(neighborhoods[i]);
			}			
			//alert(neighborhoods.length);
		}
		
		//position 으로 배열안의 마커들의 위도,경도를 넘겨받음
		function addMarker(position){			
			var marker = new google.maps.Marker({
				position : position,
				map : map,
				animation : google.maps.Animation.DROP //버튼클릭시 드롭되는 마커 애니메이션설정	
			});			   
		 	 //infowindow창 생성
			var infowindow = new google.maps.InfoWindow({
			    content: contentString[i]			
			});
				
			marker.addListener('click', function() {
			    infowindow.open(map, marker);
			});
			i++;
			//alert(i);
		}	
		
	</script>
	<!-- Google Map API Key & Script -->
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAfPHiUGM5v1-aJidTTjH_Pbm3OkfTjQ1A&callback=initMap"></script>
</head>
<body>
	<!-- Page Wrapper -->
	<div id="page-wrapper">
		<!-- index_layout / include -->
		<%@include file="../index_layout/header.jsp"%>

		<!-- Main -->
		<article id="main">
			<header class="headfont">
				<h2>Contact us</h2>
			</header>
			<!-- Google Map -->
			<!-- Google Map을 감싸고있는 div의 padding 과 height style 추가 -->
			<section class="wrapper style5" style="height: 650px; padding-top: 0px; padding-bottom: 0px;">
					<div id="map"></div>
			</section>
		</article>

		<!-- index_layout / include -->
		<%@include file="../index_layout/footer.jsp"%>

	</div>	
	<!-- Scripts -->
	<script	src="${pageContext.request.contextPath }/resources/indexpage/assets/js/jquery.min.js"></script>
	<script	src="${pageContext.request.contextPath }/resources/indexpage/assets/js/jquery.scrollex.min.js"></script>
	<script	src="${pageContext.request.contextPath }/resources/indexpage/assets/js/jquery.scrolly.min.js"></script>
	<script	src="${pageContext.request.contextPath }/resources/indexpage/assets/js/skel.min.js"></script>
	<script	src="${pageContext.request.contextPath }/resources/indexpage/assets/js/util.js"></script>
	<script	src="${pageContext.request.contextPath }/resources/indexpage/assets/js/main.js"></script>

</body>
</html>