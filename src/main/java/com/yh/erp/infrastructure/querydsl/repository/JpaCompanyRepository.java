package com.yh.erp.infrastructure.querydsl.repository;

import com.yh.erp.domain.model.company.Company;

public interface JpaCompanyRepository {

    Company getYhCompany(); //TODO 만약 프로젝트가 확장이 된다면 삭제 필요

}
