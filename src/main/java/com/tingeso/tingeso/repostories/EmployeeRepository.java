package com.tingeso.tingeso.repostories;

import com.tingeso.tingeso.models.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {}
