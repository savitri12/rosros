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
public class ThirdPartyReconciliationInfoDto {
	
	private UUID id;
	
	private List<ThirdPartyInfoDto> thirdParty;
	
	private String thirdPartyreconciliationNote;
	
	private String thirdpartyStatus;
	
//	private ThirdPartyInfoDto thirdPartyManual;
//	
//	private ThirdPartyInfoDto thirdPartyAPI;

//	private float reconThirdPartyManualAmount;
//	
//	private float reconThirdPartyAPIAmount;
	
//	private ReconciliationMatchTypeDto reconciliationMatchTypeDto;
}
