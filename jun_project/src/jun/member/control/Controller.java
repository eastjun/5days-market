package jun.member.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jun.member.hander.MemberHandlerAdapter;

public interface Controller {

	public MemberHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response);
	
	}

