package com.yh.erp.domain.model.product.dto;

import lombok.Data;

@Data
public class ProductSearchReqDTO {

    private Long g2bNumber;
//    private String category; //TODO 나중에 추가할거임
    private String name;
    private String modelName;
    private String size;
    private Long minPrice;
    private Long maxPrice;
}
