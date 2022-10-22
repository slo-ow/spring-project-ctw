/**
 * <pre>
 *       자신의 프로젝트 조회
 * </pre>
 * @author 박혜윤(hyeonwjd@naver.com)
 * @since 2017. 5. 9.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일           수정자       수정내용
 * --------     --------    ----------------------
 * 2017. 5. 9.    박혜윤       최초작성
 * Copyright (c) 2017 by CTW All right reserved
 * </pre>
 */
$(function(){
	var mem_id = $("#mem_id").val();

	$("#maintable").DataTable({
        "order": [[ 0, "desc" ]],
        "searching" : true,
        "ordering" : true,         
        "autoWidth": false,
        "pagingType": "full_numbers",
        "scrollY": 500,
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

	// 프로젝트리스트에 있는 걸 클릭시 나오는거 
	$(".projectTb").on("click","tr",function(){
		var prjct_pk = $(this).attr('id');
		var idtest = $(this).attr('class').split(' ');
		var status = $("td:eq(3)",this).text();
//		alert(idtest[0]);
		
		if(idtest[0] == mem_id){
//			alert("팀장입니다. "+idtest);
			location.href='joinProject';
//			swal("", "프로젝트 팀장입니다.", "warning");
		}else{
			if(status=="참여중"){
				 location.href='joinProject';
			}else{
				// 참여대기상태일때
				selectInfo(prjct_pk);
			}
		}
		
//		alert("프로젝트 기본키는 ="+prjct_pk);
		
	})
	
	
	
	// 프로젝트 생성에서 부서 selectbox가 변했을때 부서별로 
	// 사원의 리스트를 가져온다.
	$("#dept").on("change",function(){
		var dept_pk = $(this).val();
		if(dept_pk == 0){
			memList();
		}else{
			deptMemList(dept_pk);
			
		}

	})
	
	// 프로젝트 생성버튼을 클릭시 해당하는 펑션을 로드한다. 그리고 모달을 띄운다.
	$("#registerBtn").click(function(){
		deptList();
		memList();
		$("#projectInsert").modal();
		
	})
	
	
	//Date picker
	$('.datepicker').datepicker({
		autoclose : true
	});
	// Getter
	var dateFormat = $(".datepicker").datepicker("option", "dateFormat");
	// Setter
	$(".datepicker").datepicker("option", "dateFormat", "yy-mm-dd");
	

	
	// 참여자 추가
	$("#memList").on("click",".memId",function(){
		
		var mem_id = $(this).attr('id');
		$("#"+mem_id+"").remove();
		var htmlCode = $(this).clone();
		$("#memList2").append(htmlCode);
	
		
	});
	
	// 참여자 삭제
	$("#memList2").on("click",".memId",function(){
		
		var mem_id = $(this).attr('id');
		$("#"+mem_id+"").remove();
		var htmlCode = $(this).clone();
		$("#memList").append(htmlCode);
		
	});

	
});
// 모달 닫기 
function Cancel(){
//	$("#projectInsert").close();
	$("#memList div").remove();
	$("#memList2 div").remove();
	$("#dept option").remove();
	$('#projectInsert').modal('toggle');
	$("#insert").reset();
	deptList();
	memList();
}

// projectinsert 저장폼
function projectSubmit(){
	$("#memList div").remove();
	$("#insert").submit();
}


// 사원리스트
function memList(){
	var member_id=$("input[name='mem_id']").val();
	// 사원목록을 가져오는 메서드 
	$.ajax({
		url : 'memList',
		type : 'post',
		dataType: 'json',
		success : function(data){
			var htmlCode ="";
			var leng = $("#memList2 div").length;
			$.each(data,function(i,v){
				
				if(leng==0){
					if(v.mem_id != member_id){
					htmlCode +="<div class='memId' id='"+v.mem_id+"'>"+"("+v.clsf_nm+")"+v.mem_nm+"("+v.dept_nm+")";
					htmlCode +="<input type='hidden' name='mem_id' value='"+v.mem_id+"'></div>";
					}
				}else{
					var keyvalue = null;
					for(var i = 0; i <= leng-1;i++){
						var mem_id = $("#memList2 div:eq("+i+")").attr('id');
							if(v.mem_id == mem_id){
								keyvalue = "들어옴";
							}
					}
					if(keyvalue == null){
						if(v.mem_id != member_id){
						htmlCode +="<div class='memId' id='"+v.mem_id+"'>"+"("+v.clsf_nm+")"+v.mem_nm+"("+v.dept_nm+")";
						htmlCode +="<input type='hidden' name='mem_id' value='"+v.mem_id+"'></div>";
						}
					}
					
				}

			});
			
			$("#memList").html(htmlCode);
		}
	});// ajax끝
	
}

// 해당부서에 사원리스트 
function deptMemList(dept_pk){
	var member_id=$("input[name='mem_id']").val();
	// 부서별 회원 리스트
	$.ajax({
		url : 'memDeptList',
		type : 'post',
		dataType: 'json',
		data:"dept_pk="+dept_pk,
		success : function(data){
			var htmlCode ="";
			var leng = $("#memList2 div").length;
			$.each(data,function(i,v){

				if(leng==0){
					if(v.mem_id != member_id){
					htmlCode +="<div class='memId' id='"+v.mem_id+"'>"+"("+v.clsf_nm+")"+v.mem_nm+"("+v.dept_nm+")";
					htmlCode +="<input type='hidden' name='mem_id' value='"+v.mem_id+"'></div>";
					}
				}else{
					var keyvalue = null;
					for(var i = 0; i <= leng-1;i++){
						var mem_id = $("#memList2 div:eq("+i+")").attr('id');
							if(v.mem_id == mem_id){
								keyvalue = "들어옴";
							}
					}
					if(keyvalue == null){
						if(v.mem_id != member_id){
						htmlCode +="<div class='memId' id='"+v.mem_id+"'>"+"("+v.clsf_nm+")"+v.mem_nm+"("+v.dept_nm+")";
						htmlCode +="<input type='hidden' name='mem_id' value='"+v.mem_id+"'></div>";
						}
					}
					
				}

			});
			
			$("#memList").html(htmlCode);
		}
	});// ajax끝

}


//부서 리스트
function deptList(){
	
	//부서리스트를 가져오기 위한 AJAX
	$.ajax({
		url : 'deptList',
		type : 'post',
		dataType: 'json',
		success : function(list){
			var htmlCode ="";
			htmlCode += "<option value='"+0+"'>전체</option>";
			$.each(list,function(i,v){
	    		htmlCode += "<option value='"+v.dept_pk+"'>"+v.dept_nm+"</option>";	
			});
			
			$("#dept").html(htmlCode);
		}
	});// ajax끝
	
}

// 프로젝트 정보
function selectInfo(prjct_pk){
	
	$.ajax({
		url : 'projectInfo',
		type : 'post',
		data : 'prjct_pk='+prjct_pk,
		dataType: 'json',
		success : function(data){
			var term = data.prjct_begin.substring(0,10)+" ~ "+data.prjct_end.substring(0,10);
			$("#prjct_fk").val(data.prjct_pk);
			$("#prjct_nm").text(data.prjct_nm);
			$("#prjct_cn").text(data.prjct_cn);
			$("#term").text(term);
		}
		
	});// ajax 끝 
	
	
	$("#acceptModal").modal();
}
// 승낙 check
function acceptCheck(x){
	console.log("수락이면 Y, 거절이면 N : "+x);
	
	if(x=='Y'){
		swal({
			  title: '프로젝트 참여 수락',
			  text: '프로젝트를 참여하시겠습니까?',
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#8cd4f5',
			  confirmButtonText: '수락',
			  cancelButtonText: '취소',
			}).then(
			  function(result) {
				  accept_success('Y');
				  // 05-11 1:36  수정 - 문성철
				  swal({
					  title : '프로젝트 참여완료',
					  text : '해당 프로젝트로 참여합니다.',
					  type : 'success'
				  }).then(
					  function(result){
						  console.log('프로젝트 참여 완료 후 프로젝트 상세 페이지로 이동');
						  alert('이동');
						  location.href='joinProject';
					}	  
				)
			  }, function(dismiss) {
				  swal("취소", "프로젝트 요청을 취소합니다.", "error");
			  }
		);
	}if(x=='N'){
		swal({
			  title: '프로젝트 참여 거절',
			  text: '프로젝트를 거절하시겠습니까?',
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#8cd4f5',
			  confirmButtonText: '거절',
			  cancelButtonText: '취소',
			}).then(
			  function(result) {
				  swal("거절", "프로젝트 참여를 거절하였습니다..", "success");
			  }, function(dismiss) {
				  swal("취소", "프로젝트 요청을 취소합니다.", "error");
			  }
		);
	}
}
// 요청수락
function accept_success(){
	$.ajax({
	    url : "acceptProject",
	    type : "POST",
	    data :  $("#acceptForm").serialize(),
	    dataType: "text",
	    success : function(res){
	    	
	    }
	});//ajax끝
};

