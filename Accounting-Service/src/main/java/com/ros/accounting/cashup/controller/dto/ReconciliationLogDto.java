package com.ros.accounting.cashup.controller.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReconciliationLogDto {
	
	private UUID id;

	private CashUpDto cashUpDto;
	
	private List<ReconciliationInfoDto> reconciliationsDto;
	
	private Date reconciliationDate;
	
	private List<CardReconciliationLogDto> cardreconDto;
	
	private List<ThirdPartyReconciliationLogDto> thirdPartyreconDto;
	
	private List<CashReconciliationLogDto> cashreconDto;
	
}
