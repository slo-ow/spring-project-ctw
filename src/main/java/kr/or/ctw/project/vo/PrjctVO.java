package kr.or.ctw.project.vo;


import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

// 프로젝트
@Data
@Alias("prjctVO")
public class PrjctVO extends ProgrsVO{

	private int prjct_pk;
	private String prjct_nm;
	private int prjct_nmpr;
	private String prjct_cn;
	private String prjct_begin;
	private String prjct_end;
	private String mem_id;
	private int progrs_fk;
}
