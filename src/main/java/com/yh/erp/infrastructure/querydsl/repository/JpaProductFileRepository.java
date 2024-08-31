package com.yh.erp.infrastructure.querydsl.repository;

import com.yh.erp.domain.model.product.ProductFile;

import java.util.List;

public interface JpaProductFileRepository {
    Integer findLastSort(Long productId);

    ProductFile findMainImageById(Long productId);

    List<ProductFile> findImagesById(Long productId);

    void delFileById(Long id);

}
