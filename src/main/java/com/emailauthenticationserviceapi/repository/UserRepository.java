package com.emailauthenticationserviceapi.repository;

import com.emailauthenticationserviceapi.common.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
}
