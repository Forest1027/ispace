package com.ispace.repository.custom;

public interface CommonCustomRepository<T>{
    void refresh(T t);
}