package com.yh.erp.infrastructure.querydsl.repository;

public interface JpaProductFileRepository {
    Integer findLastSort(Long productId);
}
