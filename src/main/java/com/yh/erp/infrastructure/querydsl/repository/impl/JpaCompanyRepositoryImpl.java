package com.yh.erp.infrastructure.querydsl.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yh.erp.domain.model.company.Company;
import com.yh.erp.domain.model.company.QCompany;
import com.yh.erp.infrastructure.querydsl.repository.JpaCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaCompanyRepositoryImpl implements JpaCompanyRepository {

    @Autowired
    private JPAQueryFactory jqf;

    private QCompany company = QCompany.company;

    @Override
    public Company getYhCompany() {
        return jqf.select(company)
            .from(company)
            .fetchFirst();
    }

}
