package kr.or.ctw.noticeboard.vo;

import kr.or.ctw.member.vo.MemberVO;
import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("cmpnynoticeVO")
public class CmpnyNoticeVO extends MemberVO{
	
	private int notice_pk;
	private String notice_sj;
	private String notice_cn;
	private String notice_date;
	private int notice_cnt;
	private String mem_id;
	private String actvty_pk;
}
