package mul.cam.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mul.cam.a.dao.BbsDao;
import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;
import mul.cam.a.service.BbsService;

@Service
public class BbsServiceImpl implements BbsService {

	@Autowired
	BbsDao dao;

	@Override
	public List<BbsDto> getBbsList(BbsParam bbs) {

		return dao.getBbsList(bbs);
	}

	@Override
	public int getAllBbs(BbsParam bbs) {
		return dao.getAllBbs(bbs);
	}

	@Override
	public BbsDto getdetail(String seq) {

		return dao.getdetail(seq);
	}

	@Override
	public boolean bbswrite(BbsDto dto) {

		int cnt = dao.bbswrite(dto);

		return cnt > 0 ? true : false;
	}

	@Override
	public boolean bbsupdate(BbsDto dto) {

		int cnt = dao.bbsupdate(dto);

		return cnt > 0 ? true : false;
	}

	@Override
	public boolean bbsdelete(String seq) {

		int cnt = dao.bbsdelete(seq);

		return cnt > 0 ? true : false;
	}

	@Override
	public void countUpdate(String seq) {

		dao.countUpdate(seq);
	}

	@Override
	public void bbsanswerUpdate(String seq) {

		dao.bbsanswerUpdate(seq);
	}

	@Override
	public boolean bbsanswerInsert(BbsDto dto) {

		int cnt = dao.bbsanswerInsert(dto);

		return cnt > 0 ? true : false;
	}

	@Override
	public boolean commentWrite(BbsComment bbc) {
		
		int cnt = dao.commentWrite(bbc);

		return cnt > 0 ? true : false;
	}

	@Override
	public List<BbsComment> commentList(int seq) {
		
		return dao.commentList(seq);
	}

}
