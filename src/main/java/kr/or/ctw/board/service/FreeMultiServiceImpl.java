package kr.or.ctw.board.service;

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

import kr.or.ctw.board.dao.FileFreeBoardDao;
import kr.or.ctw.board.dao.FreeBoardAnswerDao;
import kr.or.ctw.board.dao.FreeBoardDao;
import kr.or.ctw.board.vo.LbrtyBBSAnsVO;
import kr.or.ctw.board.vo.LbrtyBBSFileVO;
import kr.or.ctw.board.vo.LbrtyBBSVO;
import kr.or.ctw.member.vo.MemberVO;

@Service("freemultiService")
public class FreeMultiServiceImpl implements FreeMultiService{
	
	@Resource(name="freeboardDao")
	FreeBoardDao boarddao;
	
	@Resource(name="filefreeboardDao")
	FileFreeBoardDao filedao;
	
	@Resource(name="freeboardanswerDao")
	FreeBoardAnswerDao ansdao;

	//자유게시판
	@Override
	public int insertBbs(MemberVO memVO, HttpServletRequest req) {
		int res = 0;
		LbrtyBBSVO vo = new LbrtyBBSVO();
		String bbs_sj = req.getParameter("bbs_sj");
		String bbs_cn = req.getParameter("bbs_cn");
		
		vo.setMem_id(memVO.getMem_id());
		vo.setBbs_sj(bbs_sj);
		vo.setBbs_cn(bbs_cn);
		
		try {
			res = boarddao.insertBbs(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		String realFolder = "d:/freeBoard/";
		File dir = new File(realFolder);
		
		//폴더가 없을시 새로운 폴더 생성
		if(!dir.isDirectory()){
			dir.mkdirs();
		}
		LbrtyBBSFileVO fileVO = new LbrtyBBSFileVO();
		while(name.hasNext()){
			multi = multReq.getFile(name.next());
				if(multi.isEmpty() == false){
					
					//원본파일명
					String bbs_f_nm = multi.getOriginalFilename();
					//확장자
					String bbs_f_extsn = bbs_f_nm.substring(bbs_f_nm.lastIndexOf(".")+1);
					//실제 저장명
					String bbs_f_pk = simDf.format(new Date(currentTime))+bbs_f_nm;
					
					fileVO.setBbs_f_size(multi.getSize());
					fileVO.setBbs_f_extsn(bbs_f_extsn);
					fileVO.setBbs_f_nm(bbs_f_nm);
					fileVO.setBbs_f_pk(bbs_f_pk);
					fileVO.setBbs_fk(res);
					
					File test2 = new File(realFolder+"/"+bbs_f_pk);
					
					//실제 파일 저장
					try{
						multi.transferTo(test2);
						filedao.insertBbsFileInfo(fileVO);
					}catch(IllegalStateException | IOException | SQLException e){
						e.printStackTrace();
					}
					
				}//if문 end
		}// while end
		
		return res;
	}

	//게시글 조회
	@Override
	public List<LbrtyBBSVO> listAll() {
		List<LbrtyBBSVO> list = null;
		
		try {
			list = boarddao.listAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}


	//게시글 수정
	@Override
	public int updateBbs(LbrtyBBSVO vo) {
		int res = 0;
		try {
			 res = boarddao.updateBbs(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
		
	}
	
	
	//게시글 삭제
	@Override
	public int deleteBbs(int bbs_pk) {
		int res = 0;
		try {
			res = boarddao.deleteBbs(bbs_pk);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	//상세조회, 검색
	@Override
	public LbrtyBBSVO selectBbs(int pk) {
		LbrtyBBSVO vo = null;
		
		try {
			vo = boarddao.selectBbs(pk);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vo;
	}
	
	//조회수 증가
	@Override
	public void upCntBbs(int pk) {
		
		try {
			boarddao.upCntBbs(pk);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//자유게시판 파일
	//파일조회
	@Override
	public List<LbrtyBBSFileVO> selectBbsFileInfo(int bbs_f_pk) {
		
		List<LbrtyBBSFileVO> list = null;
		
		try {
			list = filedao.selectBbsFileInfo(bbs_f_pk);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	//파일 원본이름찾기(다운로드)
	@Override
	public String selectBbsFilenm(String file) {
		
		String val = null;
		
		try {
			val = filedao.selectBbsFilenm(file);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return val;
	}

	
	
	//파일 삭제
	
	@Override
	public int deleteBbsFile(String bbs_f_pk) {

		int res = 0;
		
		try {
			res = filedao.deleteBbsFile(bbs_f_pk);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	
	
	@Override
	public int insertBbsFileInfo(LbrtyBBSFileVO vo) {
		return 0;
	}

	//댓글저장
	@Override
	public int freeInsertAnswer(LbrtyBBSAnsVO vo) {
		
		int res = 0;
		
		try {
			res = ansdao.freeInsertAnswer(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	//댓글조회
	@Override
	public List<LbrtyBBSAnsVO> freeListAnswer(int bbs_ans_pk) {

		List<LbrtyBBSAnsVO> list = null;
		
		try {
			list = ansdao.freeListAnswer(bbs_ans_pk);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	//댓글삭제
	@Override
	public int freeDeleteAnswer(int bbs_ans_pk) {

		int res = 0;
		
		try {
			res = ansdao.freeDeleteAnswer(bbs_ans_pk);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}


}
