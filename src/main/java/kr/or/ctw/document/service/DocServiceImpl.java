package kr.or.ctw.document.service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.or.ctw.document.dao.DocDao;
import kr.or.ctw.document.dao.KategorieDao;
import kr.or.ctw.document.vo.DocVO;
import kr.or.ctw.document.vo.KategorieVO;
import kr.or.ctw.member.vo.MemberVO;

@Service("docService")
public class DocServiceImpl implements DocService{
	
	@Resource(name="deptDocDao")
	DocDao deptDao;
	
	@Resource(name="comDocDao")
	DocDao comDao;
	
	@Resource(name="memDocDao")
	DocDao memDao;
	
	@Resource(name="KategorieDao")
	KategorieDao kateDao;

	
	
	@Override
	public List<KategorieVO> kategorieList() {
		List<KategorieVO> list = null;
			try {
				list = kateDao.listAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return list;
	}

	// 에러가 뜨면 롤백을 하기 위한 transaction
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW , rollbackFor=Exception.class)
	public void docFileInsert(HttpServletRequest req,HttpSession ses) throws IllegalStateException, IOException, SQLException {
		// 카테고리를 구분하기위해 카테고리를 따로 찾아옴
		int se_pk = Integer.parseInt(req.getParameter("kategorie"));
		
		// 회사냐 사원이냐 부서냐를 구분하기위한 구분값
		String division = req.getParameter("division");
		
		// vo에 값을 담기 위한 vo객체 생성 
		DocVO vo = new DocVO();
		// session값을 가지고 오기위한 MemberVO객체 생성 
		MemberVO memVO = (MemberVO) ses.getAttribute("member");

		// 똑같은값이 들어가는 vo를 setter를 이용해서 값을 세팅
		vo.setMem_id(memVO.getMem_id());
		vo.setSe_pk(se_pk);
		vo.setDept_pk(memVO.getDept_pk());
		
		// HttpServletRequest를 MultipartHttpServletRequest로 형변환 
		MultipartHttpServletRequest multReq = (MultipartHttpServletRequest) req;
		
		// 형변환 값을 iterator로 파일 담음 
		Iterator<String> name= multReq.getFileNames();
		
		// 
		MultipartFile multi = null;
		
		// 실제저장명을 시간과 날짜로 하기위한 설정
	    long currentTime = System.currentTimeMillis();  
	    SimpleDateFormat simDf = new SimpleDateFormat("yyyyMMddHHmmss");  
	    
	    // 실제저장되는 폴더위치 
	    String realFolder = "d:/doc/";
        File dir = new File(realFolder);
        // 폴더가 없을시 새로운 폴더 생성
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }

	    String doc_pk="";
	    String doc_nm="";
	    String doc_extsn = null;
	    
