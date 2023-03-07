package mul.cam.a.service;

import java.util.List;

import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;

public interface BbsService {

	List<BbsDto> getBbsList(BbsParam bbs);
	int getAllBbs(BbsParam bbs);
	BbsDto getdetail(String seq);
	boolean bbswrite(BbsDto dto);
	boolean bbsupdate(BbsDto dto);
	boolean bbsdelete(String seq);
	void countUpdate(String seq);
	void bbsanswerUpdate(String seq);
	boolean bbsanswerInsert(BbsDto dto);
	boolean commentWrite(BbsComment bbc);
	List<BbsComment> commentList(int seq);
	
}
