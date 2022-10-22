package kr.or.ctw.mssage.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

@Data
@Alias("mssVO")
public class MssVO extends MssFileVO{
	private int mss_pk;
	private String dspt_del;
	private String recp_del;
	private String mss_sj;
	private String mss_cn;
	private String mss_date;
	private String dspt_id;
	private String recp_id;
	private String mms_check;
}
