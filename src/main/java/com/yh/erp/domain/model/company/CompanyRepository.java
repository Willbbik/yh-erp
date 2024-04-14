package com.yh.erp.domain.model.company;

import com.yh.erp.infrastructure.querydsl.repository.JpaCompanyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, JpaCompanyRepository {
}
