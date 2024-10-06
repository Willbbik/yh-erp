package com.yh.erp.interfaces.controller;

import com.yh.erp.application.ProductCategoryService;
import com.yh.erp.domain.model.product.dto.ProductCategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping("/api/products/categories")
    public List<ProductCategoryDTO> getCategoryList(){
        return productCategoryService.getCategories();
    }

}
