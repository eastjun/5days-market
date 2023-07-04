package jun.member.service;

import java.util.ArrayList;

import jun.member.dto.MemberDTO;

public interface MemberService {

	public ArrayList<MemberDTO> memberSelectAll();
	public MemberDTO memberSelect(MemberDTO memberDTO);
	public MemberDTO memberInsert(MemberDTO memberDTO);
	public MemberDTO memberUpdate(MemberDTO memberDTO);
	public MemberDTO memberDelete(String id);
	public MemberDTO findById(String id);
}
