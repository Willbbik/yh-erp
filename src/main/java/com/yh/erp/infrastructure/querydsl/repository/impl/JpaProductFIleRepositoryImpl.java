package com.yh.erp.infrastructure.querydsl.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yh.erp.domain.model.product.QProductFile;
import com.yh.erp.infrastructure.querydsl.repository.JpaProductFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaProductFIleRepositoryImpl implements JpaProductFileRepository {

    @Autowired
    private JPAQueryFactory jqf;

    private QProductFile productFile = QProductFile.productFile;


    @Override
    public Integer getLastSort(Long productId) {
        return jqf.select(productFile.sort.max())
                .from(productFile)
                .where(productFile.id.eq(productId))
                .fetchFirst().intValue();
    }
}
