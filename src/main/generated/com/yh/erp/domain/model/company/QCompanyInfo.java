package com.yh.erp.domain.model.company;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCompanyInfo is a Querydsl query type for CompanyInfo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCompanyInfo extends BeanPath<CompanyInfo> {

    private static final long serialVersionUID = 1072130824L;

    public static final QCompanyInfo companyInfo = new QCompanyInfo("companyInfo");

    public final StringPath businessNumber = createString("businessNumber");

    public final StringPath companyType = createString("companyType");

    public final StringPath email = createString("email");

    public final StringPath faxNumber = createString("faxNumber");

    public final StringPath location = createString("location");

    public final StringPath name = createString("name");

    public final StringPath ownerName = createString("ownerName");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath sealImage = createString("sealImage");

    public QCompanyInfo(String variable) {
        super(CompanyInfo.class, forVariable(variable));
    }

    public QCompanyInfo(Path<? extends CompanyInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCompanyInfo(PathMetadata metadata) {
        super(CompanyInfo.class, metadata);
    }

}

