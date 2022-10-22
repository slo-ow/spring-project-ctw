<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%--
* @Description 조직원 현황을 게시판 형태로 보여주는 ViewPage
* @author 박창현
* @version 1.0
* @since 2017. 4. 28.
*
* [[개정이력(Modification Information)]]
*  수정일        수정자       수정내용
* ----------    ---------   -------------
* 2017. 4. 28.   박창현       조직원수정링크지정
* Copyright (c) 2017 by CTW All right reserved
 --%>
 
<style>
 	.table{
 		text-align: center;
 		cursor: pointer;
 	}
 	th{
 		text-align: center;
 	} 	
 	tr:hover {
 		background-color: oldlace;
	}
	.box{
		height: 725px;	
	}
	}
</style>  
 
<script type="text/javascript">

// 테이블 설정
$(function(){
	   $('#memberTable').DataTable({
	         "order": [[ 0, "desc" ]],
	         "searching" : true,
	         "ordering" : true,        
	         "autoWidth" : false,
	         "pagingType": "full_numbers",
	         "scrollY": 580,
	         "scrollCollapse": true,
	         "pageLength": 10,
	         "language" : {
	            "search" : "<span>검색</span> _INPUT_", //search
	            "lengthMenu" : '게시글 수 : <select>'
	                  + '<option value="10">10</option>'
	                  + '<option value="20">20</option>'
	                  + '<option value="30">30</option>'
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
	})

	

	// Ajax방식을 이용하여, Controller에 접근, 데이터를 Select한 후에 
	// Modal창을 띄우는 함수
	function openModal(id) {
		$.ajax({
			type : 'get',
			url : 'selectMember',
			data : {
				"id" : id
			},
			success : function(data) {
				$("#id").val(data.mem_id);
				$("#name").val(data.mem_nm);
				$("#dept").val(data.dept_pk);
				$("#clsf").val(data.clsf_pk);
				$("#telno").val(data.mem_telno);
				$("#date").val(data.mem_date);
				$("#brthdy").val(data.mem_brthdy);
				$("#mail").val(data.mem_email);
				$("#addr").val(data.mem_addr);
				$("#sex").val(data.mem_sex);
				$("#grad").val(data.grad_pk);

				$("#viewModal").modal('show');
			},
			error : function() {
				alert("오류가 발생했습니다.");
			},
			dataType : 'json'
		});
	}
	
	// Update Controller로 보내는 함수
	$(function(){
		$("#selectBtn").click(function(){
		var mem_id = $("#id").val();
		//alert(mem_id);
		location.href="selectUpdate?mem_id="+mem_id;
		});
	})
</script> 
 
 
 
<section class="content-header">
	<h1>
		조직원관리 <small>조직원현황</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 조직원관리</a></li>
		<li class="active">조직원현황</li>
	</ol>
</section>
<!-- content -->
<section class="content">
	<!-- box -->
	<div class="box">
		<!-- Content body -->
		<div class="box-body">
	<h3 class="box-title">조직원 현황</h3>

	<div class="box-body table-responsive no-padding">
		<table id="memberTable" class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>사원코드</th>
					<th>사원명</th>
					<th>부서</th>
					<th>직급</th>
					<th>전화번호</th>
					<th>입사일</th>
					<th>생년월일</th>
					<th>E-mail</th>
					<th>주소</th>
					<th>성별</th>	
				</tr>
			</thead>
			<tbody>
				<!-- Admin_MemberController에서 Select쿼리를 진행한 결과를 ${list}로 담아와서 member변수로 접근 -->
				<c:forEach items="${list }" var="member">
					<tr>
						<td><a href="#" onclick="openModal('${member.mem_id}');">${member.mem_id }</a></td>
						<td>${member.mem_nm }</td>
						<td>${member.dept_nm }</td>
						<td>${member.clsf_nm}</td>
						<td>${member.mem_telno }</td>
						<td>${member.mem_date }</td>
						<td>${member.mem_brthdy }</td>
						<td>${member.mem_email }</td>
						<td>${member.mem_addr }</td>
						<td>${member.mem_sex }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- /.box-body -->

	<!-- 정보 상세보기 Modal -->
	<div class="modal fade" id="viewModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">조직원 정보</h4>
				</div>
				<form action="selectUpdate" method="get">
				<div class="modal-body">
					<div class="form-group has-success">
						<label class="control-label" for="inputSuccess"><i class="fa fa-check"></i> 조직원 상세정보</label> 
						<table style="text-align: center;">
							<tr>
								<td>사진 : &nbsp;&nbsp;</td>
								<td><input type="hidden" class="form-control" id="photo" name="mem_photo" readonly="readonly"></td>
							</tr>						
							<tr>
								<td>사원코드 : &nbsp;&nbsp;</td>
								<td><input type="text" class="form-control" id="id" name="mem_id" readonly="readonly"></td>
							</tr>							
							<tr>
								<td>사원명 : &nbsp;&nbsp;</td>
								<td><input type="text" class="form-control" id="name" name="mem_nm" readonly="readonly"> </td>
							</tr>	
							<tr>
								<td>부서 : &nbsp;&nbsp;</td>
								<td><input type="text" class="form-control" id="dept" name="dept_pk" readonly="readonly"> </td>
							</tr>
							<tr>
								<td>직급 : &nbsp;&nbsp;</td>
								<td><input type="text" class="form-control" id="clsf" name="clsf_pk" readonly="readonly"> </td>
							</tr>
							<tr>
								<td>전화번호 : &nbsp;&nbsp;</td>
								<td><input type="text" class="form-control" id="telno" name="mem_telno" readonly="readonly"> </td>
							</tr>
							<tr>
								<td>입사일 : &nbsp;&nbsp;</td>
								<td><input type="text" class="form-control" id="date" name="mem_date" readonly="readonly"> </td>
							</tr>
							<tr>
								<td>생년월일 : &nbsp;&nbsp;</td>
								<td><input type="text" class="form-control" id="brthdy" name="mem_brthdy" readonly="readonly"> </td>
							</tr>
							<tr>
								<td>E-Mail : &nbsp;&nbsp;</td>
								<td><input type="text" class="form-control" id="mail" name="mem_email" readonly="readonly"> </td>
							</tr>							
							<tr>
								<td>주소 : &nbsp;&nbsp;</td>
								<td><input type="text" class="form-control" id="addr" name="mem_addr" readonly="readonly"> </td>
							</tr>
							<tr>
								<td>성별 : &nbsp;&nbsp;</td>
								<td><input type="text" class="form-control" id="sex" name="mem_sex" readonly="readonly"> </td>
							</tr>
							<tr>
								<td><input type="hidden" class="form-control" id="grad" name="grad_pk" readonly="readonly"> </td>
							</tr>																																																															
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" id="selectBtn">정보수정</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
				 </form>
			</div>
         </div> 
	</div><!-- End of Modal -->
		          
		</div>
		<!-- End of Content body -->
	</div>
	<!-- End of box -->
</section>
<!-- End content -->
