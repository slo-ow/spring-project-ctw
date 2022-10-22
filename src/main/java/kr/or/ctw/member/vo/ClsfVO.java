package kr.or.ctw.member.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;
import lombok.EqualsAndHashCode;

// 직급테이블
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("clsfVO")
public class ClsfVO extends DeptVO{
	
	private int clsf_pk;
	private String clsf_nm;
	
}