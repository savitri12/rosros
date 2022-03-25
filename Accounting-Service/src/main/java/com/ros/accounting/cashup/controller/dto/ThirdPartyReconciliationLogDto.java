/**
 * 
 */
package com.ros.accounting.cashup.controller.dto;

import java.util.Date;
import java.util.List;

import com.ros.accounting.cashup.model.CashUpTimeIndicator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ayush
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThirdPartyReconciliationLogDto {
	
	private Date cashUpDate;
	
	private String sheetStatus;
	
	private String thirdPartyName;
	
	private float totalAmount;
	
	private float totalApiAmount;
	
	private List<CashUpDateTimeDto> cashupIds;

}
