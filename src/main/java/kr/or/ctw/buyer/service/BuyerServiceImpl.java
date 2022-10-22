package kr.or.ctw.buyer.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import kr.or.ctw.buyer.dao.BuyerDao;
import kr.or.ctw.buyer.vo.BcncVO;
import kr.or.ctw.member.vo.MemberVO;

@Service("buyerService")
public class BuyerServiceImpl implements BuyerService {

	@Resource(name="buyerDao")
	BuyerDao dao;

	@Resource(name="buyertempDao")
	BuyerDao tempdao;

	/**
	 * 	거래처 or 임시거래처 등록 / division 으로 구분해서 등록함  
	 * 
	 * @param BcncVO
	 * @return int
	 * @author 문성철
	 * @date 2017. 5. 02.
	 */
	// 거래처 등록
	@Override
	// 임시거래처의 상태업데이트와 거래처테이블에 insert를 동시에 진행하는데, 만약 하나라도 실패할경우에 트랜잭션으로 rollback 처리를 진행한다.
	@Transactional(rollbackFor=SQLException.class)
	public int insertBcnc(BcncVO vo,String division){
		int res = 0;
		int bcnc_pk = vo.getBcnc_pk();
		try {			
			// 구분기호인 division을 사용해서 전단계에서 어떤것이 날라왔나 구분해서 
			// 임시거래처인가 거래처인가를 구분한다.
			if(division.equals("bcnc")){	
				// 임시거래처의 상태를 업데이트함.
				dao.updateStatus(bcnc_pk);
				res = dao.insertBcnc(vo);
			}else if(division.equals("bcnc_tmpr")){
				res = tempdao.insertBcnc(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return res;
	}

	
	// 임시거래처와 거래처 리스트 
	@Override
	public List<BcncVO> listAll(String division,HttpSession ses){
		List<BcncVO> list = null;
		
		MemberVO vo = (MemberVO) ses.getAttribute("member");
		// 세션을 사용해서 저장되어있는 회원의 값을 가져와 파라메터로 넘겨준다
		
		try {
			// 구분기호인 division을 사용해서 전단계에서 어떤것이 날라왔나 구분해서 
			// 임시거래처인가 거래처인가를 구분한다. 
			// 착각을 해서 회사랑 부서로 나눴음 
			if(division.equals("bcnc")){
				list = dao.listAll(vo);
			}else if(division.equals("bcnc_tmpr")){
				list =	tempdao.listAll(vo);
			}else{
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return list;
	}

	
	@Override
	public int deleteBcnc(int num,String division){
		int a = 0;
		
		try {
			a = dao.deleteBcnc(num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return a;
	}

	@Override
	public int updateBcnc(BcncVO vo,String division){
		int a = 0;
		
		try {
			a = dao.updateBcnc(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return a;
	}

	
	// 거래처 정보
	@Override
	public BcncVO infoBcnc(int bcnc_pk,String division) {
		
		BcncVO vo = null;
		try {
			// 구분기호인 division을 사용해서 전단계에서 어떤것이 날라왔나 구분해서 
			// 임시거래처인가 거래처인가를 구분한다. 
			// 착각을 해서 회사랑 부서로 나눴음 
			if(division.equals("bcnc")){
				vo = dao.infoBcnc(bcnc_pk);
			}else if(division.equals("bcnc_tmpr")){
				vo =	tempdao.infoBcnc(bcnc_pk);
			}else{
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	/**
	 * 	거래처 승인 이후 승인여부의 상태값을 변경하는 메소드 
	 * 
	 * @param bcnc_pk
	 * @return 
	 * @author 문성철
	 * @date 2017. 5. 4.
	 */
	@Override	
	public void updateStatus(int bcnc_pk){
		try {
			dao.updateStatus(bcnc_pk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	};
}
