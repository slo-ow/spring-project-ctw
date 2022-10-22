<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>
<script	src="${pageContext.request.contextPath }/resources/project/js/custom.js"></script>


<section class="content">
<div class="row">
<div class="col-md-6">
   	<!-- 성비 -->
      <div class="box box-solid">
        <div class="box-header with-border" align="center">
          <h3 class="box-title">남녀 비율</h3>
        </div>
        <div class="box-body" id="chart">
        	<div id="legendDiv"></div>
          	<canvas id="pieChart" style="height:250px"></canvas>
        </div>
        <!-- /.box-body -->
      </div>
      <!-- /.box -->
     </div>
     <div class="col-md-6">
      <!-- 부서별 사원수 -->
       <div class="box box-solid">
         <div class="box-header with-border" align="center">
           <h3 class="box-title">사원수</h3>
         </div>
         <div class="box-body chart-responsive">
           <div class="chart" id="bar-chart" style="height: 290px;"></div>
         </div>
         <!-- /.box-body -->
       </div>
       <!-- /.box -->
       </div>
    </div>
    <!-- 성비, 사원수 묶음 끝 -->
   
     
	 <!-- TABLE: 프로젝트 진행사항 -->
	 <div class="box box-info">
	   <div class="box-header with-border">
	     <h3 class="box-title">프로젝트</h3>
	   </div>
	   <!-- /.box-header -->
	     <div class="box-body">
	     <div class="table-responsive">
	     <input type="hidden" id="mem_id" value="${member.mem_id}"/>
	       <table class="table no-margin">
	         <thead>
	         <tr>
	           <th>프로젝트명</th>
	           <th>팀장</th>
	           <th>진행상태</th>
	           <th>인원</th>
	         </tr>
	         </thead>
	         <tbody>
	         <!-- 프로젝트 리스트 -->
	       		<c:forEach var="pro" items="${projectList}" end="3">
	       			<tr>
	       				<td>${pro.prjct_nm}</td>
	       				<td>${pro.mem_id}</td>
						    <c:if test="${pro.progrs_fk == 1 }">
								<td><span class='label label-danger'>${pro.progrs_nm}</span></td>
							</c:if>
							<c:if test="${pro.progrs_fk == 2 }">
								<td><span class='label label-warning'>${pro.progrs_nm}</span></td>
							</c:if>
							<c:if test="${pro.progrs_fk == 3 }">
								<td><span class='label label-success'>${pro.progrs_nm}</span></td>
							</c:if>	
	       				<td>${pro.prjct_nmpr}</td>
	       			</tr>
	       		</c:forEach>
	         </tbody>
	       </table>
	     </div>
	     <!-- /.table-responsive -->
	   </div>
	   <!-- /.box-body -->
	   <div class="box-footer clearfix">
	     <a href="project" class="btn btn-sm btn-default btn-flat pull-right"><i class="fa fa-plus"></i>더보기</a>
	   </div>
	   <!-- /.box-footer -->
	 </div>
	 <!-- /.box -->
   
</section>
<!-- ChartJS 1.0.1 -->
<script src="${pageContext.request.contextPath }/resources/plugins/chartjs/Chart.js"></script>
<script>
$(function () {
	 //-------------
    //- PIE CHART -
    //-------------
    // Get context with jQuery - using jQuery's .get() method.
    var pieChartCanvas = $("#pieChart").get(0).getContext("2d");
    var pieChart = new Chart(pieChartCanvas);
    

    var PieData;
    var datas;
    var legend;
    var pieOptions = {
    	      //Boolean - Whether we should show a stroke on each segment
    	      segmentShowStroke: true,
    	      //String - The colour of each segment stroke
    	      segmentStrokeColor: "#fff",
    	      //Number - The width of each segment stroke
    	      segmentStrokeWidth: 2,
    	      //Number - The percentage of the chart that we cut out of the middle
    	      percentageInnerCutout: 50, // This is 0 for Pie charts
    	      //Number - Amount of animation steps
    	      animationSteps: 100,
    	      //String - Animation easing effect
    	      animationEasing: "easeOutBounce",
    	      //Boolean - Whether we animate the rotation of the Doughnut
    	      animateRotate: true,
    	      //Boolean - Whether we animate scaling the Doughnut from the centre
    	      animateScale: false,
    	      //Boolean - whether to make the chart responsive to window resizing
    	      responsive: true,
    	      // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
    	      maintainAspectRatio: true,
    	      legend: false,
    	      legendCallback: function(chart) {
    	    	    var text = [];
    	    	    text.push('<ul class="' + chart.PieData.mem_sex + '-legend">');
    	    	    for (var i = 0; i < chart.PieData.length; i++) {
    	    	      text.push('<li><span style="background-color:' + chart.PieData.color[i] + '">');
    	    	      if (chart.PieData.label[i]) {
    	    	        text.push(chart.PieData.label[i]);
    	    	      }
    	    	      text.push('</span></li>');
    	    	    }
    	    	    text.push('</ul>');
    	    	    return text.join("");
    	    	  }
//     			legendTemplate : function legendCallback
    	    };
   
     
    // 성비
      $.ajax({
		url : 'sexRateChart',
		type : 'get',
		dataType: 'json',
		success:function(list){
			var cnt = 0;
			var element = {};
			var arr = [];
			var color ="";
			$.each(list,function(i,v){
				if(v.mem_sex == '여성'){
					color ="#FF6384";
				}else{
					color ="#36A2EB";
				}
				
				element = {
					value: v.mem_count,
					label: v.mem_sex,
					color: color
				};
				
				arr[cnt]=element;
				cnt++;
			});
			PieData = arr;
		    pieChart.Doughnut(PieData, pieOptions);
		}
    });	// ajax 끝
      
//     $("#legendDiv").html(pieChart.generateLegend());
    
    // 부서별 사원수

     $.ajax({
 		url : 'deptChart',
 		type : 'get',
 		dataType: 'json',
 		success:function(list){
 			var cnt = 0;
			var element = {};
			var arr = [];
			$.each(list,function(i,v){
				element = {
					y: v.dept_nm,
					a: v.mem_count
				};
				
				arr[cnt]=element;
				cnt++;
			});
			var bar = new Morris.Bar({
				element: 'bar-chart',
			    resize: true,
			    data: arr,
			    barColors: ['#4BC0C0', '#FFCE56'],
				xkey: 'y',
				ykeys: ['a'],
				labels: ['인원'],
				hideHover: 'auto'
			});
 		}
	});// ajax끝
	
});

</script>
