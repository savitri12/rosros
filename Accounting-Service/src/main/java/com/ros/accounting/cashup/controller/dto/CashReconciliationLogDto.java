/**
 * 
 */
package com.ros.accounting.cashup.controller.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ros.accounting.cashup.model.BankingStatus;
import com.ros.accounting.cashup.model.CashUpStatus;
import com.ros.accounting.cashup.model.CashUpTimeIndicator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ayush.negi
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CashReconciliationLogDto {

//	private String sheetStatus;

	private UUID id;

	private Date date;

	private BankingStatus reconcileStatus;

//	private String sheetStatus;

//	private float bankedTotal;
//	
//	private float bankingTotal;

	private float totalAmount;

//	private List<CashUpDateTimeDto> cashupIds;

//	private List<Date> cashUpDates;

}
