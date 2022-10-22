package kr.or.ctw.personalFolder.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

@Data
@Alias("memdtaprogrsVO")
public class MemDtaProgrsVO {
	private int col;
	private int dta_progrs_fk;
	private String dta_fk;
}
