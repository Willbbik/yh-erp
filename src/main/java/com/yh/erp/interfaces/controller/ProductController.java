package com.yh.erp.interfaces.controller;

import com.yh.erp.application.ProductService;
import com.yh.erp.domain.model.product.dto.ProductCreateDTO;
import com.yh.erp.domain.model.product.dto.ProductDTO;
import com.yh.erp.domain.model.product.dto.ProductSearchReqDTO;
import com.yh.erp.domain.model.product.dto.ProductUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/api/products")
    public ResponseEntity<List<ProductDTO>> getProducts(ProductSearchReqDTO dto) {
        return ResponseEntity.ok().body(productService.getProducts(dto));
    }

    @GetMapping("/api/products/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    @PostMapping("/api/products")
    public ResponseEntity<ProductDTO> createProduct(ProductCreateDTO dto,
                                            @RequestPart(name = "mainImage", required = false) MultipartFile mainImage,
                                            MultipartHttpServletRequest request) throws Exception {

        dto.getImages().add(request.getFile("file1"));
        dto.getImages().add(request.getFile("file2"));
        dto.getImages().add(request.getFile("file3"));
        dto.getImages().add(request.getFile("file4"));
        dto.getImages().add(request.getFile("file5"));
//        dto.setMainImage(mainImage);

        return ResponseEntity.ok().body(productService.createProduct(dto, mainImage));
    }

    @PutMapping("/api/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Long id,
                                                ProductUpdateDTO dto,
                                                @RequestPart(name = "mainImage", required = false) MultipartFile mainImage,
                                                MultipartHttpServletRequest request) throws Exception {

        dto.getImages().add(request.getFile("file1"));
        dto.getImages().add(request.getFile("file2"));
        dto.getImages().add(request.getFile("file3"));
        dto.getImages().add(request.getFile("file4"));
        dto.getImages().add(request.getFile("file5"));
//        dto.setMainImage(request.getFile("mainImage"));

        return ResponseEntity.ok().body(productService.updateProduct(id, dto, mainImage));
    }

    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }



}
