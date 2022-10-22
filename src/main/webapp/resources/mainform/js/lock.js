/**
 * <pre>
 *  LockScreen.jsp custom js
 * </pre>
 * @author 문성철
 * @since 2017. 5. 2.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일         수정자         수정내용
 * --------     --------      -------------
 * 2017. 5. 2.    작성자명          최초작성
 * Copyright (c) 2017 by CTW All right reserved
 * </pre>
 */ 
$(function(){
	
	//페이지 redirect시 보여줄 Alert경고창
	swal({
		  title: "잠금상태",
		  text: "잠금상태를 해제하시려면 다시 로그인 해주세요",
		  type: "warning",
		  confirmButtonText: "닫기"
	});
	
	//새로 고침 방지 기능 실행
	function LockF5(){
		 if (event.keyCode == 116) {
		  event.keyCode = 0;
		  return false;
		 }
	}
	document.onkeydown = LockF5;
	
});