package com.example.dayone.repository;

import com.example.dayone.persist.entity.CompanyEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

@Repository
@RedisHash
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

  boolean existsByTicker(String ticker);

  Optional<CompanyEntity> findByName(String name);

  Optional<CompanyEntity> findByTicker(String ticker);

  Page<CompanyEntity> findByNameStartingWithIgnoreCase(String s, Pageable p);
}
