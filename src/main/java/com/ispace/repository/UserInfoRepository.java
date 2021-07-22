package com.ispace.repository;

import com.ispace.entity.UserInfo;
import com.ispace.repository.custom.UserInfoCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "users")
public interface UserInfoRepository extends JpaRepository<UserInfo, String>, UserInfoCustomRepository {
}
