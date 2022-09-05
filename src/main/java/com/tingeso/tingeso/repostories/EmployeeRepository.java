package com.tingeso.tingeso.repostories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.tingeso.tingeso.models.Employee;

@Repository
@EnableJpaRepositories
public interface EmployeeRepository  extends JpaRepository<Employee, Integer> {
}

