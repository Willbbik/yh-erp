package com.yh.erp.application.impl;

import com.yh.erp.application.ProductCategoryService;
import com.yh.erp.domain.model.product.ProductCategoryRepository;
import com.yh.erp.domain.model.product.dto.ProductCategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategoryDTO> getCategories() {
        return productCategoryRepository.findCategories();
    }
}
