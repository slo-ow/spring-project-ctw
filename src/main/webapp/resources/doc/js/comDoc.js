/**
 * 
 */

var contextPath;
   $(function(){
		var grad_pk =$("#grad_pk").val();
				
	   
	   // 카테고리 가져오기 
	   $.ajax({
	    	url:'kategoriList',	
	    	type:'POST',
	    	dataType:'json',
	    	success:function(res){
	    		var htmlCode = "";
	    		$.each(res,function(i,v){
	    			htmlCode += "<option value='"+v.se_pk+"'>"+v.se_nm+"</option>";	
	    			
	    		})
	    		  $(".kategorie").html(htmlCode);
	    		  $(".kategorie2").html(htmlCode);
	    		
	    		
	    	}
	   });
	   var kategorie = "30";
	   var division = $("input[name=division]").val();
	   var extsn = $("#extsn").val();
	   // 카테고리를 가져오기 위한 change이벤트 
	   $('#sel1').on("change",function(){
		    kategorie = $("#sel1 option:selected").val();
		    $(".filelist").empty();
	        filelist(kategorie,extsn,division);
	    });
	   

	
		$(window).load(function(){
		    filelist(kategorie,extsn,division);
		});   

	   // 비동기를 위한 값 집어넣기 
		$("#extsn1").click(function(){
			$("#extsn").val('excel');
			extsn = $("#extsn").val();
		    $(".filelist").empty();
			 filelist(kategorie,extsn,division);
		});		
		$("#extsn2").click(function(){
			$("#extsn").val('word');
			extsn = $("#extsn").val();
		    $(".filelist").empty();
			 filelist(kategorie,extsn,division);
		});		
		$("#extsn3").click(function(){
			$("#extsn").val('hangle');
			extsn = $("#extsn").val();
		    $(".filelist").empty();
			 filelist(kategorie,extsn,division);
		});		
		$("#extsn4").click(function(){
			$("#extsn").val('ppt');
			extsn = $("#extsn").val();
		    $(".filelist").empty();
			 filelist(kategorie,extsn,division);
		});		
		$("#extsn5").click(function(){
			$("#extsn").val('zip');
			extsn = $("#extsn").val();
		    $(".filelist").empty();
			 filelist(kategorie,extsn,division);
		});		
		$("#extsn6").click(function(){
			$("#extsn").val('pdf');
			extsn = $("#extsn").val();
		    $(".filelist").empty();
			 filelist(kategorie,extsn,division);
		});		
		$("#extsn7").click(function(){
		    $(".filelist").empty();
			$("#extsn").val('');
			extsn = $("#extsn").val();
			 filelist(kategorie,extsn,division);
		});		
	   
	   $("#fileSearch").click(function(event){
		   var search = $("#filename").val();
		   
		   $.ajax({
		    	url:'docList',	
		    	type:'POST',
		    	dataType:'json',
		    	data : {"kategorie" : kategorie,"extsn" : extsn,"division" : division,"search" : search},
		    	success:function(res){
		    		var htmlCode = "";
		    		$.each(res,function(i,v){
		    			if(v.doc_extsn == 'excel'){
		    				htmlCode +="<div class='filefloat'><img src='/ctw/resources/icon/Excel 2013.png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
		    			}else if(v.doc_extsn == 'word'){
		    				htmlCode +="<div class='filefloat'><img src='/ctw/resources/icon/Word 2013.png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
		    			}else if(v.doc_extsn == 'ppt'){
		    				htmlCode +="<div class='filefloat'><img src='/ctw/resources/icon/PowerPoint 2013.png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
		    			}else if(v.doc_extsn == 'pdf'){
		    				htmlCode +="<div class='filefloat'><img src='/ctw/resources/icon/PDF.png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
		    			}else if(v.doc_extsn == 'hangle'){
		    				htmlCode +="<div class='filefloat'><img src='/ctw/resources/icon/HWP (1).png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
		    			}else if(v.doc_extsn == 'zip'){
		    				htmlCode +="<div class='filefloat'><img src='/ctw/resources/icon/ZIP (2).png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
		    			}else{
		    				htmlCode +="<div class='filefloat'><img src='/ctw/resources/icon/document.png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
		    			}
		    			if(grad_pk  > 7){
							htmlCode +="<span class='deleteFile' id='test/"+v.doc_pk+"'></span>";
		    			}else{
							htmlCode +="<span class='deleteFile' id='test/"+v.doc_pk+"'><b>X</b></span>";
		    			}
						htmlCode +="<span class='updateFile' id='test1/"+v.doc_pk+"'></span>";
	    				htmlCode +="<br>"+v.doc_nm+"</div>";
		    		})
		    		  $(".filelist").html(htmlCode);
		    	}
		   });
		   
		   
	   }); 
	   
	   // 다운로드
	   $(".filelist").on("click",".down",function(){
		   var id = $(this).attr('id');
		   var doc_pk = id.split('/')
		   $(location).attr('href', 'download/docFile?doc_pk='+doc_pk[1]+'&real_name='+doc_pk[0]);		   
	   });
	   // 삭제
	   $(".filelist").on("click",".deleteFile",function(){
		   var keyvalue = $(this).attr('id');
		   var doc_pk =  keyvalue.split("/");
		   $(location).attr('href', 'docDelete?doc_pk='+doc_pk[1]+'&division='+division);
	   });
	   
	   
	   
	   
	   	// 파일 스타일을 지정하는 이벤트 
	   	$(":file").filestyle({size: "sm"});
	   	
	  
	   // 파일추가하는 이벤트 
		$("#addfile").click(function(){
			console.log('확인')
			 var filecnt = $('input[class=filestyle]').length;
			if(filecnt < 5){
			$("#fileappend").append("<div class='fileinput'><input type='file' class='filestyle' data-size='sm' name='docfile["+filecnt+"]'>");		
			$("#fileappend").append("<button type='button' class='btn btn-default btn-xs subfile glyphicon glyphicon-minus' > </button></div>");		
				
			}else{
				alert("파일은 5개 까지만 가능합니다. ");
			
			}
			
		   	$(":file").filestyle({size: "sm"}); 
		   	
		});
		
		// 파일을 삭제하는 이벤트
		$("#fileappend").on('click','.subfile',function(){
				//alert("되나요?");
			  //$("#fileappend").empty(); 
			
		});
		
		// 파일을 저장하는 이벤트    
		$("#fileSave").click(function(){
			$("#fileFormSave").submit();
		});
   
   });
   
   
   
   
   function filelist(kategorie,extsn,division){
		var grad_pk =$("#grad_pk").val();
       $.ajax({
	    	url:'docList',	
	    	type:'POST',
	    	dataType:'json',
	    	data : {"kategorie" : kategorie,"extsn" : extsn,"division" : division},
	    	success:function(res){
	    		var htmlCode = "";
	    		$.each(res,function(i,v){
	    			if(v.doc_extsn == 'excel'){
	    				htmlCode +="<div class='filefloat'><img src='/ctw/resources/icon/Excel 2013.png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
	    			}else if(v.doc_extsn == 'word'){
	    				htmlCode +="<div class='filefloat'><img src='/ctw/resources/icon/Word 2013.png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
	    			}else if(v.doc_extsn == 'ppt'){
	    				htmlCode +="<div class='filefloat'><img src='/ctw/resources/icon/PowerPoint 2013.png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
	    			}else if(v.doc_extsn == 'pdf'){
	    				htmlCode +="<div class='filefloat'><img src='/ctw/resources/icon/PDF.png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
	    			}else if(v.doc_extsn == 'hangle'){
	    				htmlCode +="<div class='filefloat'><img src='/ctw/resources/icon/HWP (1).png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
	    			}else if(v.doc_extsn == 'zip'){
	    				htmlCode +="<div class='filefloat'><img src='/ctw/resources/icon/ZIP (2).png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
	    			}else{
	    				htmlCode +="<div class='filefloat'><img src='/ctw/resources/icon/document.png' class='down' id='"+v.doc_nm+"/"+v.doc_pk+"'>";
	    			}
	    			if(grad_pk  > 7){
						htmlCode +="<span class='deleteFile' id='test/"+v.doc_pk+"'><b>X</b></span>";
	    			}else{
						htmlCode +="<span class='deleteFile' id='test/"+v.doc_pk+"'></span>";
	    			}
					htmlCode +="<span class='updateFile' id='test1/"+v.doc_pk+"'></span>";
    				htmlCode +="<br>"+v.doc_nm+"</div>";
	    		})
	    		  $(".filelist").html(htmlCode);
	    	}
	   });
	   
   };
   
   function close1(){
	  $("#fileappend").empty(); 
	   
   };
   