package jun.spring.project.member.dao;

import java.util.List;

import jun.spring.project.member.dto.MemberDTO;

public interface MemberDAO {

	public List<MemberDTO> memberSelectAll();
	public MemberDTO checkID(String userid);
	public MemberDTO checkNickname(String nickname);
	public MemberDTO checkEmail(String email);
	public MemberDTO memberSelect(String userid);
	public MemberDTO memberInsert(MemberDTO memberDTO);
	public void memberUpdate(MemberDTO memberDTO);
	public void memberDelete(String id);
	public MemberDTO findUserid(MemberDTO memberDTO);
}
