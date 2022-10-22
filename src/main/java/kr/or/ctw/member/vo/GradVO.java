package kr.or.ctw.member.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

// 등급테이블
@Alias("gradVO")
public @Data class GradVO {
	
	private int grad_pk;
	private String grad_nm;
	
}
