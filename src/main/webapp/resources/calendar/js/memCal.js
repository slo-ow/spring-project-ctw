/**
 * 
 */
$(function () {
	// 시작시간과 종료 시간 
	$("#calendar").on("click",".fc-day",function(){
	$("#insertFX").modal();
	
	})
	$("#calendar").on('click','a',function(){
		var id = $(this).attr('id');
		var division = id.split("-");
	 	 $.ajax({
		    	url:'fxInfo',	// 캘린더를 가져오는 주소
		    	type:'POST',
		    	dataType:'json',
		    	data:"pk="+division[1]+"&division="+division[0],
		    	success:function(res){
		    		var htmlCode = "";
		    		var startT = res.fx_begin.split(' ');
		    		var endT = res.fx_end.split(' ');
		    		var startT2 = startT[1].split(':');
		    		var endT2 = endT[1].split(':');
		    		// 2017-04-12 12:00:00 - 2017-05-17 23:00:00
		    		// 04/05/2017 - 05/16/2017
		    		
		    		
		    		
		    		var startdate = startT[0].split('-');
		    		var enddate = endT[0].split('-');
		    		
		    		var begin = startdate[1]+"/"+startdate[2]+"/"+startdate[0];
		    		var end    = enddate[1]+"/"+enddate[2]+"/"+enddate[0];
		    		
		    		if (startT2[0] > 12) {
		    			$('#startTime2').val(startT2[0]+":"+startT2[1]+" PM");
						
					}else{
		    			$('#startTime2').val(startT2[0]+":"+startT2[1]+" AM");
						
					}
		    		
		    		if (endT2[0] >12) {
		    			$('#endTime2').val(endT2[0]+":"+endT2[1]+" PM");
					}else{
		    			$('#endTime2').val(endT2[0]+":"+endT2[1]+" AM");
						
					}
		    		
		    		
		       		$("#fx_pk").val(division[1]);
			    	$('#fx_nm').val(res.fx_nm);
			    	$('#fx_memo').val(res.fx_memo);
			    	$("#reservationtime2").val(begin+" - "+end);
			   
			    	
			    	
			    	
		    		$("#fx_begin2").val(res.fx_begin);
		    		$("#fx_end2").val(res.fx_end);
		    		if(division[0]=='mem'){
		    			$("#infoFX input").attr("readonly",false);
			    		$("#infoFX textarea").attr("readonly",false);
		    		htmlCode += "<input type='button' class='btn btn-info' value='수정' id='updateFx'>";
		    		htmlCode += "<input type='button' class='btn btn-info' value='삭제' onclick=\"location.href='fxDelete?pk="+division[1]+"&division="+division[0]+"'\">";
		    		htmlCode += "<input type='button' class='btn btn-info' data-dismiss='modal' value='닫기'>";
		    		$('#division1').html(htmlCode);
		    		}else{
		    			$("#infoFX input").attr("readonly",true);
			    		$("#infoFX textarea").attr("readonly",true);
			    		htmlCode += "<input type='button' class='btn btn-info' data-dismiss='modal' value='닫기'>";
			    		$('#division1').html(htmlCode);
		    		}
				$('#infoFX').modal();
		    	}
		 });
	});
	$("#division1").on("click","#updateFx",function(){
		
		
		swal({
			  title: '일정수정',
			  text: '일정을 수정 하시겠습니까?',
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#8cd4f5',
			  confirmButtonText: '수정',
			  cancelButtonText: '취소',
			}).then(function(){
				 swal({
					  title: '수정완료',
					  text: '일정이 수정 되었습니다..',
					  type: 'success',
					}).then(function(){
						if(startTime != null ){
							var time = $("#startTime2").val().split(" ");
							startTime = time[0];
						}
						if(endTime != null ){
							var time2 = $("#endTime2").val().split(" ");
							endTime = time2[0];
						}
						var date = $("#reservationtime2").val();
						var arr = date.split("/");
						var arr2 = arr[2].split("-");
						var arr3 = arr2[1].split("/");
						var fx_begin = arr2[0].trim()+"-"+arr[0]+"-"+arr[1]+" "+startTime+":00";
						var fx_end = arr[4]+"-"+arr3[0].trim()+"-"+arr[3]+" "+endTime+":00";
						$("#fx_begin2").val(fx_begin);	
						$("#fx_end2").val(fx_end);	
						
						$("#fxupdate").submit();		
						
					})
			}, function() {
				  swal("취소", "일정수정을 취소합니다.", "error");
			}
		);
		
		
	})
	  	
		// datepicket을 사용 할 수 있게 하는 function
  	 $('#reservationtime2').daterangepicker({
		 timePicker: true, 
		 timePickerIncrement: 30, 
		 format: 'YYYY-MM-DD h:mm A'
		 
	 });
	
	
	
	// datepicket을 사용 할 수 있게 하는 function
	 $('#reservationtime').daterangepicker({
		 timePicker: true, 
		 timePickerIncrement: 30, 
		 format: 'YYYY-MM-DD h:mm A'
		 
	 });

	var startTime = "";
	var endTime = "";
	
	$(".applyBtn").click(function(){
		// 시간
		startTime = "";
		endTime = "";
		
		var starthour = $('.left .hourselect').val();
		var endhour =  $(".right .hourselect").val();
		
		// 분
		var startminute = $('.left .minuteselect').val();
		var endminute =  $(".right .minuteselect").val();
		
		// 오전오후 구분
		var startampm = $(".left .ampmselect").val();
		var endampm = $(".right .ampmselect").val();
		
		// 시간을 디비형식에 맞게 변환한다. 
		if (startampm == ' PM') {
			startTime += 12+Number(starthour);
		}else{
			startTime += Number(starthour);
		}		
		
		if(startminute == '0'){
			startTime += ":"+startminute+'0';
		}else{
			startTime += ":"+startminute;
		}		
	
		if (endampm == ' PM') {
			endTime += 12+Number(endhour);
		}else{
			endTime += Number(endhour);
		}
		if(endminute == '0'){
			endTime += ":"+endminute+'0';
		}else{
		endTime += ":"+endminute;
		}
				
		$('#startTime').val(startTime+" "+startampm);
		$('#endTime').val(endTime+" "+endampm);
		
		// 업데이트를 위한 시간 
		$('#startTime2').val(startTime+" "+startampm);
		$('#endTime2').val(endTime+" "+endampm);
		// 업데이트를 위한 날짜 및 시간 



	})
	// 날짜를 디비 형식의 맞게 자른다. 
/* 		
		var date = $("#reservationtime").val();
		var arr = date.split("/");
		var arr2 = arr[2].split("-");
		var arr3 = arr2[1].split("/");
		
		
		var fx_begin = arr2[0].trim()+"-"+arr[0]+"-"+arr[1]+" "+startTime;
		var fx_end = arr[4]+"-"+arr3[0].trim()+"-"+arr[3]+" "+endTime;
		
		$("#fx_begin").val(fx_begin);	
		$("#fx_end").val(fx_end);	
			 */
 
		   		
		
		
	$("#insert").click(function(){
		
		var date = $("#reservationtime").val();
		var arr = date.split("/");
		var arr2 = arr[2].split("-");
		var arr3 = arr2[1].split("/");
		
		
		var fx_begin = arr2[0].trim()+"-"+arr[0]+"-"+arr[1]+" "+startTime+":00";
		var fx_end = arr[4]+"-"+arr3[0].trim()+"-"+arr[3]+" "+endTime+":00";
		
		$("#fx_begin").val(fx_begin);	
		$("#fx_end").val(fx_end);	
		
		$("#fxinsert").submit();
		/* 	alert(arr2[0]); // 년도
			alert(arr[0]); // 월
			alert(arr[1]); // 일
			
			
			alert(arr[4]); // 년도
			alert(arr3[0]); // 월
			alert(arr[3]); // 일 */
	
		
	})

	
	
    /* initialize the external events
     -----------------------------------------------------------------*/
    function ini_events(ele) {
      ele.each(function () {

        // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
        // it doesn't need to have a start or end
        var eventObject = {
          title: $.trim($(this).text()) // use the element's text as the event title
        };

        // store the Event Object in the DOM element so we can get to it later
        $(this).data('eventObject', eventObject);

        // make the event draggable using jQuery UI
        $(this).draggable({
          zIndex: 1070,
          revert: true, // will cause the event to go back to its
          revertDuration: 0  //  original position after the drag
        });

      });
    }
	// ajax를 이용 해서 캘린더를 출력한다 
	  $.ajax({
	    	url:'memFxList',	// 캘린더를 가져오는 주소
	    	type:'POST',
	    	dataType:'json',
	    	success:function(res){
	    		// 값을 json 형태로 집어넣기 위해 배열을 만든다. 
	    		var obj = {};
				var evntArr = [];
				// 배열의 순서를 지키기 위해 변수를 하나 선언 해준다. 
				var cnt = 0;
				
				// 부서별 일정 
	    		$.each(res.dept,function(i,v){
	    			// 날짜를 집어넣기 위해 날짜를 짤라 준다. 
					var begin = v.fx_begin.split('-');
					var beginDay = begin[2].split(' ');
					var beginTime = beginDay[1].split(':');
					var end = v.fx_end.split('-');
					var endDay = end[2].split(' ');
					var endTime = endDay[1].split(':');
					// 들어가는 배열의 값
					obj = {
							   id:'dept-'+v.fx_pk,
			    	          title: v.fx_nm,
			    	          start: new Date(begin[0], begin[1]-1, beginDay[0],beginTime[0],beginTime[1]),
			    	          end: new Date(end[0], end[1]-1, endDay[0],endTime[0],endTime[1]),
			    	          backgroundColor: "#ff992d", //yellow
			    	          borderColor: "#ff992d" //yellow
			    	        };
			
					evntArr[cnt] = obj;
					cnt++;
					
	    		})
	    		// 사원 일정 
	    			$.each(res.mem,function(i,v){
	    				var begin = v.fx_begin.split('-');
						var beginDay = begin[2].split(' ');
						var beginTime = beginDay[1].split(':');
						var end = v.fx_end.split('-');
						var endDay = end[2].split(' ');
						var endTime = endDay[1].split(':');				
					obj = {
							  id:'mem-'+v.fx_pk,
			    	          title: v.fx_nm,
			    	          start: new Date(begin[0], begin[1]-1, beginDay[0],beginTime[0],beginTime[1]),
			     	          end: new Date(end[0], end[1]-1, endDay[0],endTime[0],endTime[1]),
			    	          backgroundColor: "#00BA22",
			    	          borderColor: "#00BA22" 
			    	        };
			
					evntArr[cnt] = obj;
					cnt++;
					
	    		})
	    		// 전체 일정
	    			$.each(res.cmpny,function(i,v){
	    				
	    				var begin = v.fx_begin.split('-');
						var beginDay = begin[2].split(' ');
						var beginTime = beginDay[1].split(':');
						
						var end = v.fx_end.split('-');
						var endDay = end[2].split(' ');
						var endTime = endDay[1].split(':');
					obj = {
							  id:'com-'+v.fx_pk,
			    	          title: v.fx_nm,
			    	          start: new Date(begin[0], begin[1]-1, beginDay[0],beginTime[0],beginTime[1]),
			     	          end: new Date(end[0], end[1]-1, endDay[0],endTime[0],endTime[1]),
			    	          backgroundColor: "#3091f2", //yellow
			    	          borderColor: "#3091f2" //yellow
			    	        }; 
			
					evntArr[cnt] = obj;
					cnt++;
					
	    		});
	    		
	    
	    		 var date = new Date();
	    	    var d = date.getDate(),
	    	        m = date.getMonth(),
	    	        y = date.getFullYear();
	    	    $('#calendar').fullCalendar({
	    	      header: {
	    	        left: 'prev,next today',
	    	        center: 'title',
	    	        right: 'month,agendaWeek,agendaDay'
	    	      },
	    	      buttonText: {
	    	        today: 'today',
	    	        month: 'month',
	    	        week: 'week',
	    	        day: 'day'
	    	      },allDayText: '시간', // 주간,월간
	    	      monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    	      monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    	      dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],
	    	      dayNamesShort: ['일','월','화','수','목','금','토']
	    	      ,
	    	      buttonText: {
	    	    	  today: '오늘',
	    	    	  month: '월간',
	    	    	  week: '주간',
	    	    	  day: '일간'
	    	    	    },eventAfterRender:function( event, element, view ) { 
	    	    	    	$(element).attr("id",event._id);
	    	    	    },
	    	      events: evntArr
	    	    	  
	    	      ,
	    	      editable: false,
	    	      droppable: false, // this allows things to be dropped onto the calendar !!!
	    	      drop: function (date, allDay) { // this function is called when something is dropped

	    	        // retrieve the dropped element's stored Event Object
	    	        var originalEventObject = $(this).data('eventObject');

	    	        // we need to copy it, so that multiple events don't have a reference to the same object
	    	        var copiedEventObject = $.extend({}, originalEventObject);

	    	        // assign it the date that was reported
	    	        copiedEventObject.start = date;
	    	        copiedEventObject.allDay = allDay;
	    	        copiedEventObject.backgroundColor = $(this).css("background-color");
	    	        copiedEventObject.borderColor = $(this).css("border-color");

	    	        // render the event on the calendar
	    	        // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
	    	        $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

	    	        // is the "remove after drop" checkbox checked?
	    	        if ($('#drop-remove').is(':checked')) {
	    	          // if so, remove the element from the "Draggable Events" list
	    	          $(this).remove();
	    	        }

	    	      }
	    	    });
	    	}
	    		
	      }); 

				
    ini_events($('#external-events div.external-event'));

    /* initialize the calendar
     -----------------------------------------------------------------*/
    //Date for the calendar events (dummy data)
   

    /* ADDING EVENTS */
    var currColor = "#3c8dbc"; //Red by default
    //Color chooser button
    var colorChooser = $("#color-chooser-btn");
    $("#color-chooser > li > a").click(function (e) {
      e.preventDefault();
      //Save color
      currColor = $(this).css("color");
      //Add color effect to button
      $('#add-new-event').css({"background-color": currColor, "border-color": currColor});
    });
    $("#add-new-event").click(function (e) {
      e.preventDefault();
      //Get value and make sure it is not null
      var val = $("#new-event").val();
      if (val.length == 0) {
        return;
      }

      //Create events
      var event = $("<div />");
      event.css({"background-color": currColor, "border-color": currColor, "color": "#fff"}).addClass("external-event");
      event.html(val);
      $('#external-events').prepend(event);

      //Add draggable funtionality
      ini_events(event);

      //Remove event from text input
      $("#new-event").val("");
    });
  });