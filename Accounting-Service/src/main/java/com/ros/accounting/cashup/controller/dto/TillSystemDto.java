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
public class TillSystemDto {
	
	private UUID id;
	
	private float amount;
	
	private boolean match;
	
	private boolean partialMatch;

	private String name;

	public void setDefaultValues() {
		// TODO Auto-generated method stub
		this.setId(null);
		this.setAmount(0);
		this.setMatch(false);
		this.setPartialMatch(false);
		this.setName("NA");
	}
	
	
	public void setDefaultValues(String name) {
		// TODO Auto-generated method stub
		this.setId(null);
		this.setAmount(0);
		this.setMatch(false);
		this.setPartialMatch(false);
		this.setName(name);
	}
	
	
}
