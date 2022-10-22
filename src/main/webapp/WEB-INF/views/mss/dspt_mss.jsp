<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
    
    
<section class="content-header">
	<h1>
		쪽지 <small>쪽지발신함</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>쪽지발신함</a></li>
		<li class="active">쪽지발신함</li>
	</ol>
</section>
<section class="content">
	<div class="box">
		<div class="box-header">
			<h3 class="box-title">발신목록</h3>
			<c:if test="${member.grad_pk >= 9 }">
				<input type="button" id="registerBtn" class="btn btn-default" data-toggle="modal" data-target="#writeModal" value="쪽지쓰기" />
			</c:if>
		</div>

		<!-- 게시판 목록 리스트 -->
			<input type="hidden" id="grad" value="${member.grad_pk }" >
			<div class="box-body">
			<table id="tb" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>받는사람</th>
						<th>제목</th>
						<th>보낸날짜</th>
						<th>읽음여부</th>
					</tr>
				</thead>
				<tbody id ="tbodylist">
				
			

				</tbody>
					
			</table>
			</div>

	</div>
</section>    


		
		<!-- 공지글 작성 Modal -->
		<div class="modal fade" id="writeModal" role="dialog">
			<div class="modal-dialog modal-lg">

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
									<td>
							            <div class="box-body pad">
							                <textarea class="textarea" name="notice_cn" placeholder="내용을 작성하세요" style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;">${vo.notice_cn }</textarea>
							            </div>
									</td>
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