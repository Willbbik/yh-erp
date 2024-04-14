package com.yh.erp.domain.model.category;

import com.yh.erp.domain.shared.YesOrNo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_CATEGORY")
@Getter
@NoArgsConstructor
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_category_id")
    private Long parentCategoryId;

    @Column(name = "code")
    private String code;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "code_desc")
    private Long desc;

    @Column(name = "del_yn")
    @Convert(converter = YesOrNo.StringTo.class)
    private YesOrNo delYn;

}
