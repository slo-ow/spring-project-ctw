package kr.or.ctw.project.vo;

import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;

// 프로젝트를 진행하고 있는 사원들
@Data
@Alias("progrsmemVO")
public class ProgrsMemVO {

	private int progrs_pk;
	private int partcptn_fk;
	private int progrs_f_fk;
}
