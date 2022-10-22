<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<!--
* @Description : 회사 공지사항 게시판의 게시물 리스트
* @author 박혜윤 (hyeonwjd@naver.com)
* @version 1.0
* @since 2017.04.25
-->
<script>
$(function(){
	
// 	$("#tb").DataTable({
// 		"ordering": false,
// 	});
	
		
	 $('#tb').DataTable({
	         "order": [[ 0, "desc" ]],
	         "searching" : true,
	         "ordering" : true,         
	         "autoWidth": false,
	         "pagingType": "full_numbers",
	         "scrollY": 500,
	         "scrollCollapse": false,
	         "pageLength": 15,
	         "language" : {
	            "search" : "<span>검색</span> _INPUT_", //search
	            "lengthMenu" : '게시글 수 : <select>'
	                  + '<option value="15">15</option>'
	                  + '<option value="25">25</option>'
	                  + '<option value="50">50</option>'
	                  + '<option value="-1">All</option>'
	                  + '</select>',
	            "paginate" : {
	               "first" : "처음",
	               "last" : "마지막",
	               "previous" : "이전",
	               "next" : "다음"
	            },
	            "zeroRecords" : "데이터가 없습니다.",
	            "info" : "총_TOTAL_건 중, _START_건부터_END_건까지 표시",
	           "infoEmpty" : " 0 건 중, 0 부터 0 까지 표시",
	           "infoFiltered" : "（총 _MAX_ 건에서 추출    ）",
	           "processing" : "처리중...",
	           "loadingRecords": "로딩중..."
	         }         
	     });
	   
	// 공지글 작성 submit
	$("#noticeInsertForm").submit(function() {

		if (noticeInsertForm.notice_sj.value == "") {
			swal("제목를 입력하세요","", "warning");
			noticeInsertForm.notice_sj.focus();
			return false;
		}

		if (noticeInsertForm.notice_cn.value == "") {
			swal("내용를 입력하세요","", "warning");
			noticeInsertForm.notice_cn.focus();
			return false;
		}
		
		var grad = $("#grad").val();
// 		alert(grad);
		 
		
		$(this).attr('action', 'companynoticeInsert');
		$(this).append('<input type="hidden" name="mem_id" value="${member.mem_id}"/>');
		
		
	});
	
	//bootstrap WYSIHTML5 - text editor
    $(".textarea").wysihtml5();
	
	// 상세보기
    $("#tb").on("click",".tr",function(){
    	  var notice_pk = $("td:eq(0)",this).text().trim();
//     	  alert(notice_pk);
    	  $(location).attr('href','companyInfo?notice_pk='+notice_pk);
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
			<div class="box-body">
			<table id="tb" class="table table-bordered table-striped">
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
				
				<c:if test="${empty companylist}">
					<tr align="center">
						<td colspan="5"><font color="navy">등록된 게시글이 없습니다.</font></td>
					</tr>
				</c:if>
				<c:if test="${not empty companylist }">
					<c:forEach var="list" items="${companylist }">
						<!-- 해당 부서만 조회 되어짐 -->
							<tr class="tr">
								<td>${list.notice_pk }</td>
								<td>${list.notice_sj }</td>
								<td>${list.mem_nm }</td>
								<td>${fn:substring(list.notice_date,0,10)}</td>
								<td>${list.notice_cnt}</td>
							</tr>
					</c:forEach>
				</c:if> 

				</tbody>
					
			</table>
			</div>
		
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
	</div>
</section>
