<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<%--
*  부서 공지게시판
* [[개정이력(Modification Information)]]
* 수정일        수정자       수정내용
* ----------  ---------  -----------------
* 2017. 5. 8.   박혜윤      최초작성
* Copyright (c) 2017 by CTW All right reserved
 --%>
<style>
#td{
	text-align: center;
	width: 130px;
}
</style>
<section class="content-header">
	<h1>
		게시물관리 <small>공지사항게시판</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>게시물관리</a></li>
		<li class="active">공지사항게시판</li>
	</ol>
</section>
<section class="content">
	<div class="box">
		<div class="box-header">
			<h3 class="box-title">공지사항게시판</h3>
		</div>
		<div class="box-body">
			<form action="deptUpdateForm" method="post">
				<table class="table table-condensed">
					<tr>
						<td id="td">게시글 번호</td>
						<td>${vo.notice_pk}</td>
					</tr>
					<tr>
						<td id="td">작성자</td>
						<td>${vo.mem_nm}</td>
					</tr>
					<tr>
						<td id="td">작성일</td>
						<td> ${fn:substring(vo.notice_date,0,10) }</td>
					</tr>
					<tr>
						<td id="td">제목</td>
						<td>${vo.notice_sj}</td>
					</tr>
					<tr>
						<td id="td">내용</td>
						<td>
						</td>
					</tr>
					<tr>
						<td colspan="2">
				            <div class="box-body pad">
				                <pre><div class="textarea" name="notice_cn" style="width: 100%; height: 300px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;" readonly>${vo.notice_cn }</div></pre>
				            </div>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="right">
						<c:if test="${member.grad_pk >= 5 }">
							<input type="submit" class="btn btn-default" value="수정"/>
							<a type="button" class="btn btn-default" href='deptDel?notice_pk=${vo.notice_pk}'>삭제</a>
						</c:if>
							<a type="button" class="btn btn-default" href='notice'>목록</a>
						</td>
					</tr>
			
				</table>
			</form>
		</div>
		<!-- box-body 끝 -->
	</div>
	<!-- box 끝 -->
</section>
<script>
	
</script>