	    int cnt = 0;
	    // 파일이 있으면 시작
		while(name.hasNext()){
		  multi = multReq.getFile(name.next());
	        if(multi.isEmpty() == false){
	        	
	        	//원본파일명
	        	doc_nm = multi.getOriginalFilename();
	        	// 확장자
	        	doc_extsn = doc_nm.substring(doc_nm.lastIndexOf(".")+1);
	        	// 실제 저장명
	        	doc_pk    = simDf.format(new Date(currentTime))+"["+cnt+"]"+doc_nm;
	        	cnt++;
	        	
	        	// 디비에 들어갈 값 세팅 
	        	vo.setDoc_pk(doc_pk);
	        	vo.setDoc_nm(doc_nm);
	        	
	        	String doc = doc_extsn.toLowerCase();
	        	// 확장자를 select 하기위해 임의로 값을 정해준다. 
	        	if(doc.equals("xlsx")||doc_extsn.equals("xls")||doc_extsn.equals("xlsm")){
	        		vo.setDoc_extsn("excel");
	        	}else if(doc.equals("docx")||doc.equals("doc")){
	        		vo.setDoc_extsn("word");
	        	}else if(doc.equals("hwp")||doc.equals("hwpx")){
		        	vo.setDoc_extsn("hangle");
		        }else if(doc.equals("ppt")||doc.equals("pptx")||doc.equals("ppsx")){
		        	vo.setDoc_extsn("ppt");
		        }else if(doc.equals("alz")||doc.equals("jar")||doc.equals("7z")||doc.equals("zip")){
		        	vo.setDoc_extsn("zip");
		        }else if(doc.equals("pdf")){
		        	vo.setDoc_extsn("pdf");
		        }else{
		        	vo.setDoc_extsn(doc);
		        }
	        	
	        	
	        	
	        	vo.setDoc_size(multi.getSize());
	
	        	File test2 = new File(realFolder+"/"+doc_pk);
	        	// 실제 파일 저장 
	        	multi.transferTo(test2);
	        	// 구분기호를 사용해서 회사,사원,부서를 구분해서 다오를 사용 함
	        	if (division.equals("dept")) {
	        		deptDao.insertDoc(vo);
	        	}else if(division.equals("com")){
	        		comDao.insertDoc(vo);
	        	}else{
	        		memDao.insertDoc(vo);
	        	}	
	
	        }// if문 end
	   
		}// while end

	}// method end

	@Override
	public List<DocVO> docList(MemberVO vo, HttpServletRequest req) {
		
		List<DocVO> list = null;
		
		String division = req.getParameter("division");
		int se_pk = Integer.parseInt(req.getParameter("kategorie"));
		String doc_extsn = req.getParameter("extsn");
		int dept_pk = vo.getDept_pk();
		String search = req.getParameter("search");
		
		// 파일의 select 조건을 보타입에 저장한다. 
		DocVO docVO = new DocVO() ;
		docVO.setDoc_extsn(doc_extsn);
		docVO.setDept_pk(dept_pk);
		docVO.setSe_pk(se_pk);
		docVO.setSearch(search);
		try {
			
			if (division.equals("com")) {
				list = comDao.docList(docVO);
			}else if(division.equals("dept")){
				list = deptDao.docList(docVO);
			}else{
				list = memDao.docList(docVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void docDelete(String doc_pk, String division) {
		// 휴지통으로 보내는 작업은 해당 문서의 기본키와 구분기호를 받아와 
		// 구분기호로 구분후 삭제처리를 한다.
		try {
			if (division.equals("com")) {
					comDao.docDelete(doc_pk);
			}else if(division.equals("dept")){
					deptDao.docDelete(doc_pk);
			}else{
				    memDao.docDelete(doc_pk);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// 메서드 

	@Override
	public List<DocVO> trashcanDocList(MemberVO vo,String division) {
		// 파라미터를 vo로 받아 해당하는 조건을 충족시킨다. 
		List<DocVO> list = null;
		try {
			if (division.equals("com")) {
					list = comDao.trashcanDocList(vo);
			}else if(division.equals("dept")){
				list = deptDao.trashcanDocList(vo);
			}else{
				list = memDao.trashcanDocList(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}// -- method

	@Override
	public void trashcanDel(String doc_pk, String division) throws SQLException {
		// 파마미터로 실제저장명을 가져와 밑에 있는 실제 저장 폴더를 적어서 저 폴더에 있는 파일을 지운다. 
	   String realFolder = "d:/doc/";
	   File fileDel = new File(realFolder+doc_pk);
	   if(fileDel.delete()){
		   System.out.println("삭제 성공 ");
	   }
		   
		if (division.equals("com")) {
			comDao.trashcanDocDel(doc_pk);
		}else if(division.equals("dept")){
			deptDao.trashcanDocDel(doc_pk);
		}else{
			memDao.trashcanDocDel(doc_pk);
		}
	}

	@Override
	public void trashcanUp(String doc_pk, String division) {
		
		try {
			if (division.equals("com")) {
				comDao.trashcanDocUP(doc_pk);
			}else if(division.equals("dept")){
				deptDao.trashcanDocUP(doc_pk);
			}else{
				memDao.trashcanDocUP(doc_pk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}// 클래스
