<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<%--
* @Description 
* @author 김완수
* @version 1.0
* @since 2017.05.02 
*
* [[개정이력(Modification Information)]]
*  수정일          		수정자          수정내용
* ----------    		---------   -------------
* 2017.05.02        김완수		     최초작성
* Copyright (c) 2017 by CTW All right reserved
--%>
<section class="content-header">
	<h1>
	  메일
	  <small>메일</small>
	</h1>
	<ol class="breadcrumb">
	  <li><a href="#"><i class="fa fa-dashboard"></i> 메일</a></li>
	  <li class="active">메일</li>
	</ol>
</section>
<style>
#fileappend .bootstrap-filestyle{
margin-bottom: 5px;	
width: 100%;


}
.bootstrap-filestyle{
margin-bottom: 5px;	
width: 50%;

}
.form-horizontal{
	width: 70%;
	margin: 0 auto;
	margin-top: 10%;
}
.box{
	padding-bottom: 10%;
}
</style>

<script type="text/javascript">
$(function(){
 	$("#mailsend").click(function(){
		 var params = $("#mailform").serialize();
		
		   $.ajax({
		    	url:'mail/mailSending',	
		    	type:'POST',
		    	dataType:'json',
		    	data:params,
		    	success:function(res){
		    		if(res.check == '1'){
		    			swal("메일발송 성공", "메일 발송이 완료되었습니다.", "success");
		    		
    				  //방법2 - 문서에 있는 모든 form을 리셋
    				  $('form').each(function(){
    				    this.reset();
    				  });
		    		
		    		}else{
		    			swal({
		    				  title: "메일발송 실패",
		    				  text: "메일을 다시 한번 확인해주세요",
		    				  type: "error",
		    				  confirmButtonText: "닫기"
		    			});			 
		    		 $('#tomail').val("");
		    		 $('#tomail').focus();
		    		 	
		    		}// if문 종료
		    	}// success 종료
		   });// ajax 종료
		
	})// function종료
	

	
})

</script>
<!--  <form>
        송신 메시지 작성하는 창
        <input id="textMessage" type="text">
        송신 버튼
        <input onclick="sendMessage()" value="Send" type="button">
        종료 버튼
        <input onclick="disconnect()" value="Disconnect" type="button">
    </form>
    <br />
    결과 메시지 보여주는 창
    <textarea id="messageTextArea" rows="10" cols="50"></textarea> -->
     
    <script type="text/javascript">
       /*  //WebSocketEx는 프로젝트 이름
        //websocket 클래스 이름
        var webSocket = new WebSocket("ws:192.168.207.26:8081/ctw/websocket");
        var messageTextArea = document.getElementById("messageTextArea");
        //웹 소켓이 연결되었을 때 호출되는 이벤트
        webSocket.onopen = function(message){
            messageTextArea.value += "Server connect...\n";
        };
        //웹 소켓이 닫혔을 때 호출되는 이벤트
        webSocket.onclose = function(message){
            messageTextArea.value += "Server Disconnect...\n";
        };
        //웹 소켓이 에러가 났을 때 호출되는 이벤트
        webSocket.onerror = function(message){
            messageTextArea.value += "error...\n";
        };
        
        //웹 소켓에서 메시지가 날라왔을 때 호출되는 이벤트
        webSocket.onmessage = function(message){
            messageTextArea.value += "Recieve From Server => "+message.data+"\n";
        };
        //Send 버튼을 누르면 실행되는 함수
        function sendMessage(){
            var message = document.getElementById("textMessage");
           // messageTextArea.value += "Send to Server => "+message.value+"\n";
            //웹소켓으로 textMessage객체의 값을 보낸다.
            webSocket.send(message.value);
            //textMessage객체의 값 초기화
            message.value = "";
        }
        //웹소켓 종료
        function disconnect(){
            webSocket.close();
        } */
    </script>


<section class="content">

 <div class="box">
<form class="form-horizontal" action="mail/mailSending" id="mailform" enctype="multipart/form-data" method="post">

	<div class="form-group">
		<label for="email_id" class="control-label col-sm-2">E-Mail</label>
		<div class="col-sm-10"> 
			<input type="email" class="form-control" id="tomail" name="tomail" placeholder="name@domain.com">
		</div>	
	</div>
	<div class="form-group">
		<label for="email_id" class="control-label col-sm-2">Title</label>
		<div class="col-sm-10"> 
			<input type="text" class="form-control" id="tomail" name="title" >
		</div>	
	</div>
	<div class="form-group">
		<label for="email_id" class="control-label col-sm-2">Content</label>
		<div class="col-sm-10">
			<textarea style="height: 300px;" class="form-control" id="comment" name="content"></textarea>
		</div>	
	</div>
	<div class="form-group">
		<label for="email_id" class="control-label col-sm-2">File</label>
		<div class="col-sm-10">
		    <input type="file" class="filestyle" data-size="sm" name="file" />
		</div>	
	</div>
	
	<div class="form-group"> 
		<div class="col-sm-10 col-sm-offset-2"> 					
			<button type="button" class="btn btn-primary" id="mailsend">Submit</button>
		</div>
	</div>		
	
</form>	

    </div>

	    
</section>