<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>
<body>
<script>

	$(function() {
		
		
		
		$(".vote").on("click","#add",function() {
			var size = $(".input").length;
			if (size < 5) {
				var codeHTML;
				codeHTML = "<input type='text' class='input form-control aaa'/>";
				$("#addQ").append(codeHTML);
			} else {
				alert("문항은 5개까지 작성하실 수 있습니다.");
			}
		});
		
		$(".vote").on("click","#del",function(){
// 			$("#addQ").remove();
		});
		
		
		//Date picker
		$('#datepicker').datepicker({
			autoclose : true
		});
		// Getter
		var dateFormat = $("#datepicker").datepicker("option", "dateFormat");
		// Setter
		$("#datepicker").datepicker("option", "dateFormat", "yy-mm-dd");

	});
</script>

<section class="content-header">
	<h1>
		온라인투표 <small>투표등록</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-check-circle"></i> 온라인투표</a></li>
		<li class="active">투표등록</li>
	</ol>
</section>
<section class="content">
	<div class="box">
		<div class="box-header">
			<h3 class="box-title">투표등록</h3>
		</div>
		<!-- /.box-header -->
		<div class="box-body no-padding">
			<form>
				<table class="table table-condensed">
					<tr>
						<td>질문</td>
						<td><input type="text" class="form-control" /></td>
					</tr>
					<tr>
						<td>문항</td>
						<td class="vote">
							<div>
							<style>
								.aaa{
									width:70%;
								    float:left;
								}
								.bbb{
									width:5%;	
									float:left;
								}
							</style>
				
								<input type="text" class="form-control aaa"/> &nbsp;&nbsp;
								<a href="#" class="bbb btn btn-default"  id="add">+</a> 
								<a href="#" type="button" class="bbb btn btn-default" id="del">-</a>
<!-- 								<input type="button" class="btn btn-default" class="del" value="-" /> -->
							</div>
							<div  id="addQ" ></div>
						</td>
					</tr>
					<tr>
						<td>투표마감기간</td>
						<td>
							<!-- Date -->
							<div class="form-group">
								<div class="input-group date">
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
									<input type="text" class="form-control pull-right" id="datepicker">
								</div>
								<!-- /.input group -->
							</div> <!-- /.form group -->
						</td>
					</tr>
					<tr>
						<td>참여설정</td>
						<td>전사 <input type="checkbox" checked />&nbsp;&nbsp;&nbsp;
							부서 <input type="checkbox" /></td>
					</tr>
					<tr>
						<td></td>
						<td align="right"><input type="button"
							class="btn btn-default" value="등록" /></td>
					</tr>

				</table>
			</form>
		</div>
		<!-- /.box-body -->
	</div>
	<!-- /.box -->

<div class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
        <p>One fine body&hellip;</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


</section>
</body>