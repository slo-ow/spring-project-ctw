<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>   
<section class="content-header">
   <h1>
     일정관리
     <small>일정관리</small>
   </h1>
   <ol class="breadcrumb">
     <li><a href="#"><i class="fa fa-dashboard"></i> My page</a></li>
     <li class="active">일정관리</li>
   </ol>
</section>


<%-- 부서일정
<c:forEach items="${dlist }" var="fx">
${fx.fx_nm}<br>
${fx.fx_begin}<br>
${fx.fx_end}<br>
${fx.fx_memo}<br>
${fx.mem_id}<br>
</c:forEach>
 --%>
개인일정
<c:forEach items="${plist }" var="fx">
${fx.fx_nm}<br>
${fx.fx_begin}<br>
${fx.fx_end}<br>
${fx.fx_memo}<br>
${fx.mem_id}<br>
</c:forEach>

 
 <form action="personalInsert"  method="post">
  <div class="form-group" >
    <label for="fx_nm">title:</label>
    <input type="text" class="form-control" name="fx_nm">
  </div>
    <div class="form-group">
    <label for="fx_memo">memo:</label>
    <input type="text" class="form-control" name="fx_memo">
  </div>
     <input type="hidden" value="${member.mem_id }">
  <button type="submit" class="btn btn-default">Submit</button>
</form>
 
 
 