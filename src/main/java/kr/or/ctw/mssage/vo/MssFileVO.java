package kr.or.ctw.mssage.vo;

import kr.or.ctw.member.vo.MemberVO;
import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

@Data
@Alias("mssFileVO")
public class MssFileVO extends MemberVO{
	
	private String mss_f_pk;
	private String mss_f_nm;
	private long mss_f_size;
	private String mss_f_date;
	private int mss_fk;
}
