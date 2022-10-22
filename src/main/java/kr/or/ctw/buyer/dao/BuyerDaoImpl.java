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
 * 거래처 Data Access Object 구현 클래스
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
@Repository("buyerDao")
public class BuyerDaoImpl implements BuyerDao {

	@Autowired
	private SqlMapClient client;
	
	// 개인 거래처 요청을 거래처 테이블에 저장하는 메소드
	@Override
	public int insertBcnc(BcncVO vo) throws SQLException {
		return (int) client.insert("bcnc.insertBcnc",vo);
	}

	@Override
	public List<BcncVO> listAll(MemberVO vo) throws SQLException {
		return client.queryForList("bcnc.listAll");
	}

	@Override
	public int deleteBcnc(int num) throws SQLException {
		return client.delete("bcnc.deleteBcnc", num);
	}

	@Override
	public int updateBcnc(BcncVO vo) throws SQLException {
		return client.update("bcnc.updateBcnc", vo);
	}

	@Override
	public BcncVO infoBcnc(int bcnc_pk) throws SQLException {
		return (BcncVO) client.queryForObject("bcnc.infoBcnc",bcnc_pk);
	}
		
	// 거래처 승인 이후 승인여부의 상태값을 변경하는 메소드
	@Override
	public void updateStatus(int bcnc_pk) throws SQLException{
		client.update("bcnc.updateStatus",bcnc_pk);
	}
}
