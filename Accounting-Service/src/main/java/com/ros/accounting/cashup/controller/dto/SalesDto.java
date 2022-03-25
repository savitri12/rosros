package com.ros.accounting.cashup.controller.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesDto {
	private float foodPayment;

	private float drinksPayment;

	private float takeAwayPayment;

	private float otherPayment;

	private float serviceCharges;

	private float creditCardTip;

	private List<TaxInfoDto> taxInfo;


	public void setDefaultValues() {
		TaxInfoDto taxInfo = new TaxInfoDto("Vat", 0);
		List<TaxInfoDto> taxInfos = new ArrayList<>();
		taxInfos.add(taxInfo);
		this.setFoodPayment(0);
		this.setDrinksPayment(0);
		this.setTakeAwayPayment(0);
		this.setOtherPayment(0);
		this.setServiceCharges(0);
		this.setCreditCardTip(0);
		this.setTaxInfo(taxInfos);
		
	}
}
