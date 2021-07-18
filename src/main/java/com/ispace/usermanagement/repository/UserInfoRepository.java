package com.ispace.usermanagement.repository;

import com.ispace.usermanagement.entity.UserInfo;
import com.ispace.usermanagement.repository.custom.UserInfoCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "users")
public interface UserInfoRepository extends JpaRepository<UserInfo, String>, UserInfoCustomRepository {
}
