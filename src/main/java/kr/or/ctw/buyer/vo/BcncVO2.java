package kr.or.ctw.buyer.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

// 거래처
@EqualsAndHashCode
@Data
public class BcncVO2 {

	private int bcnc_pk;	
	private String bcnc_nm;
	private String bcnc_telno;
	private String bcnc_addr;
	private String bcnc_rprsntv;
	private String mem_id;
	private String lat;
	private String lng;
	private String bcnc_mem_name;
	private String bcnc_mem_telno;
	
}


