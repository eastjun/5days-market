package jun.member.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jun.member.control.Controller;
import jun.member.dao.MemberDAO;
import jun.member.dto.MemberDTO;
import jun.member.hander.MemberHandlerAdapter;

public class IdCheckController implements Controller{
	private static Log log = LogFactory.getLog(IdCheckController.class);
	
	@Override
	public MemberHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getParameter("userid");
		log.info(userid);
	
		MemberDTO memberDTO = new MemberDTO();
		
		MemberDAO memberDAO = new MemberDAO();
		
		memberDTO = memberDAO.findById(userid);
		
		   String result = "";
	        if (memberDTO != null && memberDTO.getUserid() != null) {
	            log.info("User ID: " + memberDTO.getUserid() + " is found.");
	            result = "duplicate";
	        } else {
	            log.info("User ID: " + userid + " is not found.");
	            result = "available";
	        }
	        
	        
	        try {
				response.getWriter().write(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        return null;
		
		
		
	}

}
