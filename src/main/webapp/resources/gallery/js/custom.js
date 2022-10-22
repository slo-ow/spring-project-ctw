/**
 * <pre>
 *       게시판활성에 따른 처리
 * </pre>
 * @author 박혜윤(hyeonwjd@naver.com)
 * @since 2017. 5. 3.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일           수정자       수정내용
 * --------     --------    ----------------------
 * 2017. 5. 2.    박혜윤       최초작성
 * Copyright (c) 2017 by CTW All right reserved
 * </pre>
 */
var contextPath;
var g_mem_id;
var root = "/ctw/ctwimgfile/";
$(function(){
   
   goPage(1);
   
   // like
   $("#listdiv").on("click",".likeimg",function(){
      var mem_id = $("#memId").val();
      var photo_fk = $(this).parent().parent().attr("id");
      var params = 'photo_fk='+photo_fk+'&mem_id='+mem_id;
      $.ajax({
         url : 'likeProcess',
         type : 'post',
         data : params,
         dataType: 'json',
         success:function(data){
            alert(data.test);
         }
      });
      
      location.reload();
      
   });
   
   // 사진보기모달
   $(".modal").on('show.bs.modal', function() {
        setTimeout(function() {
           var swiper = new Swiper('.swiper-container', {
               pagination: '.swiper-pagination',
               nextButton: '.swiper-button-next',
               prevButton: '.swiper-button-prev',
               slidesPerView: 1,
               mousewheelControl:true,
               paginationClickable: true,
               spaceBetween: 30,
               loop: true
           });
        }, 500);
    });
   
   /* 이미지미리보기 */
   $("#imgInp").on('change', function(){
        readURL(this);
    });
   
   function readURL(input) {
          if (input.files && input.files[0]) {
          var reader = new FileReader();

          reader.onload = function (e) {
                  $('#imageView').attr('src', e.target.result);
              }

            reader.readAsDataURL(input.files[0]);
          }
      }
   
      
   /* 유효성검사 */
   $("#galleryInsertForm").submit(function() {

      if (galleryInsertForm.photo_sj.value == "") {
         swal("제목를 입력하세요","", "warning");
         galleryInsertForm.photo_sj.focus();
         return false;
      }
      if (galleryInsertForm.photo_cn.value == "") {
         swal("내용를 입력하세요","", "warning");
         galleryInsertForm.photo_cn.focus();
         return false;
      }
      if (galleryInsertForm.file.value == "") {
         swal("이미지를 첨부하세요","", "warning");
         galleryInsertForm.photo_cn.focus();
         return false;
      }
      $(this).attr('action', 'potoInsert');
   });
   
   
   // 사진클릭
	$("#listdiv").on("click",".firstimage",function(){
		var photo_pk = $(this).attr("id");
//		alert(photo_pk);
		$.ajax({
			url : 'selectInfo',
			type : 'post',
			data : "photo_pk="+photo_pk,
			dataType: 'json',
			success:function(data){
				var htmlCode = "";
				$.each(data,function(i,v){
					htmlCode += "<div class='swiper-slide' ><img src='" + root + v.photo_f_nm + "' style='width: 100%;' ></div>";
					
				});
				
				$("#modaldiv").html(htmlCode);
				
			}, error : function(){
				alert("오류");		
			}
			
		});// ajax 종료
		
	});
	
	// 삭제
	$("#listdiv").on("click",".delBtn",function(){
		var photo_pk = $(this).attr("id");
//		alert(photo_pk);
		$.ajax({
			url : 'photoDelete',
			type : 'post',
			data : "photo_pk="+photo_pk,
			dataType: 'text',
			seccess:function(res){
				swal("삭제성공", "삭제되었습니다.", "success");
			}
			
		});
		
		location.reload();
		
	});
   
   
});
// AJAX Paging처리
function goPage(page){
   var url = "pagingMove";   // controller에 있는 페이징 처리 함수
   
   // 나중에 이부분은 유저가 글 출력 갯수를 입력받을수 있게 만들어 보자.
//    var screenSize = 6;
   
   // <input type="hidden" id="currentPage" name="currentPage"/>에 page값을 넣어준다... 신기함
   $("#currentPage").val(page);
   
   // serialrize를 이용하여 form안에 있는 값들을 가져온다.
   var data = $('#pagingForm').serialize();
   
   // ajax로 페이징 처리를 위해 parmeter값을 넘긴다.
   $.ajax({
      url : url,
      data : data,
      success : function(data){   // 값을 성공적으로 넘겨주면 controller에서 json타입 자료를 반환 해 주는데, 이것을 'data'라는 이름으로 가져온다.
          var htmlCode = "";
          var root = "/ctw/ctwimgfile/";
         // data.page에 있는 게시글 정보를 for문을 돌려서 html문을 만들어준다.
         $(data.page.dataList).each(function(i, v){   // e = this
            var string = v.photo_date;
            var date = string.substring(0,10);
            if(data.page.dataList==null){
                     htmlCode += "<div><font color='navy'>업로드된 사진이 없습니다.</font></div>";
                  }
            if(v.delcheck=='N'){
                     htmlCode += "<div class='col-sm-2 col-md-2 thumbnail-height'>";
                     htmlCode += "     <div class='thumbnail' style='border-radius:10px;'>";
                     htmlCode += "           <div style='height:60%;' class='centered'>";
                     htmlCode += "                 <a href='#'data-toggle='modal' data-target='#imageModal' data-backdrop='static' ><img src='" + root + v.imglist[0].photo_f_nm + "' alt='' class='img-responsive firstimage' id='"+v.photo_pk+"' style='height: 120px;' ></a>";
                     htmlCode += "                  <span class='badge bg-purple num'>"+v.file_count+"개</span>";
                     htmlCode += "           </div>";
                     htmlCode += "           <div class='caption'>";
                     htmlCode += "                 <input type='hidden' id='photoPk' value="+v.photo_pk+"/>";
                     htmlCode += "                 <strong>"+v.photo_sj+"</strong>";
                     htmlCode += "                 <div>"+v.photo_cn+"</div>";
                     htmlCode += "                 <div style='font-size: 11px;'>"+date+"</div>";
                     htmlCode += "                 <div>"+v.mem_nm+"</div>";
                     htmlCode += "                 <div id="+v.photo_pk+"><a href='#'><img class ='likeimg' src='"+contextPath+"/resources/gallery/image/like.png' width='25px'></a>";
                     htmlCode += "                 <sapn style='font-size: 8px;'>"+v.like_count+"명</span></div>";
                     if(g_mem_id == v.mem_id){
                    	 htmlCode += "             <div align='right'><input type='button' id="+v.photo_pk+" class='delBtn btn btn-danger btn-xs'  value='삭제'/></div></div>";
                     }
                     htmlCode += "           </div>";
                     htmlCode += "     </div>";
                     htmlCode += "</div>";
               }

            
         });
                     
         
         $("#pagingResult").html(data.page.pagingHTML);
         $("#listdiv").html(htmlCode);
         $("searchType").val(data.page.searchType);
         $("searchWord").val(data.page.searchWord);
      },
         dataType: 'json'
      });
}

function Cancel(){
   $("#photo_sj").val('');
    $("#photo_cn").val('');
    $("#galleryModal").modal('hide');   
}