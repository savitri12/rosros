package com.ros.accounting.cashup.controller.dto;

import java.util.Date;
import java.util.List;

import com.ros.accounting.cashup.model.CashUpStatus;
import com.ros.accounting.cashup.model.CashUpTimeIndicator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PendingDepositDto {

	private Date cashUpDate;

	private CashUpStatus cashUpStatus;

	private CashUpTimeIndicator cashUpTimeIndicator;

	private List<SalesDto> sales;

	private CashnPDQDto cashnPdq;

}
