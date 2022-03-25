package com.ros.accounting.cashup.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
/**
 * 
 * @author Mohith
 * This class represents the main entity for tax and money
 *
 */
@Data
@Entity
@Table(name = "EPOS")
public class EPOS implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "epos_id")
	private long id;

	private long foodPayment;

	private long drinksPayment;

	private long takeAwayPayment;

	private long otherPayment;

	private long VATTaxPayment;

	private long serviceCharges;
	
	private long creditCardTip;
	
}
