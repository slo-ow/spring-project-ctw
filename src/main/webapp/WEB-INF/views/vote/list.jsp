<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<jsp:useBean id="now" class="java.util.Date" />
<%--
* 온라인투표 목록
* [[개정이력(Modification Information)]]
* 수정일        수정자       수정내용
* ----------  ---------  -----------------
* 2017. 04. ?.   박혜윤		최초작성
* Copyright (c) 2017 by CTW All right reserved
 --%>
<script>
	$(function() {
		
	
		var qsCount = 1;
		$(".vote")
				.on(
						"click",
						"#add",
						function() {
							var size = $(".input").length;
							if (size < 5) {
								var codeHTML;
								codeHTML = "<input type='text' class='input form-control qusinput' name='questions[" + qsCount + "].choice_cn' />";
								$("#addQ").append(codeHTML);
								qsCount++;
							} else {
								alert("문항은 5개까지 작성하실 수 있습니다.");
							}
						});

		$(".vote").on("click", "#del", function() {
			$("#addQ>input:last").remove();
			if (qsCount > 1)
				qsCount--;
		});

		//Date picker
		$('#datepicker').datepicker({
			yearRange : "2017:2030"
		});
		
		var dept = $("#deptcode").val();
		$("#deptRadio").val(dept);

		$("#testBtn").click(function(){
			alert(dept);
		});
		
		 //Flat red color scheme for iCheck
	    $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
	      checkboxClass: 'icheckbox_flat-green',
	      radioClass: 'iradio_flat-green'
	    });
		
		// 투표등록 submit
		$("#voteInsertForm").submit(function() {

			if (voteInsertForm.vote_sj.value == "") {
				swal("주제를 입력하세요","", "warning");
				voteInsertForm.vote_sj.focus();
				return false;
			}
			if (voteInsertForm.vote_cn.value == "") {
				swal("내용를 입력하세요","", "warning");
				voteInsertForm.vote_cn.focus();
				return false;
			}
			var qusInput = document.getElementsByClassName("qusinput");
			for (var i = 0; i < qusInput.length; i++) {
				if (qusInput[i].value == "") {
					swal("문항를 입력하세요","", "warning");
					qusInput[i].focus();
					return false;
				}
			}
			if (voteInsertForm.vote_end.value == "") {
				swal("기간를 입력하세요","", "warning");
				voteInsertForm.vote_end.focus();
				return false;
			}
			
			

			$(this).attr('action', 'voteInsert');
			$(this).append('<input type="hidden" name="mem_id" value="${member.mem_id}"/>');
		});

		// 상세보기
		$(function(){
			$(".voteBtn").click(function(){
				 var pk = $(this).parent().attr("id");
				 var vote_pk = parseInt(pk);
// 				 alert(vote_pk);
				
				 $(location).attr('href', "voteinfo?vote_pk="+pk);
							
			})
			
		})
		
	
	 
		 // 모달결과보기
 		$(".chartBtn").click(function(){
			
 			var vote_pk = $(this).parent().attr("id");
// 			alert(vote_pk);
			var params = 'vote_pk='+vote_pk;
			
			
			
			$.ajax({
				url : 'voteChartList',
				type : 'get',
				data : params,
				dataType: 'json',
				success:function(res){
					var cnt = 0;
					var element = {};
					var arr = [];
					$.each(res,function(i){
						
						element={
									label: res[i].choice_cn,	
									value: res[i].vote_count
						};
						
						arr[cnt]=element;
						cnt++;
						
						
					})
					
					Morris.Donut({
						element: 'vote_chart',
						resize: true,
						data: arr,
						hideHover: 'auto'
					})
					
					
				}
			});
			
			
			//모달 보이기
// 			$('#chartModal').modal('show');
			
 		});
		  
	//colors: ["#3c8dbc", "#f56954", "#00a65a","#7758A5","#F2CB61"],
		
		
	
		
		

	});
</script>
<style>
.contentlist{
 	clear: left;
	height: 80%;
	width: 100%;
	margin: 10px;
	padding: 5px;
	border-color: gray;
	border-style: outset;
}
</style>
<section class="content-header">
	<h1>
		온라인투표 <small>투표참여</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-check-circle"></i> 온라인투표</a></li>
		<li class="active">투표참여</li>
	</ol>
</section>

<section class="content">
	<div class="box">
		<div class="box-header" align="right">
			<input type="button" id="registerBtn" class="btn btn-info"
				data-toggle="modal" data-target="#voteModal" value="투표등록" />
		</div>
		
		<!-- 투표조회 -->
		<c:forEach var="vote" items="${votelist }">
		<!-- 투표오픈한 것만 조회 -->
		<c:if test="${vote.vote_sttus eq 'Y' }">
		
		<!-- 진행중인 투표 조회 -->
		<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" /> 
		<fmt:parseDate value="${vote.vote_end }" var="vdate" pattern="yyyy-MM-dd" scope="page"/>
		<fmt:formatDate value="${vdate }" pattern="yyyy-MM-dd" var="votedate"/>
		
		<c:if test="${today <= votedate }">
		
			<input type="hidden" id="deptcode" value="${vote.dept_pk }">
			<div class='col-md-6'>
				<div class = "contentlist ">
				<img src="${pageContext.request.contextPath }/resources/mainform/image/voteicon.png"> <strong>no.${vote.vote_pk } ] ${vote.vote_sj }</strong><br>
					
					<div class="sub_content" style="color:#8C8C8C;">
						<span class="ty1">전사 투표</span>
						<img src="${pageContext.request.contextPath }/resources/mainform/image/bar1.png" alt="">
						
						<span> 진행중 </span> <img src="${pageContext.request.contextPath }/resources/mainform/image/bar1.png" alt="">
						
						<span class="summaryViewInfo_name">진행자 : ${vote.mem_nm } </span>
						<img src="${pageContext.request.contextPath }/resources/mainform/image/bar1.png" alt="">
						
