<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<%--
* @Description 조직원 거래처 등록 요청 페이지
* @author 문성철
* @version 1.0
* @since 2017. 5. 2.
*
* [[개정이력(Modification Information)]]
*  수정일          수정자          수정내용
* ----------    ---------   -------------
* 2017. 5. 2.      작성자명      최초작성
* Copyright (c) 2017 by CTW All right reserved
 --%>
<style>
	<!-- 다음 지도의 기본 CSS -->
    .map_wrap {position:relative;width:100%;height:350px;}
    .title {font-weight:bold;display:block; margin-left: 40px;}
    .title2 {font-weight:bold;display:block; margin-left: 0px;}
    .hAddr {position:absolute;left:10px;top:10px;border-radius: 2px;background:#fff;background:rgba(255,255,255,0.8);z-index:1;padding:5px;margin-top:60px;}
    #centerAddr {display:block;margin-top:2px;font-weight: normal;}
    .bAddr {padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
</style>
<section class="content-header">
	<h1>
		개인 거래처 요청
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 부서 거래처</a></li>
		<li class="active">개인거래처요청</li>
	</ol>
</section>
<!-- content -->
<section class="content">
	<!-- box -->
	<div class="box">
		<!-- body -->
		<div class="box-body">
			<!-- daum Map API -->
			<div class="map_wrap">
			    <div id="map" style="width:100%;height:700px;position:relative;overflow:hidden;"></div>
			    <div class="hAddr">
			        <span class="title2">지도중심기준 행정동 주소정보</span>
			        <span id="centerAddr"></span>
			    </div>
			    <!-- 거래처 등록 모달 호출버튼 -->
			  <input type="hidden" data-toggle="modal" data-target="#buyerModal"  data-keyboard="false" data-backdrop="static" id="bcnc_add"/>
			</div>
			<!-- end daum Map API -->						
		</div>
		<!-- body -->
	</div>
	<!-- box -->
</section>
<!-- end content -->

<!-- daum API Script -->
<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=d8c34d93f6b5f2fce63b735f5d31c728&libraries=services"></script>

<!-- customizing Script -->
<script src="${pageContext.request.contextPath }/resources/buyer/js/custom.js"></script>

<!-- 모달의 style customizing -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/buyer/css/custom.css" />

<!-- 거래처 등록 모달창 -->
<div class="example-modal">
 <!-- modal / 여기서 ID 값으로 모달을 호출한다 $("#id").modal('show'); -->
  <div class="modal fade" id="buyerModal" role="dialog">
  <!-- 모달창 위치 재정의 -->
    <div class="modal-dialog" style="margin-top: 250px;">
     <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span></button>
          <h4 class="modal-title"><i class="fa fa-bank"></i>&nbsp;거래처 등록</h4>
        </div>
        <div class="modal-body">	        
          	<form id="bcnc_request">
				<!-- 거래처 대표명 -->
		        <div class="input-group">
                	<input type="text" name="bcnc_rprsntv" class="bcnc_rprsntv form-control" placeholder="거래처대표명"/>
                	<span class="input-group-addon"><i class="fa fa-pencil"></i></span>
              	</div>
              	<!-- 거래처 이름 -->
              	<div class="input-group">
	                <input type="text" name="bcnc_nm" class="bcnc_nm form-control" placeholder="거래처명" />
	                <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
              	</div>
              	<!-- 거래처 전화번호 -->
              	<div class="input-group">
              		<input type="text" name="bcnc_telno" class="bcnc_telno form-control" placeholder="거래처전화번호" /><br/>
	                <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
              	</div>
              	<!-- 거래처 주소 -->
              	<div class="input-group">
              		<input type="text" name="bcnc_addr" class="bcnc_addr form-control" placeholder="거래처주소" readonly/><br/>
	                <span class="input-group-addon"><i class="fa fa-check"></i></span>
              	</div>
              	<!-- 사원 ID -->
              	<div class="input-group">
              		<input type="text" name="mem_id" class="mem_id form-control" value="${member.mem_id}" placeholder="사원ID" readonly/><br>
	                <span class="input-group-addon"><i class="fa fa-check"></i></span>
              	</div>
              	<!-- 사원이름 -->
              	<div class="input-group">
              		<input type="text" name="bcnc_mem_name" class="bcnc_mem_name form-control" value="${member.mem_nm}" placeholder="사원이름" readonly/><br>
	                <span class="input-group-addon"><i class="fa fa-check"></i></span>
              	</div>
              	<!-- 사원연락처  -->
				<div class="input-group">
              		<input type="text" name="bcnc_mem_telno" class="bcnc_mem_telno form-control" value="${member.mem_telno}" placeholder="사원연락처" readonly/><br>
	                <span class="input-group-addon"><i class="fa fa-check"></i></span>
              	</div>
	        	<input type="hidden" name="lat" class="lat" placeholder="위도"/>
	        	<input type="hidden" name="lng" class="lng" placeholder="경도"/>
	        	<input type="hidden" name="dept_pk" value="${member.dept_pk}" placeholder="부서코드"/>
	       	</form>          	
        </div>
        <div class="modal-footer">
	        <button type="button" class="btn btn-outline" onclick="Add_bcnc()">등록</button>
          	<button type="button" class="btn btn-outline" onclick="Cancel()">취소</button>
        </div>
      </div>
     	<!-- modal-content end -->
	  </div>
	  <!-- modal-dialog end -->
  </div>
  <!-- modal end -->
</div>
<!-- 거래처 등록 모달창  끝 -->