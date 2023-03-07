package mul.cam.a.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mul.cam.a.dto.KakaoDto;
import mul.cam.a.dto.MemberDto;
import mul.cam.a.service.MemberService;

@Controller
public class MemberController {

	// 서비스 (접근)생성
	@Autowired // Bean을 자동으로 매핑해주는 개념
	MemberService service;
	
	@RequestMapping(value="/kakaoLogin.do", method=RequestMethod.GET)
	public String kakaoLogin(@RequestParam(value = "code", required = false) String code) throws Exception {
		System.out.println("#########" + code);
		// code를 보내 access_Token얻기
		String access_Token = service.getAccessToken(code);
		System.out.println("###access_Token#### : " + access_Token);
		
		KakaoDto userInfo = service.getUserInfo(access_Token);
		System.out.println("###access_Token#### : " + access_Token);
		//System.out.println("###nickname#### : " + userInfo.get("nickname"));
		//System.out.println("###email#### : " + userInfo.get("email"));
		
		service.invalidate();
		// 위 코드는 session객체에 담긴 정보를 초기화 하는 코드.
		service.setAttribute("kakaoN", userInfo.getK_name());
		service.setAttribute("kakaoE", userInfo.getK_email());
		// 위 2개의 코드는 닉네임과 이메일을 session객체에 담는 코드
		// jsp에서 ${sessionScope.kakaoN} 이런 형식으로 사용할 수 있다.
		
		
		return "kakaologin";
		/*
		 * 리턴값의 testPage는 아무 페이지로 대체해도 괜찮습니다.
		 * 없는 페이지를 넣어도 무방합니다.
		 * 404가 떠도 제일 중요한건 #########인증코드 가 잘 출력이 되는지가 중요하므로 너무 신경 안쓰셔도 됩니다.
		 */
    	}
	
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login() {
		
		
		return "login";
	}
	@RequestMapping(value = "regi.do", method = RequestMethod.GET)
	public String regi() {
		
		
		return "regi";
	}
	@ResponseBody
	@RequestMapping(value = "idcheck.do", method = RequestMethod.POST)
	public String idcheck(String id) {
		
		
		boolean isS = service.idcheck(id);
		if (isS == true) { // id 있음
			return "NO";
		}
		
		return "YES";
	}
	@RequestMapping(value = "regiAf.do", method = RequestMethod.POST)
	public String regiAf(MemberDto dto, Model model) {
		
		
		boolean isS = service.addmember(dto);
		
		String msg = "";
		
		if (isS) {
			
			msg = "MEMBER_YES";
			
			
		}else {
			msg = "MEMBER_NO";
		}
		model.addAttribute("msg", msg);
		return "message";
	}
	
	
	
	@RequestMapping(value = "loginAf.do", method = RequestMethod.POST)
	public String loginAf(HttpServletRequest req, MemberDto dto, Model model) {
		
		MemberDto mem = service.loginAf(dto);
		
		String loginOK = "";
		if (mem != null) {
			 req.getSession().setAttribute("login", mem);
			// req.getSession().setMaxInactiveInterval(60 * 60 * 60 * 60);
			 
			 loginOK = "BBS_ADD_OK"; // 로그인 성공
			
		}else {
			 loginOK = "BBS_ADD_NO";
			
		}
		model.addAttribute("loginOK", loginOK);
		
		model.addAttribute("mem", mem);
		
		return "message";
	}
	@RequestMapping(value = "sessionOut.do", method = RequestMethod.GET)
	public String sessionOut(Model model) {
		
		String sessionOut = "logout";
		
		model.addAttribute("sessionOut", sessionOut);
		
		return "message";
	}
	
} 
