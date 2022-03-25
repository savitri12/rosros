package com.ros.accounting.cashup.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

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
@Table(name = "old_safesummary")
public class SafeSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "old_safe_summary_id",length = 16)
	private UUID id;

	private float safeTillAmount;

	private int safeCount;

	private float bankedAmount;

	private UUID witnessId;

}
