package com.yh.erp.infrastructure.querydsl.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yh.erp.domain.model.product.QProduct;
import com.yh.erp.domain.model.product.dto.ProductDTO;
import com.yh.erp.domain.model.product.dto.ProductSearchReqDTO;
import com.yh.erp.domain.shared.YesOrNo;
import com.yh.erp.infrastructure.querydsl.repository.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaProductRepositoryImpl implements JpaProductRepository {

    @Autowired
    private JPAQueryFactory jqf;

    private QProduct product = QProduct.product;

    @Override
    public ProductDTO findProductById(Long id) {
        return jqf.select(Projections.fields(ProductDTO.class,
                        product.id,
                        product.g2bNumber,
                        product.name,
                        product.modelName,
                        product.size,
                        product.price,
                        product.mainImageFullPath
                ))
                .from(product)
                .where(this.eqProductId(id),
                        this.notDeleted())
                .fetchOne();
    }

    @Override
    public List<ProductDTO> findProducts(ProductSearchReqDTO dto) {
        return jqf.select(Projections.fields(ProductDTO.class,
                    product.id,
                    product.g2bNumber,
                    product.name,
                    product.modelName,
                    product.size,
                    product.price,
                    product.mainImageFullPath
            ))
            .from(product)
            .where(this.notDeleted(),
                    this.containsG2bNumber(dto.getG2bNumber()),
                    this.containsName(dto.getName()),
                    this.containsModelName(dto.getModelName()),
                    this.containsSize(dto.getSize()),
                    this.betweenPrice(dto.getMinPrice(), dto.getMaxPrice()))
            .orderBy(product.id.desc())
            .fetch();
    }

    @Override
    public void removeProductById(Long id) {
        jqf.update(product)
                .set(product.delYn, YesOrNo.YES)
                .where(this.eqProductId(id))
                .execute();
    }

    public BooleanExpression eqProductId(Long id) {
        return id != null ? product.id.eq(id) : null;
    }

    public BooleanExpression containsG2bNumber(Long g2bNumber) {
        return g2bNumber != null ? product.g2bNumber.stringValue().contains(String.valueOf(g2bNumber)) : null;
    }

    public BooleanExpression containsName(String name) {
        return name != null ? product.name.contains(name) : null;
    }

    public BooleanExpression containsModelName(String modelName) {
        return modelName != null ? product.modelName.contains(modelName) : null;
    }

    public BooleanExpression containsSize(String size) {
        return size != null ? product.size.contains(size) : null;
    }

    public BooleanExpression betweenPrice(Long minPrice, Long maxPrice) {
        if(minPrice != null && maxPrice != null){
            return product.price.between(minPrice, maxPrice);
        } else if (minPrice != null){
            return product.price.goe(minPrice);
        } else if (maxPrice != null){
            return product.price.loe(maxPrice);
        } else {
            return null;
        }
    }

    public BooleanExpression notDeleted() {
        return product.delYn.eq(YesOrNo.NO);
    }

}
