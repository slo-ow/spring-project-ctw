package kr.or.ctw.common.test;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("countService")
public class CountServiceImpl implements CountService{
	
	@Resource(name="countDao")
	private CountDao dao;
	
	 

	
	@Override
	public int testCal() {
		int res =0;
		try {
			res = dao.testCal();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("res = "+res);
		return res;
	}



}
