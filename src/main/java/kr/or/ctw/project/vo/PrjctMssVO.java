package kr.or.ctw.project.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

// 프로젝트 대화방
@Data
@Alias("prjctmssVO")
public class PrjctMssVO {

	private int mss_pk;
	private String mss_cn;
	private String mss_date;
	private int prjct_fk;
	private String mem_id;
	
}
