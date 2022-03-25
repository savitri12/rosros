package com.ros.accounting.cashup.controller.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ros.accounting.cashup.model.CashUpStatus;
import com.ros.accounting.cashup.model.CashUpTimeIndicator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardReconciliationLogDto {
	

	private String sheetStatus;
	
	private Date date;
	
	private String cardName;
	
	private float totalAmout;
	
	private float totalApiAmout;
	
	private List<CashUpDateTimeDto> cashupIds;
	
}
