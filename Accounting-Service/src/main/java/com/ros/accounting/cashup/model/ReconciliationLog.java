package com.ros.accounting.cashup.model;

import java.io.Serializable;
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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ayush
 *
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReconciliationLog extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "recon_log_id", length = 16)
	private UUID id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cash_up_id")
	private CashUp cashUp;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "recon_info_id")
	private List<ReconciliationInfo> reconciliationInfo;
}
