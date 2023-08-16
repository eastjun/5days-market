package jun.spring.project.goods.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsQnaDTO {
	private int qnaid;
	private int goodsid;
	private int uniqueid;
	String title;
	String content;
	String answer;
	String status;
	String writeday;
	String a_writeday;
	String nickname;

}
