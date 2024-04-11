package com.yh.erp.domain.model.specification;

import com.yh.erp.domain.shared.YesOrNo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SPECIFICATION")
@Getter
@NoArgsConstructor
public class Specification {

    @Id
    @Column(name = "specification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "standard_spec_number")
    private Integer standardSpecNumber;

    @Column(name = "detail_product_number")
    private Integer detailProductNumber;

    @Column(name = "detail_product_name")
    private String detailProductName;

    @Column(name = "classification_json_content", columnDefinition = "json")
    private String statementJsonContent;

    @Column(name = "ingredient_json_content", columnDefinition = "json")
    private String ingredientJsonContent;

    @Column(name = "shape_json_content", columnDefinition = "json")
    private String shapeJsonContent;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "del_yn")
    @Enumerated(EnumType.STRING)
    private YesOrNo delYn;

}
