package kr.or.ctw.board.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

// 표현구분
@Data
@Alias("exprsnseVO")
public class ExprsnSeVO {
	
	private String exprsn_se_pk;
	private String exprsn_se_nm;
	
}
