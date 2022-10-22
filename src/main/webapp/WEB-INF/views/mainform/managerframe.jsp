<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<!DOCTYPE html>
<html>
<!--
	* @Description : 관리자 메인 프레임
	* @author 문성철 (icet25@naver.com)
	* @version 1.0
	* @since 2017.04.26
-->
<head>
	<%@include file="head.jsp"%>
	<!-- mainframe에 관련 javascript -->
	<script	src="${pageContext.request.contextPath }/resources/mainform/js/custom.js"></script>
</head>
<script type="text/javascript">

</script>
<style>
.fa-list-alt:before{
	color:#708090;
}
.fa-refresh:before{
	color: #2f4f4f;
}
.fa-list-alt:before{
	color: #708090;
}
.fa-sitemap:before{
	color: #ff6347;
}
.fa-book:before{
	color: #6495ed;
}
.glyphicon-tags:before{
	color: #483d8b;
}
.glyphicon-blackboard:before{
	color: #6b8e23;
}
.fa-check-circle:before{
	color: #800000;
}
.fa-group:before, .fa-users:before{
	color: #778899;
}
.glyphicon-envelope:before{
	color: #191970;
}
.fa-envelope:before{
	color: #1e90ff;
}
.fa-calendar:before{
	color: #87cefa;
}
</style>
<body class="hold-transition skin-purple-light sidebar-mini">
<div class="wrapper">
	<input type="hidden" id="grad" value="${member.grad_pk}"/>
	<header class="main-header">
		<a href="/ctw/manager/main" class="logo"> 
			<!-- 아이콘 로고 -->
			<span class="logo-mini">
				<img alt="CTW" src="${pageContext.request.contextPath }/resources/indexpage/assets/css/images/weblogo.png" width="45px" height="45px">
			</span>
			<!-- 폰트 로고 -->
			<span class="logo-lg">
				<img id="textLogo" src="${pageContext.request.contextPath }/resources/mainform/image/textLogo2.png">
			</span>
		</a>
		<!-- 상단 네비게이션 -->
		<nav class="navbar navbar-static-top">
			<!-- 사이드바 토글 버튼 -->
			<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button"> 
				<span class="sr-only">Toggle navigation</span>
			</a>
		<!-- 우측상단 쪽지, 메일, 알림 -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<!-- 쪽지알림 -->
				<li class="dropdown messages-menu">
					<!-- 시작 아이콘 및 라벨 -->
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
						<i class="fa fa-envelope-o"></i> <span class="label label-success">4</span>
					</a>
					<!-- 쪽지 드롭메뉴 -->
					<ul class="dropdown-menu">
						<li class="header">You have 4 messages</li>
							<!-- li 몸통부분 body -->
							<li>
								<!-- ul menu 시작 -->
								<ul class="menu">
									<!-- start message --> 
									<li>
										<a href="#">
											<div class="pull-left">
												<img src="${pageContext.request.contextPath }/resources/mainform/image/woman.png" class="img-circle" alt="User Image">
											</div>
											<h4>Support Team<small><i class="fa fa-clock-o"></i> 5mins</small></h4>
											<p>Why not buy a new awesome theme?</p>
										</a>
									</li>
									<!-- end message -->
									<!-- start message --> 
									<li>
										<a href="#">
											<div class="pull-left">
												<img src="${pageContext.request.contextPath }/resources/mainform/image/woman.png" class="img-circle" alt="User Image">
											</div>
											<h4>CompanyWare <small><i class="fa fa-clock-o"></i> 2hours</small></h4>
											<p>Why not buy a new awesome theme?</p>
										</a>
									</li>
									<!-- end message -->
								</ul>
								<!-- ul menu 끝 -->
							</li>
							<!-- li 몸통부분 body 끝 -->
							<!-- footer -->
							<li class="footer"><a href="#">See All Messages</a></li>
							<!-- footer end -->
						</ul>
					</li>
					<!-- 쪽지알림 끝 -->
					<!-- Notifications 드롭다운 메뉴-->
					<li class="dropdown notifications-menu">
						<!-- 시작 아이콘 및 라벨 -->
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<i class="fa fa-bell-o"></i> <span class="label label-warning">10</span>
						</a>
						<!-- ul body -->
						<ul class="dropdown-menu">
							<!-- header -->
							<li class="header">You have 10 notifications</li>
								<!-- body -->
								<li>
									<!-- menu body -->
									<ul class="menu">
										<li>
											<a href="#"> <i class="fa fa-users text-aqua"></i>5 new members joined today</a>
										</li>
										<li>
											<a href="#"> <i class="fa fa-warning text-yellow"></i>Very long description here that may not fit into the page</a>
										</li>
										<li>
											<a href="#"> <i class="fa fa-users text-red"></i>5 new members joined</a>
										</li>
										<li>
											<a href="#"> <i class="fa fa-shopping-cart text-green"></i> 25 sales made</a>
										</li>
										<li>
											<a href="#"> <i class="fa fa-user text-red"></i>You changed your username	</a>
										</li>
									</ul>
									<!-- menu body end -->
								</li>
								<!-- body end -->
							<!-- footer -->
							<li class="footer"><a href="#">View all</a></li>
						</ul>
						<!-- ul body end -->
					</li>
					<!-- Notifications 드롭다운 메뉴 끝 -->
					<!-- 업무진행도 드롭다운 메뉴 -->
					<li class="dropdown tasks-menu">
						<!-- 시작 아이콘 및 라벨 -->
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
							<i class="fa fa-flag-o"></i> <span class="label label-danger">9</span>
						</a>
						<!-- 드롭다운메뉴 -->
						<ul class="dropdown-menu">
							<!-- header -->
							<li class="header">You have 9 tasks</li>
							<!-- body -->
							<li>
								<!-- ul menu body -->
								<ul class="menu">
									<!-- item -->
									<li>
										<a href="#">
											<h3>Design some buttons <small class="pull-right">20%</small></h3>
											<div class="progress xs">
												<div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
													<span class="sr-only">20% Complete</span>
												</div>
											</div>
										</a>
									</li>
									<!-- end item -->
									<!-- item -->
									<li>
										<a href="#">
											<h3>Design some buttons <small class="pull-right">40%</small></h3>
											<div class="progress xs">
												<div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
													<span class="sr-only">40% Complete</span>
												</div>
											</div>
										</a>
									</li>
									<!-- end item -->
									<!-- item -->
									<li>
										<a href="#">
											<h3>Design some buttons <small class="pull-right">60%</small></h3>
											<div class="progress xs">
												<div class="progress-bar progress-bar-red" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
													<span class="sr-only">60% Complete</span>
												</div>
											</div>
										</a>
									</li>
									<!-- end item -->
									<!-- item -->
									<li>
										<a href="#">
											<h3>Design some buttons <small class="pull-right">80%</small></h3>
											<div class="progress xs">
												<div class="progress-bar progress-bar-yellow" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
													<span class="sr-only">80% Complete</span>
												</div>
											</div>
										</a>
									</li>
									<!-- end item -->
								</ul>
								<!-- ul menu body end -->
							</li>
							<!-- body end -->
							<li class="footer"><a href="#">View all tasks</a></li>
						</ul>
					</li>
					<!-- 업무진행도 드롭다운 끝 -->
					<!-- 유저 프로필 -->
					<li class="dropdown user user-menu">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<!-- 사이드바 프로필 사진 --> 
							<img src="${pageContext.request.contextPath }/resources/mainform/image/woman.png" class="user-image" alt="사이드바 유저이미지">
							<!-- 사이드바 유저 정보 --> 
							<span class="hidden-xs">${member.mem_nm }(${member.grad_nm })</span>
						</a>
						 <!-- 드롭다운 메뉴 -->
						<ul class="dropdown-menu">
							<!-- User image -->
							<li class="user-header">
								<!-- 상단헤더 유저 이미지 -->
								<img src="${pageContext.request.contextPath }/resources/mainform/image/woman.png" class="img-circle" alt="상단헤더 유저이미지">
								<!-- 상단헤더 유저 정보 -->
								<p>${member.mem_nm } (${member.clsf_nm}) <small>입사일${member.mem_date }</small></p>
							</li>
							<!-- 유저 프로필 footer 버튼 -->
							<li class="user-footer">
								<div class="pull-left">										
									<a href="javascript:location.replace('/ctw/lock/lockscreen')" class="btn btn-default btn-flat">
										<i class="fa fa-lock"></i>&nbsp;잠금
									</a>
								</div>
								<div class="pull-right">
									<a href="logout" class="btn btn-default btn-flat">
										<i class="fa fa-sign-out"></i>&nbsp;로그아웃
									</a>
								</div>
							</li>
							<!-- 유저 프로필 footer 버튼 끝 -->
						</ul>
						<!-- 드롭다운 메뉴 끝 -->
					</li>
					<!-- 유저 프로필 끝 -->
				</ul>
			</div>
		</nav>
	</header>

	<!-- 좌측 사이드바 -->
	<!-- Left side column. contains the logo and sidebar -->
	<aside class="main-sidebar">
		<!-- sidebar: style can be found in sidebar.less -->
		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					<img
						src="${pageContext.request.contextPath }/resources/mainform/image/woman.png"
						class="img-circle" alt="User Image">
				</div>
				<div class="pull-left info">
					<p>${member.mem_nm }(${member.grad_nm })</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>

			<!-- 사이드메뉴 -->
			<ul class="sidebar-menu">
				<li class="header"></li>

				<!-- 조직관리 -->
				<li class="treeview"><a href="#"> <i
						class="fa fa-list-alt"></i> <span>조직원관리</span> <span
						class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i></span>
				</a>
					<ul class="treeview-menu">
						<li class="active"><a
							href="/ctw/manager/organizationInsertForm"><i
								class="fa fa-circle-o"></i>조직원등록</a></li>
						<li><a href="/ctw/manager/memberList"><i
								class="fa fa-circle-o"></i>조직원현황</a></li>
					</ul></li>

				<li class="treeview"><a href="#"> <i class="fa fa-sitemap"></i>
						<span>조직도 보기</span> <span class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i></span>
				</a>
					<ul class="treeview-menu">
						<li><a href="/ctw/manager/orgChart"><i
								class="fa fa-circle-o"></i>조직도</a></li>
					</ul></li>


				<!-- 조직문서관리 -->
				<li class="treeview"><a href="#"> <i class="fa fa-book"></i>
						<span>조직문서관리</span> <span class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i></span>
				</a>
					<ul class="treeview-menu">
						<li class="active"><a href="/ctw/manager/managerdoc"><i
								class="fa fa-circle-o"></i>표준문서관리</a></li>
						<li><a href="/ctw/manager/trashcanCoList"><i
								class="fa fa-circle-o"></i>휴지통</a></li>
					</ul></li>


				<li class="treeview"><a href="#"> <i class="fa fa-book"></i>
						<span>부서문서관리</span> <span class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i></span>
				</a>
					<ul class="treeview-menu">
						<li class="active"><a href="/ctw/manager/departmentdoc"><i
								class="fa fa-circle-o"></i>표준문서관리</a></li>
						<li><a href="/ctw/manager/trashcanDeList"><i
								class="fa fa-circle-o"></i>휴지통</a></li>
					</ul></li>

				<!-- 거래처관리  -->
				<li class="treeview"><a href="#"> <i
						class="glyphicon glyphicon-tags"></i> <span>거래처관리</span> <span
						class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i></span>
				</a>
					<ul class="treeview-menu">
						<li class="active"><a href="/ctw/manager/buyer"><i
								class="fa fa-circle-o"></i>거래처 현황</a></li>
						<li><a href="/ctw/manager/debuyer"><i
								class="fa fa-circle-o"></i>거래처 요청승인</a></li>
					</ul></li>

				<!-- 일정관리 -->
				<li class="treeview"><a href="#"> <i
						class="fa fa-calendar"></i> <span>일정관리</span> <span
						class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i></span>
				</a>
					<ul class="treeview-menu">
						<li class="active"><a href="/ctw/manager/myschedule"><i
								class="fa fa-circle-o"></i>일정관리</a></li>

					</ul></li>

				<!-- 게시판관리 -->
				<li class="treeview">
					<a href="#"><i class="glyphicon glyphicon-blackboard"></i> 
						<span>게시판관리</span> <span class="pull-right-container"> 
						<i class="fa fa-angle-left pull-right"></i></span>
					</a>
					<ul class="treeview-menu">
						<li class="active" id="supermanagermenu">
							<!-- 최고관리자만 게시판을 관리가능  -->
						</li>
						<li>
						<a href="#"> <i
								class="glyphicon glyphicon-chevron-down"></i> <span>게시물관리</span>
								<span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span>
							</a>
							<ul class="treeview-menu" id="boardmanage">
								<!-- 게시판 관리 list(ajax) -->
							</ul>
						</li>
					</ul>
				</li>

				<!-- 온라인투표 -->
				<li class="treeview"><a href="#"> <i
						class="fa fa-check-circle"></i> <span>온라인투표</span> <span
						class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i></span>
				</a>
					<ul class="treeview-menu">
						<li class="active"><a href="/ctw/manager/vote"><i
								class="fa fa-circle-o"></i>투표참여</a></li>
						<li><a href="/ctw/manager/myvote"><i
								class="fa fa-circle-o"></i>나의투표함</a></li>
					</ul></li>

				<!-- 프로젝트 -->
				<li class="treeview"><a href="#"> <i class="fa fa-group"></i>
						<span>프로젝트관리</span>
				</a>
					<ul class="treeview-menu">
						<li><a href="/ctw/manager/project"><i
								class="fa fa-circle-o"></i></a></li>
						<li><a href="pages/layout/boxed.html"><i
								class="fa fa-circle-o"></i> Boxed</a></li>
						<li><a href="pages/layout/fixed.html"><i
								class="fa fa-circle-o"></i> Fixed</a></li>
						<li><a href="pages/layout/collapsed-sidebar.html"><i
								class="fa fa-circle-o"></i> Collapsed Sidebar</a></li>
					</ul></li>

				<!-- 메일 -->
				<li><a href="/ctw/manager/mail"> <i
						class="glyphicon glyphicon-envelope"></i> <span>메일</span> 
						<span class="pull-right-container"> <small class="label pull-right bg-green">new</small></a>
				</li>

				<!-- 쪽지 -->
				<li class="treeview"><a href="#"> <i
						class="fa fa-envelope"></i> <span>쪽지</span> <span
						class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i></span>
				</a>
					<ul class="treeview-menu">
						<li class="active"><a href="/ctw/manager/sendnote"><i
								class="fa fa-circle-o"></i>쪽지발신</a></li>
						<li><a href="/ctw/manager/receivenote"><i
								class="fa fa-circle-o"></i>쪽지수신</a></li>
					</ul></li>
			</ul>
		</section>
		<!-- /.sidebar -->
	</aside>
	<!-- ./좌측사이드바 -->
	<!-- Content Wrapper. Contains page content -->
	<!-- 내용 Main - sitemesh -->
	<div class="content-wrapper">
		<decorator:body />
	</div>
	<!-- /.content-wrapper -->

	<!-- footer main -->
	<footer class="main-footer">
		<div class="pull-right hidden-xs">
			<b>Version</b> 1.0
		</div>
		<strong>Copyright &copy; 2017 <a href="#">CTW(Company
				Where this ware)</a>
		</strong> all rights reserved.
	</footer>
	<div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
</body>
</html>