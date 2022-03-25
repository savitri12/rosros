package com.ros.accounting.cashup.controller.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ros.accounting.cashup.model.BankingStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankingReconcilationDto {

	private UUID id;

	private Date bankingDate;

	private float bankingTotal;

	private float bankedTotal;

	private List<CashReconciliationDto> cashUps;

	private String note;

	private BankingStatus reconcileStatus;

}
