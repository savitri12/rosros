package com.ros.accounting.cashup.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ros.accounting.cashup.model.master.TillMaster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TillSystem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "till_system_id",length = 16)
	private UUID id;

//	private float amount;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "till_id")
	private TillMaster tillMaster;
	
	@Column
	private String reconciliationNote;

	public void setDefaultValues() {
		TillMaster tillMaster = new TillMaster(null,"NA", false, false,null,"NA");
		this.setTillMaster(tillMaster);
		
	}
}
