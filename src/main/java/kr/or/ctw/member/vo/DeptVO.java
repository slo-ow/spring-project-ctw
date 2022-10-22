package kr.or.ctw.member.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;
import lombok.EqualsAndHashCode;

// 부서테이블
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("deptVO")
public class DeptVO extends GradVO{
	
	private int dept_pk;
	private String dept_nm;
		
}
