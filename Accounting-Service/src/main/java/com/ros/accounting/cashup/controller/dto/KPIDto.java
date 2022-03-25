package com.ros.accounting.cashup.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.ros.accounting.cashup.model.enums.ComplaintReason;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KPIDto {
	private List<KpiCoverDto> kpiCovers;

	private List<ComplaintDto> complaints;

	private List<BreakDownDto> breakDownDetails;
	
	
	public void setDefaultValues() {
	List<KpiCoverDto> kpiCovers = new ArrayList<>();
	List<ComplaintDto> complaints = new ArrayList<>();
	List<BreakDownDto> breakDownDetails = new ArrayList<>();
	
	
	KpiCoverDto kpiCoverDto = new KpiCoverDto(0, "NA");
	ComplaintDto complaintDto = new ComplaintDto(ComplaintReason.Damaged_Accessories, "NA");
	
	
	
	//breakdown
	BreakDownDto refundbreakDownDto = new BreakDownDto();
	BreakDownDto discountbreakDownDto = new BreakDownDto();
	refundbreakDownDto.setDefaultValues("refund_breakdown");
	discountbreakDownDto.setDefaultValues("discount_breakdown");
	
	
	//add values
	breakDownDetails.add(refundbreakDownDto);
	breakDownDetails.add(discountbreakDownDto);
	complaints.add(complaintDto);
	kpiCovers.add(kpiCoverDto);
	
	//set 
	this.setBreakDownDetails(breakDownDetails);
	this.setComplaints(complaints);
	this.setKpiCovers(kpiCovers);
	
	}
}
