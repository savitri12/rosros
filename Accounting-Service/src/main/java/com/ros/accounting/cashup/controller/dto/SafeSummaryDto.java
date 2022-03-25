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
public class SafeSummaryDto {

	private float safeTillAmount;

	private int safeCount;

	private float bankedAmount;

	private UUID witnessId;

	
	
	public void setDefaultValues() {
		this.setBankedAmount(0);
		this.setSafeCount(0);
		this.setSafeTillAmount(0);
		this.setWitnessId(null);
		
	}
}
