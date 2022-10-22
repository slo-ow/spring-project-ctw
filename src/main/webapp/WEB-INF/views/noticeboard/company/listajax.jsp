<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<!--
* @Description : 회사 공지사항 게시판의 게시물 리스트 (ajax)
* @author 박혜윤 (hyeonwjd@naver.com)
* @version 1.0
* @since 2017.04.25
-->
<script>
$(function(){
	
	$.ajax({
		url : 'noticeList',
		type : 'post',
		dataType: 'json',
		success:function(data){
			var htmlCode = "";
			$.each(data,function(i,v){
				
				if(data==null){
					htmlCode += "<tr align='center'>";
					htmlCode += "<td colspan='5'><font color='navy'>등록된 게시글이 없습니다.</font></td>";
					htmlCode += "</tr>";
				}else{
					htmlCode += "<tr><td>"+v.notice_pk+"</td>";
					htmlCode += "<td>"+v.notice_sj+"</td>";
					htmlCode += "<td>"+v.mem_nm+"</td>";
					htmlCode += "<td>"+v.notice_date+"</td>";
					htmlCode += "<td>"+v.notice_cnt+"</td></tr>";
				}
			});
			
			$("#tbodylist").html(htmlCode);
			
		}, error : function(){
			alert("오류");		
		}
		
	});// ajax 종료
	
	// 공지글 작성 submit
	$("#noticeInsertForm").submit(function() {

		if (noticeInsertForm.notice_sj.value == "") {
			alert("제목를 입력하세요");
			noticeInsertForm.notice_sj.focus();
			return false;
		}

		if (noticeInsertForm.notice_cn.value == "") {
			alert("내용을 입력하세요");
			noticeInsertForm.notice_cn.focus();
			return false;
		}
		
		var grad = $("#grad").val();
// 		alert(grad);
		 
		
		$(this).attr('action', 'companynoticeInsert');
		$(this).append('<input type="hidden" name="mem_id" value="${member.mem_id}"/>');
	
		
	});
	
	
});
</script>
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
			<c:if test="${member.grad_pk >= 9 }">
				<input type="button" id="registerBtn" class="btn btn-default" data-toggle="modal" data-target="#writeModal" value="공지글 작성" />
			</c:if>
		</div>

		<!-- 게시판 목록 리스트 -->
			<input type="hidden" id="grad" value="${member.grad_pk }" >
		
			<table id="tb" class="table table-bordered">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody id ="tbodylist">
					<!-- 공지게시물 리스트 -->
				</tbody>
					
			</table>
		
		<!-- 공지글 작성 Modal -->
		<div class="modal fade" id="writeModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">공지글 작성</h4>
					</div>
					<div class="modal-body">
						<form action="" id="noticeInsertForm">
							<table class="table table-condensed">
								<tr>
									<td>제목</td>
									<td><input type="text" class="form-control" name="notice_sj" value="<공지> "/></td>
								</tr>
								<tr>
									<td>내용</td>
									<td><textarea rows="20" cols="50" class="form-control" id = "notice_cn" name="notice_cn" ></textarea></td>
								</tr>
								<tr>
									
									<td colspan="4" align="right">
										<input type="submit" class="btn btn-default" value="등록" />
										<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
									</td>
								</tr>

							</table>
						</form>
					</div>
					<div class="modal-footer"></div>
				</div>

			</div>
		</div>
	</div>
</section>
