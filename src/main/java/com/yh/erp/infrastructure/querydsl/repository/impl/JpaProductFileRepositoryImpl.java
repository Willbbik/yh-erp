package com.yh.erp.infrastructure.querydsl.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yh.erp.domain.model.product.ProductFile;
import com.yh.erp.domain.model.product.QProductFile;
import com.yh.erp.domain.model.product.dto.ProductFileDTO;
import com.yh.erp.domain.shared.YesOrNo;
import com.yh.erp.infrastructure.querydsl.repository.JpaProductFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaProductFileRepositoryImpl implements JpaProductFileRepository {

    @Autowired
    private JPAQueryFactory jqf;

    private QProductFile productFile = QProductFile.productFile;


    @Override
    public Integer findLastSort(Long productId) {
        return jqf.select(productFile.sort.max().coalesce(0).as("sort"))
                .from(productFile)
                .where(this.eqProductId(productId),
                        this.notDeleted())
                .fetchFirst();
    }

    @Override
    public ProductFile findMainImageById(Long productId) {
        return jqf.select(productFile)
                .from(productFile)
                .where(this.eqProductId(productId),
                        this.eqMainFileYn(YesOrNo.YES),
                        this.notDeleted())
                .fetchOne();
    }

    @Override
    public List<ProductFile> findImagesById(Long productId) {
        return jqf.select(productFile)
                .from(productFile)
                .where(this.eqProductId(productId),
                        this.eqMainFileYn(YesOrNo.NO),
                        this.notDeleted())
                .fetch();
    }

    @Override
    public void removeFileById(Long id) {
        jqf.update(productFile)
            .set(productFile.delYn, YesOrNo.YES)
            .where(productFile.id.eq(id))
            .execute();
    }

    @Override
    public void removeFilesByIds(Long productId, List<Long> ids) {
        jqf.update(productFile)
                .set(productFile.delYn, YesOrNo.YES)
                .where(this.eqProductId(productId),
                        productFile.id.in(ids))
                .execute();
    }

    @Override
    public void removeAllFilesByProductId(Long productId) {
        jqf.update(productFile)
                .set(productFile.delYn, YesOrNo.YES)
                .where(productFile.productId.eq(productId))
                .execute();
    }

    public BooleanExpression eqProductId(Long productId) {
        return productId != null ? productFile.productId.eq(productId) : null;
    }

    public BooleanExpression notDeleted() {
        return productFile.delYn.eq(YesOrNo.NO);
    }

    public BooleanExpression eqMainFileYn(YesOrNo yesOrNo) {
        return yesOrNo != null ? productFile.mainFileYn.eq(yesOrNo) : null;
    }

}
