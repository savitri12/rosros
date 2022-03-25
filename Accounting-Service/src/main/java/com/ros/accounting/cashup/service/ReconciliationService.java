package com.ros.accounting.cashup.service;

import com.ros.accounting.cashup.controller.dto.BankingReconcilationDto;
import com.ros.accounting.cashup.controller.dto.CardReconciliationInfoDto;
import com.ros.accounting.cashup.controller.dto.CashReconciliationInfoDto;
import com.ros.accounting.cashup.controller.dto.CashUpDto;
import com.ros.accounting.cashup.controller.dto.PDQSystemDto;
import com.ros.accounting.cashup.controller.dto.ReconciliationInfoDto;
import com.ros.accounting.cashup.controller.dto.ReconciliationLogDto;
import com.ros.accounting.cashup.controller.dto.ThirdPartyInfoDto;
import com.ros.accounting.cashup.controller.dto.ThirdPartyReconciliationInfoDto;
import com.ros.accounting.cashup.controller.dto.TillSystemDto;
import com.ros.accounting.cashup.exceptions.cashUpNotFoundException;
import com.ros.accounting.cashup.model.CashUpStatus;
import com.ros.accounting.cashup.model.PDQSystem;
import com.ros.accounting.cashup.model.master.PDQCardMaster;
import com.ros.accounting.cashup.model.master.ThirdPartyInfoMaster;

import java.util.*;

public interface ReconciliationService {

	List<ReconciliationLogDto> reconcileCashUps(List<ReconciliationLogDto> reconciliationLogs)
			throws cashUpNotFoundException;

	// List<?> getPendingReconciliationSummary(CashUpStatus status, Date fromDate,
	// Date toDate) throws cashUpNotFoundException;

	ReconciliationInfoDto getPendingReconciliationSummary(Date fromDate, Date toDate, UUID restuarantId) throws cashUpNotFoundException;

	ReconciliationLogDto reconcileCashUp(ReconciliationLogDto reconciliationLog) throws cashUpNotFoundException;

	List<ReconciliationLogDto> getCashUps(CashUpStatus status, Date fromDate, Date toDate)
			throws cashUpNotFoundException;

	ReconciliationInfoDto getReconciledSummary(Date fromDate, Date toDate, UUID restuarantId) throws cashUpNotFoundException;

	ReconciliationLogDto getReconciliationLog(Date fromDate, Date toDate, UUID restuarantId, String reconType)
			throws cashUpNotFoundException;

	CashUpDto cardReconciliation(CardReconciliationInfoDto cards) throws cashUpNotFoundException;

	CashUpDto thirdPartyReconciliation(ThirdPartyReconciliationInfoDto thirdParty) throws cashUpNotFoundException;

	BankingReconcilationDto cashReconcilied(BankingReconcilationDto bankingInfoDto) throws cashUpNotFoundException;

	List<PDQSystemDto> getCardSheetsToReconcile(UUID id) throws cashUpNotFoundException;

	BankingReconcilationDto getCashSheetsToReconcile(UUID id) throws cashUpNotFoundException;

	List<ThirdPartyInfoDto> getThirdPartySheetsToReconcile(UUID id) throws cashUpNotFoundException;
}
