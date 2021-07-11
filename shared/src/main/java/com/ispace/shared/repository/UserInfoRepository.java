package com.ispace.shared.repository;

import com.ispace.shared.repository.custom.UserInfoCustomRepository;
import com.ispace.shared.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "users")
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>, UserInfoCustomRepository {
}
