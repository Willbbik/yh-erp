package com.yh.erp.domain.model.product;

import com.yh.erp.domain.shared.YesOrNo;
import jakarta.persistence.*;
import lombok.Builder;
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
    private String name;

    @Column(name = "product_size")
    private String size;

    @Column(name = "price")
    private Long price;

    @Column(name = "main_image_full_path")
    private String mainImageFullPath;

    @Column(name = "del_yn")
    @Convert(converter = YesOrNo.StringTo.class)
    private YesOrNo delYn;

    @Builder
    public Product(Long identifyNumber, String name, String size, Long price, String mainImageFullPath) {
        this.identifyNumber = identifyNumber;
        this.name = name;
        this.size = size;
        this.price = price;
        this.mainImageFullPath = mainImageFullPath;
        this.delYn = YesOrNo.NO;
    }
}
