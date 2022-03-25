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
public class PettyCashMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "petty_cash_master_id")
	private UUID id;

	private String name;

	private String code;
	
	@Column(name = "restaurant_id",length = 16)
	private UUID restaurantId;

	@Column(name = "client_id",length = 16)
	private UUID clientId;

}
