<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<script type="text/javascript">
$(function(){
	//bootstrap WYSIHTML5 - text editor
    $(".textarea").wysihtml5();
})
</script>

<style>
#td{
	text-align: center;
	width: 130px;
}
</style>
<section class="content-header">
	<h1>
		게시물관리 <small>자유게시판</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>게시물관리</a></li>
		<li class="active">자유게시판</li>
	</ol>
</section>
<section class="content">
	<div class="box">
	<!-- 자유게시판 상세보기 -->
		<div class="box-header">
			<h3 class="box-title">글 수정</h3>
		</div>
		<div class="box-body">
			<form action="fUpdate" method="post">
				<input type="hidden" name="bbs_pk" value="${vo.bbs_pk}">
				<input type="hidden" name="mem_id" value="${vo.bbs_pk}">
				<input type="hidden" name="bbs_date" value="${vo.bbs_date}">
				
				<table class="table table-condensed">
					<tr>
						<th id="td">번호</th>
						<td>${vo.bbs_pk}</td>
					</tr>
					<tr>
						<th id="td">작성자</th>
						<td>${vo.mem_id}</td>
					</tr>
					<tr>
						<th id="td">작성일</th>
						<td> ${fn:substring(vo.bbs_date,0,10) }</td>
					</tr>
					<tr>
						<th id="td">제목</th>
						<td><input type="text" name="bbs_sj" value="${vo.bbs_sj}" id="sj"></td>
					</tr>
					<tr>
						<th id="td">내용</th>
						<td>
						</td>
					</tr>
		
					<tr>
						<td colspan="2">
				            <div class="box-body pad">
				               <textarea class="textarea" name="bbs_cn" style="width: 100%; height: 300px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;" readonly>${vo.bbs_cn }</textarea>
				            </div>
						</td>
					</tr>
					
					<tr>
						<td colspan="4" align="right">
							<input type="submit" class="btn btn-default" value="수정"/>
							<button type="button" class="btn btn-default" href="free">목록</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- box-body 끝 -->
	</div>
	<!-- box 끝 -->
	
</section>
