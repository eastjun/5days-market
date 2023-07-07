package jun.spring.project.member.service;

import java.security.SecureRandom;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jun.spring.project.member.dao.MemberDAOImp;
import jun.spring.project.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService{

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImp.class);
	
	@Autowired
	private final MemberDAOImp memberDAOImp;
	
	@Override
	public MemberDTO login(MemberDTO memberDTO) {
		List <MemberDTO> list = memberDAOImp.memberSelectAll();
		 for (MemberDTO member : list) {
	            if (memberDTO.getUserid().equals(member.getUserid())) {
	                if (memberDTO.getPassword().equals(member.getPassword())) {
	                    return member;  // 로그인 성공
	                }
	                return null;  // 비밀번호 불일치
	            }
	          }
		return new MemberDTO(); //아이디 미존재
	}
	@Override
	public String checkID(String id) {
		
		MemberDTO memberDTO=memberDAOImp.checkID(id);
		
		  if (memberDTO != null && memberDTO.getUserid() != null) {
	            return "duplicate";
	        } else {
	            return "available";
	        }
	}
	@Override
	public String checkNickname(String nickname) {
		MemberDTO memberDTO = memberDAOImp.checkNickname(nickname);
			if (memberDTO !=null && memberDTO.getNickname() !=null) {
				return "duplicate";
			}
			else {
				return "available";
			}
	}
	@Override
	public String checkEmail(String email) {
		MemberDTO memberDTO = memberDAOImp.checkEmail(email);
			if (memberDTO !=null && memberDTO.getEmail() !=null) {
			return "duplicate";
		}
		else {
			return "available";
		}
	}
	@Override
	public String findUserID(MemberDTO memberDTO) {
		memberDTO = memberDAOImp.findUserid(memberDTO);
		if (memberDTO==null) {
			return null;			
		}
		return memberDTO.getUserid();
	}

	@Override
	public MemberDTO memberInsert(MemberDTO memberDTO) {
		
		SecureRandom secureRandom = new SecureRandom();
		int uniqueid=secureRandom.nextInt(100000);
		memberDTO.setUniqueid(uniqueid);
		memberDTO=memberDAOImp.memberInsert(memberDTO);
		
		return memberDTO;
	}

	@Override
	public MemberDTO memberUpdateView(MemberDTO memberDTO, HttpServletRequest request) {
		 String userid = (String) request.getSession().getAttribute("userid");
		 memberDTO= memberDAOImp.memberSelect(userid);
		return memberDTO;
	}
	@Override
	public boolean memberUpdate(MemberDTO memberDTO, HttpServletRequest request) {
		 String userid = (String) request.getSession().getAttribute("userid");
		 String oldPassword=memberDTO.getPassword();
		 String realPassword=memberDAOImp.memberSelect(userid).getPassword();
		 logger.info("업데이트 확인--"+realPassword);
		 if (!oldPassword.equals(realPassword)) {
			return false;
		}
		 String newPassword=memberDTO.getNewPassword();
		 if (!newPassword.isEmpty()) {
			memberDTO.setPassword(newPassword);
		}
		 memberDAOImp.memberUpdate(memberDTO);
		return true;
	}

	@Override
	public boolean memberDelete(MemberDTO memberDTO) {
		 logger.info("삭제 확인 --"+memberDTO);
		 String userid= memberDTO.getUserid();
		 String password=memberDTO.getPassword();
		 String realpassword=memberDAOImp.memberSelect(userid).getPassword();
		 
		 if (password.equals(realpassword)) {
			 memberDAOImp.memberDelete(userid);
			 return true;
		}
		 return false;
	}
	
	
	

	

	


	
	
}
