package jun.spring.project.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jun.spring.project.board.dao.BoardDAOImp;
import jun.spring.project.board.dto.BoardDTO;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class BoardController {

	 private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	 
	 private final BoardDAOImp boardDAOImp;
	  
	 @GetMapping("/mainView")
	 public String mainView() {	 		 
		return "./index";	 
	 }
	 
	 @GetMapping("/boardSelectAll")
	 public String boardSelectAll(Model model) {
		
		 BoardDTO boardDTO = new BoardDTO();
		 List<BoardDTO> list = boardDAOImp.boardSelectAll(boardDTO);
		 model.addAttribute("boardSelectAll",list);
		 
		 return "./board/notice";		 
	 }
	 @GetMapping("mypageView")
	 public String mypageView() {
		 return"./board/mypage";
	 }
	 
	 
	 
}
