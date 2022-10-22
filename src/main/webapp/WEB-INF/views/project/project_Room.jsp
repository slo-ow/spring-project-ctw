<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<%--
* 프로젝트 Room / 참여자 List / 개인 프로필
* [[개정이력(Modification Information)]]
* 수정일              수정자       수정내용
* ----------      ---------  -----------------
* 2017. 5. 11.   문성철      최초작성
* Copyright (c) 2017 by CTW All right reserved
 --%>
     <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.common-material.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.material.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.material.mobile.min.css" />
	<link rel="stylesheet" href="http://kendo.cdn.telerik.com/2017.1.223/styles/kendo.common.min.css">
	<link rel="stylesheet" href="http://kendo.cdn.telerik.com/2017.1.223/styles/kendo.rtl.min.css">
	<link rel="stylesheet" href="http://kendo.cdn.telerik.com/2017.1.223/styles/kendo.default.min.css">
	<link rel="stylesheet" href="http://kendo.cdn.telerik.com/2017.1.223/styles/kendo.mobile.all.min.css">
	<link rel="stylesheet" href="../lib/semantic.css">
	<!-- script -->
    <script src="http://code.jquery.com/jquery-1.12.3.min.js"></script>
    <script src="../lib/semantic.js"></script>
    <script src="http://kendo.cdn.telerik.com/2017.1.223/js/jszip.min.js"></script>
    <script src="http://kendo.cdn.telerik.com/2017.1.223/js/kendo.all.min.js"></script>
<script>
// 상단 메뉴바 제거, 사이드메뉴 접기
$(function(){
	   $(".sidebar-toggle").hide();
	   $("body").removeClass();
	   $("body").addClass("skin-green-light sidebar-mini sidebar-collapse");
		$("#spreadsheet").kendoSpreadsheet();
		spreadsheet = $("#spreadsheet").data("kendoSpreadsheet");
		$('.k-spreadsheet-toolbar:eq(0)').prepend('<button class="k-button k-button-icon" title="Cloud Save" style="visibility: visible;"><i class="cloud upload icon"></i></button>');
	   
})
$(function() {
	//엑셀 셋팅
});
</script>
<style>
// custom CSS
.widget-user-2 .widget-user-header {
    padding: 20px;
    height: 120px;
    border-top-right-radius: 0px;
    border-top-left-radius: 0px;
}
.widget-user-2 .widget-user-username, .widget-user-2 .widget-user-desc {
    margin-left: 75px;
    color: white;
}
.col-md-3 {
    width: 33.3333333333%;
}
.col-md-4 {
    width: 33.3333333333%;
}
.box-body2 {
    border-top-left-radius: 0;
    border-top-right-radius: 0;
    border-bottom-right-radius: 3px;
    border-bottom-left-radius: 3px;
    padding: 10px;
    height: 650px;
}
.box2 {
    position: relative;
    border-radius: 3px;
    background: #ffffff;
    border-top: 3px solid #d2d6de;
    margin-bottom: 0px;
    width: 102%;
    box-shadow: 0 1px 1px rgba(0,0,0,0.1);
}
.bg-custom{
	background-color: rgb(60, 141, 188) !important;
}
.box{
	margin-bottom: 5px;
}
</style>
	<div style="width: 70%; float:left;">
		<div id="spreadsheet" style="width:100%;height:900px;" onkeyup="ExcelTextsend();"></div>
	</div>
	
	<div style="width: 20%; float:left;">
		<div>
	        <input type="text" id="messageinput" />
	    </div>
	    <div>
	        <button type="button" onclick="openSocket();">Open</button>
	        <button type="button" onclick="send();">Send</button>
	        <button type="button" onclick="closeSocket();">Close</button>
	    </div>
	    <!-- Server responses get written here -->
	    <div id="messages"></div>
	</div>
    <!-- Script to utilise the WebSocket -->
    <script type="text/javascript">
        var messages = document.getElementById("messages");
       	var webSocket = new WebSocket("ws://192.168.207.26:8081/ctw/websocket");
        function openSocket() {
            // Ensures only one connection is open at a time
            if (webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED) {
                writeResponse("WebSocket is already opened.");
                return;
            }
            
            // Create a new instance of the websocket
    

            /**
             * Binds functions to the listeners for the websocket.
             */
            webSocket.onopen = function(event) {
                // For reasons I can't determine, onopen gets called twice
                // and the first time event.data is undefined.
                // Leave a comment if you know the answer.
                if (event.data === undefined)
                    return;

                writeResponse(event.data);
            };

            webSocket.onmessage = function(event) {
                writeResponse(event.data);
            };

            webSocket.onclose = function(event) {
                writeResponse("Connection closed");
            };
        }

        /**
         * Sends the value of the text input to the server
         */
        function send() {
            var text = document.getElementById("messageinput").value;
            webSocket.send(text);
        }

        function closeSocket() {
            webSocket.close();
        }

        function writeResponse(text) {
            messages.innerHTML += "<br/>" + text;
        }
    </script>
	
	
	
	
	
	
	
	
	
	
	
	<%-- 
 <div class="row" >
  <div class="col-md-8">
    <!-- Box Comment -->
    <div class="box2 box-widget">
      <div class="box-header with-border">
        <div class="user-block">
          <img class="img-circle" src="${pageContext.request.contextPath}/resources/dist/img/user1-128x128.jpg" alt="User Image">
          <span class="username"><a href="#">프로젝트 이름 들어오는부분</a></span>
          <span class="description">부서 - (프로젝트생성시간) 7:30 PM Today</span>
          <!-- aside menu 호출 버튼 -->
	      <!-- <button class="btn btn-default" data-toggle="control-sidebar">Toggle Right Sidebar</button> -->
	      <span class="description">
		      <a class="btn btn-app" data-toggle="control-sidebar">
		      	<i class="fa fa-pause"></i> Pause
		      </a>
	      </span>
        </div>
      </div>
      <!-- /.box-header -->
      <div class="box-body2">
        	<h1>여기에 채팅내용을 뿌려준다.</h1>
      </div>
      <!-- /.box-body -->
      <div class="box-footer box-comments">
        <div class="box-comment">
          <!-- User image -->
          <img class="img-circle img-sm" src="${pageContext.request.contextPath}/resources/dist/img/user3-128x128.jpg" alt="User Image">

          <div class="comment-text">
                <span class="username">
                  Maria Gonzales
                  <span class="text-muted pull-right">8:03 PM Today</span>
                </span><!-- /.username -->
            It is a long established fact that a reader will be distracted
            by the readable content of a page when looking at its layout.
          </div>
          <!-- /.comment-text -->
        </div>
        <!-- /.box-comment -->
        <div class="box-comment">
          <!-- User image -->
          <img class="img-circle img-sm" src="${pageContext.request.contextPath}/resources/dist/img/user4-128x128.jpg" alt="User Image">

          <div class="comment-text">
                <span class="username">
                  Luna Stark
                  <span class="text-muted pull-right">8:03 PM Today</span>
                </span><!-- /.username -->
            It is a long established fact that a reader will be distracted
            by the readable content of a page when looking at its layout.
          </div>
          <!-- /.comment-text -->
        </div>
        <!-- /.box-comment -->
      </div>
      <!-- /.box-footer -->
      <div class="box-footer">
              <form action="#" method="post">
                <div class="input-group">
                  <input type="text" name="message" placeholder="메세지를 이곳에 입력하세요" class="form-control">
                      <span class="input-group-btn">
                        <button type="submit" class="btn btn-primary btn-flat">전송</button>
                      </span>
                </div>
              </form>
            </div>
      <!-- /.box-footer -->
    </div>
    <!-- /.box -->
  </div>
  --%>
  <%-- 
