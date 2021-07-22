package com.ispace.repository.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class CommonCustomRepositoryImpl<T> implements CommonCustomRepository<T> {
    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void refresh(T t) {
        entityManager.refresh(t);
    }
}
