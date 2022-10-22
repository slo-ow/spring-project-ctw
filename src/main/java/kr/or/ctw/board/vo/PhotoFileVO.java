package kr.or.ctw.board.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;
import lombok.EqualsAndHashCode;

// 이미지 파일
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("photofileVO")
public class PhotoFileVO {
	private String photo_f_pk;	// 파일이름
	private String photo_f_nm;	// 오리지널 파일이름
	private String photo_f_date;
	private long photo_f_size;	// 파일 사이즈
	private int photo_fk;
}
