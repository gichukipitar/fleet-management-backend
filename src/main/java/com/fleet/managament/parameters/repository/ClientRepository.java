package com.fleet.managament.parameters.repository;

import com.fleet.managament.parameters.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository


public interface ClientRepository extends JpaRepository<Client, Long> {

    Page<Client> findByFieldNameContainingIgnoreCase(String fieldName, Pageable pageable);
}
