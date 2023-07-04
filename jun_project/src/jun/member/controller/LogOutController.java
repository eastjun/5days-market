package jun.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jun.member.control.Controller;
import jun.member.hander.MemberHandlerAdapter;

public class LogOutController implements Controller{

	@Override
	public MemberHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
	    session.invalidate();

	    // 쿠키 삭제
	    Cookie cookie = new Cookie("userid", null);
	    cookie.setMaxAge(0);
	    cookie.setPath("/");
	    response.addCookie(cookie);
		MemberHandlerAdapter memberHandlerAdapter = new MemberHandlerAdapter();
		memberHandlerAdapter.setPath("/WEB-INF/view/logout.jsp");
		return memberHandlerAdapter;
	}

}
