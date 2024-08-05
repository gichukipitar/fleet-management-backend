package com.fleet.managament.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomAppRepository <T> {
    List<T> findByFieldAndValue(Class <T> entityClass, String fieldName, String value);
    Page<T> findByQueryWithPagination(Class <T> entityClass, Query query, PageRequest pageRequest,
                                      Pageable pageable);
}
