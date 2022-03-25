package com.ros.accounting.cashup.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.ros.accounting.cashup.model.PettyCashDocument;
import com.ros.accounting.cashup.model.master.PettyCashMaster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PettyCashDto {
	
	private float amount;

	private List<DocumentDto> documents;
	
	private String pettyCashName;
	
	
	
	//setting default values

	public void setDefaultValues() {
		List<DocumentDto> pettyCashDocuments = new ArrayList<>();
		this.setAmount(0);
		this.setDocuments(pettyCashDocuments);
		this.setPettyCashName("NA");
		
	}
	
	public void setDefaultValues(String pettyCashName) {
		List<DocumentDto> pettyCashDocuments = new ArrayList<>();
		this.setAmount(0);
		this.setDocuments(pettyCashDocuments);
		this.setPettyCashName(pettyCashName);
		
	}
}
