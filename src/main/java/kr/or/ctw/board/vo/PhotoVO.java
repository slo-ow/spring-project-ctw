package kr.or.ctw.board.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ctw.member.vo.MemberVO;
import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;
import lombok.EqualsAndHashCode;

// 갤러리게시판
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("photoVO")
public class PhotoVO extends MemberVO{

	private int photo_pk;
	private String photo_sj;
	private String photo_cn;
	private String photo_date;
	private int photo_cnt;
	private String mem_id;
	private String actvty_pk;
	private String delcheck;
	private MultipartFile[] file;
	private List<PhotoFileVO> imglist;
	private int like_count;		// 좋아요개수
	private int file_count;		// 파일개수
	
}