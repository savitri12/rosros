package com.ros.accounting.cashup.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ros.accounting.cashup.model.master.PettyCashMaster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PettyCash implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "petty_cash_id",length = 16)
	private UUID id;
	
	private float amount;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "petty_cash_master_id")
	private PettyCashMaster pettyCashMaster;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "petty_cash_id")
	private List<PettyCashDocument> documents;

	
	 public void setDefaultValues() {
		List<PettyCashDocument> pettyCashDocuments = new ArrayList<>();
		PettyCashMaster pettyCashMaster = new PettyCashMaster();
		pettyCashMaster.setName("not available");
		this.setAmount(0);
		this.setDocuments(pettyCashDocuments);
		this.setPettyCashMaster(pettyCashMaster);
	}
}
