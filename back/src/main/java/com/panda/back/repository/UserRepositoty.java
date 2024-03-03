package com.panda.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.panda.back.entity.UserEntity;

@Repository
public interface UserRepositoty extends JpaRepository<UserEntity, String> {
  boolean existsByUserId(String userId);

  UserEntity findByUserId(String userId);
}
