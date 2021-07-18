package com.ispace.articlemanagement.repository.custom;

public interface CommonCustomRepository<T>{
    void refresh(T t);
}