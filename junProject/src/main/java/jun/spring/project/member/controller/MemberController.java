package jun.spring.project.member.controller;




import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	 
	 //로그인 
	 @GetMapping("/login")
	 public String loginView() {
		 
		 return"/member/login";
	 }
	 @PostMapping("/login")
	 public String login(HttpServletRequest request, HttpServletResponse response, Model model ,MemberDTO memberDTO) {
		 
		 MemberDTO loginResult = memberServiceImp.login(memberDTO);

	        if (loginResult != null) {
	            if (loginResult.getUserid() != null) {  // 로그인 성공
	                request.getSession().setAttribute("userid", loginResult.getUserid());
	                request.getSession().setAttribute("uniqueid", loginResult.getUniqueid());
	                Cookie cookie = new Cookie("userid", loginResult.getUserid());
	                cookie.setMaxAge(1800);  // 30 minutes
	                response.addCookie(cookie);
	                model.addAttribute("message", "환영합니다 " + loginResult.getUserid() + " 님");
	                model.addAttribute("loginSuccess", true);
	               // model.addAttribute("userid", loginResult.getUserid());
	                return "./member/loginSucceed";
	            } else {  // 아이디 미존재
	                model.addAttribute("message", "아이디가 존재하지 않습니다.");
	                //model.addAttribute("loginSuccess", false);
	                return "./member/loginSucceed";
	            }
	        } else {  // 비밀번호 불일치
	            model.addAttribute("message", "비밀번호가 틀립니다.");
	           // model.addAttribute("loginSuccess", false);
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
	 //회원가입
	 @GetMapping("/signup")
	 public String signup() {	 
		 return"/member/signup";
	 }
	 
	 @PostMapping("/memberInsert")
	 public String memberInsert(Model model, MemberDTO memberDTO) {
		 
		 	memberDTO = memberServiceImp.memberInsert(memberDTO);
			model.addAttribute("success",true);
			
		 return "/member/signupView";
	 }
	 // 회원가입 시 아이디 중복체크
	 @PostMapping("/checkID")
	 public ResponseEntity<String>checkID(@RequestParam String userid) {
		 
		 	String result=memberServiceImp.checkID(userid);
		 
	        return new ResponseEntity<String>(result, HttpStatus.OK);
	 }
	 @PostMapping("/checkNickname")
	 public ResponseEntity<String>checkNickname(@RequestParam String nickname) {
		 
		 	String result=memberServiceImp.checkNickname(nickname);
		 
	        return new ResponseEntity<String>(result, HttpStatus.OK);
	 }
	 @PostMapping("/checkEmail")
	 public ResponseEntity<String>checkEmail(@RequestParam String email) {
		 
		 	String result=memberServiceImp.checkEmail(email);
		 
	        return new ResponseEntity<String>(result, HttpStatus.OK);
	 }
	 //회원정보 수정
	 @GetMapping("/memberUpdate")
	 public String memberUpdateView(Model model, MemberDTO memberDTO, HttpServletRequest request) {
		
		 memberDTO=memberServiceImp.memberUpdateView(memberDTO, request);
		 model.addAttribute("memberDTO",memberDTO);
		 return"/member/memberupdate";
	 }
	 
	 @PostMapping("/memberUpdate")
	 public String memberUpdate(MemberDTO memberDTO, HttpServletRequest request, Model model) {
		
		 boolean success= memberServiceImp.memberUpdate(memberDTO, request);
		 model.addAttribute("success",success);
		 
		return "member/memberupdateSucceed";
	 } 
	 //회원 탈퇴
	 @GetMapping("/memberDelete")
	 public String memberDeleteView() {
		 return"member/memberDelete";
	 }
	 @PostMapping("/memberDelete")
	 public String memberDelete(MemberDTO memberDTO, Model model, HttpServletRequest request) {	
		 boolean success =memberServiceImp.memberDelete(memberDTO);
		 String message;
		 if (success) {
			 message = "회원 탈퇴가 완료되었습니다.";	
			 HttpSession session = request.getSession();
			 session.invalidate(); //로그아웃
		}
		 else {
			 message="비밀번호가 틀립니다.";
		}
		model.addAttribute("message",message);
		
		return "member/memberDeleteSucceed";
		 
	 }
	 //아이디 찾기
	 @GetMapping("/findUserid")
	 public String findUseridView() {
		 return "member/findUserid";
	 }
	 @PostMapping("/findUserid")
	 public String findUserid(MemberDTO memberDTO , Model model) {
		 
		 String userid = memberServiceImp.findUserID(memberDTO);
		 if (userid==null) {
			 model.addAttribute("message","회원정보가 존재하지 않습니다.");
			 return "member/findUserid";
		}
		 model.addAttribute("message","당신의 아이디는 " + userid + " 입니다.");
		 
		 return "member/findUseridSucceed";
		 
		 
	 }	 
}

