package com.yh.erp.domain.model.quotation;

import com.yh.erp.domain.model.company.CompanyInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_QUOTATION")
@Getter
@NoArgsConstructor
public class Quotation {

    @Id
    @Column(name = "quotation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "title")
    private String title;

    @Column(name = "statement_json_content", columnDefinition = "json")
    private String statementJsonContent;

    @Column(name = "quote_date")
    private String quoteDate; //TODO 날짜타입으로 할지 확인 필요

    @Column(name = "quotationType")
    @Enumerated(EnumType.STRING)
    private QuotationType type;

    @Column(name = "quote_number")
    private String quoteNumber;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "total_price_string")
    private String totalPriceString;

    @Column(name = "product_quantity")
    private String productQuantity;

    @Column(name = "description")
    private String desc;

    @Embedded
    private CompanyInfo companyInfo;

}
