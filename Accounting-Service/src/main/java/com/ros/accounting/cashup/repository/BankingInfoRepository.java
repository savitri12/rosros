/**
 * 
 */
package com.ros.accounting.cashup.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ros.accounting.cashup.model.BankingInfo;
import com.ros.accounting.cashup.model.CashUp;
import com.ros.accounting.cashup.model.CashUpStatus;

/**
 * @author debad
 *
 */
@Repository
public interface BankingInfoRepository extends JpaRepository<BankingInfo, UUID>{
	
	@Query(value = "select b.bankingTotal,b.bankedTotal from BankingInfo b")
	List<BankingInfo> findVarience();
	
	@Query(value = "select b from BankingInfo b where"
			+ " b.bankingDate between :fromDate and :toDate")
	List<BankingInfo> findReconciliationSheets(@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);
	
	@Query(value = "select b from BankingInfo b where"
			+ " b.id = :bankingId")
	List<BankingInfo> findBankingInfo(@Param("bankingId") UUID bankingId);
	
	@Query(value = "select * from banking_info order by banking_date desc limit :limit offset :offset",nativeQuery=true)
	List<BankingInfo> findDatewiseBankings(@Param("limit") int limit,
			@Param("offset") int offset);

	@Query(value = "select * from banking_info where banking_info.restaurant_id =:restaurantId order by banking_date desc limit :limit offset :offset",nativeQuery=true)
	List<BankingInfo> findDatewiseBankingsForRestaurant(@Param("limit") int limit,@Param("offset") int offset,@Param("restaurantId") UUID restaurantId);

	@Query(value = "select b from BankingInfo b where" + " b.bankingDate between :fromDate and :toDate"
			+ " and b.restaurantId =:restaurantId")
	List<BankingInfo> findBankingSheets(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate, @Param("restaurantId") UUID restaurantId);

}
