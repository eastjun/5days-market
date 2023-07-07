package jun.spring.project.goods.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jun.spring.project.goods.dto.GoodsDTO;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GoodsDAOImp implements GoodsDAO{

	@Autowired
	private final SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<GoodsDTO> goodsList(GoodsDTO goodsDTO) {
		
		return sqlSessionTemplate.selectList("goodsList", goodsDTO);
	}

	@Override
	public void goodsInsert(GoodsDTO goodsDTO) {
		
		sqlSessionTemplate.insert("goodsInsert",goodsDTO);		
	}

	@Override
	public GoodsDTO goodsSelect(GoodsDTO goodsDTO) {
		
		return sqlSessionTemplate.selectOne("goodsSelect",goodsDTO);
	}

	@Override
	public void goodsUpdate(GoodsDTO goodsDTO) {
		sqlSessionTemplate.update("goodsUpdate",goodsDTO);
		
	}

	@Override
	public void goodsDelete(int num) {
		sqlSessionTemplate.delete("goodsDelete",num);
		
	}

}
