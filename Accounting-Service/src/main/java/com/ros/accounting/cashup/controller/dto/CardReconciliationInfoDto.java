package com.ros.accounting.cashup.controller.dto;

import java.util.List;
import java.util.UUID;

import com.ros.accounting.cashup.model.CashUpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardReconciliationInfoDto {
	
	private UUID id;
	
	private List<PDQSystemDto> cards;
	
	private String cardreconciliationNote;
	
	private String sheetStatus;

	
//	private CashnPDQDto cashnpdqdto;
//
//	private float reconCardManualAmount;
//	
//	private float reconCardAPIAmount;
//	
//	private ReconciliationMatchTypeDto reconciliationMatchTypeDto;
}
