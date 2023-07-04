package jun.member.controller;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jun.member.control.Controller;
import jun.member.dao.MemberDAO;
import jun.member.dto.MemberDTO;
import jun.member.hander.MemberHandlerAdapter;

public class LoginController implements Controller {

    private static Log log = LogFactory.getLog(LoginController.class);

    @Override
    public MemberHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {

        String userid = request.getParameter("userid");
        log.info(userid);
        String password = request.getParameter("password");
        log.info(password);

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserid(userid);
        memberDTO.setPassword(password);

        MemberDAO memberDAO = new MemberDAO();

        ArrayList<MemberDTO> arrayList = new ArrayList<MemberDTO>();

        arrayList = memberDAO.memberSelectAll();
        log.info(arrayList);

        boolean loginSuccess = false;
        boolean useridExists = false;
        for (MemberDTO member : arrayList) {
            if (userid.equals(member.getUserid())) {
                useridExists = true;
                if (password.equals(member.getPassword())) {
                    loginSuccess = true;
                }
                break;
            }
        }
        
        if (loginSuccess) {
        	// 로그인 성공 시 세션에 사용자 ID 저장
            request.getSession().setAttribute("userid", memberDTO.getUserid());

            // 로그인 성공 시 쿠키에 사용자 ID 저장
            Cookie cookie = new Cookie("userid", memberDTO.getUserid());
            cookie.setMaxAge(1800);  // 쿠키 유효 시간 설정 (예: 30분)
            response.addCookie(cookie);

            request.setAttribute("message", "환영합니다 " + memberDTO.getUserid() + " 님");
            request.setAttribute("loginSuccess", true);
            request.setAttribute("userid", memberDTO.getUserid());
            log.info("로그인 성공 - ");
            MemberHandlerAdapter memberHandlerAdapter = new MemberHandlerAdapter();
            memberHandlerAdapter.setPath("/WEB-INF/view/loginView.jsp");
            return memberHandlerAdapter;

        } else if (useridExists) {
            log.info("비밀번호가 틀립니다 -");
            request.setAttribute("message", "비밀번호가 틀립니다.");
            request.setAttribute("loginSuccess", false);
            MemberHandlerAdapter memberHandlerAdapter = new MemberHandlerAdapter();
            memberHandlerAdapter.setPath("/WEB-INF/view/loginView.jsp");
            return memberHandlerAdapter;
        } else {
            log.info("아이디가 존재하지 않습니다 -");
            request.setAttribute("message", "아이디가 존재하지 않습니다.");
            request.setAttribute("loginSuccess", false);
            MemberHandlerAdapter memberHandlerAdapter = new MemberHandlerAdapter();
            memberHandlerAdapter.setPath("/WEB-INF/view/loginView.jsp");
            return memberHandlerAdapter;
        }
    }
}
