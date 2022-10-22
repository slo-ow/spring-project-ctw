<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<%--
* @Description : 다음 지도 API를 이용한  거래처 현황 확인 페이지 
* @author 문성철
* @version 1.0
* @since 2017. 5. 4.
*
* [[개정이력(Modification Information)]]
*  수정일          수정자          수정내용
* ----------    ---------   -------------
* 2017. 5. 2.      작성자명      최초작성
* Copyright (c) 2017 by CTW All right reserved
 --%>
<style>
    .wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
    .wrap * {padding: 0;margin: 0;}
    .wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
    .wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
    .info .title {padding: 0px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold; text-align: center;}
    .info .close {position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url('http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');}
    .info .close:hover {cursor: pointer;}
    .info .body {position: relative;overflow: hidden;}
    .info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
    .desc .ellipsis {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
    .desc .jibun {font-size: 11px;color: #888;margin-top: -2px;}
    .info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
    .info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
    .info .link {color: #5085BB;}
</style>
 <section class="content-header">
	<h1>
	 거래처 현황
	</h1>
	<ol class="breadcrumb">
	  <li><a href="#"><i class="fa fa-dashboard"></i>거래처 관리</a></li>
	  <li class="active">거래처 현황</li>
	</ol>
</section>
<section class="content">
		<div class="box">	
			<!-- box-body -->
			<div class="box-body">
		
				<!-- 지도가 표시되는 div -->
				<div id="map" style="width:100%;height:735px;"></div>		
	
			</div> 
			<!-- box-body end -->
		 </div> 
 </section>
 <script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=d8c34d93f6b5f2fce63b735f5d31c728&libraries=clusterer"></script>
<script>
    var map = new daum.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center : new daum.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표
        level : 13 // 지도의 확대 레벨
    });

    // 마커 클러스터러를 생성합니다
    var clusterer = new daum.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 10, // 클러스터 할 최소 지도 레벨
        calculator: [10, 30, 50], // 클러스터의 크기 구분 값, 각 사이값마다 설정된 text나 style이 적용된다
        texts: getTexts, // texts는 ['삐약', '꼬꼬', '꼬끼오', '치멘'] 이렇게 배열로도 설정할 수 있다
        styles: [{ // calculator 각 사이 값 마다 적용될 스타일을 지정한다
                width : '30px', height : '30px',
                background: 'rgba(51, 204, 255, .8)',
                borderRadius: '15px',
                color: '#000',
                textAlign: 'center',
                fontWeight: 'bold',
                lineHeight: '31px'
            },
            {
                width : '40px', height : '40px',
                background: 'rgba(255, 153, 0, .8)',
                borderRadius: '20px',
                color: '#000',
                textAlign: 'center',
                fontWeight: 'bold',
                lineHeight: '41px'
            },
            {
                width : '50px', height : '50px',
                background: 'rgba(255, 51, 204, .8)',
                borderRadius: '25px',
                color: '#000',
                textAlign: 'center',
                fontWeight: 'bold',
                lineHeight: '51px'
            },
            {	
            	// VIP            
                width : '60px', height : '60px',
                background: 'rgba(39, 255, 155, .8)',
                borderRadius: '30px',
                color: '#000',
                textAlign: 'center',
                fontWeight: 'bold',
                lineHeight: '61px'
            }
        ]
    });

    // 클러스터 내부에 삽입할 문자열 생성 함수입니다
    function getTexts( count ) {

      // 한 클러스터 객체가 포함하는 마커의 개수에 따라 다른 텍스트 값을 표시합니다
      if(count < 10) {
        return 'Ac'; //ac == account 거래처의 약자 
      } else if(count < 30) {
        return 'Silver';
      } else if(count < 50) {
        return 'Gold';
      } else {
        return 'VIP';
      }
    }
	
   	console.log("Ajax 거래처 현황 조회 요청")
   	$.ajax({
   	    url : "buyerlist",
   	    type : "POST",
   	    dataType: "json",
   	    success : function(data) {
   	    console.log("Ajax  거래처 현황 조회(모든 거래처의 정보) 가져오기 성공")
   	    var markers = $(data.positions).map(function(i, position) {
	        return new daum.maps.Marker({
	           position : new daum.maps.LatLng(position.lat, position.lng),
	        });	       		
	    });
   	       		
   	    // 마커의 개수만큼 인포윈도우 이벤트를 등록시켜준다.
   	   	addInfo(data,markers);
        // 클러스터러에 마커들을 추가합니다
        clusterer.addMarkers(markers);	     	
        
   	    },
   	    error : function(jqXHR, textStatus, errorThrown) {
   	        alert("Ajax 에러발생 !! Request Header 확인 \n" + textStatus + " : " + errorThrown);
   	    }
   	});	
	
 	// 마커의 개수만큼 인포윈도우 이벤트를 등록시켜준다.
 	function addInfo(data,markers){
 		for(var i =0; i < data.positions.length; i++){
 		// 커스텀 오버레이에 표시할 컨텐츠 입니다
 		// 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
 		// 별도의 이벤트 메소드를 제공하지 않습니다 
 		var content = '<div class="wrap">' + 
 		            '    <div class="info">' + 
 		            '        <div class="title">' + 
 		            '            '+data.positions[i].bcnc_nm+'' + 
 		            '        </div>' + 
 		            '        <div class="body">' + 
 		            '            <div class="img">' +
 		            '                <img src="${pageContext.request.contextPath}/resources/buyer/img/cityscape.png" width="73" height="70">' +
 		            '           </div>' + 
 		            '            <div class="desc">' + 
 		            '                <div class="ellipsis">대표명 : '+data.positions[i].bcnc_rprsntv+'</div>' + 
 		            '                <div class="ellipsis">주소 : '+data.positions[i].bcnc_addr+'</div>' + 
 		            '                <div class="jibun ellipsis">연락처 : '+data.positions[i].bcnc_telno+'</div>' + 
 		            '            </div>' + 
 		            '        </div>' + 
 		            '    </div>' +    
 		            '</div>';

 		// 마커 위에 커스텀오버레이를 표시합니다
 		// 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
 		var overlay = new daum.maps.CustomOverlay({
 		    content: content,
 		    position: markers[i].getPosition()       
 		});
 			
 		// 마커의 개수만큼 mouseover event 를 등록시켜주고 overlay를 여는 함수를호출
 		daum.maps.event.addListener(markers[i], 'mouseover', makeOverListener(map, markers[i], overlay));
 		// 마커의 개수만큼 mouseout event 를 등록시켜주고 overlay를 닫는 함수를호출 
 	    daum.maps.event.addListener(markers[i], 'mouseout', makeOutListener(overlay));
 	    		
 		// 마우스 오버시 오버레이를 띄움 
 		function makeOverListener(map, markers, overlay) {
 		    return function() {
 		    	overlay.setMap(map);
 		    };
 		}

 		// 마우스 오버아웃시 오버레이를 닫아줌 
 		function makeOutListener(overlay) {
 		    return function() {
 		    	overlay.setMap(null);
 		    };
 		}
 		
 	}// 반복문 종료
 		
 }
</script>