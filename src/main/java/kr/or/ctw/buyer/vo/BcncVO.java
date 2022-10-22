package kr.or.ctw.buyer.vo;

import kr.or.ctw.member.vo.MemberVO;
import kr.or.ddit.commons.ibatis.Alias;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <pre>
 * 	거래처,거래처 임시 Value Object
 * </pre>
 * @author 문성철
 * @since 2017. 5. 2.
 * @version 1.0
 * @see
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일          수정자            수정내용
 * --------     --------      -------------
 * 2017. 5. 2.    작성자명          최초작성
 * Copyright (c) 2017 by CTW All right reserved
 * </pre>
 */ 

@Data
@Alias("BcncVO")
@EqualsAndHashCode(callSuper=false)
public class BcncVO extends MemberVO{
	
	private int bcnc_pk;
	private String bcnc_nm;
	private String bcnc_telno;
	private String bcnc_addr;
	private String bcnc_rprsntv;
	private String lat;
	private String lng;
	private String confm;
	private String mem_id;
	private String bcnc_mem_name;
	private String bcnc_mem_telno;
	private int dept_pk;	
	
}
