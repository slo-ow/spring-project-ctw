<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<section class="content-header">
	<h1>
	  개인거래처
	  <small>개인거래처 조회</small>
	</h1>
	<ol class="breadcrumb">
	  <li><a href="#"><i class="fa fa-dashboard"></i> 개인거래처</a></li>
	  <li class="active">개인거래처조회</li>
	</ol>
</section>
<c:forEach items="${list }" var="a">
	${a.bcnc_nm }
	${a.bcnc_mem_name }
	${a.bcnc_addr }
	${a.bcnc_mem_telno }

</c:forEach>