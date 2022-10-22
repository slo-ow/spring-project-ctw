package kr.or.ctw.project.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

// 프로젝트 파일(업무)
@Data
@Alias("prjctfileVO")
public class PrjctFileVO {

	private String prjct_f_pk;
	private String prjct_f_nm;
	private String prjct_f_extsn;
	private String prjct_f_date;
	private int prjct_f_size;
	private String prjct_f_del;
	private int partcptn_fk;
	private int progrs_f_pk;
}
