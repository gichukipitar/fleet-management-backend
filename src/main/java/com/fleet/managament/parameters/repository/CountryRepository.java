package com.fleet.managament.parameters.repository;

import com.fleet.managament.parameters.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query(value = "select c from Country c where " +
    "c.description LIKE %?1% or c.capitalCity like %?1%")
    List<Country> findByKeyword(String description);

}
