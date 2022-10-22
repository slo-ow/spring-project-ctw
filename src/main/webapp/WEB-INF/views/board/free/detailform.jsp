<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<script type="text/javascript">

</script>

<style>
#td {
	text-align: center;
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
			<h3 class="box-title">자유게시판</h3>
		</div>
		<div class="box-body">
			<form action="freeUpdate" method="post">
				<input type="hidden" name="bbs_pk" value="${vo.bbs_pk}"/>
				<input type="hidden" name="mem_id" value="${vo.mem_id}"/> 
				<input type="hidden" name="bbs_date" value="${vo.bbs_date}"/> 
				<input type="hidden" name="bbs_sj" value="${vo.bbs_sj}"/> 
				<input type="hidden" name="bbs_cn" value="${vo.bbs_cn}"/>
				
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
						<td>${fn:substring(vo.bbs_date,0,10) }</td>
					</tr>
					<tr>
						<th id="td">제목</th>
						<td>${vo.bbs_sj}</td>
					</tr>
					<tr>
						<th id="td">첨부파일</th>
						<c:forEach var="file" items="${list}">
							<td>파일명 :<a href="download/freefiledown?bbs_f_pk=${file.bbs_f_pk }&real_name=${file.bbs_f_nm}"> ${file.bbs_f_nm }</a></td>
						</c:forEach>
					</tr>
					<tr>
						<th id="td">내용</th>
						<td></td>
					</tr>

					<tr>
						<td colspan="2">
							<div class="box-body pad">
								<div class="textarea" name="bbs_cn"
									style="width: 100%; height: 300px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"
									readonly>${vo.bbs_cn }</div>
							</div>
						</td>
					</tr>

					<tr>
						<td colspan="4" align="right">
						<input type="submit" class="btn btn-default" value="수정"> 
						<a type="button" class="btn btn-default" href='freeDelete?bbs_pk=${vo.bbs_pk}'>삭제</a>
						<a type="button" class="btn btn-default" href='free'>목록</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- box-body 끝 -->
		
	</div>
	<!-- box 끝 -->

</section>
