<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<!--
	* @Description : 관리자 메인 페이지
	* @author 문성철 (icet25@naver.com)
	* @version 1.0
	* @since 2017.04.26
-->

<script type="text/javascript">
//동기화 버튼 누르면 실행되는 함수 정의
function sync(){
	alert('동기화');		
};
</script>
<style>
.btn-app{
    padding-bottom: 200px;
    padding-right: 125px;
    min-width: 250px;
    font-size: 15px;
    margin-top:10px;
}
.row{
	margin-right: -0px;
    margin-left: 10px;
}
.btn-app>.fa, .btn-app>.glyphicon, .btn-app>.ion{
    font-size: 80px;
    margin-top: 40px;
    margin-left: 70%;
    display: block;
}
.btn-app>.badge{
	font-size: 20px;
}
.btn-app{
    text-align: center;
}
.fa-list-alt:before{
	color:#708090;
}
.fa-refresh:before{
	color: #2f4f4f;
}
.fa-list-alt:before{
	color: #708090;
}
.fa-sitemap:before{
	color: #ff6347;
}
.fa-book:before{
	color: #6495ed;
}
.glyphicon-tags:before{
	color: #483d8b;
}
.glyphicon-blackboard:before{
	color: #6b8e23;
}
.fa-check-circle:before{
	color: #800000;
}
.fa-group:before, .fa-users:before{
	color: #778899;
}
.glyphicon-envelope:before{
	color: #191970;
}
.fa-envelope:before{
	color: #1e90ff;
}
.fa-calendar:before{
	color: #87cefa;
}
</style>
<section class="content">
  <!-- Default box -->
  <div class="box">
    <div class="box-header with-border">
      <!-- <div class="box-tools pull-right">
        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
          <i class="fa fa-minus"></i></button>        
      </div> -->
    </div>
    <!-- body 내용부분 -->
    <div class="box-body">
      <!-- select box UI 부분 -->
		<div class="box-body">
		<div class="row">
		  <div class="col-md-3">
			<a class="btn btn-app" href="/ctw/manager/memberList">
				<span class="badge bg-teal">4</span>
		    	<i class="fa fa-list-alt"></i> 조직원 관리
		  	</a>
		  </div>
		  <div class="col-md-3">
		  	<a class="btn btn-app" href="/ctw/manager/orgChart">
		  		<span class="badge bg-aqua">5</span>
		    	<i class="fa fa-sitemap"></i> 조직도관리
		  	</a>
		  </div>
		  <div class="col-md-3">
		  	<a class="btn btn-app" href="/ctw/manager/managerdoc">
		  		<span class="badge bg-red">2</span>
		    	<i class="fa fa-book"></i> 조직문서관리
		  	</a>	
		  </div>
		  <div class="col-md-3">
		  	<a class="btn btn-app" href="/ctw/manager/departmentdoc">
		  		<span class="badge bg-purple">3</span>
		    	<i class="fa fa-book"></i> 부서문서관리
		  	</a>
		  </div>
		</div>
		
		<div class="row">
		  <div class="col-md-3">
		  <a class="btn btn-app" href="/ctw/manager/debuyer">
		  	<span class="badge bg-yellow">5</span>
		    <i class="glyphicon glyphicon-tags"></i> 거래처관리&nbsp;
		  </a>
		  </div>
		  <div class="col-md-3">
		  <a class="btn btn-app" href="/ctw/manager/myschedule">
		    <span class="badge bg-green">3</span>
		    <i class="fa fa-calendar"></i> 일정관리
		  </a>
		  </div>
		  
		  <div class="col-md-3">
		    <a class="btn btn-app" href='/ctw/manager/manageList'>
		    <span class="badge bg-teal">10</span>
		    <i class="glyphicon glyphicon-blackboard"></i> 게시판관리
		  </a>
		  </div>
		  
		  <div class="col-md-3">
		  <a class="btn btn-app" href="/ctw/manager/vote">
		    <span class="badge bg-aqua">8</span>
		    <i class="fa fa-check-circle"></i> 온라인투표
		  </a>
		  </div>
		</div>
		<div class="row">
		  <div class="col-md-3">
		 	<a class="btn btn-app" href="/ctw/manager/project">
		    	<span class="badge bg-teal">5</span>
		    	<i class="fa fa-group"></i> 프로젝트관리
		  	</a>
		  </div>
		  <div class="col-md-3">
		  	<a class="btn btn-app" href="/ctw/manager/mail">
		    	<span class="badge bg-aqua">12</span>
		    	<i class="glyphicon glyphicon-envelope"></i> 메일
		  	</a>
		  </div>
		  <div class="col-md-3">
			<a class="btn btn-app custom-app"  href="/ctw/manager/receivenote">
				<span class="badge bg-red">5</span>
			 	<i class="fa fa-envelope"></i> 쪽지
			</a>
		  </div>
		   <div class="col-md-3">
			<a class="btn btn-app custom-app">
				<span class="badge bg-purple">1</span>
			 	<i class="fa fa-refresh" title="Ajax Request" onclick="sync();"></i> 동기화
			</a>
		  </div>
		</div>
	</div>
      <!-- select box UI 부분 끝 -->
      
    </div>
    <!-- body 내용부분 끝 -->
    <!-- footer 부분 -->
    <div class="box-footer">
    </div>
    <!-- footer 부분 끝 -->
  </div>
  <!-- Default box end -->

</section>

<!-- custom css -->
<style type="text/css">
<!-- custom css 정의 -->
@CHARSET "UTF-8";
.custom-app{
    min-width: 350px;
    height: 250px;
}
</style>