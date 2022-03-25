package com.ros.accounting.cashup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ros.accounting.cashup.model.BreakDownDocument;

@Repository
public interface BreakDownDocumentRepository extends JpaRepository<BreakDownDocument, Long> {

}
