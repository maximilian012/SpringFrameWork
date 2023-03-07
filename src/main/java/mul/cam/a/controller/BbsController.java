package mul.cam.a.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;
import mul.cam.a.service.BbsService;

@Controller
public class BbsController {

	@Autowired
	BbsService service;
	
	
	
	@GetMapping(value = "bbslist.do")
	public String bbslist(Model model, BbsParam param) {
		
		
		//글의 시작과 끝
		int pn = param.getPageNumber(); // 0,1,2,3,4
		int start = 1 + (pn * 10);
		int end = (pn + 1) * 10; // 10,20,30,40...
		
		param.setStart(start);
		param.setEnd(end);
		
		
		List<BbsDto> list = service.getBbsList(param);
		int len = service.getAllBbs(param);
		
		int pageBbs = len / 10; // 25 / 10 -> 2
		if ((len % 10) > 0) {
			pageBbs = pageBbs + 1;
		}
		
		if (param.getChoice() == null || param.getChoice().equals("") || param.getSearch() == null
				|| param.getSearch().equals("")) {
		
			param.setChoice("검색");
			param.setSearch("");;
		}
		
		model.addAttribute("bbslist", list); // 게시판 리스트
		model.addAttribute("pageBbs", pageBbs);
		model.addAttribute("pageNumber", param.getPageNumber());// 현재 페이지
		model.addAttribute("choice", param.getChoice()); // 검색
		model.addAttribute("search", param.getSearch()); // 
		
		return "bbslist";
	}
	
	@GetMapping(value = "bbsdetail.do")
	public String bbslist(Model model, String seq) {
		
		BbsDto dto =  service.getdetail(seq);
		service.countUpdate(seq);
		model.addAttribute("dto", dto);
		return "bbsdetail";
	}
	
	@GetMapping(value = "bbswrite.do")
	public String bbswrite() {
		return "bbswrite";
	}
	
	@GetMapping(value = "bbswriteAF.do")
	public String bbswriteAf(Model model, BbsDto dto) {
		
		boolean b =  service.bbswrite(dto);
		String bbswrite = "";
		if (b) {
			bbswrite = "writeOK";
		}else {
			bbswrite = "writeNO";
		}
		model.addAttribute("bbswrite", bbswrite);
		
		return "message"; // controller 에서 controller로 이동 redirect:/bbslist.do, forward
	}
	
	@GetMapping(value = "bbsupdate.do")
	public String bbsupdate(BbsDto dto, Model model) {
		model.addAttribute("dto", dto);
		return "bbsupdate";
	}
	@GetMapping(value = "bbsupdateAf.do")
	public String bbsupdateAf(BbsDto dto, Model model) {
		
		boolean b = service.bbsupdate(dto);
		
		String update = "";
		if (b) {
			update = "updateOK";
		} else {
			update = "updateNO";
		}
		model.addAttribute("update", update);
		return "message";
	}
	
	@GetMapping(value = "bbsdelete.do")
	public String bbsdelete(String seq, Model model) {
		
		boolean b = service.bbsdelete(seq);
		
		String delete = "";
		if (b) {
			delete = "deleteOK";
		} else {
			delete = "deleteNO";
		}
		model.addAttribute("delete", delete);
		
		return "message";
	}
	
	@GetMapping(value = "bbsanswer.do")
	public String bbsanswer(String seq, Model model) {
		
		BbsDto dto =  service.getdetail(seq);
		model.addAttribute("dto", dto);
		
		return "answer";
	}
	
	
	@GetMapping(value = "bbsanswerAf.do")
	public String bbsanswerAf(String seq, Model model, BbsDto dto) {
		
		service.bbsanswerUpdate(seq);
		boolean b = service.bbsanswerInsert(dto);
		
		String bbsanswer = "";
		if (b) {
			bbsanswer = "bbsanswerOK";
		} else {
			bbsanswer = "bbsanswerNO";
		}
		model.addAttribute("bbsanswer", bbsanswer);
		
		return "message";
	}
	// 댓글용
	// db에 넣기
	@PostMapping(value = "commentWriteAf.do")
	public String commentWriteAf(Model model, BbsComment bbc) {
		
		boolean b = service.commentWrite(bbc);
		
		if (b) {
			System.out.println("댓글 작성 성공");
		}else {
			System.out.println("댓글 작성 실패");
			
		}
		
		return "redirect:/bbsdetail.do?seq=" + bbc.getSeq();
	}
	
	// 웹에다 뿌려주기
	@ResponseBody
	@GetMapping(value = "commentList.do")
	public List<BbsComment> commentList(int seq){
		
		List<BbsComment> list = service.commentList(seq);
		
		return list;
	}
	
	
	@GetMapping(value = "kakaomap.do")
	public String kakaomap(){
		
		
		return "kakaomap";
	}
	
}
