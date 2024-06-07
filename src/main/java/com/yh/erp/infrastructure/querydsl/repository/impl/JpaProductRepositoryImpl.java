package com.yh.erp.infrastructure.querydsl.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yh.erp.infrastructure.querydsl.repository.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaProductRepositoryImpl implements JpaProductRepository {

    @Autowired
    private JPAQueryFactory jqf;

}
