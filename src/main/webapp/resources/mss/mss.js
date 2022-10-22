/**
 * @Description : 쪽지보내기스크립트 , 모달 
 * @author 김완수
 * @version 1.0
 * @since 2017.05.13
 * 
 */
$(function(){
	// data table
	  $('#tb').DataTable({
	         "order": [[ 3, "desc" ]],
	         "searching" : true,
	         "ordering" : true,        
	         "autoWidth" : false,
	         "pagingType": "full_numbers",
	         "scrollY": 580,
	         "scrollCollapse": false,
	         "pageLength": 15,
	         "language" : {
	            "search" : "<span>검색</span> _INPUT_", //search
	            "lengthMenu" : '<div class="form-group">'
	            	  +'<label for="sel1">Select list:</label> :   <select class="form-control" id="sel1">'
	                  + '<option value="15" selected="selected">15</option>'
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
		 	
	
	
	deptList();
	memList();
	$("#dept").on("change",function(){
		var dept_pk = $(this).val();
		if(dept_pk == 0){
			memList();
		}else{
			deptMemList(dept_pk);
			
		}
		
	})
	
	$("#memberList").on("click",".memId",function(){
		var mem_id = $(this).attr("id");
		var mem_text = $(this).text();
	
		$("#recp_id").val(mem_id);
		$(".fakeId").val(mem_text);
		
	});
	
	$("#memSelect").click(function(){
		
		var fakeId = $(".fakeId").val();
		$("#fakeId").val(fakeId);
		$('#memList').modal('toggle');
	})
	
	
	$("#tbodylist").on("click","tr",function(){
			
		var mss_pk = $(this).attr('id');
		var division = $("#division").val();
		
		
		
		$.ajax({
			  url: "mssInfo",
			  type:"post",
			  dataType: 'json',
			  data: {'mss_pk':mss_pk,"division":division},
			  success: function(res){
				  
				  var htmlCode = "";
				  htmlCode +="<label class='control-label col-sm-8 idfind' id='"+res.mem_id+"'>"+res.mem_nm+"("+res.dept_nm+","+res.clsf_nm+")"+"</label>";
				  htmlCode +="<input type='hidden' id='idfind2' value='"+res.mss_pk+"'>";
				  $("#infoMem").html(htmlCode);
				  
				  htmlCode ="<label class='control-label col-sm-8' id=''>"+res.mss_sj+"</label>";
				  $("#infoSj").html(htmlCode);
				  
				  htmlCode ="<label class='control-label col-sm-8' id=''>"+res.mss_date+"</label>";
				  $("#infoDate").html(htmlCode);
				  
				  htmlCode ="<label class='control-label col-sm-8' id=''>"+res.mss_cn+"</label>";
				  $("#infoCn").html(htmlCode);
				  
  			      if(res.mss_f_nm == '1234'){
					  htmlCode ="<label class='control-label col-sm-8' id=''>파일이 없습니다.</label>";
					  $("#infoFile").html(htmlCode);
					  
				  }else{
					  htmlCode ="<a href='mssFileDown?real_name="+res.mss_f_nm+"&file_nm="+res.mss_f_pk+"'><label class='control-label col-sm-8' id=''>"+res.mss_f_nm+"</label></a>";
					  $("#infoFile").html(htmlCode);
				  }
		
				  $("#mssInfo").modal();
			  }
			  
			  
			});


		
	})	


	$("#insertBtn").click(function(){
		
		swal({
			  title: '쪽지발송',
			  text: '쪽지를 발송 하겠습니까?',
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#8cd4f5',
			  confirmButtonText: '발송',
			  cancelButtonText: '취소',
			}).then(function(){
				 swal({
					  title: '발송완료',
					  text: '쪽지가 발송 되었습니다.',
					  type: 'success',
					}).then(function(){
						insertMss();
					})
			}, function() {
				  swal("취소", "쪽지 발송을 취소합니다.", "error");
				  modalCancel();
			}
		);
		
	})
	/*	*/
	
	
	
	
})
	
// insert 메서드 
function insertMss(){
	$("#insert").submit();
}






function mssDelete(){
	var pk = $("#idfind2").val();
	var division = $("#division").val();
	swal({
		  title: '쪽지삭제',
		  text: '쪽지를 삭제 하겠습니까?',
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#8cd4f5',
		  confirmButtonText: '삭제',
		  cancelButtonText: '취소',
		}).then(function(){
			 swal({
				  title: '삭제완료',
				  text: '쪽지가 삭제 되었습니다.',
				  type: 'success',
				}).then(function(){
					insertMss();
					location.href = "mssDelete?mss_pk="+pk+"&division="+division;
				})
		}, function() {
			  swal("취소", "쪽지 삭제를 취소합니다.", "error");
		}
	);
	
	
	
}

function insertRect(){
	
	var id = $('.idfind').attr('id');
	var fake = $('.idfind').text();
	

	$("#recp_id").val(id);
	$("#fakeId").val(fake);
	
	$('#mssInfo').modal('toggle');
	$("#writeModal").modal();
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
			$.each(data,function(i,v){
				
					if(v.mem_id != member_id){
						htmlCode +="<div class='memId' id='"+v.mem_id+"'>"+v.mem_nm+"("+v.dept_nm+","+v.clsf_nm+")</div>";
					}

			});
			
			$("#memberList").html(htmlCode);
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
			$.each(data,function(i,v){
				
				if(v.mem_id != member_id){
					htmlCode +="<div class='memId' id='"+v.mem_id+"'>"+v.mem_nm+"("+v.dept_nm+","+v.clsf_nm+")</div>";
				}

			});
			
			$("#memberList").html(htmlCode);
		}
	});// ajax끝

}
//모달 닫기 
function memCancel(){
	$("#memberList div").remove();
	$("#dept option").remove();
	$(".fakeId").val('');
	$('#memList').modal('toggle');
	deptList();
	memList();
}
function modalCancel(){

	$('#writeModal').modal('toggle');
	$("#fakeId").val('');
	$(".filestyle").replaceWith($(".filestyle").val('').clone(true));
	deptList();
	memList();
}
function infoModalCancel(){
	var division = $("#division").val();
	if (division == 'recp') {
		window.location.reload(true); 
	}
	$('#mssInfo').modal('toggle');
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
