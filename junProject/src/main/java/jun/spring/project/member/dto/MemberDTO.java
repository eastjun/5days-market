package jun.spring.project.member.dto;

import java.util.Date;

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
	String newPassword;
	String passwordResetToken;
	private Date passwordResetTokenExpiry;
	
}
