package com.yh.erp.domain.model.product.dto;

import com.yh.erp.domain.shared.YesOrNo;
import lombok.Data;

@Data
public class ProductCategoryDTO {

    private Long id;

    private String code;

    private String codeName;

    private String codeDesc;

    private YesOrNo delYn;

}
