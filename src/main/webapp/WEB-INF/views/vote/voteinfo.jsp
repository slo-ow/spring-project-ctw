<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<script>
$(function(){
	 
	$("#choiceForm").submit(function(){
		var choice_no = $('[name="choice"]:checked').attr('name');
		if (choice_no == null) {
			alert("문항을 선택해주세요.");
			return;
		}
		
		var checkedValue = $("input[type=radio][name=choice]:checked").val();
//		alert(checkedValue);
		
		$(this).attr('action','voteChoiceInsert?choice_fk='+checkedValue);
		$(this).append('<input type="hidden" name="vote_pk" value="${vote.vote_pk}"/>');
		$(this).append('<input type="hidden" name="mem_id" value="${member.mem_id}"/>');
	});
	
	
});



</script>
<style>
.contentlist{
 	clear: left;
	height: 80%;
	width: 100%;
	margin-right: 10px;
	padding: 5px;
	border-width: 1px;
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
		<div class="box-header">
			<h3 class="box-title">투표목록</h3>
		</div>

		<form action="" id="choiceForm" method="post" >
			<div class = "contentlist">
				<img src="${pageContext.request.contextPath }/resources/mainform/image/voteicon.png">  <strong>no.${vote.vote_pk } ] ${vote.vote_sj }</strong><br>
						
					<div class="sub_content" style="color:#8C8C8C">
						<span class="ty1">전사 투표</span>
						<img src="${pageContext.request.contextPath }/resources/mainform/image/bar1.png" alt="">
						
						<span> 진행중 </span> <img src="${pageContext.request.contextPath }/resources/mainform/image/bar1.png" alt="">
						
						<span class="summaryViewInfo_name">진행자 : ${vote.mem_nm } </span>
						<img src="${pageContext.request.contextPath }/resources/mainform/image/bar1.png" alt="">
						
						<span>투표마감기간 : ${vote.vote_end }</span>
						
						<img src="${pageContext.request.contextPath }/resources/mainform/image/bar1.png" alt="">
						
						<span> 참여자 : 3명 </span>
					</div>
					
				
				<!-- 문항 -->
				<div id="${vote.vote_pk }" style="font-size: 14px;">
					
					<c:forEach var="que" items="${vote.questions }" >
						  <input type="radio" name="choice" value="${que.choice_pk }">
						(${que.choice_no }) ${que.choice_cn }<br>
					</c:forEach>
				</div>

					<input type="submit" class="voteBtn btn btn default" value="투표하기" >
					
			</div>
			<!-- /.contentlist -->
		</form>
		


		<!-- /.box-header -->
		<div class="box-body no-padding"></div>
		<!-- /.box-body -->
	</div>
	<!-- /.box -->


</section>