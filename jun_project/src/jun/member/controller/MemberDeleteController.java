package jun.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jun.member.control.Controller;
import jun.member.dao.MemberDAO;
import jun.member.dto.MemberDTO;
import jun.member.hander.MemberHandlerAdapter;

public class MemberDeleteController implements Controller{
	private static final Log log = LogFactory.getLog(MemberDeleteController.class);
	@Override
	public MemberHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirm");
		
		log.info(userid);
		log.info(confirm);
		
		MemberDTO	 memberDTO = new MemberDTO();
		memberDTO.setUserid(userid);
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO existingMemberDTO = memberDAO.memberSelect(memberDTO);
		String currentPassword = existingMemberDTO.getPassword();
		
		
		if ("yes".equals(confirm)) {
			if (currentPassword.equals(password)) {			
				memberDTO = memberDAO.memberDelete(userid);				
			}
			else {
				request.setAttribute("errorMessage", "비밀번호가 틀립니다.");
			}
			
		}
		else {
			request.setAttribute("errorMessage", "탈퇴를 취소합니다.");
		}
		MemberHandlerAdapter memberHandlerAdapter = new MemberHandlerAdapter();
        memberHandlerAdapter.setPath("/WEB-INF/view/memberDelete.jsp");
        return memberHandlerAdapter;
		
	}

}
