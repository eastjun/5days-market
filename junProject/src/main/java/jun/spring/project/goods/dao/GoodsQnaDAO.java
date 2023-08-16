package jun.spring.project.goods.dao;


import java.util.List;

import jun.spring.project.goods.dto.GoodsQnaDTO;

public interface GoodsQnaDAO {

	List <GoodsQnaDTO> qnaSelect(int goodsid);
	void qnaInsert(GoodsQnaDTO goodsQnaDTO);
	void qnaAnswer(GoodsQnaDTO goodsQnaDTO);
}
