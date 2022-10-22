package kr.or.ctw.project.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

// 프로젝트 대화방 파일
@Data
@Alias("prjctmssfileVO")
public class PrjctMssFileVO {

	private int mss_pk;
	private String mss_f_nm;
	private int mss_f_size;
	private String mss_f_date;
}
