package com.ros.accounting.cashup.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * This entity represents for the cash & PDQ
 *
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashnPDQ implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cash_n_pdq_id",length = 16)
	private UUID id;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cash_n_pdq_id")
	private List<PettyCash> pettyCashs;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cash_n_pdq_id")
	private List<TillSystem> tillSystems;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cash_n_pdq_id")
	private List<PDQSystem> pdqSystems;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cash_n_pdq_id")
	private List<WageAdvance> wageAdvances;
	
	@Enumerated(EnumType.STRING)
	private CashUpInfoMode cashUpInfoMode;
	
	private void setDefaultValues() {
		PettyCash pettyCash = new PettyCash();
		List<PettyCash> pettyCashs = new ArrayList<>();
		TillSystem tillSystem = new TillSystem();
		List<TillSystem> tillSystems = new ArrayList<>();
		PDQSystem pdqSystem = new PDQSystem();
		List<PDQSystem> pdqSystems = new ArrayList<>();
		WageAdvance wageAdvance = new WageAdvance(null,0,null);
		List<WageAdvance> wageAdvances = new ArrayList<>();
		
//		set defaults
		pettyCash.setDefaultValues();
		tillSystem.setDefaultValues();
		pdqSystem.setDefaultValues();
		
//		set to this
		this.setCashUpInfoMode(CashUpInfoMode.MANUAL);
		this.setPdqSystems(pdqSystems);
		this.setPettyCashs(pettyCashs);
		this.setTillSystems(tillSystems);
		this.setWageAdvances(wageAdvances);
	
	}
}
