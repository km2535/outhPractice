package com.panda.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.panda.back.entity.CertificationEntity;

import jakarta.transaction.Transactional;

@Repository
public interface CertificationRepositoty extends JpaRepository<CertificationEntity, String> {

  CertificationEntity findByUserId(String userId);

  @Transactional
  void deleteByUserId(String userId);
}
