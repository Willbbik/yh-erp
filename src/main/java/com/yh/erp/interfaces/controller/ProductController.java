package com.yh.erp.interfaces.controller;

import com.yh.erp.application.ProductService;
import com.yh.erp.domain.model.product.dto.ProductCreateDTO;
import com.yh.erp.domain.model.product.dto.ProductDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(ProductCreateDTO dto, MultipartHttpServletRequest request) {
        log.info("/products api를 호출합니다.");

        dto.setMainImage(request.getFile("mainImage"));
        dto.setImage1(request.getFile("image1"));
        dto.setImage2(request.getFile("image2"));
        dto.setImage3(request.getFile("image3"));
        dto.setImage4(request.getFile("image4"));
        ProductDTO productDTO = productService.createProduct(dto);

        return ResponseEntity.ok().body(productDTO);
    }

}
