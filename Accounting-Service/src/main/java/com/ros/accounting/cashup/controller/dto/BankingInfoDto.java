/**
 * 
 */
package com.ros.accounting.cashup.controller.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ros.accounting.cashup.model.CashUpStatus;
import com.ros.accounting.cashup.model.BankingStatus;
import com.ros.accounting.cashup.model.BankingTimeIndicator;
import com.ros.accounting.cashup.model.CashUpTimeIndicator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author debadutta
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankingInfoDto {

	private UUID id;

	private Date bankingDate;

	private BankingTimeIndicator bankingTimeIndicator;

	private String giroSlipNumber;

	private float bankingTotal;

	private float bankedTotal;

	private String reason;

	private List<Date> cashUpDates;

	private List<CashUpTimeIndicator> cashUpTimeIndicators;

	private int noOfcashUps;

	private BankingStatus reconcileStatus;

	private String sealedBy;

	private UUID restaurantId;
}
