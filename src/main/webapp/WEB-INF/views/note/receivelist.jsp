<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<section class="content-header">
	<h1>
		쪽지 <small>쪽지수신함</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>쪽지수신함</a></li>
		<li class="active">쪽지수신함</li>
	</ol>
</section>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/mss/mss.css" >
		 <script src="${pageContext.request.contextPath }/resources/mss/mss.js"></script>



<section class="content">
	<input type="hidden" name="mem_id" value="${member.mem_id }">
	<div class="box">
		<div class="box-header">
			<h3 class="box-title">수신목록</h3>
		</div>

		<!-- 게시판 목록 리스트 -->
			<input type="hidden" id="grad" value="${member.grad_pk }" >
			<div class="box-body">
			<table id="tb" class="table" style="text-align: center;">
		
				<thead style="text-align: center;">
					<tr style="text-align: center;">
						<th style="width:20%; text-align:center;">보낸사람</th>
						<th style="width:40%; text-align:center;">제목</th>
						<th style="width:20%; text-align:center;">보낸날짜</th>
						<th style="width:20%; text-align:center; ">읽음여부</th>
					</tr>
				</thead>
				<tbody id ="tbodylist" >
					<c:forEach var="mss" items="${mssList }" >
						<tr id="${mss.mss_pk }">
							<td style="width:20%;">${mss.mem_nm }(${mss.dept_nm },${mss.clsf_nm })</td>
							<td style="width:40%;">${mss.mss_sj }</td>
							<fmt:parseDate var="dateString" value="${mss.mss_date }" pattern="yyyy-MM-dd HH:mm:ss" />
							<fmt:formatDate value="${dateString }" type="time" var="bbsDate"/>
							<td style="width:20%;">${bbsDate }</td>
						<c:set var="check" value="${mss.mms_check }" />
							<c:choose>
							    <c:when test="${check eq 'Y'}">
									<td style="width:20%;"><b>읽음</b></td>
							    </c:when>
							    <c:otherwise>
									<td style="width:20%;"><b>읽지않음</b></td>
							    </c:otherwise>
							</c:choose>
							


							
						</tr>
						
					
					</c:forEach>
			

				</tbody>
					
			</table>
			</div>
			<input type="hidden" id="division" value="recp">
	</div>
</section>    

		
		<!-- 쪽지보내기 Modal -->
		<div class="modal fade" id="writeModal" role="dialog"  role="dialog" data-toggle="modal" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-lg">
	
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" onclick="modalCancel();">&times;</button>
						<h4 class="modal-title">쪽지작성</h4>
					</div>
					<div class="modal-body">
				<form action="mssInsert" id="insert" method="post" enctype="multipart/form-data">	 
              	<div class="form-group">
              		<label class="control-label col-sm-2" for="title">수신자 :</label>
              		<div class="col-sm-10">
						<input type="hidden" class="form-control" id="recp_id" name="recp_id">              		
						<input type="text" class="form-control" id="fakeId" name="" placeholder="수신자를 선택해주세요" readonly="readonly" >              		
              		</div>
              		<br>
              	</div>
              	<br>
              	<div class="form-group">
              		<label class="control-label col-sm-2" for="title">제목 :</label>
              		<div class="col-sm-10">
						<input type="text" class="form-control" id="" name="mss_sj" placeholder="Enter Title">              		
              			<br>
              		</div>
              	</div>
              	<br>
              	<div class="form-group">
              		<label class="control-label col-sm-2" for="mss_cn">내용 :</label>
              		<div class="col-sm-10">
						<textarea class="textarea"  name="mss_cn" placeholder="내용을 작성하세요" style="width:100%; height:200px; font-size:14px; line-height:18px; border:1px solid #dddddd; padding:10px;"></textarea>              		
              				<br><br>
              		</div>
              	</div>
              	<br>
              	<div class="form-group">
              		<label class="control-label col-sm-2" for="bbs_cn">파일첨부 :</label>
              		<div class="col-sm-10">
						<input type="file" class="filestyle" name="file">              		
              		</div>
              	</div>
              	 <br>
              		<div class="form-group">
              		<div class="col-sm-offset-2 col-sm-10">
						  <button type="button" onclick="modalCancel();" class="btn btn-default pull-rigth" ><span class='fa fa-fw fa-close' ></span>&nbsp;취소</button>
                		  <button type="button" class="btn btn-default pull-rigth" style="margin-rigth:1%;" id="insertBtn"><span class="glyphicon glyphicon-send"></span>&nbsp;발송</button>         		
              		</div>
              	</div>
              	</form>
					</div>
					<div class="modal-footer"></div>
				</div>

			</div>
		</div>
		
		
		
		
		
		

		<!-- 정보보기 modal -->
		<div class="modal fade" id="mssInfo" role="dialog"  role="dialog" data-toggle="modal" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-lg">
	
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" onclick="infoModalCancel();">&times;</button>
						<h4 class="modal-title">쪽지정보</h4>
					</div>
					<div class="modal-body info">
              	<div class="form-group">
                      	<label class="control-label col-sm-2" for="title" style="text-align: right;">발신자 :</label>
	              		<div class="col-sm-4 test" id="infoMem">
	              		</div>
             	 		 <label class="control-label col-sm-2" for="title" style="text-align: right;">날짜 :</label>
	              		<div class="col-sm-4 test" id="infoDate">
	              		</div>
              	</div>
              	<br>
              	<div class="form-group">
              		<label class="control-label col-sm-2" for="title" style="text-align: right;">제목 :</label>
              		<div class="col-sm-10 test" id="infoSj">
              			
              		</div>
              	</div>
              	<br>
              	<div class="form-group">
              		<label class="control-label col-sm-2" for="mss_cn" style="text-align: right;">내용 :</label>
              		<div class="col-sm-10 test" id="infoCn">
				
              		</div>
              	</div>
              	<hr>
              	<br>
              	<div class="form-group">
              		<label class="control-label col-sm-2" style="text-align: right;" >파일 :</label>
              		<div class="col-sm-10 test" id="infoFile">
					          		
              		</div>
              	</div>
              		
					</div>
					<div class="modal-footer">
						  <button type="button" onclick="infoModalCancel();" class="btn btn-default pull-rigth" ><span class='fa fa-fw fa-close' ></span>&nbsp;닫기</button>
						  <button type="button" onclick="mssDelete();" class="btn btn-default pull-rigth" ><span class='glyphicon glyphicon-trash' ></span>&nbsp;삭제</button>
					      <button type="button" class="btn btn-default pull-rigth" style="margin-rigth:1%;" onclick="insertRect();"><span class="glyphicon glyphicon-send"></span>&nbsp;답장</button>
					
					</div>
				</div>

			</div>
		</div>
		
		