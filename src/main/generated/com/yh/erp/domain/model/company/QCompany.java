package com.yh.erp.domain.model.company;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCompany is a Querydsl query type for Company
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCompany extends EntityPathBase<Company> {

    private static final long serialVersionUID = -838156486L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCompany company = new QCompany("company");

    public final QCompanyInfo companyInfo;

    public final EnumPath<com.yh.erp.domain.shared.YesOrNo> delYn = createEnum("delYn", com.yh.erp.domain.shared.YesOrNo.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCompany(String variable) {
        this(Company.class, forVariable(variable), INITS);
    }

    public QCompany(Path<? extends Company> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCompany(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCompany(PathMetadata metadata, PathInits inits) {
        this(Company.class, metadata, inits);
    }

    public QCompany(Class<? extends Company> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.companyInfo = inits.isInitialized("companyInfo") ? new QCompanyInfo(forProperty("companyInfo")) : null;
    }

}

