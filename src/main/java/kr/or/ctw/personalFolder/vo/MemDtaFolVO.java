package kr.or.ctw.personalFolder.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

// 개인자료폴더
@Data
@Alias("memdtafolVO")
public class MemDtaFolVO {

	private int dta_fol_pk;
	private int dta_fol_gr;
	private int dta_fol_lev;
	private String dta_fol_nm;
	private String dta_fol_del;
	private String mem_id;
	
}
