package kr.or.ctw.board.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ctw.board.dao.FileGalleryBoardDao;
import kr.or.ctw.board.dao.GalleryBoardDao;
import kr.or.ctw.board.dao.PhotoExprsnDao;
import kr.or.ctw.board.vo.PhotoExprsnVO;
import kr.or.ctw.board.vo.PhotoFileVO;
import kr.or.ctw.board.vo.PhotoVO;
import kr.or.ctw.common.vo.pagingVO;

@Service("gallerymultiService")
public class GalleryMultiServiceImpl implements GalleryMultiService {
	
	// 사진게시판
	@Resource(name="galleryboardDao")
	GalleryBoardDao boarddao;
	
	// 이미지파일
	@Resource(name="filegalleryboardDao")
	FileGalleryBoardDao filedao;

	@Resource(name="photoexprsnDao")
	PhotoExprsnDao exprsndao;
	
	// 사진등록
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=SQLException.class)
	public int potoInsert(PhotoVO vo) throws IllegalStateException, IOException, SQLException {
		int res = 0;
		try {
			// 제목, 내용등록
			res = boarddao.potoInsert(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 이미지파일저장
		// 50MB 제한
	    int maxSize  = 1024*1024*50;
	    int size = 0;
		
		MultipartFile[] imgarr = vo.getFile(); 
		String fileName = "";
		
		for(MultipartFile img : imgarr){
			PhotoFileVO imgvo = new PhotoFileVO();
			// 파일 이름
			fileName = img.getOriginalFilename()+"_"+ System.currentTimeMillis();
			imgvo.setPhoto_f_pk(fileName);
			// 오리지널 파일 이름
			imgvo.setPhoto_f_nm(img.getOriginalFilename());
			size = (int) img.getSize();
			size = size < 1024 ? size : size/1024;
			imgvo.setPhoto_f_size(size);
			imgvo.setPhoto_fk(res);
			
			// 파일저장
			if(img.getOriginalFilename().length()!=0){

				// 파일저장경로
				// Invalid escape sequence (valid ones are  \b  \t  \n  \f  \r  \"  \'  \\ )
				String root = "D:\\finalProject(CTW)\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\companyware\\ctwimgfile";
				 
				File dir = new File(root);
				if(!dir.exists()){
					dir.mkdirs();
				}
				
				File file = new File(root+"/"+img.getOriginalFilename());
				
				img.transferTo(file);
				filedao.photoFileInsert(imgvo);
			}
			
		}
		
		return res;
	}	// potoInsert() 끝


	// 갤러리 조회  
	@Override
	public List<PhotoVO> potoList(Map<String, Object> map) {
		List<PhotoVO> list = null;
		try {
			list = boarddao.potoList(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public int likeInsert(Map<String, Object> map) {
		int res = 0;
		try {
			res = exprsndao.likeInsert(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}


	// 좋아요 취소
	@Override
	public int likedelete(Map<String, Object> map) {
		int res = 0;
		try {
			res = exprsndao.likedelete(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	// 해당 게시물의 좋아요 
	@Override
	public List<PhotoExprsnVO> photoExprsnList(Map<String, Object> map) {
		List<PhotoExprsnVO> list = null;
		try {
			list = exprsndao.photoExprsnList(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	// 좋아요개수
	@Override
	public List<PhotoExprsnVO> likeCount() {
		List<PhotoExprsnVO> list = null;
		try {
			list = exprsndao.likeCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	

	@Override
	public List<PhotoVO> listAll(pagingVO page) {
		List<PhotoVO> list = new ArrayList<PhotoVO>();

		try {
			list = boarddao.listAll(page);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 상세보기
	@Override
	public List<PhotoFileVO> selectInfo(int photo_pk) {
		List<PhotoFileVO> list = null;
		try {
			list = filedao.selectInfo(photo_pk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 포토 게시물 삭제
	@Override
	public int photoDelete(int photo_pk) {
		int res =0;
		try {
			res = boarddao.photoDelete(photo_pk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
 
	
}
