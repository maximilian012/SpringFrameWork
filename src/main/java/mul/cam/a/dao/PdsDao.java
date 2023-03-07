package mul.cam.a.dao;

import java.util.List;

import mul.cam.a.dto.PdsDto;
import mul.cam.a.dto.PdsParam;

public interface PdsDao {
	
	//List<PdsDto> pdslist();
	
	int uploadPds(PdsDto dto);

	void downCnt(int seq);

	PdsDto pdsdetail(String seq);

	List<PdsDto> getPdsList(PdsParam param);

	int pdsdelete(String seq);

	int pdsupdate(PdsDto dto);



}
