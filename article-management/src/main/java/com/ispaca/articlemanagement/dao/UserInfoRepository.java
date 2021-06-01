package com.ispaca.articlemanagement.dao;

import com.ispaca.articlemanagement.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
}
