package kr.or.ctw.schedule.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

@Data
@Alias("fxVO")
public class FxVO {
	private int fx_pk;
	private String fx_nm;
	private String fx_begin;
	private String fx_end;
	private String fx_memo;
	private String fx_del;
	private String mem_id;
	private int dept_fk;

	
	
	
}
