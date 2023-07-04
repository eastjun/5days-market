package jun.spring.project.board.dao;

import java.util.List;

import jun.spring.project.board.dto.BoardDTO;

public interface BoardDAO {

	public List<BoardDTO> boardSelectAll(BoardDTO boardDTO);
	public BoardDTO boardSelect(BoardDTO boardDTO);
	public void boardInsert(BoardDTO boardDTO);
	public void boardUpdate(BoardDTO boardDTO);
	public void boardDelete(int num);
}
