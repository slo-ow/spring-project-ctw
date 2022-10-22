<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 타이틀 -->
<title>CompanyWare</title>

<!-- ************************************************** CSS 부분 *************************************************** -->
<!-- calendar -->	
<!-- fullCalendar 2.2.5-->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/plugins/fullcalendar/fullcalendar.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/plugins/fullcalendar/fullcalendar.print.css" media="print">
<!-- 로고 이미지 -->
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath }/resources/indexpage/assets/css/images/logo.png">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- DataTables -->
 <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/plugins/datatables/dataTables.bootstrap.css">
<!-- Theme style -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/dist/css/skins/_all-skins.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/plugins/iCheck/flat/blue.css">
<!-- Morris chart -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/plugins/morris/morris.css">
<!-- jvectormap -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<!-- Date Picker -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/plugins/datepicker/datepicker3.css">
<!-- Daterange picker -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/plugins/daterangepicker/daterangepicker.css">
<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/plugins/iCheck/all.css">
<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.css">
<!-- 메인 css -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/mainform/css/custom.css">
<!-- sweetalert -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/sweetalert/css/sweetalert2.min.css">

<!-- ************************************************** CSS 부분 *************************************************** -->


<!-- ************************************************** Script 부분 *************************************************** --> 
<!-- jQuery 2.2.3 -->
<script	src="${pageContext.request.contextPath }/resources/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- jQuery form -->
<script	src="${pageContext.request.contextPath }/resources/dist/js/jquery.form.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
	$.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.6 -->
<script	src="${pageContext.request.contextPath }/resources/bootstrap/js/bootstrap.min.js"></script>
<script	src="${pageContext.request.contextPath }/resources/bootstrap/js/bootstrap-filestyle.js"></script>
<!-- DataTables -->
<script src="${pageContext.request.contextPath }/resources/plugins/datatables/jquery.dataTables.js"></script>
<script src="${pageContext.request.contextPath }/resources/plugins/datatables/dataTables.bootstrap.js"></script>
<!-- Morris.js charts -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="${pageContext.request.contextPath }/resources/plugins/morris/morris.js"></script>
<!-- Sparkline -->
<script	src="${pageContext.request.contextPath }/resources/plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script	src="${pageContext.request.contextPath }/resources/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script	src="${pageContext.request.contextPath }/resources/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script	src="${pageContext.request.contextPath }/resources/plugins/knob/jquery.knob.js"></script>
<!-- daterangepicker -->
<script	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script	src="${pageContext.request.contextPath }/resources/plugins/daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script	src="${pageContext.request.contextPath }/resources/plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- fullCalendar 2.2.5-->
<script src="${pageContext.request.contextPath }/resources/plugins/fullcalendar/fullcalendar.js"></script>
<!-- iCheck 1.0.1 -->
<script src="${pageContext.request.contextPath }/resources/plugins/iCheck/icheck.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script	src="${pageContext.request.contextPath }/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.js"></script>
<!-- Slimscroll -->
<script	src="${pageContext.request.contextPath }/resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script	src="${pageContext.request.contextPath }/resources/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath }/resources/dist/js/app.min.js"></script>
<!-- CK Editor -->
<script src="https://cdn.ckeditor.com/4.5.7/standard/ckeditor.js"></script>
<!-- sweetalert -->
<script src="${pageContext.request.contextPath }/resources/sweetalert/js/sweetalert2.min.js"></script>

<!-- ************************************************** Script 부분 *************************************************** -->