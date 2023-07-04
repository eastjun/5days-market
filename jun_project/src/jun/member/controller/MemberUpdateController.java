package jun.member.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jun.member.control.Controller;
import jun.member.dao.MemberDAO;
import jun.member.dto.MemberDTO;
import jun.member.hander.MemberHandlerAdapter;

public class MemberUpdateController implements Controller{

	private static Log log = LogFactory.getLog(MemberUpdateController.class);
	@Override
	public MemberHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		
		String userid= request.getParameter("userid");
		log.info(userid);
		String nickname = request.getParameter("nickname");
		log.info(nickname);
		String phonenum = request.getParameter("phonenum");
		log.info(phonenum);
		String email = request.getParameter("email");
		log.info(email);
		String oldpassword= request.getParameter("oldPassword");
		String newPassword= request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setUserid(userid);

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO existingMemberDTO = memberDAO.memberSelect(memberDTO);
	    String currentPassword = existingMemberDTO.getPassword();
		
	    // 입력된 이전 비밀번호와 현재 비밀번호를 비교
        if (currentPassword.equals(oldpassword)) {
            // 새 비밀번호가 입력되고 확인 비밀번호와 일치하는지 확인
            if (newPassword != null && !newPassword.isEmpty() && newPassword.equals(confirmPassword)) {
                // 회원 비밀번호 업데이트
                existingMemberDTO.setPassword(newPassword);
                // 다른 회원 정보 업데이트
                existingMemberDTO.setNickname(nickname);
                existingMemberDTO.setPhonenum(phonenum);
                existingMemberDTO.setEmail(email);

                // 회원 업데이트 수행
                memberDAO.memberUpdate(existingMemberDTO);
                log.info("비밀번호가 성공적으로 업데이트되었습니다.");
            }
            else if (newPassword.isEmpty()) {
            	
            	 existingMemberDTO.setNickname(nickname);
                 existingMemberDTO.setPhonenum(phonenum);
                 existingMemberDTO.setEmail(email);

                 // 회원 업데이트 수행
                 memberDAO.memberUpdate(existingMemberDTO);
                 log.info("비밀번호가 성공적으로 업데이트되었습니다.");				
			}
            else {
				request.setAttribute("errorMessage", "새 비밀번호와 확인이 일치하지 않습니다.");
			}

            
            MemberHandlerAdapter memberHandlerAdapter = new MemberHandlerAdapter();
            memberHandlerAdapter.setPath("/WEB-INF/view/memberupdate.jsp");
            return memberHandlerAdapter;
        } else {
            // 에러 메시지 설정하고 JSP 페이지로 포워딩
            request.setAttribute("errorMessage", "비밀번호가 올바르지 않습니다.");

            // 수정 성공 페이지로 리다이렉트
            MemberHandlerAdapter memberHandlerAdapter = new MemberHandlerAdapter();
            memberHandlerAdapter.setPath("/WEB-INF/view/memberupdate.jsp");
            return memberHandlerAdapter;
        }
    }
}


	


