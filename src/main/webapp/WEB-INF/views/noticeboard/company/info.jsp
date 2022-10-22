<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<%--
*  회사 공지게시판
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
			<form action="companyUpdateForm" method="post">
				<input type="hidden" name="notice_pk" value="${vo.notice_pk}"/>
				<input type="hidden" name="mem_nm" value="${vo.mem_nm}"/>
				<input type="hidden" name="notice_date" value="${vo.notice_date}"/>
				<input type="hidden" name="notice_sj" value="${vo.notice_sj}"/>
				<input type="hidden" name="notice_cn" value="${vo.notice_cn}"/>
				
				<table class="table table-condensed">
					<tr>
						<th id="td">게시글 번호</th>
						<td>${vo.notice_pk}</td>
					</tr>
					<tr>
						<th id="td">작성자</th>
						<td>${vo.mem_nm}</td>
					</tr>
					<tr>
						<th id="td">작성일</th>
						<td> ${fn:substring(vo.notice_date,0,10) }</td>
					</tr>
					<tr>
						<th id="td">제목</th>
						<td>${vo.notice_sj}</td>
					</tr>
					<tr>
						<th id="td">내용</th>
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
						<c:if test="${member.grad_pk >= 9 }">
							<input type="submit" class="btn btn-info" value="수정"></a>
							<a type="button" class="btn btn-danger" href='companyDel?notice_pk=${vo.notice_pk}'>삭제</a>
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