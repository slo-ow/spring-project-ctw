package kr.or.ctw.vote.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;
import lombok.EqualsAndHashCode;

// 투표선택
@EqualsAndHashCode(callSuper=false)
@Data
@Alias("votechoiceVO")
public class VoteChoiceVO extends VoteVO{

	private int choice_pk;
	private int choice_no;
	private String choice_cn;
	private int vote_fk;
	private int vote_count;
	
}
