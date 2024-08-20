package com.yh.erp.infrastructure.querydsl.repository;

public interface JpaProductFileRepository {
    Integer getLastSort(Long productId);
}
