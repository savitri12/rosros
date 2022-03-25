package com.ros.accounting.cashup.controller.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThirdPartyInfoDto {
	
	private UUID id;
	
	private float amount;
	
	private float apiAmount;
	
	private boolean match;
	
	private boolean partialMatch;
	
	private String name;
	
	private String note;
	
	
public void setDefaultValues() {
		this.setAmount(0);
		this.setName("NA");
		this.setAmount(0);
		this.setMatch(false);
		this.setPartialMatch(false);
		this.setNote("NA");
		this.setId(null);
	}
}
