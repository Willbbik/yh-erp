package com.yh.erp.domain.model.product.dto;

import com.yh.erp.domain.shared.YesOrNo;
import lombok.Data;

@Data
public class ProductFileDTO {

    private Long id;
    private Long productId;
    private String fileName;
    private String fileOriginName;
    private Long fileSize;
    private String fileExt;
    private String filePath;
    private String fileFullPath;
    private Integer sort;
    private YesOrNo mainFileYn;
}
