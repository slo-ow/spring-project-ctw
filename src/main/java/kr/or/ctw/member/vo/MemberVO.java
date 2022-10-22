package kr.or.ctw.member.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;
import lombok.EqualsAndHashCode;

// 사원테이블
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("memberVO")
public class MemberVO extends ClsfVO{
	
	private String mem_id;
	private String mem_nm;
	private String mem_pass;
	private String mem_telno;
	private String mem_date;
	private String mem_email;
	private String mem_addr;
	private String mem_brthdy;
	private int grad_pk;
	private int dept_pk;
	private int clsf_pk;
	private String mem_sex;
	private String mem_photo;	
	private int mem_count;

}
