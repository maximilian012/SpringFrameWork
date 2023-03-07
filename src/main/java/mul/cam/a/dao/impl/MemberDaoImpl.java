package mul.cam.a.dao.impl;

import java.util.HashMap;
import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.cam.a.dao.MemberDao;
import mul.cam.a.dto.KakaoDto;
import mul.cam.a.dto.MemberDto;

@Repository // 저장소
public class MemberDaoImpl implements MemberDao{

	
	// MyBatis 접근(생성) dao에서 db에 접근 하려면 myBatis와 조인을 해야한다. 그럴려면 myBatis문법을 사용해야
	@Autowired // new 할필요가 없다, 클래스 자동 생성 객체로
	SqlSession session; // 이게 mybatis 이걸 언제든지 갖다 쓰기 위해 applicationContext.xml에서 갖다 쓴다
	
	
	@Override
	public List<MemberDto> allMember() {

		 ///          mybatis와 조인후 db에 값을 꺼내온다
		return session.selectList("Member." + "allMember"); // 꺼내와서 리턴 session을 통해 mapper로 접근가능(applicationContext.xml에 있음)
	}
	@Override
	public int idcheck(String id) {
		
		///          mybatis와 조인후 db에 값을 꺼내온다
		return session.selectOne("Member." + "idcheck", id); // 꺼내와서 리턴 session을 통해 mapper로 접근가능(applicationContext.xml에 있음)
	}
	@Override
	public int addmember(MemberDto dto) {
		
		
		return session.insert("Member." + "addmember", dto);
	}
	@Override
	public MemberDto loginAf(MemberDto dto) {
		
		
		return  session.selectOne("Member." + "loginAf", dto);
	}
	@Override
	public KakaoDto findkakao(HashMap<String, Object> userInfo) {
		
		System.out.println("RN:"+userInfo.get("nickname"));
		System.out.println("RE:"+userInfo.get("email"));
		
		return session.selectOne("Member.findKakao", userInfo);
	}
	@Override
	public void kakaoinsert(HashMap<String, Object> userInfo) {
		
		session.insert("Member.kakaoInsert",userInfo);
		
	}
	
	
	
	
	
}
