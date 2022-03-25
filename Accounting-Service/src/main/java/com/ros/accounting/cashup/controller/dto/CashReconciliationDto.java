package com.ros.accounting.cashup.controller.dto;

import java.util.Date;
import java.util.UUID;

import com.ros.accounting.cashup.model.CashUpTimeIndicator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CashReconciliationDto {
	
	private UUID id;
	
	private Date cashUpDate;
	
	private CashUpTimeIndicator cashUpTimeIndicator; 

	private float cashTotal;
	
	private boolean match;
	
	private boolean partialMatch;
	
	private String cashStatus;

	private String cardStatus;

	private String thirdpartyStatus; 
	
	private String note;

}
