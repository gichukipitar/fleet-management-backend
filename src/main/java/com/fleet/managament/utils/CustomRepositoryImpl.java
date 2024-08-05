package com.fleet.managament.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomRepositoryImpl<T> implements CustomAppRepository<T> {

    private final EntityManager entityManager;

    @Autowired
    public CustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<T> findByFieldAndValue(Class<T> entityClass, String fieldName, String value) {
        String queryStr = "SELECT e FROM " + entityClass.getName() + " e WHERE e." + fieldName + " = :value";
        TypedQuery<T> query = entityManager.createQuery(queryStr, entityClass);
        query.setParameter("value", value);
        return query.getResultList();
    }

    @Override
    public Page<T> findByQueryWithPagination(Class<T> entityClass, Query query, PageRequest pageRequest, Pageable pageable) {
        return null;
    }
}
