package jun.spring.project.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jun.spring.project.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService{

	@Autowired
	private final SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public MemberDTO checkID(String id) {
		
		return sqlSessionTemplate.selectOne("checkID",id);
	}


	
	
}
