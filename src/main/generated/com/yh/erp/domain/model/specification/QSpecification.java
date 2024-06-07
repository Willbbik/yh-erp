package com.yh.erp.domain.model.specification;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSpecification is a Querydsl query type for Specification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSpecification extends EntityPathBase<Specification> {

    private static final long serialVersionUID = 1931301254L;

    public static final QSpecification specification = new QSpecification("specification");

    public final NumberPath<Long> companyId = createNumber("companyId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final EnumPath<com.yh.erp.domain.shared.YesOrNo> delYn = createEnum("delYn", com.yh.erp.domain.shared.YesOrNo.class);

    public final StringPath detailProductName = createString("detailProductName");

    public final NumberPath<Integer> detailProductNumber = createNumber("detailProductNumber", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ingredientJsonContent = createString("ingredientJsonContent");

    public final StringPath shapeJsonContent = createString("shapeJsonContent");

    public final NumberPath<Integer> standardSpecNumber = createNumber("standardSpecNumber", Integer.class);

    public final StringPath statementJsonContent = createString("statementJsonContent");

    public QSpecification(String variable) {
        super(Specification.class, forVariable(variable));
    }

    public QSpecification(Path<? extends Specification> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSpecification(PathMetadata metadata) {
        super(Specification.class, metadata);
    }

}

