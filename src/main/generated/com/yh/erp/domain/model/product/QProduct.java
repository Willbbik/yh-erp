package com.yh.erp.domain.model.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 1992379934L;

    public static final QProduct product = new QProduct("product");

    public final EnumPath<com.yh.erp.domain.shared.YesOrNo> delYn = createEnum("delYn", com.yh.erp.domain.shared.YesOrNo.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> identifyNumber = createNumber("identifyNumber", Long.class);

    public final StringPath mainImageFullPath = createString("mainImageFullPath");

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final StringPath productName = createString("productName");

    public final StringPath productSize = createString("productSize");

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

