package com.ros.accounting.cashup.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ros.accounting.cashup.controller.dto.BankingInfoDto;
import com.ros.accounting.cashup.controller.dto.CashUpDto;
import com.ros.accounting.cashup.exceptions.RestaurantNotFoundException;
import com.ros.accounting.cashup.exceptions.cashUpNotFoundException;
import com.ros.accounting.cashup.model.CashUpStatus;

public interface CashUpService {

//	CashUpDto addNewCashUp(CashUpDto cashUp) throws cashUpNotFoundException, ParseException;
	
	CashUpDto addNewCashUp(CashUpDto cashUp) throws cashUpNotFoundException;

	CashUpDto getCashUpById(UUID id) throws cashUpNotFoundException;

	Object editCashUp(CashUpDto dto) throws cashUpNotFoundException;

	String deleteCashUp(UUID id) throws cashUpNotFoundException;

	List<?> getCashUpSheets(CashUpStatus status, Date fromDate, Date toDate) throws cashUpNotFoundException;

//	List<?> getCashUpSheets(CashUpStatus status) throws cashUpNotFoundException;

	BankingInfoDto CreateBanking(BankingInfoDto banking) throws cashUpNotFoundException;

	List<BankingInfoDto> getAllBanks(int limit, int pageNo) throws cashUpNotFoundException;

	BankingInfoDto getBankById(UUID id) throws cashUpNotFoundException;

	BankingInfoDto updateBanking(BankingInfoDto bank) throws cashUpNotFoundException;

	String DeleteBankingById(UUID id) throws cashUpNotFoundException;


	List<CashUpDto> getAllCashUpSheets(int limit, int pageNo) throws cashUpNotFoundException;
	void checkNullData(CashUpDto cashUpDto);
	void checkNullData(List<CashUpDto> cashUpDtos);

	List<CashUpDto> getAllCashUpSheets(int limit, int pageNo, UUID restaurantUUID) throws cashUpNotFoundException, RestaurantNotFoundException;

	List<BankingInfoDto> getAllBanks(int limit, int pageNo, UUID restaurantId) throws cashUpNotFoundException;
}