<!-- 사용자의 미니 프로필 -->
<div class="col-md-4">
	<div class="box box-widget widget-user-2">
	  <div class="widget-user-header bg-custom">
	    <div class="widget-user-image">
	      <img class="img-circle" src="${pageContext.request.contextPath}/resources/dist/img/user7-128x128.jpg" alt="User Avatar">
	    </div>
	    <h3 class="widget-user-username">개발부/문성철</h3>
	    <h5 class="widget-user-desc">조직원/팀장/관리자</h5>
	  </div>
	  <div class="box-footer no-padding">
	    <ul class="nav nav-stacked">
	      <li><a href="#">접속중인 사원 <span class="pull-right badge bg-blue">31</span></a></li>
	      <li><a href="#">1:1 대화요청<span class="pull-right badge bg-aqua">5</span></a></li>
	      <li><a href="#">프로젝트에 초대하기<span class="pull-right badge bg-green">12</span></a></li>
	      <li><a href="#">Followers <span class="pull-right badge bg-red">842</span></a></li>
	    </ul>
	  </div>
	</div>
</div>
<!-- 사용자의 미니 프로필 끝 -->
 	
<!-- 현재 Room에 접속중인 인원들을 보여줄 List -->
<div class="col-md-3">
 <div class="box box-primary box-solid">
   <div class="box-header with-border">
     <h3 class="box-title">프로젝트 참여자 리스트</h3>
     <div class="box-tools pull-right">
       <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
       </button>
     </div>
   </div>
   <!-- body 시작 -->
   <div class="box-body" style="display: block;">
	   <select multiple class="form-control">
	   		<c:forEach begin="1" end="6">
	  			<option>option 1</option>
     		</c:forEach>
	     </select>     
   </div>
   <!-- body 끝 -->
 </div>
</div>
<!-- 현재 Room에 접속중인 인원들을 보여줄 List 끝 -->
</div>



<!-- The Right Sidebar -->
<aside class="control-sidebar control-sidebar-light">
  <!-- Content of the sidebar goes here -->
</aside>
<!-- The sidebar's background -->
<!-- This div must placed right after the sidebar for it to work-->
<div class="control-sidebar-bg"></div>  --%>