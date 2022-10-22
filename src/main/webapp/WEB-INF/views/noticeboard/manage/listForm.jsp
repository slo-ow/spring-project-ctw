<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>

<%--
* @Description : 관리자의 게시판 관리 내역
* [[개정이력(Modification Information)]]
* 수정일        수정자       수정내용
* ----------  ---------  -----------------
* 2017. 4. 25.   박혜윤       최초작성
* 2017. 5. 01.   박혜윤       활성/비활성
* Copyright (c) 2017 by CTW All right reserved
 --%>
<script type="text/javascript">
	$(function() {
		

		//Flat red color scheme for iCheck
// 	    $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
// 	      checkboxClass: 'icheckbox_flat-green',
// 	      radioClass: 'iradio_flat-green'
// 	    });
		
		// checkbox 눌렀을 때 - 활성/비활성화 ajax boardActivationDele
		$("input[type='checkbox']").change(function(){
			var actvty_pk = $(this).attr("id");
			var actvty_nm = $(this).val();
			if(actvty_nm == 'Y'){
				actvty_nm = 'N'
			}else{
				actvty_nm = 'Y'
			}
			
			var params = 'actvty_pk='+actvty_pk+'&actvty_nm='+actvty_nm;
			 
	 		// 활성/비활성
			$.ajax({
				url : 'activation',
				type : 'post',
				data : params,
				dataType: 'text',
				success : function(res){
					swal("변경성공", "변경되었습니다.", "success");
				},
				error : function(){
					swal("실패", "등록되지않았습니다.", "error");	
				}
			});// ajax종료
		
		});
		

	});

</script>
<section class="content-header">
	<h1>
		게시판관리 <small>게시판관리</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="glyphicon glyphicon-blackboard"></i>
				게시판관리</a></li>
		<li class="active">게시판관리</li>
	</ol>
</section>
<section class="content">
			
	<div class="box">
		<div class="box-header">
			<h3 class="box-title">게시판 내역</h3>
		</div>
		<!-- /.box-header -->
		<div class="box-body">
		
		<input type="hidden" id="grad" value="${member.grad_pk }" >
		
			<c:choose>
			<c:when test="${member.grad_pk >= 9}">
				<table id="example2" class="table table-bordered">
					<thead>
						<tr>
							<th>게시판</th>
							<th>활성화</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>공지게시판</td>
							<td><input type="checkbox" id="notice" class="flat-red board" value="${list[0].actvty_nm }" <c:if test="${list[0].actvty_nm eq 'Y' }">checked</c:if> /></td>
						</tr>
						<tr>
							<td>자유게시판</td>
							<td><input type="checkbox" id="free" class="flat-red board" value="${list[2].actvty_nm }" <c:if test="${list[2].actvty_nm eq 'Y' }">checked</c:if>/></td>
						</tr>
						<tr>
							<td>갤러리게시판</td>
							<td><input type="checkbox" id="gallery" class="flat-red board" value="${list[1].actvty_nm }" <c:if test="${list[1].actvty_nm eq 'Y' }">checked</c:if>/></td>
						</tr>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<strong ><font color="navy">접근권한이 없습니다.</font></strong>
				<!-- alert 접근권한이 없습니다.  -->
			</c:otherwise>
			</c:choose>


		</div>
		<!-- /.box-body -->
	</div>
	<!-- /.box -->

</section>

