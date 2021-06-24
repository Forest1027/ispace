package com.ispace.usermanagement.dao;

import com.ispace.usermanagement.dao.custom.UserInfoCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.ispace.usermanagement.entity.UserInfo;

@RepositoryRestResource(path = "users")
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>, UserInfoCustomRepository {
}
