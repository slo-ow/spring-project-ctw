package kr.or.ctw.mssage.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.or.ctw.board.vo.LbrtyBBSFileVO;
import kr.or.ctw.mssage.dao.MssDao;
import kr.or.ctw.mssage.vo.MssFileVO;
import kr.or.ctw.mssage.vo.MssVO;

@Service("mssService")
public class MssServiceImpl implements MssService{

	
	// 수신자
	@Resource(name="rectDao")
	MssDao rectDao;
	
	// 발신자
	@Resource(name="dsptDao")
	MssDao dsptDao;


	@Override
	public void mssInsert(HttpServletRequest req,String mem_id) {
		MssVO mvo = new MssVO();
		MssFileVO mfvo = new MssFileVO();
		
		mvo.setRecp_id(req.getParameter("recp_id"));
		mvo.setDspt_id(mem_id);
		mvo.setMss_sj(req.getParameter("mss_sj"));
		mvo.setMss_cn(req.getParameter("mss_cn"));
		int res = 0;
		try {
			res=dsptDao.mssInsert(mvo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// HttpServletRequest를 MultipartHttpServletRequest로 형변환
				MultipartHttpServletRequest multReq = (MultipartHttpServletRequest) req;
				
				//형변환 값을 iterator로 파일 담음
				Iterator<String> name = multReq.getFileNames();
				
				MultipartFile multi = null;
				
				//실제 저장명을 시간, 날짜로 하기위한 설정
				long currentTime = System.currentTimeMillis();
				SimpleDateFormat simDf = new SimpleDateFormat("yyyyMMddHHmmss");
				
				//실제저장되는 폴더위치
				String realFolder = "d:/mssage_file/";
				File dir = new File(realFolder);
				
				//폴더가 없을시 새로운 폴더 생성
				if(!dir.isDirectory()){
					dir.mkdirs();
				}
				System.out.println("file==============="+name.hasNext());
				
				while(name.hasNext()){
					System.out.println("와일문으로 들어왔다 나는 ");
					
					multi = multReq.getFile(name.next());
						if(multi.isEmpty() == false){
							System.out.println("qwdhwuivobefoubvaofdgbnodafigbdofb");
							System.out.println(multi.isEmpty());
							//원본파일명
							String mss_f_nm = multi.getOriginalFilename();
							long mss_f_size = multi.getSize();
							//실제 저장명
							String mss_f_pk = simDf.format(new Date(currentTime))+mss_f_nm;
							
							mfvo.setMss_f_nm(mss_f_nm);
							mfvo.setMss_f_size(mss_f_size);
							mfvo.setMss_f_pk(mss_f_pk);
							mfvo.setMss_fk(res);
							File test2 = new File(realFolder+"/"+mss_f_pk);
							
							//실제 파일 저장
							try{
								dsptDao.mssFileInsert(mfvo);
								multi.transferTo(test2);
							}catch(IllegalStateException | IOException | SQLException e){
								e.printStackTrace();
							}
							
						}else{
							System.out.println("그렇지 않으면 저장해라  ");
							mfvo.setMss_f_nm("1234");
							mfvo.setMss_f_size(123456);
							mfvo.setMss_f_pk(simDf.format(new Date(currentTime)));
							mfvo.setMss_fk(res);
							try {
								dsptDao.mssFileInsert(mfvo);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
				}// while end
		
		
	}



	@Override
	public List<MssVO> mssList(String mem_id, String division) {
		
		List<MssVO> list = null;
		

			try {
				if(division.equals("dspt")){
					list = dsptDao.mssList(mem_id);
				}else{
					list = rectDao.mssList(mem_id);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		
		return list;
	}

	@Override
	public void mssDeleteCheck(int mss_pk, String division) {
		
		try {
			if(division.equals("dspt")){
			     dsptDao.mssDeleteCheck(mss_pk);
			}else{
				 rectDao.mssDeleteCheck(mss_pk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		
	}

	@Override
	public void mssDelete(MssVO mvo, String division) {
		try {
			if(division.equals("dspt")){
			     dsptDao.mssDelete(mvo);
			}else{
				 rectDao.mssDelete(mvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public MssVO mssInfo(int mss_pk, String division) {
		MssVO vo = null;
		
		try {
			if(division.equals("dspt")){
			    vo = dsptDao.mssInfo(mss_pk);
			}else{
				vo = rectDao.mssInfo(mss_pk);
				System.out.println("여기로 들어오나요 ?");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return vo;
	}


	@Override
	public void mmsCheck(int mss_pk) {
		
	}

}
