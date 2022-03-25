package com.ros.accounting.safesummary.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ros.accounting.safesummary.model.NewSafeSummary;

@Repository
public interface NewSafeSummaryRepository extends JpaRepository<NewSafeSummary, UUID> {

    @Query(value = "select s from NewSafeSummary s where s.restaurantId = :restaurantId ")
    List<NewSafeSummary> findSafeSummaryById(@Param("restaurantId") UUID restaurantId);
    
    
}
