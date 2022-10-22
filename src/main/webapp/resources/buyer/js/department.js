/**
 * <pre>
 *  거래처 요청 승인 javascript
 * </pre>
 * @author 문성철
 * @since 2017. 5. 4.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일         수정자         수정내용
 * --------     --------      -------------
 * 2017. 5. 4.    작성자명          최초작성
 * Copyright (c) 2017 by CTW All right reserved
 * </pre>
 */ 

$(function(){
	
	// 데이터 테이블
   $('#tb').DataTable({
         "order": [[ 0, "desc" ]],
         "searching" : true,
         "ordering" : true,        
         "autoWidth" : false,
         "pagingType": "full_numbers",
         "scrollY": 580,
         "scrollCollapse": false,
         "pageLength": 15,
         "language" : {
            "search" : "<span>검색</span> _INPUT_", //search
            "lengthMenu" : '게시글 수 : <select>'
                  + '<option value="15">15</option>'
                  + '<option value="25">25</option>'
                  + '<option value="50">50</option>'
                  + '<option value="-1">All</option>'
                  + '</select>',
            "paginate" : {
            	"first" : "처음",
            	"last" : "마지막",
            	"previous" : "이전",
            	"next" : "다음"
            },            
            "zeroRecords" : "데이터가 없습니다.",
            "info" : "총_TOTAL_건 중, _START_건부터_END_건까지 표시",
	        "infoEmpty" : " 0 건 중, 0 부터 0 까지 표시",
	        "infoFiltered" : "（총 _MAX_ 건에서 추출    ）",
	        "processing" : "처리중...",
	        "loadingRecords": "로딩중..."
         }         
     }); 
	 	
	$("tbody").on("click",".getInfo",function(){
		console.log('거래처 클릭 / Ajax 요청 상세정보 가져옴');
		var bcnc_pk = $(this).attr('id');
		$.ajax({
		    	url:'buyerInfo',	
		    	type:'POST',
		    	dataType:'json',
		    	data:"pk="+bcnc_pk+"&division="+"bcnc_tmpr",
		    	success:function(res){
		    		// 거래처 임시 글번호(기본키)로 상세정보를 모두받아옴
		    		// 함수를 새롭게 호출하면서 Object 를 넘겨준다.
		    		// 거래처 승인이 완료된 상태라면 함수를 호출 하지 않는다.
		    		var check = res.confm;
		    		if(check == 'Y'){
		    			swal("이미 승인된 거래처","", "warning");
		    		}else{
			    		insertBcnc(res);		    		
		    		}
		    	}
		 });
		
	})
})

// 임시거래처 글의 모든 정보를 넘겨받고 승인여부를 Alert로 판단하고 처리한다.
function insertBcnc(data){
	console.log('insertBcnc / 거래처 상세정보 데이터 넘겨받음');
	swal({
		  title: '거래처 요청 승인',
		  text: '개인 거래처 요청을 승인하시겠습니까?',
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#8cd4f5',
		  confirmButtonText: '승인',
		  cancelButtonText: '취소',
		}).then(
		  function(result) {
				// 성공 alert
	    		swal({
	    			title:"승인완료", 
	    			text:"개인 거래처 요청이 승인되었습니다.", 
	    			type:"success"
	    			}).then(
	    				function(result){
	    					// alert에서 확인 버튼 누르는순간 Ajax 비동기 동작
	    					$.ajax({
	    				    	url:'buyerInsert',	
	    				    	type:'POST',
	    				    	data:"pk="+data.bcnc_pk+"&division="+"bcnc",
	    				    	dataType : "json",
	    				    	success:function(res){
	    				    		// 거래처 임시 글번호(기본키)로 거래처테이블 insert
	    				    		// 거래처 임시 글번호(기본키)로 임시 테이블 상태값 update
	    				    		console.log('거래처 최종 등록 확인 / 거래처 테이블에 저장된 시퀀스번호 = '+res.res);
	    				    		location.reload();	
	    				    	},
	    				    	error:function(jqXHR, textStatus, errorThrown){
	    				    		console.log('Ajax에러발생 =>'+textStatus+' || 원인 : '+errorThrown);
	    				    	}
	    				    	
	    				 });
	    				}		
	    			);
	    			
		  }, function(dismiss) {
			  swal("취소", "개인 거래처 요청을 취소합니다.", "error");
		  }
	);
}