package com.ros.accounting.cashup.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ros.accounting.cashup.model.master.TillMaster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PettyCashDocument {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pettycash_document_id",length = 16)
	private UUID id;

	private String url;

}
