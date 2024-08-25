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

    @Column(name = "g2b_number")
    private Long g2bNumber;

    @Column(name = "product_name")
    private String name;

    @Column(name = "model_name")
    private String modelName;

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
    public Product(Long g2bNumber, String name, String modelName, String size, Long price) {
        this.g2bNumber = g2bNumber;
        this.name = name;
        this.modelName = modelName;
        this.size = size;
        this.price = price;
        this.delYn = YesOrNo.NO;
    }

    public void updateMainImageFullPath(String fullPath) {
        this.mainImageFullPath = fullPath;
    }

}
