package jun.spring.project.member.service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import jun.spring.project.member.dao.MemberDAOImp;
import jun.spring.project.member.dto.MemberDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService{

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImp.class);
	
	@Autowired
	private final MemberDAOImp memberDAOImp;
	@Autowired
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private final JavaMailSender mailSender;
	
	@Override
	public MemberDTO login(MemberDTO memberDTO) {
		
		List <MemberDTO> list = memberDAOImp.memberSelectAll();
		
		 for (MemberDTO member : list) {
	            if (memberDTO.getUserid().equals(member.getUserid())) {
	                if (bCryptPasswordEncoder.matches(memberDTO.getPassword(), member.getPassword())) {
	                    return member;  // 로그인 성공
	                }
	                return null;  // 비밀번호 불일치
	            }
	          }
		return new MemberDTO(); //아이디 미존재
	}
	@Override
	public String checkID(String userid) {
		
		MemberDTO memberDTO=memberDAOImp.checkID(userid);
		
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
		
		String rawPassword=memberDTO.getPassword();
		String encodingPassword=bCryptPasswordEncoder.encode(rawPassword);
		memberDTO.setPassword(encodingPassword);
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
		 if (!bCryptPasswordEncoder.matches(oldPassword, realPassword)) {
			return false;
		}
		 String newPassword=memberDTO.getNewPassword();
		 if (!newPassword.isEmpty()) {
			 String encodedNewPassword = bCryptPasswordEncoder.encode(newPassword);
		        memberDTO.setPassword(encodedNewPassword);
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
		 
		 if (bCryptPasswordEncoder.matches(password, realpassword)) {
			 memberDAOImp.memberDelete(userid);
			 return true;
		}
		 return false;
	}
	
	@Override
	public String forgotPassword(MemberDTO memberDTO) {
		memberDTO=memberDAOImp.findPassword(memberDTO);
		String email = memberDTO.getEmail();
		 if (email == null || email.trim().length() == 0) {
		        throw new IllegalArgumentException("이메일이 없습니다.");
		    }
		if (memberDTO != null) {
			String token = UUID.randomUUID().toString();
			memberDTO.setPasswordResetToken(token);
			logger.info("여기까지 오는지 확인-----"+memberDTO);
			memberDAOImp.savePasswordToken(memberDTO);
			memberDTO.setPasswordResetTokenExpiry(new Date(System.currentTimeMillis() + 5*60*1000)); 
			logger.info("토큰 저장 확인 ---" + memberDTO);
			String resetUrl = "http://localhost:8090/resetpassword?token=" + token;
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email);
			message.setSubject("비밀번호 변경요청");
			message.setText("링크를 눌러 비밀번호를 변경하세요.\n"
			+resetUrl);
			mailSender.send(message);
		}
		return null;
	}
	@Override
	public MemberDTO resetPassword(@RequestParam String token,@RequestParam String password) {
		logger.info("토큰 값 확인" + token +"비밀번호"+ password);
		if (token == null || token.trim().length() == 0 || password == null || password.trim().length() == 0) {
	        throw new IllegalArgumentException("토큰이나 비밀번호가 없습니다");
	    }
		MemberDTO memberDTO= memberDAOImp.findByToken(token);
		logger.info("DTO 값 확인 ------ " + memberDTO);
		
	    String encodedPassword = bCryptPasswordEncoder.encode(password);
	    memberDTO.setPassword(encodedPassword);
	   
		MemberDTO result = memberDAOImp.resetPassword(memberDTO);
		 memberDTO.setPasswordResetToken(null);
		 memberDTO.setPasswordResetTokenExpiry(null);
		logger.info("결과값 확인" +password+ "결과 확인" + result);
		return result;
		
	}
	
	
	
	
	
	
	
}
	
	
