package com.yh.erp.domain.model.product;

import com.yh.erp.domain.model.company.CompanyInfo;
import com.yh.erp.domain.model.quotation.QuotationType;
import com.yh.erp.domain.shared.YesOrNo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_PRODUCT")
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identify_number")
    private Long identifyNumber;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_size")
    private String productSize;

    @Column(name = "price")
    private Long price;

    @Column(name = "main_image")
    private String mainImage;

    @Column(name = "del_yn")
    @Enumerated(EnumType.STRING)
    private YesOrNo delYn;

}
