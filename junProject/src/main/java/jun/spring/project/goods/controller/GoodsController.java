package jun.spring.project.goods.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jun.spring.project.goods.dao.GoodsDAOImp;
import jun.spring.project.goods.dto.GoodsDTO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GoodsController {
	
	 private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
	@Autowired
	private final GoodsDAOImp goodsDAOImp;
	
	//상품 조회
	@GetMapping("goodsList")
	 public String goodsList(Model model, GoodsDTO goodsDTO) {
		List<GoodsDTO> list = goodsDAOImp.goodsList(goodsDTO);
		model.addAttribute("goodsList",list);
		 return"/board/goodsList";
	 }
	 @GetMapping("goodsSelect")
	 public String goodsDetail(GoodsDTO goodsDTO, Model model) { 
		 goodsDTO=goodsDAOImp.goodsSelect(goodsDTO);
		 model.addAttribute("goodsDTO",goodsDTO);
		 return"/board/goodsSelect";
	 }
	 //상품 등록
	@GetMapping("goodsInsert")
	public String goodsInsertView() {
		
		return "/board/goodsInsert";
	}
	@PostMapping("goodsInsert")
	public String goodsInsert(GoodsDTO goodsDTO, Model model, HttpServletRequest request) {
		logger.info("넘어오는 값 확인----------------------------" + goodsDTO.getParcel());
		String userid = (String) request.getSession().getAttribute("userid");
		goodsDTO.setUserid(userid);
		
	      try {
			 goodsDAOImp.goodsInsert(goodsDTO);
			 model.addAttribute("success", true);
			
		} catch (Exception e) {
			 logger.info("글 작성 실패 --" + e);
			 model.addAttribute("success", false);
		}	 
		 return"/board/goodsInsertSucceed";
	}
	//상품 수정
	@GetMapping("goodsUpdate")
	public String goodsUpdateView(GoodsDTO goodsDTO, Model model) {
		logger.info("배송비 확인-----------------"+ goodsDTO);
		goodsDTO = goodsDAOImp.goodsSelect(goodsDTO);
		logger.info("배송비 확인2222-----------------"+ goodsDTO);
		model.addAttribute("goodsDTO",goodsDTO);
		return"board/goodsUpdate";
	}
	@PostMapping("goodsUpdate")
	public String goodsUpdate(GoodsDTO goodsDTO, Model model,HttpServletRequest request) {
		logger.info("배송비 확인3333333-----------------"+ goodsDTO);
		String userid = (String) request.getSession().getAttribute("userid");
		goodsDTO.setUserid(userid);
		try {
			goodsDAOImp.goodsUpdate(goodsDTO);
			model.addAttribute("success",true);
		} catch (Exception e) {
			model.addAttribute("success",false);
		}		
		return"board/goodsUpdateSucceed";
	}
	@GetMapping("goodsDelete")
	public String goodsDelete(@RequestParam ("goodsid") int num, Model model) {
		
		try {
			goodsDAOImp.goodsDelete(num);
			model.addAttribute("success",true);
			
		} catch (Exception e) {
			model.addAttribute("success",false);
		}
		return"board/goodsDelete";
	}
}
