package com.yh.erp.infrastructure.querydsl.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yh.erp.domain.model.product.QProductFile;
import com.yh.erp.domain.shared.YesOrNo;
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
                .where(this.eqProductId(productId),
                        this.notDeleted())
                .fetchFirst();
    }


    public BooleanExpression eqProductId(Long productId) {
        return productId != null ? productFile.productId.eq(productId) : null;
    }

    public BooleanExpression notDeleted() {
        return productFile.delYn.eq(YesOrNo.NO);
    }

}
