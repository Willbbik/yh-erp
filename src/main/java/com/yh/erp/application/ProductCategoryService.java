package com.yh.erp.application;

import com.yh.erp.domain.model.product.dto.ProductCategoryDTO;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategoryDTO> getCategories();

}
