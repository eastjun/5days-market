package jun.member.controller;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jun.member.control.Controller;
import jun.member.dao.MemberDAO;
import jun.member.dto.MemberDTO;
import jun.member.hander.MemberHandlerAdapter;

public class MemberInsertController implements Controller{

	
	private static Log log = LogFactory.getLog(MemberInsertController.class);
	@Override
	public MemberHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		
		Random random = new Random();
		int uniqieid=random.nextInt(100000);
		log.info(uniqieid);
		String userid = request.getParameter("userid");
		log.info(userid);
		String password = request.getParameter("password");
		log.info(password);
		String nickname = request.getParameter("nickname");
		log.info(nickname);
		String phonenum = request.getParameter("phonenum");
		log.info(phonenum);
		String email = request.getParameter("email");
		log.info(email);
		String birthday = request.getParameter("birthday");
		log.info(birthday);
		
		MemberDTO memberDTO = new MemberDTO();
		MemberDAO memberDAO = new MemberDAO();
		ArrayList<MemberDTO> arrayList = new ArrayList<MemberDTO>();
		
		arrayList=memberDAO.memberSelectAll();
		log.info(arrayList);
		request.setAttribute("arrayList", arrayList);
		memberDTO.setUniqueId(uniqieid);
		memberDTO.setUserid(userid);
		memberDTO.setPassword(password);
		memberDTO.setNickname(nickname);
		memberDTO.setPhonenum(phonenum);
		memberDTO.setEmail(email);
		memberDTO.setBirthday(birthday);
		log.info(memberDTO);
		memberDTO = memberDAO.memberInsert(memberDTO);
		request.setAttribute("memberDTO", memberDTO);
		log.info("회원가입 성공-");
		request.setAttribute("success", true);
		MemberHandlerAdapter memberHandlerAdapter = new MemberHandlerAdapter();
		memberHandlerAdapter.setPath("WEB-INF/view/sign-upView.jsp");
		
		return memberHandlerAdapter;
	}

}
