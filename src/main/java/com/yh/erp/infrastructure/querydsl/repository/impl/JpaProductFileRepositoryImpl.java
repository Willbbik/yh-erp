package com.yh.erp.infrastructure.querydsl.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yh.erp.domain.model.product.QProductFile;
import com.yh.erp.infrastructure.querydsl.repository.JpaProductFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaProductFileRepositoryImpl implements JpaProductFileRepository {

    @Autowired
    private JPAQueryFactory jqf;

    private QProductFile productFile = QProductFile.productFile;


    @Override
    public Integer findLastSort(Long productId) {
        return jqf.select(productFile.sort.max().coalesce(1).as("sort"))
                .from(productFile)
                .where(productFile.productId.eq(productId))
                .fetchFirst();
    }
}
