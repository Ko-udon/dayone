package com.example.dayone.persist.repository;

import com.example.dayone.persist.entity.CompanyEntity;
import java.util.Optional;
import org.aspectj.apache.bcel.classfile.Module.Open;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

  boolean existsByTicker(String ticker);

  Optional<CompanyEntity> findByName(String name);

  //Optional<CompanyEntity> findByTicker(String ticker);
}
