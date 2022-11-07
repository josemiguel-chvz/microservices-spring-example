package com.example.scheduleservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.scheduleservice.entities.ClockEntity;

public interface ClockRepository extends JpaRepository<ClockEntity, Long>{

}
