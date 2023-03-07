package mul.cam.a.dao;

import java.util.List;

import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;

public interface BbsDao {

	List<BbsDto> getBbsList(BbsParam bbs);
	
	int getAllBbs(BbsParam bbs);

	BbsDto getdetail(String seq);

	int bbswrite(BbsDto dto);

	int bbsupdate(BbsDto dto);

	int bbsdelete(String seq);

	void countUpdate(String seq);

	void bbsanswerUpdate(String seq);

	int bbsanswerInsert(BbsDto dto);
	
	// 댓글용
	int commentWrite(BbsComment bbc);
	List<BbsComment>commentList(int seq);
}
