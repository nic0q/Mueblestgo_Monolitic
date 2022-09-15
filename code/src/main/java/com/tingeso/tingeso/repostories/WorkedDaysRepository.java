package com.tingeso.tingeso.repostories;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tingeso.tingeso.entities.WorkedDaysEntity;
@Repository
@EnableJpaRepositories
public interface WorkedDaysRepository 
  extends JpaRepository<WorkedDaysEntity, Integer> {
    @Query(value="SELECT `date` FROM worked_days LIMIT 1;", nativeQuery=true)
    Date getDate();
    @Query(value="SELECT * FROM worked_days WHERE rut_employee = :rut AND `date` = :datee", nativeQuery=true)
    WorkedDaysEntity getWorkedDay(@Param("rut")String rut,@Param("datee") Date date);
}
