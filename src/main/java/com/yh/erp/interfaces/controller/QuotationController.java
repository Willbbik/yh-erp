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
public class QuotationController {

    private final ProductService productService;

    @GetMapping("/api/quotation/procurement/create")
    public ResponseEntity<List<ProductDTO>> createProcurementQuotation(ProductSearchReqDTO dto) throws Exception {
        return ResponseEntity.ok().body(productService.getProducts(dto));
    }

}
