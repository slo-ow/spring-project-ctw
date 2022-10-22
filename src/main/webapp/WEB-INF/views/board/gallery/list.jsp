<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<!--
* @Description : 갤러리게시판의 초기 화면
* @author 박혜윤 (hyeonwjd@naver.com)
* @version 1.0
* @since 2017.04.27
-->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/gallery/css/swiper.css">
<!-- 갤러리게시판의 css -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/gallery/css/custom.css" />


<section class="content-header">
	<h1>
	  커뮤니티게시판
	  <small>갤러리게시판</small>
	</h1>
	<ol class="breadcrumb">
	  <li><a href="#"><i class="fa fa-dashboard"></i>커뮤니티게시판</a></li>
	  <li class="active">갤러리게시판</li>
	</ol>
</section>
<!-- content -->
<section class="content">
	<div class="box" style="padding: 10px;">
	<form name="pagingForm" id="pagingForm">
		<input type="hidden" id="memId" value="${member.mem_id }"/>
		<input type="hidden" id="currentPage" name="currentPage"/>
		<table width=100%>
		<tr align=left>
			<td>
				<!-- 게시글작성 Modal 호출 버튼 -->
				<input type="button" id="registerBtn" class="btn btn-info" data-toggle="modal" data-target="#galleryModal"  data-keyboard="false" data-backdrop="static" value="글작성" />
			</td>
			<td align="right">
				<!-- 이 table안에 있는 값들은 큰 form안에 있기 때문에 name값으로 serialize를 할 경우 값을 가져올수 있다. -->
				<div class="form-inline">
				<select name="searchType" class="form-control">
					<option selected value=photo_sj>글제목
					<option value=photo_cn>글내용
					<option value=mem_nm>작성자
				</select>
				<input name="searchWord" class="form-control" >&nbsp;<input type=button value="검색" class="btn btn-info" onclick="goPage(1)" />
				</div>
			</td>
		</tr>
		</table>
		
		
		
		<!-- body -->
		<div class="box-body container-fluid">
			<!-- 사진게시물  -->
			<div class="row" id="listdiv">
			
			    <!-- 갤러리 리스트 -->
			</div>
			  <!-- 사진게시물 끝  -->
		</div>
					
		<!-- body end -->
		<%-- 페이징 번호 뿌리는곳 --%>
		<div id="pagingResult" align="center"></div>
		
	
</form>
</div>
		
</section>
<!-- end content -->

<!-- 게시글작성 등록 Modal -->
<div class="modal fade" id="galleryModal" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
		<form action="" id="galleryInsertForm" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="mem_id" value="${member.mem_id}"/>
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">이미지 올리기</h4>
			</div>
			<div class="modal-body">
				
				<table class="table table-condensed">
					<tr>
						<td>제목</td>
						<td>
							<input type="text" class="form-control" id="photo_sj" name="photo_sj" />
						</td>
					</tr>
					<tr>
						<td>내용</td>
						<td><input type="text" class="form-control" id="photo_cn" name="photo_cn" /></td>
					</tr>
					<!-- 이미지 첨부만 가능 -->
					<tr>
						<td>사진첨부</td>
						<td><input type="file" accept="image/*" class="imgInp filestyle" class="form-control" name="file" multiple/>
	
							</td>
						</tr>
					<!-- 이미지 미리보기 -->
					<tr>
						<td colspan="2" align="center">
							<img id="imageView" src="#" alt="image" width="20%"/>
						</td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<input type="submit" class="btn btn-default" value="등록" />
				<button type="button" class="btn btn-default" data-dismiss="modal" onclick="Cancel()">취소</button>
			</div>
		</form>
		</div>
	</div>
</div>
<!-- 게시글작성 등록 Moda 끝 -->


<!-- 사진클릭시 Modal -->
<div class="modal fade" id="imageModal" role="dialog" >
	<div class="modal-dialog modal-fullsize" style="margin-top: 120px;">

		<!-- Modal content-->
			
		<button type="button" class="close" data-dismiss="modal">&times;</button>
		<!-- 사진내용 -->
		<div class="swiper-container">
		    <div class="swiper-wrapper" id="modaldiv">
		    <!-- 이미지리스트 -->
		    </div>
		    <!-- Add Pagination -->
		    <div class="swiper-pagination"></div>
		</div>


	</div>
</div>
<!-- 사진클릭시 Modal 끝 -->

<!-- 갤러리게시판 javascript -->
<script src="${pageContext.request.contextPath }/resources/gallery/js/swiper.js"></script>
<script	src="${pageContext.request.contextPath }/resources/gallery/js/custom.js"></script>
<script>
contextPath = "${pageContext.request.contextPath }";
g_mem_id = "${member.mem_id}";
</script>