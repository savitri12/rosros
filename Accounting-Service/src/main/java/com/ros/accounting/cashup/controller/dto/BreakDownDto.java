package com.ros.accounting.cashup.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BreakDownDto {

	private String name;

	private String billNumber;

	private String breakDownReason;

	private float amount;

	private DocumentDto document;

	
	public void setDefaultValues() {
	this.setName("NA");	
	this.setAmount(0);
	this.setBillNumber("NA");
	this.setBreakDownReason("NA");
	this.setDocument(new DocumentDto());
	}
	
	public void setDefaultValues(String name) {
	this.setName(name);	
	this.setAmount(0);
	this.setBillNumber("NA");
	this.setBreakDownReason("NA");
	this.setDocument(new DocumentDto(""));
	}
}
