package jun.spring.project.member.controller;


import java.security.SecureRandom;
import java.util.List;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jun.spring.project.member.dao.MemberDAOImp;
import jun.spring.project.member.dto.MemberDTO;
import jun.spring.project.member.service.MemberServiceImp;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MemberController {

	 private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	 
	 @Autowired
	 private final MemberDAOImp memberDAOImp; 
	 @Autowired
	 private final MemberServiceImp memberServiceImp;
	 
	 //로그인 관련
	 @GetMapping("/login")
	 public String loginView() {
		 
		 return"./member/login";
	 }
	 @PostMapping("/login")
	 public String login(HttpServletRequest request, HttpServletResponse response, Model model ,MemberDTO memberDTO) {
		 
		 List <MemberDTO> list = memberDAOImp.memberSelectAll();
		 
	     boolean loginSuccess = false;
	     boolean useridExists = false;
	     
	     for (MemberDTO member :list) {
	    	 	if (memberDTO.getUserid().equals(member.getUserid())) {
	                useridExists = true;
	                if (memberDTO.getPassword().equals(member.getPassword())) {
	                    loginSuccess = true;
	                    memberDTO =member;
	                }
	                break;
	            }
	        }     
	        if (loginSuccess) {
	            request.getSession().setAttribute("userid", memberDTO.getUserid());
	            request.getSession().setAttribute("uniqueid", memberDTO.getUniqueid());
	            logger.info("확인용-------------------------------"+memberDTO.getUniqueid());
	            Cookie cookie = new Cookie("userid", memberDTO.getUserid());
	            cookie.setMaxAge(1800);  // 30 minutes
	            response.addCookie(cookie);

	            model.addAttribute("message", "환영합니다 " + memberDTO.getUserid() + " 님");
	            model.addAttribute("loginSuccess", true);
	            model.addAttribute("userid", memberDTO.getUserid());
	            return "./member/loginSucceed";
	        } else if (useridExists) {
	            model.addAttribute("message", "비밀번호가 틀립니다.");
	            model.addAttribute("loginSuccess", false);
	            return "./member/loginSucceed";
	        } else {
	            model.addAttribute("message", "아이디가 존재하지 않습니다.");
	            model.addAttribute("loginSuccess", false);
	            return "./member/loginSucceed";
	        }
	    }
	 //로그아웃 관리
	 @GetMapping("/logout")
	 public String logout(HttpServletRequest request, HttpServletResponse response) {
	
		 	HttpSession session = request.getSession();
		    session.invalidate();

		    // 쿠키 삭제
		    Cookie cookie = new Cookie("userid", null);
		    cookie.setMaxAge(0);
		    cookie.setPath("/");
		    response.addCookie(cookie);
		 return"/member/logout";
	 }
	 //회원가입 관련
	 @GetMapping("/signup")
	 public String signup() {	 
		 return"/member/signup";
	 }
	 
	 @PostMapping("/memberInsert")
	 public String memberInsert(Model model, MemberDTO memberDTO) {
		 
		 	SecureRandom secureRandom = new SecureRandom();
			int uniqueid=secureRandom.nextInt(100000);
			memberDTO.setUniqueid(uniqueid);
		    
//			String encryptedPassword = bCryptPasswordEncoder.encode(memberDTO.getPassword());
//		    memberDTO.setPassword(encryptedPassword);
		    
			memberDTO = memberDAOImp.memberInsert(memberDTO);
			model.addAttribute("memberDTO",memberDTO);
			
			List<MemberDTO> list = memberDAOImp.memberSelectAll();
			logger.info("회원가입 확인---"+list);
			model.addAttribute("list",list);
		
			logger.info("회원가입 성공-");
			model.addAttribute("success",true);
			
		 return "/member/signupView";
	 }
	 @PostMapping("/checkID")
	 public ResponseEntity<String>checkID(@RequestParam String userid) {
		 
		 MemberDTO memberDTO = memberServiceImp.checkID(userid); 		 
		 String result = "";
	        if (memberDTO != null && memberDTO.getUserid() != null) {
	            logger.info("User ID: " + memberDTO.getUserid() + " is found.");
	            result = "duplicate";
	        } else {
	            logger.info("User ID: " + userid + " is not found.");
	            result = "available";
	        }                
	        return new ResponseEntity<String>(result, HttpStatus.OK);
	 }
	 //회원 수정
	 @GetMapping("/memberUpdate")
	 public String memberUpdateView(Model model, HttpServletRequest request) {
		 String userid = (String) request.getSession().getAttribute("userid");
		 MemberDTO memberDTO= memberDAOImp.memberSelect(userid);
		 model.addAttribute("memberDTO",memberDTO);
		 return"/member/memberupdate";
	 }
	 @PostMapping("/memberUpdate")
	 public String memberUpdate(MemberDTO memberDTO, HttpServletRequest request, Model model) {
		
		 String userid = (String) request.getSession().getAttribute("userid");
		 String oldPassword=memberDTO.getPassword();
		 String realPassword=memberDAOImp.memberSelect(userid).getPassword();
		 String newPassword=memberDTO.getNewPassword();
		 logger.info("비밀번호 확인---------"+newPassword);
		 if (oldPassword.equals(realPassword)) {
			 if (newPassword.isEmpty()) {
				 memberDAOImp.memberUpdate(memberDTO);
				 model.addAttribute("success",true);
			}
			 else {
				memberDTO.setPassword(newPassword);
				memberDAOImp.memberUpdate(memberDTO);
				model.addAttribute("success",true);
			}	 
		}
		 else {
			 model.addAttribute("success",false);		
		} 
		return "member/memberupdateSucceed";
	 }
	 @GetMapping("/memberDelete")
	 public String memberDeleteView() {
		 return"member/memberDelete";
	 }
	 @PostMapping("/memberDelete")
	 public String memberDelete(MemberDTO memberDTO, Model model) {
		 logger.info("확인-----------------------" + memberDTO);
		 String id= memberDTO.getUserid();
		 String password=memberDTO.getPassword();
		 String realpassword=memberDAOImp.memberSelect(id).getPassword();
		 
		 if (realpassword.equals(password)) {
			 memberDAOImp.memberDelete(id);
		}
		 else {
			model.addAttribute("errorMessage","비밀번호가 틀립니다.");
		}
		return "member/memberDeleteSucceed";
		 
	 }
	 
	 }

