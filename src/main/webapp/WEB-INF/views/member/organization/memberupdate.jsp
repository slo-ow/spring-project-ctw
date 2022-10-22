<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--
* @Description : 조직원 수정 페이지
* @author 박창현 (mepch@naver.com)
* @version 1.0
* @since 2017.04.17
-->
<script>
	// Date Picker 옵션
	$(function() {
		//Date picker
		$('#member_date').datepicker({
			autoclose : true
		});
	});
	
	// 라디오버튼 값에서 선택한 값 가져오기
	var checked = $(":input:radio[name=mem_sex]:checked").val();
</script>

<section class="content-header">
	<h1>
		조직원관리 <small>정보변경</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 조직원관리</a></li>
		<li class="active">정보변경</li>
	</ol>
</section>

<!-- content -->
<section class="content">
	<!-- box -->
	<div class="box">
		<!-- body -->
		<div class="box-body">
			<div class="container"><br><br>
				<table>
				<tr>
					<td>
					<img alt="증명사진" src="../resources/member/orgImg/f-10.jpg" width="180px;" height="200px;">
					</td>
					<td style="vertical-align:bottom;" >&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary btn-sm btn-info">사진올리기</button></td>
				</tr>	
				</table>
				
				<br>
		
				<!-- 등록양식 Form -->
				<form action="memberUpdate" method="post">
					<div class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
						<!-- 사원ID 입력 -->
						<input id="member_code" type="text" class="form-control" value="${vo.mem_id }" name="mem_id" placeholder="사원코드" readonly="readonly">
					</div>
					
		 			<div class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
						<!-- 사원명 입력 -->
						<input type="text" class="form-control" value="${vo.mem_nm }" name="mem_nm" placeholder="사원명">
					</div><br>
					
					<div class="input-group">
						<span class="input-group-addon">비밀번호</span> 
						<!-- 비밀번호입력 -->
						<input type="password" class="form-control" value="${vo.mem_pass }" name="mem_pass" placeholder="비밀번호">
					</div>
		
					<div class="input-group">
						<span class="input-group-addon">전화번호</span> 
						<!-- 전화번호입력 -->
						<input type="text" class="form-control" value="${vo.mem_telno }" name="mem_telno" placeholder="전화번호">
					</div>
		
					<div class="input-group">
						<span class="input-group-addon">E-Mail</span> 
						<!-- E-mail입력 -->
						<input type="email" class="form-control" value="${vo.mem_email }" name="mem_email" placeholder="E-Mail">
					</div>
		
					<div class="input-group">
						<span class="input-group-addon">주 소</span> 
						<!-- 주소입력 -->
						<input type="text" class="form-control" value="${vo.mem_addr }" name="mem_addr" placeholder="주소">
					</div><br> 
					
					<label for="date">입사일:</label>
					<div class="form-group">
						<div class="input-group date">
							<div class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</div>
							<!-- 입사일 입력 DatePicker -->
							<input type="text" class="form-control pull-right" value="${vo.mem_date }" name="mem_date" id="member_date" readonly="readonly">
						</div>
					</div>
					
					<div class="form-group">
						<label for="pwd">생년월일:</label>
						<div class="form-group">
							<div class="input-group date">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<!-- 생년월일 입력 DatePicker -->
								<input type="text" class="form-control pull-right" name="mem_brthdy" value="${vo.mem_brthdy }" readonly="readonly">
							</div>
						</div>
					</div>
		 
		            <div class="form-group">
		            	<!-- 부서선택 SelectBox -->
		            	<label>부서선택 :</label>
		                  <select class="form-control" name="dept_pk" >
		                    <option value=1 <c:if test="${vo.dept_pk eq 1 }">selected</c:if>>개발부</option>
		                    <option value=2 <c:if test="${vo.dept_pk eq 2}">selected</c:if>>총무부</option>
		                    <option value=3 <c:if test="${vo.dept_pk eq 3 }">selected</c:if>>영업부</option>
		                    <option value=4 <c:if test="${vo.dept_pk eq 4 }">selected</c:if>>기획부</option>
		                  </select>
		                </div>
		
		             <div class="form-group" >
		             	<!-- 직급선택 SelectBox -->
		             	<label>직급선택 :</label>
		                  <select class="form-control" name="clsf_pk">
		                    <option value=1 <c:if test="${vo.clsf_pk eq 1 }">selected</c:if>>인턴</option>
		                    <option value=2 <c:if test="${vo.clsf_pk eq 2 }">selected</c:if>>사원</option>
		                    <option value=3 <c:if test="${vo.clsf_pk eq 3 }">selected</c:if>>대리</option>
		                    <option value=4 <c:if test="${vo.clsf_pk eq 4 }">selected</c:if>>과장</option>
		                    <option value=5 <c:if test="${vo.clsf_pk eq 5 }">selected</c:if>>부장</option>
		                    <option value=6 <c:if test="${vo.clsf_pk eq 6 }">selected</c:if>>이사</option>
		                    <option value=7 <c:if test="${vo.clsf_pk eq 7 }">selected</c:if>>사장</option>
		                  </select>
		             </div>
		                
		             <div class="form-group">
		             	<label>권한설정 :</label>
		                  <select class="form-control" name="grad_pk">
		                    <option value=1 <c:if test="${vo.grad_pk eq 1 }">selected</c:if>>사원</option>
		                    <option value=5 <c:if test="${vo.grad_pk eq 5 }">selected</c:if>>부서관리자</option>
		                    <option value=9 <c:if test="${vo.grad_pk eq 9 }">selected</c:if>>총괄관리자</option>
		                  </select>
		             </div><br>
					
					<div>
						<!-- 성별선택 RadioButton -->
						<label>성별선택 :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						<input type="radio" name="mem_sex" value="남성" style="width: 20px; height: 20px;">
								<img src="${pageContext.request.contextPath }/resources/member/image/man.png">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="mem_sex" value="여성" style="width: 20px; height: 20px;"> 
								<img src="${pageContext.request.contextPath }/resources/member/image/girl.png">
						</label>
					</div><br>
					
					<div style="text-align: center;">
						<input type="submit" class="btn btn-link" value="정보 수정하기"> 
						<input type="reset" class="btn btn-link" value="다시입력">
					</div>
				</form><br>
			</div>							
				</div>
				<!-- body -->
			</div>
			<!-- box -->
		</section>
		<!-- end content -->




