<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
<!--
	* @Description : 멤버 메인 프레임
	* @author 문성철 (icet25@naver.com)
	* @version 1.0
	* @since 2017.04.26
-->
<head>
	<%@include file="head.jsp" %>
	<!-- mainframe에 관련한 공통 javascript -->
	<script	src="${pageContext.request.contextPath }/resources/mainform/js/custom.js"></script>
</head>
<style>
.glyphicon-tags:before{
	color: #483d8b;
}
.glyphicon-file:before{
	color: #6495ed;
}
.glyphicon-blackboard:before{
	color: #6b8e23;
}
.fa-check-circle:before{
	color: #800000;
}
.fa-user:before{
	color: #191970;
}
.fa-group:before{
	color: #778899;
}
.glyphicon-envelope:before{
	color: #191970;
}
.fa-envelope:before{
	color: #1e90ff;
}
</style>
<body class="hold-transition skin-green-light sidebar-mini">
<div class="wrapper">
	<header class="main-header">
		<!-- Logo -->
		<a href="/ctw/member/main" class="logo"> 
			<!-- mini logo for sidebar mini 50x50 pixels -->
			<span class="logo-mini"><img alt="CTW"
				src="${pageContext.request.contextPath }/resources/indexpage/assets/css/images/weblogo.png" width="45px" height="45px"></span>
			<!-- logo for regular state and mobile devices -->
			<span class="logo-lg"><img id="textLogo"
				src="${pageContext.request.contextPath }/resources/mainform/image/textLogo2.png"></span>
		</a>
		<!-- Header Navbar: style can be found in header.less --> 
		<nav class="navbar navbar-static-top">
		 <!-- Sidebar toggle button-->
			<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
			</a>
		<!-- 우측상단 쪽지, 메일, 알림 -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
			<!-- 쪽지알림: style can be found in dropdown.less-->
				<li class="dropdown messages-menu">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
							<i class="fa fa-envelope-o"></i> <span class="label label-success">4</span>
						</a>
						<ul class="dropdown-menu">
							<li class="header">You have 4 messages</li>
							<li>
								<!-- inner menu: contains the actual data -->
								<ul class="menu">
									<li>
										<!-- start message --> 
										<a href="#">
											<div class="pull-left">
												<img src="${pageContext.request.contextPath }/resources/mainform/image/woman.png"
													class="img-circle" alt="User Image">
											</div>
											<h4>Support Team <small><i class="fa fa-clock-o"></i> 5mins</small></h4>
											<p>Why not buy a new awesome theme?</p>
										</a>
									</li>
									<!-- end message -->
									<li>
										<a href="#">
											<div class="pull-left">
												<img src="${pageContext.request.contextPath }/resources/mainform/image/woman.png" class="img-circle" alt="User Image">
											</div>
											<h4>
												CompanyWare <small><i class="fa fa-clock-o"></i> 2hours</small>
											</h4>
											<p>Why not buy a new awesome theme?</p>
										</a>
									</li>
								</ul>
							</li>
							<li class="footer"><a href="#">See All Messages</a></li>
						</ul>
					</li>
					<!-- /.쪽지알림 -->
			<!-- Notifications: style can be found in dropdown.less -->
			<li class="dropdown notifications-menu"><a href="#"
				class="dropdown-toggle" data-toggle="dropdown"> <i
					class="fa fa-bell-o"></i> <span class="label label-warning">10</span>
			</a>
				<ul class="dropdown-menu">
					<li class="header">You have 10 notifications</li>
					<li>
						<!-- inner menu: contains the actual data -->
						<ul class="menu">
							<li><a href="#"> <i class="fa fa-users text-aqua"></i>
									5 new members joined today
							</a></li>
							<li><a href="#"> <i class="fa fa-warning text-yellow"></i>
									Very long description here that may not fit into the page and
									may cause design problems
							</a></li>
							<li><a href="#"> <i class="fa fa-users text-red"></i> 5
									new members joined
							</a></li>
							<li><a href="#"> <i
									class="fa fa-shopping-cart text-green"></i> 25 sales made
							</a></li>
							<li><a href="#"> <i class="fa fa-user text-red"></i>
									You changed your username
							</a></li>
						</ul>
					</li>
					<li class="footer"><a href="#">View all</a></li>
				</ul></li>
			<!-- 업무진행도 Tasks: style can be found in dropdown.less -->
			<li class="dropdown tasks-menu"><a href="#"
				class="dropdown-toggle" data-toggle="dropdown"> <i
					class="fa fa-flag-o"></i> <span class="label label-danger">9</span>
			</a>
				<ul class="dropdown-menu">
					<li class="header">You have 9 tasks</li>
					<li>
						<!-- inner menu: contains the actual data -->
						<ul class="menu">
							<li>
								<!-- Task item --> <a href="#">
									<h3>
										Design some buttons <small class="pull-right">20%</small>
									</h3>
									<div class="progress xs">
										<div class="progress-bar progress-bar-aqua"
											style="width: 20%" role="progressbar" aria-valuenow="20"
											aria-valuemin="0" aria-valuemax="100">
											<span class="sr-only">20% Complete</span>
										</div>
									</div>
							</a>
							</li>
							<!-- end task item -->
							<li>
								<!-- Task item --> <a href="#">
									<h3>
										Create a nice theme <small class="pull-right">40%</small>
									</h3>
									<div class="progress xs">
										<div class="progress-bar progress-bar-green"
											style="width: 40%" role="progressbar" aria-valuenow="20"
											aria-valuemin="0" aria-valuemax="100">
											<span class="sr-only">40% Complete</span>
										</div>
									</div>
							</a>
							</li>
							<!-- end task item -->
							<li>
								<!-- Task item --> <a href="#">
									<h3>
										Some task I need to do <small class="pull-right">60%</small>
									</h3>
									<div class="progress xs">
										<div class="progress-bar progress-bar-red" style="width: 60%"
											role="progressbar" aria-valuenow="20" aria-valuemin="0"
											aria-valuemax="100">
											<span class="sr-only">60% Complete</span>
										</div>
									</div>
							</a>
							</li>
							<!-- end task item -->
							<li>
								<!-- Task item --> <a href="#">
									<h3>
										Make beautiful transitions <small class="pull-right">80%</small>
									</h3>
									<div class="progress xs">
										<div class="progress-bar progress-bar-yellow"
											style="width: 80%" role="progressbar" aria-valuenow="20"
											aria-valuemin="0" aria-valuemax="100">
											<span class="sr-only">80% Complete</span>
										</div>
									</div>
							</a>
							</li>
							<!-- end task item -->
						</ul>
					</li>
					<li class="footer"><a href="#">View all tasks</a></li>
				</ul>
			</li>
			<!-- /.업무진행도 -->
			<!-- User Account: style can be found in dropdown.less -->
			<li class="dropdown user user-menu"><a href="#"
				class="dropdown-toggle" data-toggle="dropdown"> 
				<img src="${pageContext.request.contextPath }/resources/mainform/image/woman.png" class="user-image"
					alt="User Image"> <span class="hidden-xs">${member.mem_nm }(${member.dept_nm })
						</span>
			</a>
				<ul class="dropdown-menu">
					<!-- User image -->
					<li class="user-header"><img
						src="${pageContext.request.contextPath }/resources/mainform/image/woman.png" class="img-circle"
						alt="User Image">

						<p>
							${member.mem_nm } (${member.clsf_nm})
							<small>입사일 ${member.mem_date }</small>
						</p></li>
					<!-- 우측 상단 로그아웃 Footer-->
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
					<!-- /.우측 상단 로그아웃 Footer -->
				</ul>
			</li>
			<!-- Control Sidebar Toggle Button -->
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
					<img src="${pageContext.request.contextPath }/resources/mainform/image/woman.png" class="img-circle" alt="User Image">
				</div>
				<div class="pull-left info">
					<p>${member.mem_nm }(${member.clsf_nm })</p>
					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>

	<!-- 사이드메뉴 -->
	<ul class="sidebar-menu">
		<li class="header"></li>
		
		<!-- 개인거래처 -->
		<li class="treeview">
			<a href="#"> <i class="glyphicon glyphicon-tags"></i> 
				<span>부서거래처</span> <span class="pull-right-container"> 
				<i class="fa fa-angle-left pull-right"></i></span>
			</a>
			<ul class="treeview-menu">
				<li><a href="/ctw/member/buyer"><i
						class="fa fa-circle-o"></i>거래처 조회</a>
				</li>
				<li><a href="/ctw/member/buyerReq"><i
						class="fa fa-circle-o"></i>거래처 요청</a>
				</li>
			</ul>
		</li>
		
		<!-- 업무관련자료실 -->
		<li class="treeview">
			<a href="#"> <i class="glyphicon glyphicon-file"></i> 
				<span>업무관련자료실</span> <span class="pull-right-container"> 
				<i class="fa fa-angle-left pull-right"></i></span>
			</a>
			<ul class="treeview-menu">
				<li class="active">
					<a href="/ctw/member/managerdoc"><i class="fa fa-circle-o"></i>조직표준자료</a>
				</li>
				<li>
					<a href="/ctw/member/departmentdoc"><i class="fa fa-circle-o"></i>부서표준자료</a>
				</li>
				<li>
					<a href="/ctw/member/referenceroom"><i class="fa fa-circle-o"></i>공유자료실</a>
				</li>
			</ul>
		</li>
		
		<!-- 커뮤니티게시판 -->
		<li class="treeview">
			<a href="#"> <i class="glyphicon glyphicon-blackboard"></i> 
				<span>커뮤니티게시판</span> <span class="pull-right-container"> 
				<i class="fa fa-angle-left pull-right"></i></span>
			</a>
			<ul class="treeview-menu" id="boardmanage">
				<!-- 게시판활성내역리스트 -->
			</ul>
		</li>
		
		<!-- 온라인투표 -->
		<li class="treeview">
			<a href="#"> <i class="fa fa-check-circle"></i> 
				<span>온라인투표</span> <span class="pull-right-container"> 
				<i class="fa fa-angle-left pull-right"></i></span>
			</a>
			<ul class="treeview-menu">
				<li class="active">
					<a href="/ctw/member/vote"><i class="fa fa-circle-o"></i>투표참여</a>
				</li>
				<li>
					<a href="/ctw/member/myvote"><i class="fa fa-circle-o"></i>나의투표함</a>
				</li>
			</ul>
		</li>
		
		<!-- My Page  -->
		<li class="treeview">
			<a href="#"> <i class="fa fa-user"></i> 
				<span>My Page</span> <span class="pull-right-container"> 
				<i class="fa fa-angle-left pull-right"></i></span>
			</a>
			<ul class="treeview-menu">
				<li class="active">
					<a href="/ctw/member/myschedule"><i class="fa fa-circle-o"></i>일정관리</a>
				</li>
				<li>
					<a href="/ctw/member/mywork"><i class="fa fa-circle-o"></i>개인업무관리</a>
				</li>
				<li>
					<a href="/ctw/member/memberOrgChart"><i class="fa fa-circle-o"></i>조직도보기</a>
				</li>
			</ul>
		</li>
		
		<!-- 프로젝트 -->
		<li class="treeview"><a href="#"> <i class="fa fa-group"></i>
				<span>프로젝트</span> 
		</a>
			<ul class="treeview-menu">
				<li><a href="/ctw/member/project"><i class="fa fa-circle-o"></i>프로젝트</a></li>
			</ul>
		</li>
			
		<!-- 메일 -->
		<li>
			<a href="/ctw/member/mail"> <i class="glyphicon glyphicon-envelope"></i> <span>메일</span>
				<span class="pull-right-container"> 
				<small class="label pull-right bg-green">new</small>
			</a>
		</li>
		
		<!-- 쪽지 -->
		<li class="treeview">
			<a href="#"> <i class="fa fa-envelope"></i> 
				<span>쪽지</span> <span class="pull-right-container"> 
				<i class="fa fa-angle-left pull-right"></i></span>
			</a>
			<ul class="treeview-menu">
				<li class="active">
					<a href="/ctw/member/sendnote"><i class="fa fa-circle-o"></i>쪽지발신</a>
				</li>
				<li>
					<a href="/ctw/member/receivenote"><i class="fa fa-circle-o"></i>쪽지수신</a>
				</li>
			</ul>
		</li>
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
		<strong>Copyright &copy; 2017 <a
			href="#">CTW(Company Where this ware)</a>
		</strong> all rights reserved.
	</footer>

	<div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

</body>
</html>