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
		일정관리 <small>일정관리</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> My page</a></li>
		
	</ol>
</section>


<section class="content">
<div class="box">
	<div style="float:right; margin-top: 20px;"> 
	
			  <div class="form-group sort" >
				    <label for="text" class="sort">	개인일정 :</label>
				    <input type="text" class="form-control sort"   style="background-color: #00BA22; width:5%; height:10px; margin-top: 5px;" readonly="readonly">
			  </div>
			  <div class="form-group sort" >
				    <label for="text" class="sort">	부서일정 :</label>
				    <input type="text" class="form-control sort"   style="background-color: #ff992d; width:5%; height:10px; margin-top: 5px;" readonly="readonly">
			  </div>
			  <div class="form-group sort" >
				    <label for="text" class="sort">	회사일정 :</label>
				    <input type="text" class="form-control sort"   style="background-color: #3091f2; width:5%; height:10px; margin-top: 5px;" readonly="readonly">
			  </div>
			
	</div>

	<div class="box-body no-padding" style="margin-top: 20px;margin-buttom: 20px;">
       <!-- THE CALENDAR -->
       <div id="calendar"></div>
     </div>
     
 
    </div> 
</section>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/calendar/css/cal.css" >
  <!-- insert modal -->
  <div class="modal fade" id="insertFX" role="dialog" data-toggle="modal" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
    
      <!-- Modal content-->
 
     <div class="box box-primary">
            <div class="box-header">
              <h3 class="box-title">일정 추가</h3>
            </div>
            <form action="fxinsert" method="POST" id="fxinsert">
            <div class="box-body">
             	<div class="form-group sort"  style="width:100%;">
				    <label for="text" class="sort">제목 : &nbsp;</label>
				    <input type="text" class="form-control sort"  style="width:80%;" name="fx_nm" >
				  </div>
 				<div class="form-group sort" style="width:100%;">
				    <label for="text" class="sort">내용 : &nbsp;</label>
				      <textarea class="form-control sort" rows="7" id="comment" style="width:80%;"  name="fx_memo"></textarea>
				  </div>
              <!-- Date and time range -->
              <div class="form-group">
                <label>시작일과 종료 일을 선택해주세요 :</label>
                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-clock-o"></i>
                  </div>
                  <input type="text" class="form-control pull-right" id="reservationtime" >
                </div>
                <!-- /.input group -->
              </div>
              <div>
				  <div class="form-group sort" >
				    <label for="text" class="sort">시작시간 :</label>
				    <input type="text" class="form-control sort" id="startTime"  style="width:30%;" readonly="readonly">
				  </div>
				  
				  <div class="form-group sort" >
				    <label for="text" class="sort">종료시간 :</label>
				    <input type="text" class="form-control sort" id="endTime" style="width:30%;" readonly="readonly">
				  </div>
              </div>
               	<input type="hidden" name="division" value="mem">
  				<input type="hidden" name="fx_begin" id="fx_begin">
  				<input type="hidden" name="fx_end" id="fx_end">
              <!-- /.form group -->
				<input type="button" id="insert" class="btn btn-info" value="저장">
				<input type="button" class="btn btn-info" data-dismiss="modal" value="취소">
        		

  
              </div>
              <!-- /.form group -->
			</form>
            </div>
            <!-- /.box-body -->
          </div>
      
    </div>
    
     <!-- info modal -->
  <div class="modal fade" id="infoFX" role="dialog" data-backdrop="static" data-keyboard="false" >
    <div class="modal-dialog">
    
      <!-- Modal content-->
   
     <div class="box box-primary">
            <div class="box-header">
              <h3 class="box-title">일정 정보</h3>
            </div>
            <form action="fxupdate" method="POST" id="fxupdate">
            <div class="box-body">
             	<div class="form-group sort"  style="width:100%;">
				    <label for="text" class="sort">제목 : &nbsp;</label>
				    <input type="text" class="form-control sort"  style="width:80%;" name="fx_nm" id="fx_nm" >
				  </div>
 				<div class="form-group sort" style="width:100%;">
				    <label for="text" class="sort">내용 : &nbsp;</label>
				      <textarea class="form-control sort" rows="7" id="fx_memo" style="width:80%;"  name="fx_memo" ></textarea>
				  </div>  
              <!-- Date and time range -->
              <div class="form-group">
                <label>시작일과 종료 일을 선택해주세요 :</label>
                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-clock-o"></i>
                  </div>
                  <input type="text" class="form-control pull-right" id="reservationtime2" >
                </div>
             
                
                <!-- /.input group -->
              </div>
                            <div>
				  <div class="form-group sort" >
				    <label for="text" class="sort">시작시간 :</label>
				    <input type="text" class="form-control sort" id="startTime2"  style="width:30%;" readonly="readonly">
				  </div>
				  
				  <div class="form-group sort" >
				    <label for="text" class="sort">종료시간 :</label>
				    <input type="text" class="form-control sort" id="endTime2" style="width:30%;" readonly="readonly">
				  </div>
              </div>
              <div>
              		<input type="hidden" name="fx_begin" id="fx_begin2">
              		<input type="hidden" name="division" value="mem">
  					<input type="hidden" name="fx_end" id="fx_end2">
				  <input type="hidden" id="fx_pk" name="fx_pk">	  
              </div>
  			
              <!-- /.form group -->
              
                <div class="input-group" id="division1">
                  	
                </div>
              
              
	
              </div>
              <!-- /.form group -->
			</form>
            </div>
            <!-- /.box-body -->
          </div>
      
    </div>
    <script src="${pageContext.request.contextPath }/resources/calendar/js/memCal.js"></script>
<script type="text/javascript">

</script>     
     
