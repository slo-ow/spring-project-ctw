<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<script>
$(function(){
	
	// 투표오픈
	$(".openBtn").click(function(){
		
		var vote_pk = $(this).parent().attr("id");
		alert(vote_pk);
		var params = 'vote_pk='+vote_pk;
		
		$.ajax({
			url : 'voteOpen',
			type : 'get',
			data : params,
			dataType: 'text',
			seccess:function(res){
				alert("투표오픈이 되었습니다.");
			},
			error : function(){
				alert("오류발생");
			}
			
		});
		location.reload();
	});
	
	// 자신의 투표 삭제
	$(".delBtn").click(function(){
		var vote_pk = $(this).parent().attr("id");
		alert(vote_pk);
		var params = 'vote_pk='+vote_pk;
		
		$.ajax({
			url : 'voteDelete',
			type : 'get',
			data : params,
			dataType: 'text',
			seccess:function(res){
				
			}
			
		});
		
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
   온라인투표 <small>나의투표함</small>
</h1>
<ol class="breadcrumb">
   <li><a href="#"><i class="fa fa-dashboard"></i>온라인투표</a></li>
   <li class="active">나의투표함</li>
</ol>
</section>
<section class="content">
	<div class="box">
		<div class="box-header">
			<h3 class="box-title">나의투표함</h3>
		</div>
		<c:forEach var="vote" items="${votelist }">
		
			<input type="hidden" id="deptcode" value="${vote.dept_pk }">
			
			<div class = "contentlist">
				<img src="${pageContext.request.contextPath }/resources/mainform/image/voteicon.png">  <strong>no.${vote.vote_pk } ] ${vote.vote_sj }</strong><br>
					
						<div class="sub_content" style="color:#8C8C8C">
							<span class="ty1">전사 투표</span>
							<img src="${pageContext.request.contextPath }/resources/mainform/image/bar1.png" alt="">
							
							<span> 진행중 </span> <img src="${pageContext.request.contextPath }/resources/mainform/image/bar1.png" alt="">
							
							<span class="summaryViewInfo_name">진행자 : ${vote.mem_nm } </span>
							<img src="${pageContext.request.contextPath }/resources/mainform/image/bar1.png" alt="">
							
	<%-- 						<fmt:formatDate var="vdate" value="${vote.vote_end }" type="both" pattern="yyyy/MM/dd"/> --%>
							<span>투표마감기간 : ${fn:substring(vote.vote_end,0,10) }</span>
							
							<img src="${pageContext.request.contextPath }/resources/mainform/image/bar1.png" alt="">
							
							<span> 참여자 : 3명 </span>
						</div>
					
				
				
				<!-- 문항 -->
				<div id="${vote.vote_pk }" style="font-size: 12px;">
					<input type="hidden" id="vote_pk" value="${vote.vote_pk }">
					내용 : ${vote.vote_cn }<br><br>
					<c:forEach var="que" items="${vote.questions }" >
						(${que.choice_no }) ${que.choice_cn }
					</c:forEach>
					
					<hr color="gray">
					<c:if test="${vote.vote_sttus eq 'N'}">
						<input type="button" name="" class="openBtn btn btn-default" value="투표오픈">
					</c:if>
					<input type="button" name="" class="delBtn btn btn-default" value="삭제">
				</div>
				
			</div>
			<!-- /.contentlist -->
		</c:forEach>
	</div>
</section>
