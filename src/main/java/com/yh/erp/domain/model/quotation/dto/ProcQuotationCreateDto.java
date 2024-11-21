package com.yh.erp.domain.model.quotation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProcQuotationCreateDto {

    //고객정보
    private String title;
    private String quoteDate;
    private String customerName;

    //작업업체정보
    private String businessNumber;
    private String companyName;
    private String ownerName;
    private String companyLocation;
    private String companyType;
    private String phoneNumber;
    private String faxNumber;
    private String email;

    //상품 정보
    private String totalPrice;
    private String strTotalPrice;
    private List<QuotationProductInfo> productInfos;

}
