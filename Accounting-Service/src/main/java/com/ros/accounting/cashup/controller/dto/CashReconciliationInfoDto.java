package com.ros.accounting.cashup.controller.dto;

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
public class CashReconciliationInfoDto {
	
	private UUID id;

//	private float reconCashAmount;
	
//	private CashnPDQDto cashnpdqDto;
	
	private List<TillSystemDto> cash;
	
//	private ReconciliationMatchTypeDto reconciliationMatchTypeDto;
	
	private String cashreconciliationNote;
}
