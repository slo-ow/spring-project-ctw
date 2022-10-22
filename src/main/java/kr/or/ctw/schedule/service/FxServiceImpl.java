package kr.or.ctw.schedule.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ctw.member.vo.MemberVO;
import kr.or.ctw.schedule.dao.FxDao;
import kr.or.ctw.schedule.vo.FxVO;

@Service("fxService")
public class FxServiceImpl implements FxService{
	
	@Resource(name="departmentDao")
	private FxDao dDao;
	
	@Resource(name="personalDao")
	private FxDao pDao;
	
	@Resource(name="companyDao")
	private FxDao cDao;
	



	@Override
	public void scheduleDelete(int pk,String division) {
		
		   try {
			   if (division.equals("dept")) {
				 dDao.fxDelete(pk);
			   }else if(division.equals("com")){
				 cDao.fxDelete(pk);
			   }else if(division.equals("mem")){
				 pDao.fxDelete(pk);
			   }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

		@Override
		public void fxInsert(FxVO vo, String division){
			
			   try {
					   if (division.equals("dept")) {
							 dDao.fxInsert(vo);
					   }else if(division.equals("com")){
						   	 cDao.fxInsert(vo);
					   }else if(division.equals("mem")){
							 pDao.fxInsert(vo);
					   }
				} catch (SQLException e) {
					e.printStackTrace();
				}

			
		}

		@Override
		public FxVO scheduleInfo(String pk, String division) {
			int fx_pk = Integer.parseInt(pk);
			
			FxVO vo = null;
		   try {
				   if (division.equals("dept")) {
						vo = dDao.fxInfo(fx_pk);
				   }else if(division.equals("com")){
						vo = cDao.fxInfo(fx_pk);
				   }else if(division.equals("mem")){
						vo = pDao.fxInfo(fx_pk);
				   }
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return vo;
		}

		@Override
		public void scheduleUpdate(FxVO vo, String division) {
				
			   try {
					   if (division.equals("dept")) {
							 dDao.scheduleUpdate(vo);
					   }else if(division.equals("com")){
						   	 cDao.scheduleUpdate(vo);
					   }else if(division.equals("mem")){
							 pDao.scheduleUpdate(vo);
					   }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}

		@Override
		public List<FxVO> deptList(MemberVO vo) {
			List<FxVO> list = null;
	 		try {
				list = dDao.scheduleList(vo);

			} catch (SQLException e) {
				e.printStackTrace();
			}
	 		
			return list;
		}

		@Override
		public List<FxVO> memList(MemberVO vo) {
			List<FxVO> list = null;
	 		try {
				list = pDao.scheduleList(vo);

			} catch (SQLException e) {
				e.printStackTrace();
			}
	 		
			return list;
		}

		@Override
		public List<FxVO> cmpnyList(MemberVO vo) {
			List<FxVO> list = null;
			try {
				list = cDao.scheduleList(vo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
}
