package jun.spring.project.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardDTO {

	int num;
	String title;
	String content;
	String userid;
	String writeday;
}
