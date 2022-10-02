package com.microservicesmingeso.employeeservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microservicesmingeso.employeeservice.entities.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    @Query(value = "SELECT * FROM employees e WHERE e.identification = :identification", nativeQuery = true)
    Optional<EmployeeEntity> findByIdentification(@Param("identification") String identification);
}
