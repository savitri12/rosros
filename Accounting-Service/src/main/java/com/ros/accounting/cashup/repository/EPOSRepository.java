package com.ros.accounting.cashup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.ros.accounting.cashup.model.EPOS;
@Repository
public interface EPOSRepository extends JpaRepository<EPOS, Long> {

}
