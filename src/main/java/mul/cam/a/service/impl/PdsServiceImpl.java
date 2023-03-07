package mul.cam.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mul.cam.a.dao.PdsDao;
import mul.cam.a.dto.PdsDto;
import mul.cam.a.dto.PdsParam;
import mul.cam.a.service.PdsService;

@Service
public class PdsServiceImpl implements PdsService {

	@Autowired
	PdsDao dao;

	/*
	 * @Override public List<PdsDto> pdslist() {
	 * 
	 * return dao.pdslist(); }
	 */

	@Override
	public boolean uploadPds(PdsDto dto) {

		int cnt = dao.uploadPds(dto);

		return cnt > 0 ? true : false;
	}

	@Override
	public void downCnt(int seq) {

		dao.downCnt(seq);

	}

	@Override
	public PdsDto pdsdetail(String seq) {

		return dao.pdsdetail(seq);
	}

	@Override
	public List<PdsDto> getPdsList(PdsParam param) {

		return dao.getPdsList(param);
	}

	@Override
	public boolean pdsdelete(String seq) {

		int cnt = dao.pdsdelete(seq);

		return cnt > 0 ? true : false;
	}

	@Override
	public boolean pdsupdate(PdsDto dto) {

		int cnt = dao.pdsupdate(dto);

		return cnt > 0 ? true : false;
	}

	

}
