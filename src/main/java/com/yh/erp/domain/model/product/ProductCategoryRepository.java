package com.yh.erp.domain.model.product;

import com.yh.erp.infrastructure.querydsl.repository.JpaProductCategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>, JpaProductCategoryRepository {
}
