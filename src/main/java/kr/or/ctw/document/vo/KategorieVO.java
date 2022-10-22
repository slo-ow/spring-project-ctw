package kr.or.ctw.document.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

@Data
@Alias("kateVO")
public class KategorieVO {

	private int se_pk;
	private String se_nm;
	
}
