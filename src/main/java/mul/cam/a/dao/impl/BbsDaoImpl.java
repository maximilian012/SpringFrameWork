package mul.cam.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.cam.a.dao.BbsDao;
import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;


@Repository // 저장소
public class BbsDaoImpl implements BbsDao {

	@Autowired
	SqlSession session;
	
	String ns = "Bbs.";
	@Override
	public List<BbsDto> getBbsList(BbsParam bbs) {
		
		return session.selectList(ns + "getBbsList", bbs);
	}
	
	@Override
	public int getAllBbs(BbsParam bbs) {

		return session.selectOne(ns + "getAllBbs", bbs);
	}

	@Override
	public BbsDto getdetail(String seq) {
		
		return session.selectOne(ns + "getdetail", seq);
		
	}

	@Override
	public int bbswrite(BbsDto dto) {
		
		return session.insert(ns + "bbswrite", dto);
	}

	@Override
	public int bbsupdate(BbsDto dto) {
		
		return session.insert(ns + "bbsupdate", dto);
	}

	@Override
	public int bbsdelete(String seq) {

		return session.delete(ns + "bbsdelete", seq);
	}

	@Override
	public void countUpdate(String seq) {
		
		 session.update(ns + "countUpdate", seq);
	}

	@Override
	public void bbsanswerUpdate(String seq) {
		
		session.update(ns + "bbsanswerUpdate", seq);
	}

	@Override
	public int bbsanswerInsert(BbsDto dto) {
		
		return session.insert(ns + "bbsanswerInsert", dto);
	}

	@Override
	public int commentWrite(BbsComment bbc) {
		
		return session.insert(ns + "commentWrite", bbc);
	}

	@Override
	public List<BbsComment> commentList(int seq) {
		
		return session.selectList(ns + "commentList", seq);
	}

	

}
