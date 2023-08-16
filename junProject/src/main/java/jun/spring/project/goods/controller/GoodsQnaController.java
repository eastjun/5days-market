package jun.spring.project.goods.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jun.spring.project.goods.dao.GoodsQnaDAOImp;
import jun.spring.project.goods.dto.GoodsQnaDTO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GoodsQnaController {
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsQnaController.class);
	private final GoodsQnaDAOImp goodsQnaDAOImp;
	
	@PostMapping("/qna/new")
	public String qnaInsert(GoodsQnaDTO goodsQnaDTO, Model model) {
		logger.info("넘어오는 값 확인하기 ----"+ model);
		goodsQnaDAOImp.qnaInsert(goodsQnaDTO);
		model.addAttribute("goodsid",goodsQnaDTO.getGoodsid());
		model.addAttribute("result",true);
		return "goods/goodsQnaResult";
	}

	@PostMapping("/qnaAnswer")
	public ResponseEntity<Object> qnaAnswer(@RequestBody GoodsQnaDTO goodsQnaDTO) {
		logger.info("값 확인하기--"+goodsQnaDTO);
		goodsQnaDAOImp.qnaAnswer(goodsQnaDTO);
		return ResponseEntity.ok().build();
	}
	
	
}
