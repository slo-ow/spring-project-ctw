package kr.or.ctw.vote.vo;

import java.util.List;

import kr.or.ctw.member.vo.MemberVO;
import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;
import lombok.EqualsAndHashCode;

// 투표
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("voteVO")
public class VoteVO extends MemberVO{

	private int vote_pk;
	private String vote_sj;
	private String vote_cn;
	private String vote_begin;
	private String vote_end;
	private String vote_sttus;
	private String mem_id;
	private int dept_pk;
	private String delcheck;
	private List<VoteChoiceVO> questions;
	private int mem_count;

	

	
}
