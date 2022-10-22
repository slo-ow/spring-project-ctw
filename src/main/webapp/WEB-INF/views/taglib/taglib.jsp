<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ taglib prefix="ajax" uri="http://ajaxtags.sourceforge.net/tags/ajaxtags" %>--%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"  prefix="decorator" %>

<!--
	* URL 요청시 세션값을 확인하고 세션이 없다면 페이지를 메인페이지로 리턴.
	* @author 문성철 (icet25@naver.com)
	* @version 1.0
	* @since 2017.04.24
-->
<c:choose>
    <c:when test="${member eq 'null'}">
        <%response.sendRedirect("../index/main"); %>
    </c:when>
    <c:when test="${empty member}">
        <%response.sendRedirect("../index/main"); %>
    </c:when>
</c:choose>