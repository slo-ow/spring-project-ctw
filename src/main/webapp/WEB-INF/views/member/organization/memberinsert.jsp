<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<!--
* @Description : 조직원 등록 페이지
* @author 박창현 (mepch@naver.com)
* @version 1.0
* @since 2017.04.17
-->
<script>
	// Date Picker 옵션
	$(function() {
		//Date picker
		$('#member_date').datepicker({
			autoclose : true
		});

		$('#member_birth').datepicker({
			autoclose : true
		});
	});
	// 라디오버튼 값에서 선택한 값 가져오기
	var checked = $(":input:radio[name=mem_sex]:checked").val();


/* 	// 이미지모달 폼에서 등록한 사진으로 controller에 접근하는 함수
	$("#imageForm").submit(function() {
		$(this).attr('action', 'photoInsert');
		$(this).append('<input type="hidden" name="mem_id" value="${member.mem_id}"/>');
	}); */
	
</script>

<script type="text/javascript">

	// Image 미리보기(API적용)
	$(function() {
            $("#imgInp").on('change', function(){
                readURL(this);
            });
    });
        function readURL(input) {
            if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                    $('#blah').attr('src', e.target.result);
                }

              reader.readAsDataURL(input.files[0]);
            }
        }
        
function excelCheck() {
   var file = $("#excelFile").val();
   if (file == "" || file == null) {
       alert("파일을 선택해주세요.");
       return false;
   } else if (!checkFileType(file)) {
       alert("엑셀 파일만 업로드 가능합니다.");
       return false;
   }
/* 	var test = $("#excelUploadForm").serialize();
	location.href="excelUpload?code="+test; */
   
     if (confirm("업로드 하시겠습니까?")) {
  	 console.log('Excel파일 일괄등록');
       var options = {
          	success : successFunction,
          	error : errorFunction,
          	dataType : 'json'
       };
       $("#excelUploadForm").ajaxSubmit(options);
   }  
}

function checkFileType(filePath){
	var fileFormat = filePath.split(".");
	if(fileFormat.indexOf("xlsx")>-1){
		return true;
	}else{
		return false;
	}
}	

function successFunction(res){	
	alert("신규직원이 업로드 되었습니다.");
	location.href="massiveSuccess";
}

function errorFunction(){
	alert("에러가 발생하였습니다.");
}

function docDown(){
	location.href="download/massiveDown?real_name=일괄등록표준문서.xlsx";
	
}
     
</script>


<section class="content-header">
	<h1>
		조직원관리 <small>조직원등록</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 조직원관리</a></li>
		<li class="active">조직원등록</li>
	</ol>
</section>

