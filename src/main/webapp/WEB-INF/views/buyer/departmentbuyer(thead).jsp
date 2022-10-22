<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<%--
* @Description : 거래처 요청 승인 게시판 (관리자) 
* @author 문성철
* @version 1.0
* @since 2017. 5. 2.
*
* [[개정이력(Modification Information)]]
*  수정일          수정자          수정내용
* ----------    ---------   -------------
* 2017. 5. 2.      작성자명      최초작성
* Copyright (c) 2017 by CTW All right reserved
 --%>
 
<style>
 	.table{
 		text-align: center;
 		cursor: pointer;
 	}
 	th{
 		text-align: center;
 		background-color: aliceblue;
 		position:sticky;
 		top: -1px;
 	} 	
 	tr:hover {
 		background-color: oldlace;
	}
	.box{
		height: 725px;
		position: relative;
	}
	.box-body{
		height: 725px;
		overflow: auto;
		margin-top : 0px;
		padding: 0px 5px 0px 0px;
	}	
</style>
<script>
	$()
</script>
<section class="content-header">
	<h1>
	  거래처 요청승인
	</h1>
	<ol class="breadcrumb">
	  <li><a href="#"><i class="fa fa-dashboard"></i>거래처 관리</a></li>
	  <li class="active">거래처 요청승인</li>
	</ol>
</section>

<section class="content">
	<div class="box">  
		<!-- box-body -->
		<div class="box-body">
			<!-- 테이블 body -->
			<table class="table table-bordered">
				<thead>
					<tr>
					    <th>거래처 대표명</th>
					    <th>거래처 이름</th>
					    <th>거래처 연락처</th>
					    <th>거래처 주소</th>
					    <th>담당자 이름</th>
					    <th>담당자 직급</th>
					    <th>부서</th>
					    <th style="width: 40px">상태</th>
				  	</tr>
				</thead>
				<tbody class="scrolltbdy">
				  	<c:forEach items="${deptlist }" var="dept">
				  	<tr class="getInfo" id="${dept.bcnc_pk }">
					    <td>${dept.bcnc_rprsntv }</td>
					    <td>${dept.bcnc_nm }</td>
					    <td>${dept.bcnc_telno }</td>
					    <td>${dept.bcnc_addr }</td>
					    <td>${dept.bcnc_mem_name }</td><!-- 담당자 이름 -->
					    <td>${dept.clsf_nm }</td><!-- 담당자 직급 -->
					    <td>${dept.dept_nm}</td><!-- 담당자 부서 -->
					   <!-- 상태를 확인 CONFM = 'N' 이라면 승인 대기상태임. -->
					   <c:choose>
					   		<c:when test="${dept.confm eq 'N'}">
					   			<td><span class="label label-warning">대기중</span></td>
					   		</c:when>
					   		<c:otherwise>
					   			<td><span class="label label-success">승인완료</span></td>
					   		</c:otherwise>
					   </c:choose>
				  	</tr>
				  	</c:forEach>
				</tbody>
			</table>
			<!-- 테이블 body End -->
		</div>
	</div>
	<!-- box-body End -->	
</section>
      
<script type="text/javascript">
$(function(){
	$("tbody").on("click",".getInfo",function(){
		console.log('거래처 클릭 / Ajax 요청 상세정보 가져옴');
		var bcnc_pk = $(this).attr('id');
		$.ajax({
		    	url:'buyerInfo',	
		    	type:'POST',
		    	dataType:'json',
		    	data:"pk="+bcnc_pk+"&division="+"dept",
		    	success:function(res){
		    		// 거래처 임시 글번호(기본키)로 상세정보를 모두받아옴
		    		// 함수를 새롭게 호출하면서 Object 를 넘겨준다.
		    		// 거래처 승인이 완료된 상태라면 함수를 호출 하지 않는다.
		    		var check = res.confm;
		    		if(check == 'Y'){
		    			swal("이미 승인된 거래처","", "warning");
		    		}else{
			    		insertBcnc(res);		    		
		    		}
		    	}
		 });
		
	})
})

// 임시거래처 글의 모든 정보를 넘겨받고 승인여부를 Alert로 판단하고 처리한다.
function insertBcnc(data){
	console.log('insertBcnc / 거래처 상세정보 데이터 넘겨받음');
	swal({
		  title: '거래처 요청 승인',
		  text: '개인 거래처 요청을 승인하시겠습니까?',
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#8cd4f5',
		  confirmButtonText: '승인',
		  cancelButtonText: '취소',
		}).then(
		  function(result) {
			  swal("승인완료", "개인 거래처 요청이 승인되었습니다.", "success");
		  }, function(dismiss) {
			  swal("취소", "개인 거래처 요청을 취소합니다.", "error");
		  }
	);
}
</script>



