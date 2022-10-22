package kr.or.ctw.personalFolder.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

@Data
@Alias("memdtaVO")
public class MemDtaVO {

	private String dta_pk;
	private String dta_nm;
	private String dta_extsn;
	private String dta_date;
	private String dta_del;
	private int dta_size;
	private int dta_fol_fk;
}
