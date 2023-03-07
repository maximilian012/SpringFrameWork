package mul.cam.a.dao;

import java.util.HashMap;
import java.util.List;

import mul.cam.a.dto.KakaoDto;
import mul.cam.a.dto.MemberDto;

public interface MemberDao {

	List<MemberDto> allMember(); // 추상메서드 오버라이딩해라

	int idcheck(String id);
	
	int addmember(MemberDto dto);

	MemberDto loginAf(MemberDto dto);

	KakaoDto findkakao(HashMap<String, Object> userInfo);

	void kakaoinsert(HashMap<String, Object> userInfo);
}
