package com.yh.erp.domain.model.product;

import com.yh.erp.domain.shared.YesOrNo;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PRODUCT")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "g2b_number")
    private String g2bNumber;

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

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "mod_at")
    private LocalDateTime modAt;

    @Builder
    public Product(String g2bNumber, String category, String name, String modelName, String size, Long price) {
        this.g2bNumber = g2bNumber;
        this.category = category;
        this.name = name;
        this.modelName = modelName;
        this.size = size;
        this.price = price;
        this.delYn = YesOrNo.NO;
        this.createAt = LocalDateTime.now();
    }

    public void updateMainImageFullPath(String fullPath) {
        this.mainImageFullPath = fullPath;
    }

}
