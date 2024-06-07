package com.yh.erp.domain.model.product.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private Long identifyNumber;
    private String name;
    private String size;
    private String price;
    private String mainImageFullPath;
}
