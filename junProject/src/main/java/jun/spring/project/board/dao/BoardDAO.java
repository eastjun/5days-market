<<<<<<< HEAD
package jun.spring.project.board.dao;

import java.util.List;

import jun.spring.project.board.dto.BoardDTO;

public interface BoardDAO {

	public List<BoardDTO> boardSelectAll(BoardDTO boardDTO);
	public BoardDTO boardSelect(BoardDTO boardDTO);
	public void boardInsert(BoardDTO boardDTO);
	public boolean boardUpdate(BoardDTO boardDTO);
	public void boardDelete(int num);
}
=======
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
>>>>>>> 0fcca063f8cad73f99bf00026c779225a30764b5
