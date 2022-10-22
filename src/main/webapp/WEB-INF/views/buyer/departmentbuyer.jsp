<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<%--
* @Description : 거래처 요청 승인 게시판 (관리자) 
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

<%-- CSS --%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/buyer/css/department.css" />
<%-- Script --%>
<script src="${pageContext.request.contextPath }/resources/buyer/js/department.js"></script>

<section class="content-header">
	<h1>
	  거래처 요청승인
	</h1>
	<ol class="breadcrumb">
	  <li><a href="#"><i class="fa fa-dashboard"></i>거래처 관리</a></li>
	  <li class="active">거래처 요청승인</li>
	</ol>
</section>

<section class="content">
	<div class="box">  		
		<!-- box-body -->
		<div class="box-body">
			<!-- 테이블 body -->
			<table id="tb" class="table table-bordered table-striped">
				<thead>
					<tr>
					    <th>거래처 대표명</th>
					    <th>거래처 이름</th>
					    <th>거래처 연락처</th>
					    <th>거래처 주소</th>
					    <th>담당자 이름</th>
					    <th>담당자 직급</th>
					    <th>부서</th>
					    <th style="width: 40px">상태</th>
				  	</tr>
				</thead>
				<tbody>
				  	<c:forEach items="${deptlist }" var="dept">
				  	<tr class="getInfo" id="${dept.bcnc_pk }">
					    <td>${dept.bcnc_rprsntv }</td>
					    <td>${dept.bcnc_nm }</td>
					    <td>${dept.bcnc_telno }</td>
					    <td>${dept.bcnc_addr }</td>
					    <td>${dept.bcnc_mem_name }</td><!-- 담당자 이름 -->
					    <td>${dept.clsf_nm }</td><!-- 담당자 직급 -->
					    <td>${dept.dept_nm}</td><!-- 담당자 부서 -->
					   <!-- 상태를 확인 CONFM = 'N' 이라면 승인 대기상태임. -->
					   <c:choose>
					   		<c:when test="${dept.confm eq 'N'}">
					   			<td><span class="label label-warning">대기중</span></td>
					   		</c:when>
					   		<c:otherwise>
					   			<td><span class="label label-success">승인완료</span></td>
					   		</c:otherwise>
					   </c:choose>
				  	</tr>
				  	</c:forEach>
				</tbody>
			</table>
			<!-- 테이블 body End -->
		</div>
	</div>
	<!-- box-body End -->	
</section>
