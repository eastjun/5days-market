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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
		 return"goods/goodsList";
	 }
	 @GetMapping("goodsSelect")
	 public String goodsDetail(GoodsDTO goodsDTO, Model model) { 
		 goodsDTO=goodsDAOImp.goodsSelect(goodsDTO);
		 model.addAttribute("goodsDTO",goodsDTO);
		 return"goods/goodsSelect";
	 }
	 //상품 등록
	@GetMapping("goodsInsert")
	public String goodsInsertView() {
		
		return "goods/goodsInsert";
	}
	@PostMapping("goodsInsert")
	public String goodsInsert(GoodsDTO goodsDTO, Model model, HttpServletRequest request, MultipartFile imageFile)  {
		logger.info("넘어오는 값 확인----" + goodsDTO.getParcel());
		String userid = (String) request.getSession().getAttribute("userid");
		goodsDTO.setUserid(userid);
		
		String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
	    goodsDTO.setImage(fileName);
	      try {
			 goodsDAOImp.goodsInsert(goodsDTO);
			 model.addAttribute("success", true);
			 String uploadDir = request.getServletContext().getRealPath("/") + "resources/upload/"; // 실제 이미지를 저장할 위치
		        FileCopyUtils.copy(imageFile.getBytes(), new File(uploadDir + fileName)); // 파일 저장
			
		} catch (Exception e) {
			 logger.info("글 작성 실패 --" + e);
			 model.addAttribute("success", false);
		}	 
		 return"goods/goodsInsertSucceed";
	}
	//상품 수정
	@GetMapping("goodsUpdate")
	public String goodsUpdateView(GoodsDTO goodsDTO, Model model) {	
		goodsDTO = goodsDAOImp.goodsSelect(goodsDTO);		
		model.addAttribute("goodsDTO",goodsDTO);
		return"goods/goodsUpdate";
	}
	@PostMapping("goodsUpdate")
	public String goodsUpdate(GoodsDTO goodsDTO, Model model,HttpServletRequest request,MultipartFile imageFile) {	
		String userid = (String) request.getSession().getAttribute("userid");
		goodsDTO.setUserid(userid);
		String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
	    goodsDTO.setImage(fileName);
		try {
			goodsDAOImp.goodsUpdate(goodsDTO);
			model.addAttribute("success",true);
		    String uploadDir = request.getServletContext().getRealPath("/") + "resources/upload/"; // 실제 이미지를 저장할 위치
		    FileCopyUtils.copy(imageFile.getBytes(), new File(uploadDir + fileName)); // 파일 저장
		} catch (Exception e) {
			model.addAttribute("success",false);
		}		
		return"goods/goodsUpdateSucceed";
	}
	@GetMapping("goodsDelete")
	public String goodsDelete(@RequestParam ("goodsid") int num, Model model) {
		
		try {
			goodsDAOImp.goodsDelete(num);
			model.addAttribute("success",true);
			
		} catch (Exception e) {
			model.addAttribute("success",false);
		}
		return"goods/goodsDelete";
	}
}
