package com.yh.erp.domain.model.product;

import com.yh.erp.infrastructure.querydsl.repository.JpaProductFileRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFileRepository extends JpaRepository<ProductFile, Long>, JpaProductFileRepository {

}
