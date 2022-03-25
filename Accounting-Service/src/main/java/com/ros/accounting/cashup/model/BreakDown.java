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


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreakDown implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "break_down_id",length = 16)
	private UUID id;

	private String name;

	private String code;

	private String billNumber;

	private String breakDownReason;

	private float amount;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "document_id")
	private BreakDownDocument document;
}
