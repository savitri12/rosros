package com.ros.accounting.cashup.controller.dto;


import java.util.ArrayList;
import java.util.List;

import com.ros.accounting.cashup.model.CashUpInfoMode;
import com.ros.accounting.cashup.model.PDQSystem;
import com.ros.accounting.cashup.model.PettyCash;
import com.ros.accounting.cashup.model.TillSystem;
import com.ros.accounting.cashup.model.WageAdvance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CashnPDQDto {

	private List<PettyCashDto> pettyCashs;

	private List<TillSystemDto> tillSystems;

	private List<PDQSystemDto> pdqSystems;
	
	private List<WageAdvanceDto> wageAdvances;
	
	
	public void setDefaultValues() {
		PettyCashDto pettyCash1 = new PettyCashDto();
		PettyCashDto pettyCash2 = new PettyCashDto();
		PettyCashDto pettyCash3 = new PettyCashDto();
		List<PettyCashDto> pettyCashs = new ArrayList<>();
		
		TillSystemDto tillSystem1 = new TillSystemDto();
		TillSystemDto tillSystem2 = new TillSystemDto();
		TillSystemDto tillSystem3 = new TillSystemDto();
		List<TillSystemDto> tillSystems = new ArrayList<>();
		
		PDQSystemDto pdqSystem = new PDQSystemDto();
		List<PDQSystemDto> pdqSystems = new ArrayList<>();
		WageAdvanceDto wageAdvance = new WageAdvanceDto(0,null);
		
		
		List<WageAdvanceDto> wageAdvances = new ArrayList<>();
		
//		set defaults
		pettyCash1.setDefaultValues("food & drinks");
		pettyCash2.setDefaultValues("sundries");
		pettyCash3.setDefaultValues("Maintenance");
		tillSystem1.setDefaultValues("till1");
		tillSystem2.setDefaultValues("till2");
		tillSystem3.setDefaultValues("till3");
		pdqSystem.setDefaultValues();
		
		
		//add to list 
		pettyCashs.add(pettyCash1);
		pettyCashs.add(pettyCash2);
		pettyCashs.add(pettyCash3);
		
		tillSystems.add(tillSystem1);
		tillSystems.add(tillSystem2);
		tillSystems.add(tillSystem3);
		
		pdqSystems.add(pdqSystem);
		
		wageAdvances.add(wageAdvance);
		
//		set to this
		this.setPdqSystems(pdqSystems);
		this.setPettyCashs(pettyCashs);
		this.setTillSystems(tillSystems);
		this.setWageAdvances(wageAdvances);
	
	}
}
