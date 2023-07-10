package jun.spring.project.board.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	 @GetMapping("/mainView")
	 public String mainView(Model model, GoodsDTO goodsDTO) {
		 List<GoodsDTO> list = goodsDAOImp.goodsList(goodsDTO);
		 model.addAttribute("goodsList",list);
		return "/index";	 
	 }
	 
	 @GetMapping("/boardSelectAll")
	 public String boardSelectAll(Model model, BoardDTO boardDTO) {
		
		 List<BoardDTO> list = boardDAOImp.boardSelectAll(boardDTO);
		 model.addAttribute("boardSelectAll",list);
		 
		 return "/board/notice";		 
	 }
	 @GetMapping("/boardSelect")
	 public String boardSelect(Model model, BoardDTO boardDTO, @RequestParam("num") int num) {
		 boardDTO.setNum(num);
		 boardDTO = boardDAOImp.boardSelect(boardDTO);
		 model.addAttribute("boardSelect",boardDTO);
		 
		 return"/board/noticeSelect";
	 } 
	 @GetMapping("/boardInsert")
	 public String boardInsertView() {
		 return"/board/noticeInsert";
	 }
	 @PostMapping("/boardInsert")
	 public String boardInsert(BoardDTO boardDTO, Model model, HttpServletRequest request) {
		 String userid = (String) request.getSession().getAttribute("userid");
		 boardDTO.setUserid(userid);
		 try {
			 boardDAOImp.boardInsert(boardDTO);
			 model.addAttribute("success", true);
			
		} catch (Exception e) {
			logger.info("글 작성 실패 --" + e);
			model.addAttribute("success", false);
		}	 
		 return"/board/noticeInsertSucceed";
	 }
	 @GetMapping("/boardUpdate")
	 public String boardUpdateView(BoardDTO boardDTO, Model model) {
		 boardDTO = boardDAOImp.boardSelect(boardDTO);
		 model.addAttribute("boardDTO",boardDTO);
		 return"/board/noticeUpdate";
	 }
	 @PostMapping("/boardUpdate")
	 public String boardUpdate(BoardDTO boardDTO, Model model, HttpServletRequest request) {
		 String userid = (String) request.getSession().getAttribute("userid");
		 boardDTO.setUserid(userid);
		 try {
			 boardDAOImp.boardUpdate(boardDTO);
			 model.addAttribute("success",true);
		} catch (Exception e) {
			model.addAttribute("suceess",false);
		}		 
		 return"/board/noticeUpdateSucceed";
	 }
	 @GetMapping("/boardDelete")
	 public String boardDelete(@RequestParam ("num") int num, Model model) {
		 try {
			boardDAOImp.boardDelete(num);
			model.addAttribute("success",true);
		} catch (Exception e) {
			model.addAttribute("success",false);
		} 
		 return "/board/noticeDelete";
	 }
	 @GetMapping("mypage")
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
