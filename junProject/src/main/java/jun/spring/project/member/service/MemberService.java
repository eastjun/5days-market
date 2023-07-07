package jun.spring.project.member.service;

import javax.servlet.http.HttpServletRequest;



import jun.spring.project.member.dto.MemberDTO;

public interface MemberService {

	public MemberDTO login(MemberDTO memberDTO);
	public String checkID(String id);
	public String checkNickname(String nickname);
	public String checkEmail(String email);
	public String findUserID(MemberDTO memberDTO);
	public MemberDTO memberInsert(MemberDTO memberDTO);
	public MemberDTO memberUpdateView(MemberDTO memberDTO, HttpServletRequest request);
	public boolean memberUpdate(MemberDTO memberDTO, HttpServletRequest request);
	public boolean memberDelete(MemberDTO memberDTO);
}
