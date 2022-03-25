package com.ros.accounting.cashup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ros.accounting.cashup.model.TillSystem;

@Repository
public interface TillSystemRepository extends JpaRepository<TillSystem, Long> {

}
