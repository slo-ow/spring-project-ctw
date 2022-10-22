<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
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
	   휴지통
	  <small> 휴지통</small>
	</h1>
	<ol class="breadcrumb">
	  <li><a href="#"><i class="fa fa-dashboard"></i> 휴지통</a></li>
	  <li class="active">휴지통</li>
	</ol>
</section>
<style>


.filecontent{
	margin-top: 2%;
	width: 95%;
	background-color: white;
	height: 750px;
    border-radius: 10px;
}
.headerFile{
	width: 100%;
	background-color: #605ca8;
	height: 50px;
	 border: 1px solid #605ca8;
    border-radius: 10px;
}
.filelist{
 width:100%;
 height:550px;
 overflow-x:hidden; 
 overflow-y:auto; 
}

.filefloat{
	width: 200px; 
	height:200px;
	float: left;
	text-align: center;
	margin: 10px;
	border: 1px solid #605ca8;
    border-radius: 10px;
}
.down{	
	margin-top:20%;
	cursor:pointer;
	width: 40%;
	height: 40%;
}
.deleteFile{
	width:10%;
    margin:10%;
	color:red;
	float:right;
	cursor:pointer;
}
.updateFile{
	width:10%;
	margin:10%;
	float: left;
	color:blue;
    cursor:pointer;
}
</style>

<section class="content">
<div class="box">
	<input type="hidden" id="extsn">
	<div class="content filecontent">
		<div class="header headerFile">
	  		
	  		<br>
	  		<br>
	  		<br>
	  			<div class="form-inline">
	  				<div class="form-group" style="margin-left:3%; ">
	  				<input type="hidden" value="${member.grad_pk}" id="grad_pk">
				 	</div>
					  		
				</div>	  
	  	
		</div>
			<hr style="border: solid 1px gray;">
		<div class="filelist">
	  		<h1>로딩중입니다.....</h1>
	  	</div>
	</div>
	</div>
			<input type="hidden" value="${division }" name="division">
	
	
</section>  

        
   <script type="text/javascript">
   

   $(function(){
		var grad_pk =$("#grad_pk").val();
	

	   var division = $("input[name=division]").val();
		if(division == 'dept'){
			if(grad_pk < 6){
				$(window).load(function(){
				    filelist(division);
				});   
			}else{
			 	swal({
  				  title: "권한이 없습니다",
  				  text: "부서장으로 로그인해주세요",
  				  type: "warning",
  				  confirmButtonText: "닫기"
			 	}); 
			 	
			 	/* ,function(isConfirm){
			 		if(isConfirm){
				 	 backFunction();
			 		}else{
				 	 backFunction();
			 		}
			 	} */
			}
		}
		if(division == 'com'){
			if(grad_pk > 5){
				$(window).load(function(){
				    filelist(division);
				});   
			}else{
			 	swal({
  				  title: "권한이 없습니다",
  				  text: "관리자로 로그인해주세요",
  				  type: "warning",
  				  confirmButtonText: "닫기"
			 	}); 
			 	
			 	/* ,function(isConfirm){
			 		if(isConfirm){
				 	 backFunction();
			 		}else{
				 	 backFunction();
			 		}
			 	} */
			}
		}
	   

	   // 삭제
	   $(".filelist").on("click",".deleteFile",function(){
		   var keyvalue = $(this).attr('id');
		   var doc_pk =  keyvalue.split("/");
		   $(location).attr('href', 'trashcanDocDel?doc_pk='+doc_pk[1]+'&division='+division);
	   });
	   
	   // 파일 복귀 
	   $(".filelist").on("click",".updateFile",function(){
		   var keyvalue = $(this).attr('id');
		   var doc_pk =  keyvalue.split("/");
		   $(location).attr('href', 'trashcanDocUp?doc_pk='+doc_pk[1]+'&division='+division);
	   });
	   
	
		

   
   });
   
   
   
function backFunction(){
	 window.history.back();
}   
function filelist(division){
       $.ajax({
	    	url:'trashcanDocList',	
	    	type:'POST',
	    	dataType:'json',
	    	data : {"division" : division},
	    	success:function(res){
	    		var htmlCode = "";
	    		$.each(res,function(i,v){
	    			if(v.doc_extsn == 'excel'){
	    				htmlCode +="<div class='filefloat'><img src='${pageContext.request.contextPath}/resources/icon/Excel 2013.png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
	    
	    			}else if(v.doc_extsn == 'word'){
	    				htmlCode +="<div class='filefloat'><img src='${pageContext.request.contextPath}/resources/icon/Word 2013.png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
	    			}else if(v.doc_extsn == 'ppt'){
	    				htmlCode +="<div class='filefloat'><img src='${pageContext.request.contextPath}/resources/icon/PowerPoint 2013.png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
	    			}else if(v.doc_extsn == 'pdf'){
	    				htmlCode +="<div class='filefloat'><img src='${pageContext.request.contextPath}/resources/icon/PDF.png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
	    			}else if(v.doc_extsn == 'hangle'){
	    				htmlCode +="<div class='filefloat'><img src='${pageContext.request.contextPath}/resources/icon/HWP (1).png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
	    			}else if(v.doc_extsn == 'zip'){
	    				htmlCode +="<div class='filefloat'><img src='${pageContext.request.contextPath}/resources/icon/ZIP (2).png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
	    			}else{
	    				htmlCode +="<div class='filefloat'><img src='${pageContext.request.contextPath}/resources/icon/document.png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
	    			}
					htmlCode +="<span class='deleteFile' id='test/"+v.doc_pk+"'><b>X</b></span>";
					htmlCode +="<span class='updateFile' id='test1/"+v.doc_pk+"'><b>UP</b></span>";
    				htmlCode +="<br>"+v.doc_nm+"</div>";
	    			
	    		})
	    		  $(".filelist").html(htmlCode);
	    	
	    	}
	   });
	   
   };
   

</script>     
	  	