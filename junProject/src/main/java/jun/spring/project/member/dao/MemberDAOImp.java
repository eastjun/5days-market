package jun.spring.project.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jun.spring.project.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberDAOImp implements MemberDAO{

	@Autowired
	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<MemberDTO> memberSelectAll() {
		
		return sqlSessionTemplate.selectList("memberSelectAll");
	}

	@Override
	public MemberDTO memberSelect(MemberDTO memberDTO) {
		
		return sqlSessionTemplate.selectOne("memberSelect",memberDTO);
	}

	@Override
	public MemberDTO memberInsert(MemberDTO memberDTO) {
		
		sqlSessionTemplate.insert("memberInsert",memberDTO);
		return memberDTO;
	}

	@Override
	public void memberUpdate(MemberDTO memberDTO) {
		sqlSessionTemplate.update("memberUpdate", memberDTO);
		
	}

	@Override
	public void memberDelete(String id) {
		
		sqlSessionTemplate.delete("memberDelete",id);
	}
	
	
}
