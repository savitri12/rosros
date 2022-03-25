package com.ros.accounting.cashup.model;

import java.io.Serializable;
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
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author Ayush
 *
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardReconciliationInfo extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "card_recon_info_id", length = 16)
	private UUID id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pdq_system_id")
	private PDQSystem pdqCardManual;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "pdq_system_id")
//	private PDQSystem pdqCardAPI;
	
	@Enumerated(value = EnumType.STRING)
	private ReconciliationMatchType reconciliationMatchType;
	
	private float reconCardManualAmount;
	
	private float reconCardAPIAmount;

}
