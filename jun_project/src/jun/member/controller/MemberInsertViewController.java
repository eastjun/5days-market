package jun.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jun.member.control.Controller;
import jun.member.hander.MemberHandlerAdapter;

public class MemberInsertViewController implements Controller{

	@Override
	public MemberHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		
		MemberHandlerAdapter memberHandlerAdapter = new MemberHandlerAdapter();
		memberHandlerAdapter.setPath("/WEB-INF/view/sign-up.jsp");
		return memberHandlerAdapter;
	}

}
