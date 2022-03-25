package com.ros.accounting.cashup.model.master;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TillMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "till_id")
	private UUID id;

	private String name;
	
	@Column(nullable=true)
	private boolean match;
	
	@Column(nullable=true)
	private boolean partialMatch;
	
	@Column(nullable=true)
	private Float amount;

	private String code;

}
