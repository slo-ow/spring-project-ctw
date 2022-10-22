<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 예제 </title>	
<script type="text/javascript">
	//등록
	function goinsert(){
		var f = document.frm1;
		
		// 파일을 선택하지 않았을 경우
		if(f.file1.value==""){
			alert("파일을 선택해 주세요");
			return;
		}
		var fileNameLen = f.file1.value.length;
		
		// 확장자가 xls가 아닌 경우
		if(f.file1.value.substring(fileNameLen-3,fileNameLen)!= "xls"){
			alert("확장자가 xls인 엑셀파일을 선택해 주세요");
			return;
		}
		f.submit();
	}
</script>
</head>
<body>
	<form name="frm1" method="post" action="Exmaple2.jsp" enctype="multipart/form-data">
		<table border="0">
			<tr><td colspan="2"><b>**주의사항**</b></td></tr>
			<tr><td colspan="2">* 엑셀파일만 업로드 가능합니다.</td></tr>
			<tr><td colspan="2">* 첫번 째 시트에 데이터가 있어야 합니다.[시트명:Sheet1]</td></tr>
			<tr><td colspan="2">* 엑셀작성 시 중간에 빈줄이 없어야 합니다.</td></tr>
			<tr>
				<td colspan="2">
					<a href="/Template/ExcelTemplate.xls">
						<img src="/images/btn_insert.gif" height="20" border="0">
					</a>
			</tr>
		</table>
	</form>
</body>
</html>