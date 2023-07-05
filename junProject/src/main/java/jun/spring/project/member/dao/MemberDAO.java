<<<<<<< HEAD
package jun.spring.project.member.dao;

import java.util.List;

import jun.spring.project.member.dto.MemberDTO;

public interface MemberDAO {

	public List<MemberDTO> memberSelectAll();
	public MemberDTO memberSelect(String userid);
	public MemberDTO memberInsert(MemberDTO memberDTO);
	public void memberUpdate(MemberDTO memberDTO);
	public void memberDelete(String id);
}
=======
package jun.spring.project.member.dao;

import java.util.List;

import jun.spring.project.member.dto.MemberDTO;

public interface MemberDAO {

	public List<MemberDTO> memberSelectAll();
	public MemberDTO memberSelect(MemberDTO memberDTO);
	public MemberDTO memberInsert(MemberDTO memberDTO);
	public void memberUpdate(MemberDTO memberDTO);
	public void memberDelete(String id);
}
>>>>>>> 0fcca063f8cad73f99bf00026c779225a30764b5
