<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<section class="content-header">
	<h1>
	  조직문서관리
	  <small>표준문서관리</small>
	</h1>
	<ol class="breadcrumb">
	  <li><a href="#"><i class="fa fa-dashboard"></i> 조직표준문서</a></li>
	  <li class="active">조직표준문서목록</li>
	</ol>
</section>
<style>

</style>

<section class="content">
<div class="box">
	<input type="hidden" id="extsn">
	<div class="content filecontent">
		<div class="header headerFile">
	  		<div class="search" >
	  			<form class="form-inline">
					<div class="form-group" style="float: left; margin-bottom: 5px;">
					</div>
				  <div class="form-group" style="float: right;">
				    <label for="filename" style="color: white;">파일명 :</label>
				    <input type="text" class="form-control" id="filename" name="" size="5">
				 	 <button type="button" class="btn btn-default" id="fileSearch">검색</button>
				  </div>
				</form>
	  		</div>
	  		<br>
	  		<br>
	  		<br>
	  			<div class="form-inline">
	  				<div class="form-group" style="margin-left:3%; ">
	  				<c:if test="${member.grad_pk > 5 }">
						<button class="btn btn-info" style="float: left; margin-bottom: 10px;" data-toggle="modal" data-backdrop="static" data-keyboard="false" data-target="#insertFile"> <p class="glyphicon glyphicon-floppy-open"  ><b>파일업로드</b> </p></button>
	  				</c:if>

	  				<input type="hidden" value="${member.grad_pk}" id="grad_pk">
				 	</div>
  					<div class="form-group" style="margin-left:10%; ">
				    	<input type="button" class="btn btn-default"  id="extsn1" value="액셀">
				    	<input type="button" class="btn btn-default"  id="extsn2" value="워드">
				    	<input type="button" class="btn btn-default"  id="extsn3" value="한글">
				    	<input type="button" class="btn btn-default"  id="extsn4" value="PPT">
				    	<input type="button" class="btn btn-default"  id="extsn5" value="ZIP">
				    	<input type="button" class="btn btn-default"  id="extsn6" value="PDF">
				    	<input type="button" class="btn btn-default"  id="extsn7" value="전체">
  					</div>
					<div class="form-group" style="float:right;">
					  	<label for="sel1">카테고리:</label>
					  	<select class="form-control kategorie" id="sel1">
						
						 </select>
					</div>	
					  		
				</div>	  
	  		<hr style="border: solid 1px gray;">
	  		<div class="filelist">
	  			<h1>로딩중입니다.....</h1>
	  		</div>
		</div>
	</div>
	</div>
</section>  
		
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/doc/css/doc.css" >


        <div class="modal modal-primary" id="insertFile"  role="dialog">
          <div class="modal-dialog" >
            <div class="modal-content">
              <div class="modal-header"  >                
                  <span aria-hidden="true"   data-dismiss="modal" onclick="close1();" style="float:right; cursor: pointer;">×</span>
                <h4 class="modal-title">문서업로드</h4>
              </div>
              
            <form action="docSave" name="fileupload"  id="fileFormSave" method="post" enctype="multipart/form-data">
             <div class="modal-body" >
         <!-- 멀티 파일업로드 -->
				<div class='form-group' style="height:200px;">
				<input type="hidden" name="division" value="com">
				<button type="button" class="btn btn-default" id="addfile" style=" margin-bottom: 10px;"> <p class="glyphicon glyphicon-plus"  ><b>추가</b> </p></button>
					<div class="form-group" style="float: right;width: 50%;">
					  	<label for="sel1"  style="float: left; width: 30%;">카테고리:</label>
					  	<select class="form-control kategorie2"  name="kategorie"  style="float: left;width: 70%;">
					    
						    
						 </select>
					</div>	
				<br>
					<input type="file" class="filestyle" data-size="sm" name="docfile[0]" />
					<div class='form-group' id="fileappend">
					</div>
				</div>
              </div>
              </form>
     
              <div class="modal-footer">
                <button type="button" class="btn btn-outline" id="fileSave">Save</button>
                <button type="button" class="btn btn-outline"
                 data-dismiss="modal" onclick="close1();">Close</button>
              </div>
            </div>
          </div>
        </div>
        
         <script src="${pageContext.request.contextPath }/resources/doc/js/comDoc.js"></script>

	  	