<!-- content -->
<section class="content">
	<!-- box -->
	<div class="box">
		<!-- body -->
		<div class="box-body">
			<div class="container">
				<label>조직원 정보를 입력하시면, 조직원을 직접 등록할 수 있습니다.	</label>
				<br>
				<label>조직원을 일괄등록 하시려면, 일괄등록 버튼을 눌러주세요.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<span>
						<button type="button" class="btn btn-primary btn-sm btn-info" data-toggle="modal" data-target="#massmodal">일괄등록</button>
					</span>
				</label>
				<br>
				<!-- 등록양식 Form -->
				<form action="memberInsert" method="post">
				<br>
				
					<label>조직원 Image : </label>
					<div class="input-group">
						<!--  Image 미리보기 -->
						<img id="blah" src="#" width="150px" height="180px" />
						<br>
						<!-- Image 파일 등록 -->
						<input type='file' class="filestyle" data-size="sm" id="imgInp" name="mem_photo"/>
					</div>							        
					<br>		        				
				
					<div class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
						<!-- 사원ID 입력 -->
						<input id="member_code" type="text" class="form-control" name="mem_id" placeholder="사원코드">
					</div>
					
		 			<div class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
						<!-- 사원명 입력 -->
						<input type="text" class="form-control" name="mem_nm" placeholder="사원명">
					</div><br>
					
					<div class="input-group">
						<span class="input-group-addon">비밀번호</span> 
						<!-- 비밀번호입력 -->
						<input type="password" class="form-control" name="mem_pass" placeholder="비밀번호">
					</div>
		
					<div class="input-group">
						<span class="input-group-addon">전화번호</span> 
						<!-- 전화번호입력 -->
						<input type="text" class="form-control" name="mem_telno" placeholder="전화번호">
					</div>
		
					<div class="input-group">
						<span class="input-group-addon">E-Mail</span> 
						<!-- E-mail입력 -->
						<input type="email" class="form-control" name="mem_email" placeholder="E-Mail">
					</div>
		
					<div class="input-group">
						<span class="input-group-addon">주 소</span> 
						<!-- 주소입력 -->
						<input type="text" class="form-control" name="mem_addr" placeholder="주소">
					</div><br> 
					
					<label for="date">입사일:</label>
					<div class="form-group">
						<div class="input-group date">
							<div class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</div>
							<!-- 입사일 입력 DatePicker -->
							<input type="text" class="form-control pull-right" name="mem_date" id="member_date">
						</div>
					</div>
					
					<div class="form-group">
						<label for="pwd">생년월일:</label>
						<div class="form-group">
							<div class="input-group date">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<!-- 생년월일 입력 DatePicker -->
								<input type="text" class="form-control pull-right" name="mem_brthdy" id="member_birth">
							</div>
						</div>
					</div>
		 
		            <div class="form-group">
		            	<!-- 부서선택 SelectBox -->
		            	<label>부서선택 :</label>
		                  <select class="form-control" name="dept_pk">
		                    <option value=1>개발부</option>
		                    <option value=2>총무부</option>
		                    <option value=3>영업부</option>
		                    <option value=4>기획부</option>
		                  </select>
		                </div>
		
		             <div class="form-group" >
		             	<!-- 직급선택 SelectBox -->
		             	<label>직급선택 :</label>
		                  <select class="form-control" name="clsf_pk">
		                    <option value=1>인턴</option>
		                    <option value=2>사원</option>
		                    <option value=3>대리</option>
		                    <option value=4>과장</option>
		                    <option value=5>부장</option>
		                    <option value=6>이사</option>
		                    <option value=7>사장</option>
		                  </select>
		             </div>
		                
		             <div class="form-group">
		             	<label>권한설정 :</label>
		                  <select class="form-control" name="grad_pk">
		                    <option value=1>사원</option>
		                    <option value=5>부서관리자</option>
		                    <option value=9>총괄관리자</option>
		                  </select>
		             </div><br>
					
					<div>
						<!-- 성별선택 RadioButton -->
						<label>성별선택 :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						<input type="radio" name="mem_sex" value="남성" style="width: 20px; height: 20px;">
								<img src="${pageContext.request.contextPath }/resources/member/image/man.png">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="mem_sex" value="여성" style="width: 20px; height: 20px;"> 
								<img src="${pageContext.request.contextPath }/resources/member/image/girl.png">
						</label>
					</div><br>
					
					<div style="text-align: center;">
						<input type="submit" class="btn btn-link" value="등록하기"> 
						<input type="reset" class="btn btn-link" value="다시입력">
					</div>
				</form><br>
			</div>							
				</div>
				<!-- body -->
			</div>
			<!-- box -->
		</section>
		<!-- end content -->

	<!-- 입력양식 MODAL -->
	<div class="modal modal-success" tabindex="-1" id="massmodal" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">일괄등록</h4>
					<br>
					<p>* 아래 등록양식을 다운로드 하여 양식에 맞게 입력한 후 업로드 해주시기 바랍니다.</p>
					<p>* 이미 존재하는 조직원의 정보는 업로드 하는 정보로 업데이트 됩니다.</p>
				</div>
				<form id="excelUploadForm" name="excelUploadForm" enctype="multipart/form-data" method="post" action= "excelUploadAjax">
				<div class="modal-body">
					<div class="modal-body" style="text-align: center;">
						<a class="btn btn-app bg-olive"><i class="fa fa-save" onclick="docDown();">&nbsp;&nbsp;입력양식다운로드</i></a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						<a class="btn btn-app bg-olive"><i class="fa fa-info">&nbsp;&nbsp;업로드방법보기</i></a>
						<br> <br>
						<div style="text-align: left">
							<div class="inputfile-box">
									<span class="file-button"></span> 
     			                <label class="control-label" for="inputWarning"><i class="fa fa-file-excel-o"></i> 입력양식업로드</label>
     			               <input type="file" id="excelFile" name="excelFile" class="inputfile"> 
							</div>
						</div>
					</div>
				</div>
				</form>
				
				<div class="modal-footer">
					<input type="submit" value="업로드" class="btn btn-outline" onclick="excelCheck()">
					<button type="button" class="btn btn-outline pull-right" data-dismiss="modal">닫기</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
