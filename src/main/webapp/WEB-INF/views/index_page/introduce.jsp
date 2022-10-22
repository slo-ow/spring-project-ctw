<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!--
	* introduce 소개 페이지
	* @author 문성철 (icet25@naver.com)
	* @version 1.0
	* @since 2017.04.04
-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Company</title>
<!-- 로고 이미지 -->
<!-- 로고 이미지 -->
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath }/resources/indexpage/assets/css/images/logo.png">
<!-- CSS 커스터마이징 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/indexpage/assets/css/custom.css" />
<!-- JS 커스터마이징 -->
<script
	src="${pageContext.request.contextPath }/resources/indexpage/assets/js/custom.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/indexpage/assets/css/main.css" />
<style type="text/css">
/* 글자 가운데 정렬 */
.text-center {
	text-align: center;
}

</style>
</head>
<body>
	<!-- Page Wrapper -->
	<div id="page-wrapper">
		<!-- index_layout / include -->
		<%@include file="../index_layout/header.jsp"%>

		<!-- Main -->
		<article id="main">
			<header class="headfont">
				<h2>introduce</h2>
			</header>
			<section class="wrapper style5">
				<!-- 팀 프로필 사진 -->
				<div class="section">
					<div class="container">
						<div class="row" style="margin-left: 42%;">
							<div class="col-md-12">
								<h1 class="text-center" style="color: black; font-size: 40px;">OUR TEAM</h1>
								<br>
							</div>
						</div>
						<div class="row" style="margin-left: 25%;">
							<div class="col-md-4">
								<img src="${pageContext.request.contextPath }/resources/indexpage/images/man.png"
									class="center-block img-circle img-responsive">
								<h3 class="text-center">박창현</h3>
								<p class="text-center">PL</p>
							</div>
							<div class="col-md-4">
								<img src="${pageContext.request.contextPath }/resources/indexpage/images/astronaut.png"
									class="center-block img-circle img-responsive">
								<h3 class="text-center">문성철</h3>
								<p class="text-center">TA</p>
							</div>
							<div class="col-md-4">
								<img src="${pageContext.request.contextPath }/resources/indexpage/images/maid.png"
									class="center-block img-circle img-responsive">
								<h3 class="text-center">박혜윤</h3>
								<p class="text-center">AA</p>
							</div>
						</div>
						<div class="row" style="margin-left: 34%;">
							<div class="col-md-7">
								<img src="${pageContext.request.contextPath }/resources/indexpage/images/dog.png"
									class="center-block img-circle img-responsive">
								<h3 class="text-center">김완수</h3>
								<p class="text-center">DA</p>
							</div>
							<div class="col-md-5">
								<img src="${pageContext.request.contextPath }/resources/indexpage/images/manager.png"
									class="center-block img-circle img-responsive">
								<h3 class="text-center">김선아</h3>
								<p class="text-center">BA</p>
							</div>
						</div>
					</div>
				</div>
				<!-- 이미지 -->
				<div class="box alt">
					<div class="row uniform 50%">
						<div class="4u">
							<span class="image fit"><img
								src="${pageContext.request.contextPath }/resources/indexpage/images/pic01.jpg"
								alt=""></span>
						</div>
						<div class="4u">
							<span class="image fit"><img
								src="${pageContext.request.contextPath }/resources/indexpage/images/pic01.jpg"
								alt=""></span>
						</div>
						<div class="4u">
							<span class="image fit"><img
								src="${pageContext.request.contextPath }/resources/indexpage/images/pic01.jpg"
								alt=""></span>
						</div>
						<div class="4u">
							<span class="image fit"><img
								src="${pageContext.request.contextPath }/resources/indexpage/images/pic01.jpg"
								alt=""></span>
						</div>
						<div class="4u">
							<span class="image fit"><img
								src="${pageContext.request.contextPath }/resources/indexpage/images/pic01.jpg"
								alt=""></span>
						</div>
						<div class="4u">
							<span class="image fit"><img
								src="${pageContext.request.contextPath }/resources/indexpage/images/pic01.jpg"
								alt=""></span>
						</div>
						<!-- YouTube Video -->
						<div class="4u">
							<iframe width="625" height="480" src="https://www.youtube.com/embed/rCeM57e2BfU" frameborder="0" allowfullscreen></iframe>
						</div>
						<div class="4u">
							<iframe width="625" height="480" src="https://www.youtube.com/embed/oOrS-2fLW9Q" frameborder="0" allowfullscreen></iframe>
						</div>
						<div class="4u">
							<iframe width="625" height="480" src="https://www.youtube.com/embed/qbrFdbrV0A4" frameborder="0" allowfullscreen></iframe>
						</div>
					</div>
				</div>
			</section>
		</article>

		<!-- index_layout / include -->
		<%@include file="../index_layout/footer.jsp"%>

	</div>

	<!-- Scripts -->
	<script src="${pageContext.request.contextPath }/resources/indexpage/assets/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/indexpage/assets/js/jquery.scrollex.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/indexpage/assets/js/jquery.scrolly.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/indexpage/assets/js/skel.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/indexpage/assets/js/util.js"></script>
	<script src="${pageContext.request.contextPath }/resources/indexpage/assets/js/main.js"></script>

</body>
</html>