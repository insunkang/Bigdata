package kr.multi.bigdataShop.product.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductCommentController {
	@Autowired
	ProductCommentService service;
	
	@RequestMapping("/comment/write.do")
	public String insert(ProductCommentDTO productCommentDTO) {
		service.insert(productCommentDTO);
		return "redirect:/product/read.do?prd_no="+productCommentDTO.getPrd_no();
	}
	
}
