package kr.or.ctw.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;
/**
 * 파일을 다운로드하기위한 view컨트롤러
 * 
 * <pre>
 * 
 * </pre>
 * @author 김완수
 * @since 2017. 5. 2.
 * @version 1.0
 * @see kr.or.ctw.common.file FileDownloadView
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일             수정자        수정내용
 * --------------     --------    -----------
 * 2017. 5. 2.     	  김완수        최초작성
 * Copyright (c) 2017 by DDIT All right reserved
 * </pre>
 */
public class FileDownloadView extends AbstractView{

    public FileDownloadView(){
        //content type을 지정. 
        setContentType("apllication/download; charset=utf-8");
    }
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
         HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        // 사용자가 저장한 실제 이름 
        String fileRealName= req.getParameter("real_name");
        // file 저장된 실제  이름 
        File file = (File) model.get("downloadFile");

        res.setContentType(getContentType());
        res.setContentLength((int) file.length());
        res.setHeader("Content-Disposition", "attachment; filename=\"" +URLEncoder.encode(fileRealName, "utf-8") + "\";");
        res.setHeader("Content-Transfer-Encoding", "binary");
        OutputStream out = res.getOutputStream();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try { 
                    fis.close(); 
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        out.flush();
    }
}