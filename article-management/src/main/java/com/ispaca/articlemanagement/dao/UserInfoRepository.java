package com.ispaca.articlemanagement.dao;

import com.ispaca.articlemanagement.dao.custom.UserInfoCustomRepository;
import com.ispaca.articlemanagement.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "users")
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>, UserInfoCustomRepository {
}
