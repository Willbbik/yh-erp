package com.yh.erp.domain.model.product;

import com.yh.erp.domain.shared.YesOrNo;
import com.yh.erp.infrastructure.querydsl.repository.JpaProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaProductRepository {

    Product findByIdAndDelYn(Long id, YesOrNo yesOrNo);

}
