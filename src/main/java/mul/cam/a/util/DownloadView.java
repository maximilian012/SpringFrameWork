package mul.cam.a.util;

import java.io.File;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import mul.cam.a.service.PdsService;

public class DownloadView extends AbstractView { // 다운로드 받는 곳
	
	@Autowired
	PdsService service;
	
	/*
	 * AbstractView을 이용한 파일다운로드 
	 * 
	 * 1. file-context에다 bean설정
	 * 2. AbstractView으로 상속받아서 뷰로 사용될 클래스를 만든다
	 * 
	 */	

	

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("DownloadView renderMergedOutputModel");
		
		// controller에서 넘어온 객체들
		File downloadFile = (File)model.get("downloadFile"); // 경로와 함께 넘어온 파일
		String filename = (String)model.get("filename"); // 원본 파일명
		int seq = (Integer)model.get("seq");
		
		response.setContentType(this.getContentType());
		response.setContentLength((int)downloadFile.length());
		
		filename = URLEncoder.encode(filename, "utf-8");
		
		// 다운로드 창에 실제로 나오도록
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";"); // 다운로드 받았을때 원본파일로 바꿔준다
		response.setHeader("Content-Transfer-Encoding", "binary;"); // 2진수형태로 받아라
		response.setHeader("Content-Length", "" + downloadFile.length()); // 
		response.setHeader("Pragma", "no-cache;"); // 저장을 잠깐 하겠는냐 아니
		response.setHeader("Expires", "-1;"); // 기한을 줌는데 필요하냐? 아니(-1)
		
		OutputStream os = response.getOutputStream();
		FileInputStream fis = new FileInputStream(downloadFile);
		
		// 실제 데이터를 기입
		FileCopyUtils.copy(fis, os);
		
		// download count 증가
		service.downCnt(seq);
		 
		if (fis != null) {
			fis.close();
		}
		
	}

}
