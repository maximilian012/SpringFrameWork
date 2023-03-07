package mul.cam.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.cam.a.dao.PdsDao;
import mul.cam.a.dto.PdsDto;
import mul.cam.a.dto.PdsParam;

@Repository
public class PdsDaoImpl implements PdsDao {

	@Autowired
	SqlSession session;
	
	String ns = "Pds.";

	/*
	 * @Override public List<PdsDto> pdslist() {
	 * 
	 * return session.selectList(ns + "pdslist"); }
	 */

	@Override
	public int uploadPds(PdsDto dto) {
		
		return session.insert(ns + "uploadPds", dto);
	}

	@Override
	public void downCnt(int seq) {
		
		session.update(ns + "downCnt", seq);
		
	}

	@Override
	public PdsDto pdsdetail(String seq) {
		
		return session.selectOne(ns + "pdsdetail", seq);
	}

	@Override
	public List<PdsDto> getPdsList(PdsParam param) {
		
		return session.selectList(ns + "getPdsList", param);
	}

	@Override
	public int pdsdelete(String seq) {
		
		return session.delete(ns + "pdsdelete", seq);
	}

	@Override
	public int pdsupdate(PdsDto dto) {
		
		return session.update(ns + "pdsupdate", dto);
	}

	


	
	
}
