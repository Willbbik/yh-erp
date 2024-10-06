package com.yh.erp.infrastructure.querydsl.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yh.erp.domain.model.product.QProductCategory;
import com.yh.erp.domain.model.product.dto.ProductCategoryDTO;
import com.yh.erp.domain.shared.YesOrNo;
import com.yh.erp.infrastructure.querydsl.repository.JpaProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaProductCategoryRepositoryImpl implements JpaProductCategoryRepository {

    @Autowired
    private JPAQueryFactory jqf;

    private QProductCategory productCategory = QProductCategory.productCategory;

    @Override
    public List<ProductCategoryDTO> findCategories() {
        return jqf.select(Projections.fields(ProductCategoryDTO.class,
                    productCategory.id,
                    productCategory.code,
                    productCategory.codeName,
                    productCategory.codeDesc,
                    productCategory.delYn
                ))
                .from(productCategory)
                .where(this.notDeleted())
                .fetch();
    }


    public BooleanExpression notDeleted() {
        return productCategory.delYn.eq(YesOrNo.NO);
    }
}
