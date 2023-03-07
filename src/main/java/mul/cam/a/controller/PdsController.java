package mul.cam.a.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;
import mul.cam.a.dto.PdsDto;
import mul.cam.a.dto.PdsParam;
import mul.cam.a.service.PdsService;
import mul.cam.a.util.PdsUtil;

@Controller
public class PdsController {

	@Autowired
	PdsService service;
	
	/*
	 * @RequestMapping(value = "pdslist.do", method = RequestMethod.GET) public
	 * String pdslist(Model model) {
	 * 
	 * List<PdsDto> list = service.pdslist(); model.addAttribute("pdslist", list);
	 * 
	 * return "pdslist";
	 * 
	 * }
	 */
	@GetMapping(value = "pdswrite.do")
	public String pdswrite() {
		
		return "pdswrite";
	}
	
	@PostMapping(value = "pdsupload.do")
	public String pdsupload(PdsDto dto,@RequestParam(value = "fileload", required = false)
										MultipartFile fileload, HttpServletRequest req) { // HttpServletRequest파일경로 얻어오기 
		
		/*
		 * MultipartFile : 큰 파일을 청크 단위로 쪼개서 효율적으로 파일 업로드를 할 수 있게 해준다.
		 * 
		 * 
		 */
		// filename취득 
		String filename = fileload.getOriginalFilename(); // 원본의 파일명
		
		dto.setFilename(filename);// 원본파일명(DB에넣기)
		
		// upload 경로 설정
		// server
		String fupload = req.getServletContext().getRealPath("/upload");
		
		// 폴더
		//String fupload = "c:\\temp";
		
		System.out.println("fupload : " + fupload);
		
		// 파일명을 충돌되지 않는 명칭(시간)으로 변경
		String newfilename = PdsUtil.getNewFileName(filename);
		
		dto.setNewFilename(newfilename); // 변경된 파일명
		
		File file = new File(fupload + "/" + newfilename); // 경로 + 새파일이름
		
		
		try {
			// 실제로 파일 생성 + 기입 = upload
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());
			
			// db에 저장
			service.uploadPds(dto);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/pdslist.do";
	}
	
	@PostMapping(value = "filedownLoad.do")
	public String filedownLoad(int seq, String filename, String newfilename, Model model, HttpServletRequest req) {
		
		// 경로취득 후
		// server
		//System.out.println("seqWQWQWQWQWQWQWQWQ : " + seq);
		String fupload = req.getServletContext().getRealPath("/upload");
				
				// 폴더
				//String fupload = "c:\\temp";
		
		// 다운로드 받을 파일
		File downloadFile = new File(fupload + "/" + newfilename);
		model.addAttribute("downloadFile", downloadFile); // file 실제 업로드되어 있는 파일명 :  경로/312221312.txt
		model.addAttribute("filename", filename); // string 원파일명 abc.txt
		model.addAttribute("seq", seq); // int 다운로드 카운트 증가시키기 위해
		
		return "downloadView"; // file-context.xml에다가 설정을 해놈 어디로 가라고
	}
	
	@GetMapping(value = "pdsdetail.do")
	public String pdsdetail(Model model, String seq) {
		
		PdsDto dto =  service.pdsdetail(seq);
		model.addAttribute("dto", dto);
		
		return "pdsdetail";
	}
	
	@GetMapping(value = "pdslist.do")
	public String pdslist(Model model, PdsParam param) {
		
		
		//글의 시작과 끝
		int pn = param.getPageNumber(); // 0,1,2,3,4
		int start = 1 + (pn * 10);
		int end = (pn + 1) * 10; // 10,20,30,40...
		
		param.setStart(start);
		param.setEnd(end);
		
		
		List<PdsDto> list = service.getPdsList(param);
	
		
		
		
		if (param.getChoice() == null || param.getChoice().equals("") || param.getSearch() == null
				|| param.getSearch().equals("")) {
		
			param.setChoice("검색");
			param.setSearch("");;
		}
		
		model.addAttribute("pdslist", list); // 게시판 리스트
		model.addAttribute("choice", param.getChoice()); // 검색
		model.addAttribute("search", param.getSearch()); // 
		
		return "pdslist";
	}
	
	@GetMapping(value = "pdsupdate.do")
	public String pdsupdate(String seq, Model model) {
		
		PdsDto pds = service.pdsdetail(seq);
		model.addAttribute("pds", pds);
		
		return "pdsupdate";
	}
	
	@PostMapping(value = "pdsupdateAf.do")
	public String pdsupdateAf(PdsDto dto,@RequestParam(value = "fileload", required = false)
							 MultipartFile fileload, HttpServletRequest req) {
		
		String orignFileName = fileload.getOriginalFilename();
		
		if (orignFileName != null && !orignFileName.equals("")) { // 파일이 변경됨
			
			String newFilename = PdsUtil.getNewFileName(orignFileName);
			
			dto.setFilename(orignFileName);
			dto.setNewFilename(newFilename);
			
			String fupload = req.getServletContext().getRealPath("/upload");
			File file = new File(fupload + "/" + newFilename);
			
			try {
				
				//새로운 파일로 업로드
				FileUtils.writeByteArrayToFile(file, fileload.getBytes());
				// db갱신
				service.pdsupdate(dto);
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}else { // 파일이 변경되지 않음 
			
			service.pdsupdate(dto);
		}
		
		
		return "redirect:/pdslist.do";
	}
	
	@GetMapping(value = "pdsdelete.do")
	public String bbsdelete(String seq, Model model) {
		
		boolean b = service.pdsdelete(seq);
		
		String pdsdelete = "";
		if (b) {
			pdsdelete = "deleteOK";
		} else {
			pdsdelete = "deleteNO";
		}
		model.addAttribute("pdsdelete", pdsdelete);
		
		return "message";
	}
	
	
	
	
}
