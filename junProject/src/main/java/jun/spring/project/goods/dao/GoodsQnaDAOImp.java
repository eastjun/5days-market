package jun.spring.project.goods.dao;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jun.spring.project.goods.dto.GoodsQnaDTO;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GoodsQnaDAOImp implements GoodsQnaDAO {

	@Autowired
	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<GoodsQnaDTO> qnaSelect(int goodsid) {
		
		return sqlSessionTemplate.selectList("qnaList",goodsid);
	
	}
	@Override
	public void qnaInsert(GoodsQnaDTO goodsQnaDTO) {
		
		sqlSessionTemplate.insert("qnaInsert", goodsQnaDTO);
	}
	@Override
	public void qnaAnswer(GoodsQnaDTO goodsQnaDTO) {
		sqlSessionTemplate.update("qnaAnswer", goodsQnaDTO);
		
	}

	
}
	
	

