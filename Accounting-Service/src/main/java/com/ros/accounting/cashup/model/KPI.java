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
public class KPI implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kpi_id",length = 16)
	private UUID id;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "kpi_id")
	private List<KPICover> kpiCovers;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "kpi_id")
	private List<Complaint> complaints;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "kpi_id")
	private List<BreakDown> breakDownDetails;

}
