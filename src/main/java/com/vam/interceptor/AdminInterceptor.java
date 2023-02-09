package com.vam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.vam.model.MemberVO;


//상속 시에 차이점은 HandlerInterceptor 경우 인터페이스이기 때문에 implements 키워드를, HandlerInterceptorAdapter는 추상 클래스이기 때문에 extends를 키워드를 사용해야 합니다.
public class AdminInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		MemberVO lvo = (MemberVO)session.getAttribute("member");
		
		
        if(lvo == null || lvo.getAdminCk() == 0) {    // 관리자 계정 아닌 경우
            
            response.sendRedirect("/main");    // 메인페이지로 리다이렉트
            
            return false;
            
        }
        
        return true;    // 관리자 계정 로그인 경우(lvo != null && lvo.getAdminCk() == 1)
        
	}

}
