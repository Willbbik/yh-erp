package com.yh.erp.domain.model.company;

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

    @Column(name = "company_owner_name")
    private String ownerName;

    @Column(name = "company_name")
    private String name;

    @Column(name = "company_business_number")
    private String businessNumber;

    @Column(name = "company_location")
    private String location;

    @Column(name = "company_phone_number")
    private String phoneNumber;

    @Column(name = "company_email")
    private String email;

    @Column(name = "company_fax_number")
    private String faxNumber;

    @Column(name = "company_type")
    private String companyType;

    @Column(name = "company_seal_image")
    private String sealImage;

}
