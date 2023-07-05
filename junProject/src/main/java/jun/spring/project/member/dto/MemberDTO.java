package jun.spring.project.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberDTO {

	int uniqueid;
	String userid;
	String password;
	String nickname;
	String phonenum;
	String email;
	String birthday;
}
