package com.yh.erp.infrastructure.querydsl.repository;

import com.yh.erp.domain.model.user.User;

public interface JpaUserRepository {

    User getUserByLoginId(String loginId);

}
