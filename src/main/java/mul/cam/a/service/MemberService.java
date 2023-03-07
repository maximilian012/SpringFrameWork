package mul.cam.a.service;

import java.util.List;

import mul.cam.a.dto.KakaoDto;
import mul.cam.a.dto.MemberDto;

public interface MemberService {

	
	List<MemberDto> allMember();

	boolean idcheck(String id);
	
	boolean addmember(MemberDto dto);

	MemberDto loginAf(MemberDto dto);

	String getAccessToken(String authorize_code);

	KakaoDto getUserInfo(String access_Token);

	void invalidate();

	void setAttribute(String string, String k_name);
}
