package kr.or.ctw.board.vo;

import java.sql.Timestamp;

import lombok.Data;

//자유게시판 파일테이블

@Data
public class LbrtyBBSFileVO {
	private String bbs_f_pk;
	private String bbs_f_nm;
	private Timestamp bbs_f_date;
	private String bbs_f_extsn;
	private long bbs_f_size;
	private int bbs_fk;

}
