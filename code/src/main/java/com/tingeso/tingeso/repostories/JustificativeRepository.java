package com.tingeso.tingeso.repostories;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tingeso.tingeso.entities.JustificativeEntity;

@Repository
@EnableJpaRepositories
public interface JustificativeRepository extends JpaRepository<JustificativeEntity, Integer> {
  @Query(value="SELECT * FROM justificative WHERE rut_employee = :rut AND `date` = :datee", nativeQuery=true)
  JustificativeEntity getJustificative(@Param("rut")String rut,@Param("datee") Date date);
  
  
}
