package com.yh.erp.infrastructure.querydsl.repository;

import com.yh.erp.domain.model.product.dto.ProductDTO;
import com.yh.erp.domain.model.product.dto.ProductSearchReqDTO;

import java.util.List;

public interface JpaProductRepository {

    List<ProductDTO> findProducts(ProductSearchReqDTO dto);

}
