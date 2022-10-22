package kr.or.ctw.vote.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

// 투표율
@Data
@Alias("votertVO")
public class VoteRtVO {

	private int rt_pk;
	private String mem_id;
	private int choice_fk;
	private int vote_fk;

	

}
