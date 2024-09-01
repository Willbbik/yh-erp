package com.yh.erp.interfaces.controller;

import com.yh.erp.application.ProductService;
import com.yh.erp.domain.model.product.dto.ProductCreateDTO;
import com.yh.erp.domain.model.product.dto.ProductDTO;
import com.yh.erp.domain.model.product.dto.ProductSearchReqDTO;
import com.yh.erp.domain.model.product.dto.ProductUpdateDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts(ProductSearchReqDTO dto) throws Exception {
        return ResponseEntity.ok().body(productService.getProducts(dto));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(ProductCreateDTO dto, MultipartHttpServletRequest request) throws Exception {

        dto.getImages().add(request.getFile("file1"));
        dto.getImages().add(request.getFile("file2"));
        dto.getImages().add(request.getFile("file3"));
        dto.getImages().add(request.getFile("file4"));
        dto.setMainImage(request.getFile("mainImage"));

        return ResponseEntity.ok().body(productService.createProduct(dto));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Long id, ProductUpdateDTO dto, MultipartHttpServletRequest request) throws Exception {

        dto.getImages().add(request.getFile("file1"));
        dto.getImages().add(request.getFile("file2"));
        dto.getImages().add(request.getFile("file3"));
        dto.getImages().add(request.getFile("file4"));
        dto.setMainImage(request.getFile("mainImage"));

        return ResponseEntity.ok().body(productService.updateProduct(id, dto));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }



}
