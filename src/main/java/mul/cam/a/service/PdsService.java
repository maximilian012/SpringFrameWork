package mul.cam.a.service;

import java.util.List;

import mul.cam.a.dto.PdsDto;
import mul.cam.a.dto.PdsParam;

public interface PdsService {
	
	//List<PdsDto> pdslist();
	boolean uploadPds(PdsDto dto);
	void downCnt(int seq);
	PdsDto pdsdetail(String seq);
	List<PdsDto> getPdsList(PdsParam param);
	boolean pdsdelete(String seq);
	boolean pdsupdate(PdsDto dto);

}
