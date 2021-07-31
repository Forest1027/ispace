package com.ispace.repository;

import com.ispace.entity.UserInfo;
import com.ispace.repository.custom.UserInfoCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "users")
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>, UserInfoCustomRepository {
    @Query("select u from UserInfo u where u.email= ?1")
    UserInfo findByEmail(String email);
}
