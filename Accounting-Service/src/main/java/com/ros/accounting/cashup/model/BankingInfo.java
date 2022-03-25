package com.ros.accounting.cashup.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankingInfo extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "banking_info_id")
	private UUID id;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date bankingDate;

	@Enumerated(EnumType.STRING)
	private BankingTimeIndicator bankingTimeIndicator;

	@Column
	private String giroSlipNumber;

	@Column
	private float bankingTotal;

	@Column
	private float bankedTotal;

	private String reason;

	@Column(nullable = false)
	private String sealedBy;

	private UUID employeeId;

	private UUID restaurantId;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bankingInfo")
	private List<CashUp> cashUps;

	private String note;
	
	@Enumerated(value = EnumType.STRING)
	private BankingStatus reconcileStatus;

}
