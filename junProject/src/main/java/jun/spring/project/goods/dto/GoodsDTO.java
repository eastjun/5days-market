package jun.spring.project.goods.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsDTO {
	private int goodsid;
	private String userid;
	private String title;
	private int price;
	private String origin;
	private String parcel;
	private String content;
	private int shipprice;

}
