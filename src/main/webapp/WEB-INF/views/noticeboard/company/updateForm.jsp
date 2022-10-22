<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<%--
*  회사 공지게시판
* [[개정이력(Modification Information)]]
* 수정일        수정자       수정내용
* ----------  ---------  -----------------
* 2017. 5. 11.   박혜윤      최초작성
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
			<h3 class="box-title">수정</h3>
		</div>
		<div class="box-body">
			<form action="companyUpdate" method="post">
				<input type="hidden" name="notice_pk" value="${vo.notice_pk}"/>
				<input type="hidden" name="mem_nm" value="${vo.mem_nm}"/>
				<input type="hidden" name="notice_date" value="${vo.notice_date}"/>
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
						<td><input type="text" class="form-control" value="${vo.notice_sj}" name="notice_sj"/></td>
					</tr>
					<tr>
						<th id="td">내용</th>
						<td>
						</td>
					</tr>
					<tr>
						<td colspan="2">
				            <div class="box-body pad">
				               <textarea class="textarea form-control" name="notice_cn" placeholder="내용을 작성하세요" style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;">${vo.notice_cn }</textarea>
				            </div>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="right">
							<input type="submit" class="btn btn-info" value="수정" />
							<a type="button" class="btn btn-default" href='notice'>취소</a>
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
$(function(){
	//bootstrap WYSIHTML5 - text editor
    $(".textarea").wysihtml5();
})
</script>