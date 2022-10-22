package kr.or.ctw.project.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

// 프로젝트파일 진행상태
@Data
@Alias("progrsfileVO")
public class ProgrsFileVO {

	private int progrs_f_pk;
	private String progrs_f_nm;
	private String progrs_f_end;
	private String progrs_f_begin;
	private String confm;
	private int prjct_fk;
	
}
