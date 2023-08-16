package jun.spring.project.goods.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import jun.spring.project.goods.dao.GoodsDAOImp;
import jun.spring.project.goods.dao.GoodsQnaDAOImp;
import jun.spring.project.goods.dto.GoodsDTO;
import jun.spring.project.goods.dto.GoodsQnaDTO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GoodsController {
	
	 private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
	 
	private final GoodsDAOImp goodsDAOImp;
	private final GoodsQnaDAOImp goodsQnaDAOImp;
	
	//상품 조회
	@GetMapping("/goods")
	 public String goodsList(Model model, GoodsDTO goodsDTO) {
		List<GoodsDTO> list = goodsDAOImp.goodsList(goodsDTO);
		model.addAttribute("goods",list);
		 return"goods/goodsList";
	 }
	//상품 상세보기
	 @GetMapping("/goods/{goodsid}")
	 public String goodsDetail(@PathVariable int goodsid, GoodsDTO goodsDTO, Model model) { 
		 goodsDTO=goodsDAOImp.goodsSelect(goodsDTO);
		 List <GoodsQnaDTO> qna= goodsQnaDAOImp.qnaSelect(goodsid);
		 model.addAttribute("goodsDTO",goodsDTO);
		 model.addAttribute("qnaList",qna);
		 return"goods/goodsSelect";
	 }
	 //상품 등록
	@GetMapping("/goods/new")
	public String goodsInsertView() {
		return "goods/goodsInsert";
	}
	@PostMapping("/goods/new")
	public String goodsInsert(GoodsDTO goodsDTO, Model model, HttpServletRequest request, MultipartFile imageFile)  {
		logger.info("넘어오는 값 확인----" + goodsDTO.getParcel());
		String userid = (String) request.getSession().getAttribute("userid");
		goodsDTO.setUserid(userid);
		
		 if (imageFile.isEmpty()) {
		        model.addAttribute("result", null);
		        return "goods/goodsInsertResult";
		    }
		 
		String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
	    goodsDTO.setImage(fileName);
	      try {
			 goodsDAOImp.goodsInsert(goodsDTO);
			 model.addAttribute("result", true);
			 String uploadDir = request.getServletContext().getRealPath("/") + "resources/upload/"; // 실제 이미지를 저장할 위치
		        FileCopyUtils.copy(imageFile.getBytes(), new File(uploadDir + fileName)); // 파일 저장
			
		} catch (Exception e) {
			 logger.info("글 작성 실패 --" + e);
			 model.addAttribute("result", false);
		}	 
		 return"goods/goodsInsertResult";
	}
	//상품 수정
	@GetMapping("/goods/{goodsid}/edit")
	public String goodsUpdateView(@PathVariable int goodsid, GoodsDTO goodsDTO, Model model) {	
		goodsDTO = goodsDAOImp.goodsSelect(goodsDTO);		
		model.addAttribute("goodsDTO",goodsDTO);
		return"goods/goodsUpdate";
	}
	@PostMapping("/goods/{goodsid}/edit")
	public String goodsUpdate(@PathVariable int goodsid, GoodsDTO goodsDTO, Model model,HttpServletRequest request,MultipartFile imageFile) {	
		String userid = (String) request.getSession().getAttribute("userid");
		goodsDTO.setUserid(userid);
		
		 if (imageFile.isEmpty()) {
		        model.addAttribute("result", null);
		        return "goods/goodsUpdateResult";
		    }
		String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
	    goodsDTO.setImage(fileName);
		try {
			goodsDAOImp.goodsUpdate(goodsDTO);
			model.addAttribute("result",true);
		    String uploadDir = request.getServletContext().getRealPath("/") + "resources/upload/"; // 실제 이미지를 저장할 위치
		    FileCopyUtils.copy(imageFile.getBytes(), new File(uploadDir + fileName)); // 파일 저장
		} catch (Exception e) {
			model.addAttribute("result",false);
		}		
		return"goods/goodsUpdateResult";
	}
	@GetMapping("/goods/{goodsid}/delete")
	public String goodsDelete(@PathVariable int goodsid,  Model model) {
		
		try {
			goodsDAOImp.goodsDelete(goodsid);
			model.addAttribute("success",true);
			
		} catch (Exception e) {
			model.addAttribute("success",false);
		}
		return"goods/goodsDelete";
	}
}
