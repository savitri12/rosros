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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PDQCardMaster implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pdq_card_master_id")
	private UUID id;
	
	private String name;
	
	@Column(columnDefinition = "boolean default false")
	private boolean match;
	
	@Column(columnDefinition = "boolean default false")
	private boolean partialMatch;

	private String code;
	
	private UUID restaurantId;
	
	@Column(nullable = true)
	private Float amount;
	
	@Column(nullable = true)
	private Float apiAmount;

}
