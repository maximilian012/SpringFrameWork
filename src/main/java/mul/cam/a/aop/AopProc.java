package mul.cam.a.aop;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import mul.cam.a.dto.MemberDto;

/*
 *  AOP(Aspect Oriented Programming) 관점지향
 *  목적 : 감시자 네 여기 왔니 처리해 줄께
 * 
 */

@Aspect
public class AopProc {

//	@Around("within(mul.cam.a.controller.*) or within(mul.cam.a.dao.*.*)")
	@Around("within(mul.cam.a.controller.*)")
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		
		// seesion check 
		// request 얻어 오기 어디서나 가능
		/*
		 * HttpServletRequest request =
		 * ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).
		 * getRequest(); if (request != null) { HttpSession session =
		 * request.getSession(); MemberDto login = (MemberDto
		 * )session.getAttribute("login");
		 * 
		 * if (login == null) { // session 만료
		 * 
		 * return "redirect:/sessionOut.do";
		 * 
		 * // controller 에서 controller로 이동 시 redirect쓰기 } }
		 */
		
		
		// logger
		String signatureStr = joinpoint.getSignature().toShortString(); // 감시자가 감시자 역할을 하다가 실행이 되면
		
		try {
			Object obj = joinpoint.proceed(); // controller 진입시 
			// 공통으로 들어가는거
			System.out.println("AOP log:" + signatureStr + "method operation " + new Date()); // controller의 어떤 method가 실행 되었는지
			
			return obj;
		}finally {
			//System.out.println("After operation: ");
		}
	}
}
