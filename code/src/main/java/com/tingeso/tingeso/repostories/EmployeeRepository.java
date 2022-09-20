package com.tingeso.tingeso.repostories;

import com.tingeso.tingeso.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
  EmployeeEntity findByRut(String rut);
  @Query(value="SELECT * FROM employee LIMIT 1", nativeQuery=true)
  EmployeeEntity findOne();
}
