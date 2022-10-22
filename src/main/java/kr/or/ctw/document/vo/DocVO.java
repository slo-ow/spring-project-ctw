package kr.or.ctw.document.vo;

import kr.or.ctw.member.vo.MemberVO;
import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

@Data
@Alias("docVO")
public class DocVO extends MemberVO{
	
	private String doc_pk;
	private String doc_nm;
	private long doc_size;
	private String doc_date;
	private String doc_del;
	private String doc_extsn;
	private String mem_id;
	private int se_pk;
	private int dept_pk;
	private String search;
}
