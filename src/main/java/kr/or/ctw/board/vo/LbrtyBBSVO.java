package kr.or.ctw.board.vo;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//자유게시판 테이블
@Data 
@Alias("LbrtyBBSVO")
public class LbrtyBBSVO {
	private int bbs_pk;
	private String bbs_sj;
	private String bbs_cn;
	private Timestamp bbs_date;
	private int bbs_cnt;
	private String mem_id;
	private String actvty_pk;
	private MultipartFile file;
	

}
