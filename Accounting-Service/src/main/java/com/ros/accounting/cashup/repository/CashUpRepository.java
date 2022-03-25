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

@Repository
public interface CashUpRepository extends JpaRepository<CashUp, UUID> {
	@Query(value = "select c from CashUp c where c.cashUpStatus =:status and"
			+ " c.cashUpDate between :fromDate and :toDate")
	List<CashUp> findCashUpSheets(@Param("status") CashUpStatus status, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

	@Query(value = "select c from CashUp c where c.cashUpStatus =:status")
	List<CashUp> findCashUpSheets(@Param("status") CashUpStatus status);

	@Query(value = "select c from CashUp c where c.cashUpDate =:cashUpDate")
	List<CashUp> checkCashUpByDate(@Param("cashUpDate") Date cashUpDate);

	@Query(value = "select c from CashUp c where" + " c.cashUpDate between :fromDate and :toDate"
			+ " and c.restaurantId =:restaurantId")
	List<CashUp> findReconciliationSheets(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate,
			@Param("restaurantId") UUID restaurantId);

	@Query(value = "select * from cash_up order by cash_up_date desc limit :limit offset :offset", nativeQuery = true)
	List<CashUp> findDatewiseCashups(@Param("limit") int limit, @Param("offset") int offset);

	@Query(value = "select c from CashUp c where c.cashUpDate =:cashUpDate")
	CashUp getCashUpByDate(@Param("cashUpDate") Date cashUpDate);

//	@Query(value = "select b from BankingInfo b where" + " b.bankingDate between :fromDate and :toDate")


	@Query(value = "select * from cash_up where cash_up.restaurant_id =:restaurantId order by cash_up_date desc limit :limit offset :offset", nativeQuery = true)
	List<CashUp> findDatewiseCashupsForRestaurant(@Param("limit") int limit, @Param("offset") int offset,
			@Param("restaurantId") UUID restaurantId);

	@Query(value = "select * from cash_up where cash_up.restaurant_id =:restaurantId", nativeQuery = true)
	List<CashUp> findDatewiseReconciledForRestaurant(@Param("restaurantId") UUID restaurantId);

}
