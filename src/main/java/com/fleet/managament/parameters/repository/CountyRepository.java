package com.fleet.managament.parameters.repository;

import com.fleet.managament.parameters.entity.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountyRepository extends JpaRepository<County, Long> {
}
