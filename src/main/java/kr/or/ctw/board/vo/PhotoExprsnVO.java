package kr.or.ctw.board.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("photoexprsnVO")
public class PhotoExprsnVO extends ExprsnSeVO{

	private String exprsn_pk;	// 기본키
	private String photo_fk;	// 사진글의 외래키
	private String exprsn_se_fk;	// 표현구분의 외래키
	private String mem_id;		// 좋아요누른 사원
	private int like_count;		// 좋아요수
}
