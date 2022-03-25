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
public class PDQSystemDto {
	
	private UUID id;
	
	private boolean match;
	
	private boolean partialMatch;
	
	private float amount;

	private float apiAmount;
	
	private String cardName;

	private String name;
	
	private String cardNote;

	public void setDefaultValues() {
		// TODO Auto-generated method stub
		this.setAmount(0);
		this.setMatch(false);
		this.setPartialMatch(false);
		this.setApiAmount(0);
		this.setCardName("NA");
		this.setCardName("NA");
		this.setName("Vouchers");
	}
	
	
}
