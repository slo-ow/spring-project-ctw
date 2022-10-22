/**
 * <pre>
 * 		게시판활성에 따른 처리
 * </pre>
 * @author 박혜윤(hyeonwjd@naver.com)
 * @since 2017. 5. 2.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2017. 5. 2.    박혜윤       최초작성
 * Copyright (c) 2017 by CTW All right reserved
 * </pre>
 */
$(function(){
	// 게시판활성적용
	var grad_pk =$("#grad").val();
	$.ajax({
		url : 'baordListAll',
		type : 'get',
		dataType: 'json',
		success : function(list){
			console.log("로그인한 등급:"+grad_pk);
			var htmlCode ="";
			var code="";
			$.each(list,function(i,v){
				// 최고관리자화면
				if(grad_pk>=9){
					console.log("최고관리자");
					code="<a href='/ctw/manager/manageList'><i class='fa fa-circle-o'></i>게시판관리</a>";
					if(v.actvty_pk=='notice' && v.actvty_nm == 'Y'){
						htmlCode += "<li><a href='/ctw/manager/notice'><i class='fa fa-circle-o'></i>공지사항게시판</a></li>";
					}else if(v.actvty_pk=='free' && v.actvty_nm == 'Y'){
						htmlCode += "<li><a href='/ctw/manager/free'><i class='fa fa-circle-o'></i>자유게시판</a></li>";
					}else if(v.actvty_pk=='gallery' && v.actvty_nm == 'Y'){
						htmlCode += "<li><a href='/ctw/manager/gallery'><i class='fa fa-circle-o'></i>갤러리게시판</a></li>";
					}
				// 부서관리자 화면
				}else if(grad_pk>=5){
					console.log("관리자");
					if(v.actvty_pk=='notice' && v.actvty_nm == 'Y'){
						htmlCode += "<li><a href='/ctw/manager/notice'><i class='fa fa-circle-o'></i>공지사항게시판</a></li>";
					}else if(v.actvty_pk=='free' && v.actvty_nm == 'Y'){
						htmlCode += "<li><a href='/ctw/manager/free'><i class='fa fa-circle-o'></i>자유게시판</a></li>";
					}else if(v.actvty_pk=='gallery' && v.actvty_nm == 'Y'){
						htmlCode += "<li><a href='/ctw/manager/gallery'><i class='fa fa-circle-o'></i>갤러리게시판</a></li>";
					}
				// 조직원화면	
				}else{
					console.log("조직원");
					if(v.actvty_pk=='notice' && v.actvty_nm == 'Y'){
						htmlCode += "<li class='active'><a href='/ctw/member/companynotice'><i class='fa fa-circle-o'></i>전체공지사항</a></li>" +
								"<li><a href='/ctw/member/deptnotice'><i class='fa fa-circle-o'></i>부서게시판</a></li>";
					}else if(v.actvty_pk=='free' && v.actvty_nm == 'Y'){
						htmlCode += "<li><a href='/ctw/member/free'><i class='fa fa-circle-o'></i>자유게시판</a></li>";
					}else if(v.actvty_pk=='gallery' && v.actvty_nm == 'Y'){
						htmlCode += "<li><a href='/ctw/member/gallery'><i class='fa fa-circle-o'></i>갤러리게시판</a></li>";
					}
				}
				
			});
			$("#boardmanage").html(htmlCode);
			$("#supermanagermenu").html(code);
		},
		error : function(){
			alert("오류");	
		}
	});// ajax종료
	
	
	
	
	
	
});