package com.yh.erp.infrastructure.querydsl.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yh.erp.domain.model.company.Company;
import com.yh.erp.domain.model.company.QCompany;
import com.yh.erp.domain.model.user.QUser;
import com.yh.erp.domain.model.user.User;
import com.yh.erp.infrastructure.querydsl.repository.JpaCompanyRepository;
import com.yh.erp.infrastructure.querydsl.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaUserRepositoryImpl implements JpaUserRepository {

    @Autowired
    private JPAQueryFactory jqf;

    private QUser user = QUser.user;

    @Override
    public User getUserByLoginId(String loginId) {
        //TODO 추후 확장시 회사id까지 같이 where에 들어가야함

        return jqf.select(user)
            .from(user)
            .where(this.eqLoginId(loginId))
            .fetchFirst();
    }


    private BooleanExpression eqLoginId(String loginId){
        return loginId != null ? user.loginId.eq(loginId) : null;
    }

}
