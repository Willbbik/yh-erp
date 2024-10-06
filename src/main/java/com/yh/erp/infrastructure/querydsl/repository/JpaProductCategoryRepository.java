package com.yh.erp.infrastructure.querydsl.repository;

import com.yh.erp.domain.model.product.dto.ProductCategoryDTO;

import java.util.List;

public interface JpaProductCategoryRepository {

    List<ProductCategoryDTO> findCategories();

}
