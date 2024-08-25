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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(ProductCreateDTO dto, MultipartHttpServletRequest request) throws Exception {
        log.info("/products api를 호출합니다.");

        List<MultipartFile> images = new ArrayList<>();
        images.add(request.getFile("file1"));
        images.add(request.getFile("file2"));
        images.add(request.getFile("file3"));
        images.add(request.getFile("file4"));

        dto.setMainImage(request.getFile("mainImage"));
        dto.setImages(images);

        return ResponseEntity.ok().body(productService.createProduct(dto));
    }

}
