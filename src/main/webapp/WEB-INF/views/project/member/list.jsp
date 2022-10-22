<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<%--
*< 조직원 > 프로젝트 조회
* [[개정이력(Modification Information)]]
* 수정일        수정자       수정내용
* ----------  ---------  -----------------
* 2017. 5. 9.    박혜윤      최초작성
* Copyright (c) 2017 by CTW All right reserved
 --%>
<script	src="${pageContext.request.contextPath }/resources/project/js/custom.js"></script>
<section class="content-header">
	<h1>
	  프로젝트
	  <small>프로젝트목록</small>
	</h1>
	<ol class="breadcrumb">
	  <li><a href="#"><i class="fa fa-dashboard"></i>프로젝트</a></li>
	  <li class="active">프로젝트목록</li>

	</ol>
</section>
<section class="content">
	<div class="box">
		<div class="box-header">
			<input type="button" id="registerBtn" class="btn btn-info"  value="프로젝트생성" />
		</div>
		
	   <!-- /.box-header -->
	   <div class="box-body">
	     <div class="table-responsive">
	       <table class="table no-margin" id="maintable">
	         <thead>
	         <tr>
	           <th style="width:40%;">프로젝트명</th>
	           <th style="width:15%;">시작일</th>
	           <th style="width:15%;">팀장</th>
	           <th style="width:15%;">진행상태</th>
	           <th style="width:15%;">인원</th>
	         </tr>
	         </thead>
	         <tbody class="projectTb">
		         <c:forEach var="pro" items="${projectList}">
		         	
					<tr class="${pro.mem_id}" id="${pro.prjct_pk }">
					<td style="width:40%;">${pro.prjct_nm}</td>
					<fmt:parseDate var="listDate" value="${pro.prjct_begin }" pattern="yyyy-MM-dd" /> 
					<fmt:formatDate value="${listDate }" pattern="yyyy-MM-dd" var="projectDate"/>
					<td style="width:15%;">${projectDate}</td>
					
				    <td style="width:15%;">${pro.mem_nm}</td>
				    
				    <%--  팀장이냐 아니냐를 구분  --%>
				    <c:if test="${pro.mem_id == member.mem_id }">
					    <c:if test="${pro.progrs_fk == 1 }">
							<td style="width:15%;"><span class='label label-danger'>${pro.progrs_nm}</span></td>
						</c:if>
						<c:if test="${pro.progrs_fk == 2 }">
							<td style="width:15%;"><span class='label label-warning'>${pro.progrs_nm}</span></td>
						</c:if>
						<c:if test="${pro.progrs_fk == 3 }">
							<td style="width:15%;"><span class='label label-success'>${pro.progrs_nm}</span></td>
						</c:if>	
					</c:if>
					
					<%-- 팀장이 아닐시 나오는것  --%>
				    <c:if test="${pro.mem_id != member.mem_id }">
				        <c:if test="${pro.prjct_at == 'N' }">
							<td style="width:15%;"><span class='label label-warning'>참여대기</span></td>
						</c:if>
						<c:if test="${pro.prjct_at == 'Y'}">
							<td style="width:15%;"><span class='label label-success'>참여중</span></td>
						</c:if>	
				    </c:if>
					
					
					
					
					<td style="width:15%;">${pro.prjct_nmpr}</td>
					</tr>
				</c:forEach>
	         </tbody>
	       </table>
	     </div>
	     <!-- /.table-responsive -->
	   </div>
	   <!-- /.box-body -->
	   <div class="box-footer clearfix">
	   </div>
	   <!-- /.box-footer -->
	
	</div>
</section>
<div class="modal fade" id="projectInsert" role="dialog" data-backdrop="static" data-keyboard="false" >
    <div class="modal-dialog">
    
      <!-- Modal content-->
   
     <div class="box box-primary">
            <div class="box-header">
              <h3 class="box-title"><i class="glyphicon glyphicon-check"></i>&nbsp;프로젝트생성</h3>
                   <button type="button" class="close" onclick="Cancel()">
            <span aria-hidden="true">×</span></button>
            </div>
            <form action="projectInsert" method="POST" id="insert">
            <div class="box-body">
             	<div class="form-group sort"  style="width:100%;">
				    <label for="text" class="sort">제목 : &nbsp;</label>
				    <input type="text" class="form-control"  style="width:80%;" name="prjct_nm" id="" >
				  </div>
 				<div class="form-group sort" style="width:100%;">
				    <label for="text" class="sort">내용 : &nbsp;</label>
				      <textarea class="form-control" rows="7" id="" style="width:80%;"  name="prjct_cn" ></textarea>
				  </div>  


             <div class="form-group" style="width:30%;">
                 <label for="text"> 시작일 :</label>
						<div class="input-group date">
							<div class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</div>
							<input type="text" class="form-control pull-right datepicker" name="prjct_begin">
						</div>
					
					</div> 
             <div class="form-group" style="width:30%;">
                 <label for="text"> 종료일 :</label>
						<div class="input-group date">
							<div class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</div>
							<input type="text" class="form-control pull-right datepicker" name="prjct_end">
						</div>
					
					</div> 
					<select class="form-control dept" id="dept">
						
					</select>
				<div class="proMemTitle">
					<span>사원목록</span>
					 <div id='memList' class="projectMember">
					</div>
				</div>
					<div class="proMemTitle">
						<span >참여자목록</span>
						<div id='memList2' class="projectMember">
						</div>
					</div>
             <button type="button" class="btn btn-danger btnsort" onclick="Cancel();">취소</button>
             <button type="button" class="btn btn-info btnsort" onclick="projectSubmit();">생성</button>
				
              </div>
			</form>
              </div>
              <!-- /.form group -->
            </div>
            <!-- /.box-body -->
          </div>


<!-- 프로젝트 요청 Modal -->
  <div class="modal fade" id="acceptModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" id="prjct_nm">프로젝트 제목</h4>
        </div>
        <div class="modal-body">
        	<form id="acceptForm">
        		<input type="hidden" name="mem_id" value="${member.mem_id }" />
        		<input type="hidden" id="prjct_fk" name="prjct_fk" />
	        	<table id="modal_table">
	        	<tr>
	          		<th> 내용 </th>
	          		<td id="prjct_cn"></td>
	        	</tr>
	        	<tr>
	          		<th> 기간 </th>
	          		<td id="term"></td>
	        	</tr>
	        	<tr>
	          		<td colspan="2">
	          			 <button type="button" class="btn btn-danger" onclick="acceptCheck('Y')">수락</button>
	          			 <button type="button" class="btn btn-warning" onclick="acceptCheck('N')">거절</button>
	          		</td>
	        	</tr>
	          	</table>
          	</form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        </div>
      </div>
      
    </div>
  </div>
  <!-- 프로젝트 요청 Modal 끝 -->

<input type="hidden" name="mem_id" id="mem_id" value="${member.mem_id }">
<link	href="${pageContext.request.contextPath }/resources/project/css/custom.css"rel="stylesheet">





