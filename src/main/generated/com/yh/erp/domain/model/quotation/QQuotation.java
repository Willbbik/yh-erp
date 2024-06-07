package com.yh.erp.domain.model.quotation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuotation is a Querydsl query type for Quotation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuotation extends EntityPathBase<Quotation> {

    private static final long serialVersionUID = 1386230744L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuotation quotation = new QQuotation("quotation");

    public final NumberPath<Long> companyId = createNumber("companyId", Long.class);

    public final com.yh.erp.domain.model.company.QCompanyInfo companyInfo;

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final StringPath customerName = createString("customerName");

    public final EnumPath<com.yh.erp.domain.shared.YesOrNo> delYn = createEnum("delYn", com.yh.erp.domain.shared.YesOrNo.class);

    public final StringPath desc = createString("desc");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath productQuantity = createString("productQuantity");

    public final StringPath quoteDate = createString("quoteDate");

    public final StringPath quoteNumber = createString("quoteNumber");

    public final StringPath statementJsonContent = createString("statementJsonContent");

    public final StringPath title = createString("title");

    public final NumberPath<Long> totalPrice = createNumber("totalPrice", Long.class);

    public final StringPath totalPriceString = createString("totalPriceString");

    public final EnumPath<QuotationType> type = createEnum("type", QuotationType.class);

    public QQuotation(String variable) {
        this(Quotation.class, forVariable(variable), INITS);
    }

    public QQuotation(Path<? extends Quotation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuotation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuotation(PathMetadata metadata, PathInits inits) {
        this(Quotation.class, metadata, inits);
    }

    public QQuotation(Class<? extends Quotation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.companyInfo = inits.isInitialized("companyInfo") ? new com.yh.erp.domain.model.company.QCompanyInfo(forProperty("companyInfo")) : null;
    }

}

