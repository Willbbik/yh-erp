package com.yh.erp.domain.model.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductFile is a Querydsl query type for ProductFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductFile extends EntityPathBase<ProductFile> {

    private static final long serialVersionUID = 2066905274L;

    public static final QProductFile productFile = new QProductFile("productFile");

    public final EnumPath<com.yh.erp.domain.shared.YesOrNo> delYn = createEnum("delYn", com.yh.erp.domain.shared.YesOrNo.class);

    public final StringPath fileExt = createString("fileExt");

    public final StringPath fileFullPath = createString("fileFullPath");

    public final StringPath fileName = createString("fileName");

    public final StringPath fileOriginName = createString("fileOriginName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> fileSize = createNumber("fileSize", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.yh.erp.domain.shared.YesOrNo> mainFileYn = createEnum("mainFileYn", com.yh.erp.domain.shared.YesOrNo.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final NumberPath<Integer> sort = createNumber("sort", Integer.class);

    public QProductFile(String variable) {
        super(ProductFile.class, forVariable(variable));
    }

    public QProductFile(Path<? extends ProductFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductFile(PathMetadata metadata) {
        super(ProductFile.class, metadata);
    }

}

