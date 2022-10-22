<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>


<style>
#write {
	text-align: center;
}

#tableHeader {
	text-align: center;
}

#drop {
	border: 2px dotted #008b8b;
	width: 100%;
	height: 80px;
	color: #92AAB0;
	text-align: center;
	font-size: 24px;
	padding-top: 20px;
	margin-bottom: 20px;
}

.box{
 width : 100%;
 height : 750px;
}
#tb{
	width : 98%
}

#contentBoard tr:HOVER {
	background-color: #bacfef;
	cursor : pointer;
}

</style>
<script type='text/javascript'>

<%-- 게시물 작성 --%>
$(function(){
	
	$('#td').DataTable({
		"order": [[ 0, "desc" ]],
		"searching" : true,
		"oedering" : true,
		"autoWidth" : false,
		"pagingType" : "full_numbers",
		"scrolly": 500,
		"scrollCollapse" : false,
		"pageLength": 15,
		"language" : {
			"search" : "<span>검색</span> _ INPUT_", //search
			"lengthMenu" : '게시글 수 : <select>'
				+ '<option value="15">15</option>'
				+ '<option value="25">25</option>'
				+ '<option value="50">50</option>'
				+ '<option value="-1">All</option>'
				+ '<select>',
			"pahinate" : {
				"first" : "처음",
				"last" : "마지막",
				"previous" : "이전",
				"next" : "다음"
			},
			"zeroRecords" : "데이터가 없습니다",
			"info" : "총_TOTAL_건 중, _START_건부터_END_건까지 표시",
			"infoEmpty" : "0 건 중, 0부터 0까지 표시",
			"infoFiltered" : " (총 _MAX_건에서 추출)",
			"processing" : "처리중...",
			"loadingRecords" : "로딩중..."
		}
	
		
	})
});
	
function fileCheck(){
		
	if(writeForm.bbs_sj.value==""){
		alert("제목을 입력해주세요");
		writeForm.bbs_sj.focus();
		return;
	}
	if(writeForm.bbs_cn.value==""){
		alert("내용을 입력해주세요");
		writeForm.bbs_cn.focus();
	 	return;
	
	};
	
	
	
};
	$(function(){
		$("tbody").on("click", "tr", function(){
			var pk = $(this).attr('id');
			openModal(pk);
		});
		$(".textarea").wysihtml5();
		
		$("#tb").DataTable();
		
		$("#delete").click(function(){
			alert("게시글을 삭제합니다!" + ", " + $("#pk").val());
			location.href="freeDelete?bbs_pk="+$("#pk").val();
		});
	//게시글 상세보기
	$("#td").on("click", ".tr", function(){
		var bbs_pk = $("td:eq(0)",this).text().trim();
		$(location).attr('href', 'freeInfo?bbs_pk='+bbs_pk);
		
	});

});
	
	function boardcnt() {
		location.reload();
		
	}
	
</script>



<section class="content-header">
	<h1>
		커뮤니티게시판 <small>자유게시판</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>커뮤니티게시판</a></li>
		<li class="active">자유게시판</li>
	</ol>
</section>
<!-- 메인 -->
<section class="content">
	<div class="box">

	<div class="row">
		<div class="col-xs-12">
				<div class="box-header">
					<h3 class="box-title">자유게시판 <!-- 작성버튼 -->
		<a class="btn btn-info" data-toggle='modal' data-backdrop="static"
			data-keyboard="false" data-target='#write'>작성</a></h3>

			</div>
			
		</div>
	</div>
	<!-- 게시물 조회 -->
	<div class="row">
		<div class="col-xs-12" id='tableHeader'></div>
	</div>
	<!-- /.box-header -->
	<div class="box-body">
		<table class="table table-hover" id="td">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>	
					<th>조회수</th>
				</tr>
			</thead>
			<tbody id="contentBoard">
			<!-- 반복문으로 list뽑는곳 -->
			<c:forEach items="${list}" var="free">
				<!-- 게시물 상세보기 파라미터값 전송 -->
				<c:url value="freeInfo" var="update">
					<c:param name="bbs_pk" value="${free.bbs_pk }"/>
				</c:url>
				<tr class="tr">
					<td>${free.bbs_pk }</td>
					<td>${free.bbs_sj}</td>
					<td>${free.mem_id }</td>
					<fmt:formatDate value="${free.bbs_date }" pattern="yyyy-MM-dd" var="bbsDate"/>
					<td>${bbsDate }</td>
					<td>${free.bbs_cnt }</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div> 
	
		</div>
		
		<!-- Modal -->
        <div class="modal fade" id="write" role="dialog">
          <div class="modal-dialog modal-lg">
          	<!-- Modal content -->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" >자유게시글 작성</h4>
              </div>
              <div class="modal-body">
              
              
              <form class="form-horizontal" id="writeForm" action="freeinsert" method="post" enctype="multipart/form-data">
              
              
              	<div class="form-group">
              		<label class="control-label col-sm-2" for="title">제목 :</label>
              		<div class="col-sm-10">
						<input type="text" class="form-control" id="" name="bbs_sj" placeholder="Enter Title">              		
              		</div>
              	</div>
              	<div class="form-group">
              		<label class="control-label col-sm-2" for="bbs_cn">내용 :</label>
              		<div class="col-sm-10">
						<textarea class="textarea"  name="bbs_cn" placeholder="내용을 작성하세요" style="width:100%; height:200px; font-size:14px; line-height:18px; border:1px solid #dddddd; padding:10px;"></textarea>              		
              		</div>
              	</div>
              	<br>
              	<div class="form-group">
              		<label class="control-label col-sm-2" for="bbs_cn">파일첨부 :</label>
              		<div class="col-sm-10">
						<input type="file" class="filestyle" name="file">              		
              		</div>
              	</div>
              		<div class="form-group">
              		<div class="col-sm-offset-2 col-sm-10">
						  <button type="button" class="btn btn-default pull-rigth" data-dismiss="modal">Close <i class='fa fa-fw fa-close'></i></button>
                		  <button type="submit" class="btn btn-default pull-rigth" style="margin-rigth:1%;">Save <span class="glyphicon glyphicon-share"></span></button>         		
              		</div>
              	</div>

              	
			  </form>		

              </div>
              <div class="modal-footer"></div>
          </div>
        </div>
      </div>


	
	
</section>