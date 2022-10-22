<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<!DOCTYPE html>
<!--
* 거래처 등록 페이지
* @author 문성철 (icet25@naver.com)
* @version 1.0
* @since 2017.04.24
-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<!-- bootstrap -->
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath }/resources/indexpage/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script> --%>
<style>
	<!-- 다음 지도의 기본 CSS -->
    .map_wrap {position:relative;width:100%;height:350px;}
    .title {font-weight:bold;display:block; margin-left: 40px;}
    .hAddr {position:absolute;left:10px;top:10px;border-radius: 2px;background:#fff;background:rgba(255,255,255,0.8);z-index:1;padding:5px;margin-top:60px;}
    #centerAddr {display:block;margin-top:2px;font-weight: normal;}
    .bAddr {padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
</style>
<body>
<div class="map_wrap">
    <div id="map" style="width:100%;height:800px;position:relative;overflow:hidden;"></div>
    <div class="hAddr">
        <span class="title">지도중심기준 행정동 주소정보</span>
        <span id="centerAddr"></span>
    </div>
    <!-- 거래처 등록 모달 호출버튼 -->
  <input type="hidden" data-toggle="modal" data-target="#myModal"  data-keyboard="false" data-backdrop="static" id="bcnc_add"/>
</div>

<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=d8c34d93f6b5f2fce63b735f5d31c728&libraries=services"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new daum.maps.LatLng(36.34490990263286, 127.38658947886822), // 지도의 중심좌표
        level: 9 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new daum.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new daum.maps.services.Geocoder();

var marker = new daum.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
infowindow = new daum.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
searchAddrFromCoords(map.getCenter(), displayCenterInfo);
    
//더블클릭 이벤트
/* daum.maps.event.addListener(map, 'dblclick', function(mouseEvent) {
    var latlng = mouseEvent.latLng;
    alert('double click! ' + latlng.toString());
    
    var lat = latlng.getLat();//위도를 반환한다
    var lng = latlng.getLng();//경도를 반환한다
}); */

// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
daum.maps.event.addListener(map, 'click', function(mouseEvent) {
    searchDetailAddrFromCoords(mouseEvent.latLng, function(status, result) {
        if (status === daum.maps.services.Status.OK) {
            /* var detailAddr = !!result[0].roadAddress.name ? '<div>도로명주소 : ' + result[0].roadAddress.name + '</div>' : '';
            detailAddr += '<div주소 : ' + result[0].jibunAddress.name + '</div>'; */
            
            var content = '<div class="bAddr">' + '<span class="title">현재위치</span>' + '</div>';

            // 마커를 클릭한 위치에 표시합니다 
            marker.setPosition(mouseEvent.latLng);
            marker.setMap(map);

            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
            infowindow.setContent(content);
            infowindow.open(map, marker);
            
            var latlng = mouseEvent.latLng;
            var lat = latlng.getLat();//위도를 반환한다
            var lng = latlng.getLng();//경도를 반환한다
            var addr = result[0].jibunAddress.name; //주소를 반환
			
            //주소,위도,경도를 전달함
            $(".bcnc_addr").val(addr);
            $(".lat").val(lat);
            $(".lng").val(lng);  
            
        }   
    });
    //클릭시 트리거이벤트 발생
    daum.maps.event.trigger(marker, 'custom_action', '사용자 이벤트'); // 사용자 이벤트가 발생했습니다.
});


//마커 객체에 사용자 이벤트를 등록한다
daum.maps.event.addListener(marker, 'custom_action', function(data){
	console.log(data + '가 발생했습니다.\n거래처 등록 모달창 호출');
	//alert("마커 클릭시 트리거 이벤트 발생");
	//모달창 호출 + 트리거를 통해서 코드로 클릭이벤트를 발생시킴
	$('#bcnc_add').trigger('click');
	
});


// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
daum.maps.event.addListener(map, 'idle', function() {
    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
});

function searchAddrFromCoords(coords, callback) {
    // 좌표로 행정동 주소 정보를 요청합니다
    geocoder.coord2addr(coords, callback);         
}

function searchDetailAddrFromCoords(coords, callback) {
    // 좌표로 법정동 상세 주소 정보를 요청합니다
    geocoder.coord2detailaddr(coords, callback);
}

// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
function displayCenterInfo(status, result) {
    if (status === daum.maps.services.Status.OK) {
        var infoDiv = document.getElementById('centerAddr');
        infoDiv.innerHTML = result[0].fullName;
    }    
}

function Add_bcnc(){
	console.log("거래처 등록 ==> DB 저장")
	$.ajax({
	    url : "jsonTest",
	    type : "POST",
	    dataType: "json",
	    success : function(data) {
	        console.log("result : " + data.test1);
	    },
	    error : function(jqXHR, textStatus, errorThrown) {
	        alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
	    }
	});
}
</script>
<!-- 거래처 등록 모달창 -->
<div class="container">
   <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog" style="margin-top: 280px;"><!-- style 모달창 위치 재정의 -->
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">거래처 등록</h4>
        </div>
        <div class="modal-body">
          <p>거래처 정보등록</p>
          	  <!-- 비동기 Ajax를 통한 데이터 저장 -->
          	<input type="text" name="bcnc_nm" class="bcnc_nm" placeholder="거래처명" /><br/>
          	<input type="text" name="bcnc_addr" class="bcnc_addr" placeholder="거래처주소"/><br/>
          	<input type="text" name="lat" class="lat" placeholder="위도"/><br/>
          	<input type="text" name="lng" class="lng" placeholder="경도"/><br/>
          	<input type="text" name="mem_id" class="mem_id" placeholder="사원ID"/>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" onclick="Add_bcnc()">Save</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>
</body>
</html>