<%-- 						<fmt:formatDate var="vdate" value="${vote.vote_end }" type="both" pattern="yyyy/MM/dd"/> --%>
						<span>투표마감기간 : ${fn:substring(vote.vote_end,0,10) }</span>
						
						<img src="${pageContext.request.contextPath }/resources/mainform/image/bar1.png" alt="">
						
						<span> 참여자 : ${vote.mem_count} 명</span>
					</div>
					
				
				<!-- 문항 -->
				<div id="${vote.vote_pk }"  style=" font-size: 12px;">
					<input type="hidden" id="vote_pk" value="${vote.vote_pk }">
					내용 : ${vote.vote_cn }<br><br>
					<c:forEach var="que" items="${vote.questions }" >
						(${que.choice_no }) ${que.choice_cn }
					</c:forEach>
					
					<hr color="gray">

					<!-- 중복투표 방지 -->
					<c:set var="doneLoop" value="false"/> 
					<c:set var="cnt" value="0"/>
					<c:set var="totalcnt" value="0"/>
					
					<!-- votert.vote_fk  : 55 , 52 -->
					<c:forEach var="votert" items="${rtlist }">
					<c:set var="totalcnt" value="${totalcnt+1 }"/>
						<c:if test="${not doneLoop}">
							<c:choose>
							<c:when test="${vote.vote_pk ne votert.vote_fk}">
								<c:set var="cnt" value="${cnt+1 }"/>
								<c:set var="doneLoop" value="false"/>
							</c:when>
							<c:otherwise>
								<c:set var="doneLoop" value="true"/>
							</c:otherwise>
							</c:choose>
										
						</c:if>
					</c:forEach>
					<div align="center" id = "${vote.vote_pk }">
					<c:if test="${cnt eq totalcnt }">
						<input type="button" name="" class="voteBtn btn btn-default" value="투표하기">
					</c:if>
					<input type="button" id="chartBtn" class="chartBtn btn btn-default" data-toggle="modal" data-target="#chartModal" value="결과보기">
					</div>
				</div>
			</div>
			<!-- /.contentlist -->
			</div>
			<!--col끝  -->
			
		</c:if>
		<!-- 진행중인 -->
		</c:if>
		<!-- 투표오픈한것만 -->
			
		</c:forEach>

		

		


		<!-- /.box-header -->
		<div class="box-body no-padding">
			
		
		
		</div>
		<!-- /.box-body -->
	</div>
	<!-- /.box -->

	<!-- 투표 등록 Modal -->
	<div class="modal fade" id="voteModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">투표등록</h4>
				</div>
				<div class="modal-body">
					<form action="" id="voteInsertForm" method="post">
						<table class="table table-condensed">
							<tr>
								<td>주제</td>
								<td><input type="text" class="form-control" name="vote_sj" /></td>
							</tr>
							<tr>
								<td>내용</td>
								<td><input type="text" class="form-control" name="vote_cn" /></td>
							</tr>
							<tr>

								<td class="vote">문항 <a href="#" class="bbb btn btn-default"
									id="add">+</a> <a href="#" type="button"
									class="bbb btn btn-default" id="del">-</a>

								</td>
								<td class="vote">
									<div>
										<input type="text" class="form-control qusinput"
											name="questions[0].choice_cn" /> &nbsp;&nbsp;
									</div>
									<div id="addQ"></div>
								</td>
							</tr>
							<tr>
								<td>투표마감기간</td>
								<td>
									<!-- Date -->
									<div class="form-group">
										<div class="input-group date">
											<div class="input-group-addon">
												<i class="fa fa-calendar"></i>
											</div>
											<input type="text" class="form-control pull-right"
												id="datepicker" name="vote_end">
										</div>
										<!-- /.input group -->
									</div> <!-- /.form group -->
								</td>
							</tr>
							<tr>
								<td>참여설정</td>
								<td>전사 <input type="radio" class="flat-red" name="dept_pk" value="0" checked />&nbsp;&nbsp;&nbsp;
									부서 <input type="radio" class="flat-red" name="dept_pk" id="deptRadio" /></td>
							</tr>
							<tr>
								<td></td>
								<td align="right"><input type="submit" class="btn btn-default" value="등록" />
									<button type="button" class="btn btn-default"
										data-dismiss="modal">취소</button>
										</td>
								
							</tr>

						</table>
					</form>
				</div>
				<div class="modal-footer"></div>
			</div>

		</div>
	</div>





	<!-- 결과보기 Modal -->
	  <div class="modal fade" id="chartModal" role="dialog">
	    <div class="modal-dialog">
	    
	      <!-- Modal content-->
	      <div class="modal-content">
	        <div class="modal-header">
	          <h3 class="modal-title">투표 결과 보기</h3>
	        </div>
	        <div class="modal-body">
	              <!-- DONUT CHART -->
		          <div class="box box-danger">
		            <div class="box-header with-border">
		              <h3 class="box-title">결과보기</h3>
		
		            </div>
		            <div class="box-body chart-responsive">
		              <div class="chart" id="vote_chart" style="height: 300px; position: relative; width: 500px;"></div>
		            </div>
		            <!-- /.box-body -->
		          </div>
		          <!-- /.box -->
	          
	          
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
	        </div>
	      </div>
	      
	    </div>
	  </div>
	  
	  
</section>