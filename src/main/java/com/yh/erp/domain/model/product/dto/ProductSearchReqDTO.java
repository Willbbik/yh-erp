package com.yh.erp.domain.model.product.dto;

import lombok.Data;

@Data
public class ProductSearchReqDTO {

    private String searchKeyword;
    //    private String category; //TODO 나중에 추가할거임
    private Long minPrice;
    private Long maxPrice;
}
