package com.yh.erp.domain.model.company;

import com.yh.erp.domain.shared.YesOrNo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_COMPANY")
@Getter
@NoArgsConstructor
public class Company {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private CompanyInfo companyInfo;

    @Column(name = "del_yn")
    @Convert(converter = YesOrNo.StringTo.class)
    private YesOrNo delYn;

}
