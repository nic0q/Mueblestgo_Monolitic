package com.tingeso.tingeso.repostories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.tingeso.tingeso.entities.JustificativeEntity;

@Repository
@EnableJpaRepositories
public interface JustificativeRepository extends JpaRepository<JustificativeEntity, Integer> {
  
  
  
}
