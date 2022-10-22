package kr.or.ctw.project.vo;

import kr.or.ctw.member.vo.MemberVO;
import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

// 프로젝트 참여자
@Data
@Alias("prjctpartcptnVO")
public class PrjctPartcptnVO extends MemberVO {

	private int partcptn_pk;
	private int prjct_fk;
	private String mem_id;
	private String prjct_at;
	private String partcptn_date;
}
