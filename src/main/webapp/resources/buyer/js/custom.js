/**
 * @Description : 다음지도 API 스크립트, 모달 스크립트 
 * @author 문성철 (icet25@naver.com)
 * @version 1.0
 * @since 2017.04.25
 * 
 */
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
            //$(".bcnc_addr").val(addr);
            $(".bcnc_addr").attr('value',addr);
            $(".lat").val(lat);
            $(".lng").val(lng);  
                   
        }   
    });
    //클릭시 트리거이벤트 발생
    daum.maps.event.trigger(marker,'custom_action', '사용자 이벤트'); // 사용자 이벤트가 발생했습니다.
});

//마커 객체에 사용자 이벤트를 등록한다
daum.maps.event.addListener(marker,'custom_action', function(data){
	//alert("마커 클릭시 트리거 이벤트 발생");	
	console.log(data + '가 발생했습니다.\n거래처 등록 모달창 호출');
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

//buyerModal 거래처 등록 
function Add_bcnc(){
	//폼 유효성검사
	var t1 = $(".bcnc_rprsntv").val();
    var t2 = $(".bcnc_nm").val();
    var t3 = $(".bcnc_telno").val();
    
    if(t1==""||t2==""||t3==""){
    	swal({
  		  title: "거래처 등록실패",
  		  text: "거래처 정보를 정확히 입력해주세요",
  		  type: "warning",
  		  confirmButtonText: "닫기"
    	});
    	return;
    }
    
	//재확인 모달창 호출
	//$("#buyerModal_Alert").modal('show');
    check_alert();    	
}
//buyerModal 취소
function Cancel(){
	$(".bcnc_rprsntv").val('');
    $(".bcnc_nm").val('');
    $(".bcnc_telno").val('');
    $("#buyerModal").modal('hide');	
}

//최종 등록 확인 Alert
function check_alert(){
	swal({
		  title: '거래처 요청',
		  text: '개인 거래처 요청을 진행하시겠습니까?',
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#8cd4f5',
		  confirmButtonText: '등록',
		  cancelButtonText: '취소',
		}).then(
		  function(result) {
			  buyer_success();
			  swal("등록 성공", "개인 거래처 요청 등록이 완료되었습니다.", "success");
			  Cancel()
		  }, function(dismiss) {
			  swal("취소", "개인 거래처 요청을 취소합니다.", "error");
			  Cancel()
		  }
	);
}


//Alert모달 확인버튼 클릭시
function buyer_success(){
	console.log("거래처 등록 ==> bcnc_tmpr 테이블 Data insert ")
	$.ajax({
	    url : "addRequest",
	    type : "POST",
	    dataType: "text",
	    data : $("#bcnc_request").serialize(),
	    success : function(data) {
	        console.log("거래처등록 결과 : " + data);
	        //모달의 내용을 초기화시키고 모달창을 닫아준다.
	        $(".bcnc_rprsntv").val('');
	        $(".bcnc_nm").val('');
	        $(".bcnc_telno").val('');
	        $("#buyerModal").modal('hide');
	        $("#buyerModal_Alert").modal('hide');
	    },
	    error : function(jqXHR, textStatus, errorThrown) {
	        alert("Ajax 에러발생 !! Request Header 확인 \n" + textStatus + " : " + errorThrown);
	    }
	});
}

// Alert모달 취소버튼
function buyer_cancel(){
    $("#buyerModal_Alert").modal('hide');
	$(".bcnc_rprsntv").val('');
    $(".bcnc_nm").val('');
    $(".bcnc_telno").val('');
    $("#buyerModal").modal('hide');
}