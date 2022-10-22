package kr.or.ctw.project.vo;

import kr.or.ctw.member.vo.MemberVO;
import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

// 진행상태
@Data
@Alias("progrsVO")
public class ProgrsVO extends PrjctPartcptnVO{

	private int progrs_pk;
	private String progrs_nm;
}
