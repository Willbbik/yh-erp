package com.yh.erp.domain.model.user;

import com.yh.erp.infrastructure.querydsl.repository.JpaUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaUserRepository {
}
