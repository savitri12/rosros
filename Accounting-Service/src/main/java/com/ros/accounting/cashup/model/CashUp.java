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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

//import com.ros.accounting.reconciliation.model.ReconciliationLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Mohith This class represents the main entity of cashup
 *
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashUp extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cash_up_id", length = 16)
	private UUID id;

	@Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cashUpDate;

	@Enumerated(EnumType.STRING)
	private CashUpTimeIndicator cashUpTimeIndicator;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cashup_Note_id")
	private CashUpNote note;

	@Enumerated(value = EnumType.STRING)
	private CashUpStatus cashUpStatus;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cash_up_id")
	private List<Sales> sales;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cash_n_pdq_id")
	private CashnPDQ cashnPdq;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cash_up_id")
	private List<ThirdPartyInfo> thirdPartyInfo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "kpi_id")
	private KPI kpi;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "old_safe_summary_id")
	private SafeSummary safeSummary;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "banking_info_id")
	private BankingInfo bankingInfo;

	private UUID restaurantId;

	@Column
	private float EPOS;

	@Column
	private float CASH;

	@Column
	private float PDQ;

	@Column
	private float Delivery;

	@Column
	private float KPITotal;
	
	@Column
	private String cashreconciliationNote;
	
	@Column
	private String cardreconciliationNote;
	
	@Column
	private String thirdPartyreconciliationNote;
	
	
	@Column
	private String cashStatus;
	
	@Column
	private String cardStatus;
	
	@Column
	private String thirdpartyStatus;
	
	@Column(columnDefinition = "boolean default false")
	private boolean match;
	
	@Column(columnDefinition = "boolean default false")
	private boolean partialMatch;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CashUp other = (CashUp) obj;
		if (Float.floatToIntBits(CASH) != Float.floatToIntBits(other.CASH))
			return false;
		if (Float.floatToIntBits(Delivery) != Float.floatToIntBits(other.Delivery))
			return false;
		if (Float.floatToIntBits(EPOS) != Float.floatToIntBits(other.EPOS))
			return false;
		if (Float.floatToIntBits(KPITotal) != Float.floatToIntBits(other.KPITotal))
			return false;
		if (Float.floatToIntBits(PDQ) != Float.floatToIntBits(other.PDQ))
			return false;
		if (bankingInfo == null) {
			if (other.bankingInfo != null)
				return false;
		} else if (!bankingInfo.equals(other.bankingInfo))
			return false;
		if (cashUpDate == null) {
			if (other.cashUpDate != null)
				return false;
		} else if (!cashUpDate.equals(other.cashUpDate))
			return false;
		if (cashUpStatus != other.cashUpStatus)
			return false;
		if (cashUpTimeIndicator != other.cashUpTimeIndicator)
			return false;
		if (cashnPdq == null) {
			if (other.cashnPdq != null)
				return false;
		} else if (!cashnPdq.equals(other.cashnPdq))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (kpi == null) {
			if (other.kpi != null)
				return false;
		} else if (!kpi.equals(other.kpi))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (restaurantId == null) {
			if (other.restaurantId != null)
				return false;
		} else if (!restaurantId.equals(other.restaurantId))
			return false;
		if (safeSummary == null) {
			if (other.safeSummary != null)
				return false;
		} else if (!safeSummary.equals(other.safeSummary))
			return false;
		if (sales == null) {
			if (other.sales != null)
				return false;
		} else if (!sales.equals(other.sales))
			return false;
		if (thirdPartyInfo == null) {
			if (other.thirdPartyInfo != null)
				return false;
		} else if (!thirdPartyInfo.equals(other.thirdPartyInfo))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Float.floatToIntBits(CASH);
		result = prime * result + Float.floatToIntBits(Delivery);
		result = prime * result + Float.floatToIntBits(EPOS);
		result = prime * result + Float.floatToIntBits(KPITotal);
		result = prime * result + Float.floatToIntBits(PDQ);
		result = prime * result + ((bankingInfo == null) ? 0 : bankingInfo.hashCode());
		result = prime * result + ((cashUpDate == null) ? 0 : cashUpDate.hashCode());
		result = prime * result + ((cashUpStatus == null) ? 0 : cashUpStatus.hashCode());
		result = prime * result + ((cashUpTimeIndicator == null) ? 0 : cashUpTimeIndicator.hashCode());
		result = prime * result + ((cashnPdq == null) ? 0 : cashnPdq.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((kpi == null) ? 0 : kpi.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((restaurantId == null) ? 0 : restaurantId.hashCode());
		result = prime * result + ((safeSummary == null) ? 0 : safeSummary.hashCode());
		result = prime * result + ((sales == null) ? 0 : sales.hashCode());
		result = prime * result + ((thirdPartyInfo == null) ? 0 : thirdPartyInfo.hashCode());
		return result;
	}


//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "reconciliation_id")
//	private ReconciliationLog reconciliationLog;
	
	
}
