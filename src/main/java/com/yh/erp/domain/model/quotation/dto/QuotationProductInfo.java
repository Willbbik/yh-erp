package com.yh.erp.domain.model.quotation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuotationProductInfo {

    private Long productId;
    private String g2bNumber;
    private String modelName;
    private String size;
    private String price;
    private String totalPrice;
    private String imagePath;
    private Integer quantity;

}
