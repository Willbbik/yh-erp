package com.yh.erp.domain.model.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductCategory is a Querydsl query type for ProductCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductCategory extends EntityPathBase<ProductCategory> {

    private static final long serialVersionUID = 1718972732L;

    public static final QProductCategory productCategory = new QProductCategory("productCategory");

    public final StringPath code = createString("code");

    public final StringPath codeDesc = createString("codeDesc");

    public final StringPath codeName = createString("codeName");

    public final EnumPath<com.yh.erp.domain.shared.YesOrNo> delYn = createEnum("delYn", com.yh.erp.domain.shared.YesOrNo.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QProductCategory(String variable) {
        super(ProductCategory.class, forVariable(variable));
    }

    public QProductCategory(Path<? extends ProductCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductCategory(PathMetadata metadata) {
        super(ProductCategory.class, metadata);
    }

}

