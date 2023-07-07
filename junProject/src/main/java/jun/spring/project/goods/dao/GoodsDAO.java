package jun.spring.project.goods.dao;

import java.util.List;

import jun.spring.project.goods.dto.GoodsDTO;

public interface GoodsDAO {

	List<GoodsDTO>goodsList(GoodsDTO goodsDTO);
	GoodsDTO goodsSelect(GoodsDTO goodsDTO);
	void goodsInsert(GoodsDTO goodsDTO);
	void goodsUpdate(GoodsDTO goodsDTO);
	void goodsDelete(int num);
}
