package com.tingeso.tingeso.repostories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.tingeso.tingeso.entities.WorkedDaysEntity;
@Repository
@EnableJpaRepositories
public interface WorkedDaysRepository 
  extends JpaRepository<WorkedDaysEntity, Integer> {
}
