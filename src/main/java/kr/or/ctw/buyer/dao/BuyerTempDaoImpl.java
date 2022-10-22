package kr.or.ctw.buyer.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ctw.buyer.vo.BcncVO;
import kr.or.ctw.member.vo.MemberVO;

/**
 * <pre>
 *  거래처 임시 Data Access Object 구현 클래스
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
@Repository("buyertempDao")
public class BuyerTempDaoImpl implements BuyerDao {

	@Autowired
	private SqlMapClient client;

	// 개인 거래처 요청을 거래처 임시테이블에 저장하는 메서드
	@Override
	public int insertBcnc(BcncVO vo) throws SQLException {
		return (int) client.insert("bcnc.insertBcncTmpr", vo);
	}

	@Override
	public List<BcncVO> listAll(MemberVO vo) throws SQLException {
		return client.queryForList("bcnc.tmprlistall");
	}

	@Override
	public int deleteBcnc(int num) throws SQLException {
		return client.delete("bcnc.deleteBcncTmpr", num);
	}

	@Override
	public int updateBcnc(BcncVO vo) throws SQLException {
		return client.update("bcnc.updateBcncTmpr", vo);
	}

	@Override
	public BcncVO infoBcnc(int bcnc_pk) throws SQLException {
		return (BcncVO) client.queryForObject("bcnc.infoBcncTmpr",bcnc_pk);
	}
	
	@Override
	public void updateStatus(int bcnc_pk) throws SQLException{
	}
}
