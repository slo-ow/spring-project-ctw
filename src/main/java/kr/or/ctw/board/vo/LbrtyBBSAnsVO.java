package kr.or.ctw.board.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class LbrtyBBSAnsVO {
	
	private int bbs_ans_pk;
	private String bbs_ans_cn;
	private Timestamp bbs_ans_date;
	private int bbs_fk;
	private String mem_id;
	

}
