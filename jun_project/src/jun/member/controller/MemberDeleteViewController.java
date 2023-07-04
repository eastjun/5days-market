package jun.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jun.member.control.Controller;
import jun.member.hander.MemberHandlerAdapter;

public class MemberDeleteViewController implements Controller{

	@Override
	public MemberHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		MemberHandlerAdapter memberHandlerAdapter = new MemberHandlerAdapter();
        memberHandlerAdapter.setPath("/WEB-INF/view/memberDeleteView.jsp");
        return memberHandlerAdapter;
	}

}
