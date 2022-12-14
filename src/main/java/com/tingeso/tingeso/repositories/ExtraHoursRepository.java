package com.tingeso.tingeso.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tingeso.tingeso.entities.ExtraHoursEntity;

@Repository
@EnableJpaRepositories
public interface ExtraHoursRepository extends JpaRepository<ExtraHoursEntity, Integer> {
  @Query(value = "SELECT eh.id,eh.rut_employee, eh.`date`,eh.n_hours FROM extra_hours AS eh INNER JOIN worked_days AS wd ON (wd.rut_employee = eh.rut_employee AND wd.`date` = eh.`date`) WHERE wd.extra_hours >= eh.n_hours  AND eh.rut_employee = :rut AND wd.rut_employee = :rut", nativeQuery = true)
  List <ExtraHoursEntity> getHorasExtraEfectivas(@Param("rut")String rut);
}
