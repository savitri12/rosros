/**
 * 
 */
package com.ros.accounting.cashup.controller.dto;

import java.util.Date;
import java.util.UUID;

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
public class CashUpDateTimeDto {

	private Date cashUpDate;

	private CashUpTimeIndicator cashUpTimeIndicator;
	
	private UUID id;

}
