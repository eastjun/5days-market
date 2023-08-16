package jun.spring.project.board.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import jun.spring.project.board.dao.BoardDAOImp;
import jun.spring.project.board.dto.BoardDTO;
import jun.spring.project.goods.dao.GoodsDAOImp;
import jun.spring.project.goods.dto.GoodsDTO;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class BoardController {

	 private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	 
	 private final BoardDAOImp boardDAOImp;
	  private final GoodsDAOImp goodsDAOImp;
	 @GetMapping("/main")
	 public String mainView(Model model, GoodsDTO goodsDTO) {
		 List<GoodsDTO> list = goodsDAOImp.goodsList(goodsDTO);
		 model.addAttribute("goods",list);
		return "/index";	 
	 }
	 
	 @GetMapping("/boards")
	 public String boardSelectAll(Model model, BoardDTO boardDTO) {
		
		 List<BoardDTO> list = boardDAOImp.boardSelectAll(boardDTO);
		 model.addAttribute("boardSelectAll",list);
		 
		 return "/board/notice";		 
	 }
	 @GetMapping("/boards/{num}")
	 public String boardSelect(Model model, BoardDTO boardDTO, @PathVariable int num) {
		 boardDTO.setNum(num);
		 boardDTO = boardDAOImp.boardSelect(boardDTO);
		 model.addAttribute("boardSelect",boardDTO);
		 
		 return"/board/noticeSelect";
	 } 
	 @GetMapping("/boards/write")
	 public String boardInsertView() {
		 return"/board/noticeInsert";
	 }
	 @PostMapping("/boards/write")
	 public String boardInsert(BoardDTO boardDTO, Model model, HttpServletRequest request) {
		 String userid = (String) request.getSession().getAttribute("userid");
		 boardDTO.setUserid(userid);
		 try {
			 boardDAOImp.boardInsert(boardDTO);
			 model.addAttribute("result", true);
			
		} catch (Exception e) {
			logger.info("글 작성 실패 --" + e);
			model.addAttribute("result", false);
		}	 
		 return"/board/noticeInsertResult";
	 }
	 @GetMapping("/boards/{num}/edit")
	 public String boardUpdateView(@PathVariable int num,BoardDTO boardDTO, Model model) {
		 boardDTO = boardDAOImp.boardSelect(boardDTO);
		 model.addAttribute("boardDTO",boardDTO);
		 return"/board/noticeUpdate";
	 }
	 @PostMapping("/boards/{num}/edit")
	 public String boardUpdate(@PathVariable int num,BoardDTO boardDTO, Model model, HttpServletRequest request) {
		 String userid = (String) request.getSession().getAttribute("userid");
		 boardDTO.setUserid(userid);
		 try {
			 boardDAOImp.boardUpdate(boardDTO);
			 model.addAttribute("result",true);
		} catch (Exception e) {
			model.addAttribute("result",false);
		}		 
		 return"/board/noticeUpdateResult";
	 }
	 @GetMapping("/boards/{num}/delete")
	 public String boardDelete(@PathVariable int num, Model model) {
		 try {
			boardDAOImp.boardDelete(num);
			model.addAttribute("result",true);
		} catch (Exception e) {
			model.addAttribute("result",false);
		} 
		 return "/board/noticeDelete";
	 }
	 @GetMapping("/mypage")
	 public String mypageView() {
		 return"/member/mypage";
	 }
	 @GetMapping("/cart")
	 public String cartView() {
		 return "/board/cart";
	 }
	 @GetMapping("/orderCheck")
	 public String orderCheck() {
		 return"/board/orderCheck";
	 }
	 
}
