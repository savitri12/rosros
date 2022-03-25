package com.ros.accounting.cashup.model;

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

@Entity(name = "cashup_note")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CashUpNote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cashup_Note_id")
	private UUID id;
	
	private String reason;

	private String reasonAddedBy;
	
	private String comments;

}
