<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<!--
* @Description : 멤버 화면 조직도 출력 페이지
* @author 박창현 (mepch@naver.com)
* @version 1.0
* @since 2017.04.24
-->
<script src="${pageContext.request.contextPath}/resources/member/getorgchart/getorgchart.js"></script>
<link href="${pageContext.request.contextPath}/resources/member/getorgchart/getorgchart.css" rel="stylesheet" /> 
<link href="${pageContext.request.contextPath}/resources/member/css/orgChart.css" rel="stylesheet" /> 
<section class="content-header">
	<h1>
		My Page <small>조직도보기</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> My Page</a></li>
		<li class="active">조직도보기</li>
	</ol>
</section>
<!-- content -->
<section class="content">
	<!-- box -->
	<div class="box">
		<!-- body -->
		<div class="box-body">
		<!-- Org Chart API -->
		 <div id="people" style="height: 700px;"></div>		    
			<script src="${pageContext.request.contextPath}/resources/member/memberjs/memorgChart.js"></script>					
		</div>
		<!-- body -->
	</div>
	<!-- box -->
</section>
<!-- end content -->


   




 