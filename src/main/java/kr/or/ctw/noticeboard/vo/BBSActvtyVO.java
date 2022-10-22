package kr.or.ctw.noticeboard.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

// 게시판 활성화관련 vo
@Data
@Alias("bbsactvtyVO")
public class BBSActvtyVO {
	private String actvty_pk;
	private String actvty_nm;
}
