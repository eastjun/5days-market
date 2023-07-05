package jun.spring.project.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jun.spring.project.board.dto.BoardDTO;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardDAOImp implements BoardDAO{

	@Autowired
	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<BoardDTO> boardSelectAll(BoardDTO boardDTO) {
		
		return sqlSessionTemplate.selectList("boardSelectAll",boardDTO);
	}

	@Override
	public BoardDTO boardSelect(BoardDTO boardDTO) {
		
		return sqlSessionTemplate.selectOne("boardSelect",boardDTO);
	}

	@Override
	public void boardInsert(BoardDTO boardDTO) {
			
		sqlSessionTemplate.insert("boardInsert",boardDTO);
		
	}

	@Override
	public boolean boardUpdate(BoardDTO boardDTO) {
		
		sqlSessionTemplate.update("boardUpdate", boardDTO);
		
		return true;
		
	}

	@Override
	public void boardDelete(int num) {
		sqlSessionTemplate.delete("boardDelete",num);
		
	}
	
	
}
