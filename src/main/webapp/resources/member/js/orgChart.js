/**
* @Description : 조직도 출력 JS
* @author 박창현 (mepch@naver.com)
* @version 1.0
* @since 2017.04.24
 */

$(function(){
	$.ajax({
		url:'ajaxList',
		type:'post',
		dataType:'json',	
		success:function(res){
			// 값을 json형태로 넣기 위해 배열을 만든다.
			var obj = {};
			var memArr = [];
			var temp = [];
			
			$.each(res,function(i,v){
				var deptCode = null;
				if(v.grad_pk==9){
					deptCode = null;
				}else if(v.grad_pk==5){
					deptCode = 1;
					temp[i+1] = v.dept_pk;
				}else{
					for(var j=0; j<temp.length; j++){
						if(temp[j]==v.dept_pk){
							deptCode = j;
						}
					}
				}
					
				obj = {
						"id": i+1,
						"부서코드": deptCode, 
						"부서": v.dept_nm, 
						"직급": v.clsf_nm, 
						"성명": v.mem_nm, 
						"전화번호": v.mem_telno, 
						"Mail": v.mem_email, 
						"주소": v.mem_addr, 
						"image": "../resources/member/orgImg/f-1.jpg", 
						"성별": v.mem_sex
				};// end of obj
				
				memArr[i] = obj;
				//memArr.push(obj);
				//alert(memArr.length + ", " + memArr[i].부서);
			});// end of each
			
/*			memArr = [
						{ id: 1, 부서코드: null, 직급:"CEO", 성명: "Amber McKenzie", 전화번호: "678-772-470", Mail: "lemmons@jourrapide.com", 주소: "Atlanta, GA 30303", image: "../resources/member/orgImg/f-1.jpg", 성별:"남성"},
						{ id: 2, 부서코드: 1, 부서: "개발부", 직급:"부장", 성명: "Ava Field", 전화번호: "937-912-4971", Mail: "anderson@jourrapide.com", image: "../resources/member/orgImg/f-10.jpg", 성별:"여성" },
						{ id: 3, 부서코드: 1, 부서: "영업부", 직급:"부장", 성명: "Evie Johnson", 전화번호: "314-722-6164", Mail: "thornton@armyspy.com", image: "../resources/member/orgImg/f-9.jpg", 성별:"여성" },
						{ id: 4, 부서코드: 1, 부서: "기획부", 직급:"부장", 성명: "Paul Shetler", 전화번호: "330-263-6439", Mail: "shetler@rhyta.com", image: "../resources/member/orgImg/f-5.jpg", 성별:"남성" },
						{ id: 5, 부서코드: 2, 부서: "개발부", 직급:"대리", 성명: "Rebecca Francis", 전화번호: "408-460-0589", Mail: "usaimg@beauty.com", image: "../resources/member/orgImg/f-4.jpg", 성별:"남성" },
						{ id: 6, 부서코드: 2, 부서: "개발부", 직급:"사원", 성명: "Rebecca Randall", 전화번호: "801-920-9842", Mail: "JasonWGoodman@armyspy.com", image: "../resources/member/orgImg/f-8.jpg", 성별:"남성" },
						{ id: 7, 부서코드: 3, 부서: "영업부", 직급:"인턴", 성명: "Spencer May", 전화번호:"801-854-5554", Mail: "hodges@teleworm.us", image: "../resources/member/orgImg/f-7.jpg", 성별:"여성" },
						{ id: 8, 부서코드: 3, 부서: "영업부", 직급:"사원", 성명: "Max Ford", 전화번호: "989-474-8325", Mail: "hunter@teleworm.us", image: "../resources/member/orgImg/f-6.jpg", 성별:"남성" },
						{ id: 9, 부서코드: 4, 부서: "기획부", 직급:"사원", 성명: "Riley Bray", 전화번호: "479-359-2159", Mail:"tank9832@yahoo.co.kr", image: "../resources/member/orgImg/f-3.jpg", 성별:"남성" },
						{ id: 10, 부서코드: 4, 부서: "기획부", 직급:"사원", 성명: "Callum Whitehouse", 전화번호: "847-474-8775", Mail:"beauty@yahoo.co.kr", image: "../resources/member/orgImg/f-2.jpg", 성별:"남성" }
            ];*/
			
			getOrgChart.themes.myCustomTheme =
	        {
	            size: [270, 400],
	            toolbarHeight: 46,
	            textPoints: [
	                { x: 130, y: 50, width: 250 },
	                { x: 130, y: 90, width: 250 }
	            ],
	            textPointsNoImage: [
	                { x: 130, y: 50, width: 250 },
	                { x: 130, y: 90, width: 250 }
	            ],
	            expandCollapseBtnRadius: 20,
	            defs: '<filter id="f1" x="0" y="0" width="200%" height="200%"><feOffset result="offOut" in="SourceAlpha" dx="5" dy="5" /><feGaussianBlur result="blurOut" in="offOut" stdDeviation="5" /><feBlend in="SourceGraphic" in2="blurOut" mode="normal" /></filter>',
	            box: '<rect x="0" y="0" height="400" width="270" rx="10" ry="10" class="myCustomTheme-box" filter="url(#f1)"  />',
	            text: '<text text-anchor="middle" width="[width]" class="get-text get-text-[index]" x="[x]" y="[y]">[text]</text>',
	            image: '<clipPath id="getMonicaClip"><circle cx="135" cy="255" r="85" /></clipPath><image preserveAspectRatio="xMidYMid slice" clip-path="url(#getMonicaClip)" xlink:href="[href]" x="50" y="150" height="190" width="170"/>',
	            reporters: '<circle cx="80" cy="190" r="20" class="reporters"></circle><text class="reporters-text" text-anchor="middle" width="100" x="80" y="195">[reporters]</text>'

	        };// end of myCustomTheme


	            var peopleElement = document.getElementById("people");
	            var orgChart = new getOrgChart(peopleElement, {
	             // 테마
	           	 theme: "cassandra",
	             // 테마 색상
	           	 color : "black",
	             // 화면에 보이는 크기
	           	 scale: 0.5,
	             // 부서코드
	           	 idField: "id",
	             // 부서 설정
	           	 parentIdField: "부서코드",
	             // 줄 간격
	           	 levelSeparation: 100,
	             // 조직도 박스 간격
	           	 siblingSeparation: 130,
	           	 // 창 보기 설정
	           	 enableGridView: true,
	           	 // 필드 설정name
	             primaryFields: ["id", "부서", "직급", "성명", "전화번호", "주소", "Mail", "성별"],
	             // 이미지 설정name
	             photoFields: ["image"],
	             // 레이아웃설정 : 수직정렬, 코드 삭제시에 default(수평정렬)
//	             layout: getOrgChart.MIXED_HIERARCHY_RIGHT_LINKS,
	             
	             renderNodeEvent: renderNodHandler,
	                
	             dataSource: memArr
	            	 /*
	            	 [
								{ id: 1, 부서코드: null, secondParentId: null, 부서: "총무부", 직급:"CEO", 성명: "Amber McKenzie", 전화번호: "678-772-470", Mail: "lemmons@jourrapide.com", 주소: "Atlanta, GA 30303", image: "../resources/member/orgImg/f-1.jpg", 성별:"남성"},
								{ id: 2, 부서코드: 1, 부서: "개발부", 직급:"부장", 성명: "Ava Field", 전화번호: "937-912-4971", Mail: "anderson@jourrapide.com", image: "../resources/member/orgImg/f-10.jpg", 성별:"여성" },
								{ id: 3, 부서코드: 1, 부서: "영업부", 직급:"부장", 성명: "Evie Johnson", 전화번호: "314-722-6164", Mail: "thornton@armyspy.com", image: "../resources/member/orgImg/f-9.jpg", 성별:"여성" },
								{ id: 4, 부서코드: 1, 부서: "기획부", 직급:"부장", 성명: "Paul Shetler", 전화번호: "330-263-6439", Mail: "shetler@rhyta.com", image: "../resources/member/orgImg/f-5.jpg", 성별:"남성" },
								{ id: 5, 부서코드: 2, 부서: "개발부", 직급:"대리", 성명: "Rebecca Francis", 전화번호: "408-460-0589", Mail: "usaimg@beauty.com", image: "../resources/member/orgImg/f-4.jpg", 성별:"남성" },
								{ id: 6, 부서코드: 2, 부서: "개발부", 직급:"사원", 성명: "Rebecca Randall", 전화번호: "801-920-9842", Mail: "JasonWGoodman@armyspy.com", image: "../resources/member/orgImg/f-8.jpg", 성별:"남성" },
								{ id: 7, 부서코드: 3, 부서: "개발부", 직급:"인턴", 성명: "Spencer May", 전화번호:"801-854-5554", Mail: "hodges@teleworm.us", image: "../resources/member/orgImg/f-7.jpg", 성별:"여성" },
								{ id: 8, 부서코드: 3, 부서: "영업부", 직급:"사원", 성명: "Max Ford", 전화번호: "989-474-8325", Mail: "hunter@teleworm.us", image: "../resources/member/orgImg/f-6.jpg", 성별:"남성" },
								{ id: 9, 부서코드: 4, 부서: "영업부", 직급:"사원", 성명: "Riley Bray", 전화번호: "479-359-2159", Mail:"tank9832@yahoo.co.kr", image: "../resources/member/orgImg/f-3.jpg", 성별:"남성" },
								{ id: 10, 부서코드: 4, 부서: "기획부", 직급:"사원", 성명: "Callum Whitehouse", 전화번호: "847-474-8775", Mail:"beauty@yahoo.co.kr", image: "../resources/member/orgImg/f-2.jpg", 성별:"남성" }
	                ]*/
	             
	            });//end of getOrgChart				
		},// end of success
		
		error:function(){
			alert("오류가 발생하였습니다");
		}// end of error	
		
	});// end of ajax

            
});//end of function

            function renderNodHandler(sender, args) {
            	
                for (var i = 0; i < args.content.length; i++) {
                    if (args.content[i].indexOf("[reporters]") != -1) {
                        args.content[i] = args.content[i].replace("[reporters]", args.node.children.length);
                    }
                }
            }
