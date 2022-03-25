package com.ros.accounting.cashup.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.ros.accounting.cashup.controller.dto.BankingInfoDto;
import com.ros.accounting.cashup.controller.dto.BankingReconcilationDto;
import com.ros.accounting.cashup.controller.dto.CashReconciliationDto;
import com.ros.accounting.cashup.controller.dto.CashUpDto;
import com.ros.accounting.cashup.controller.dto.CashUpSheetDto;
import com.ros.accounting.cashup.controller.dto.PDQSystemDto;
import com.ros.accounting.cashup.controller.dto.PettyCashDto;
import com.ros.accounting.cashup.controller.dto.ThirdPartyInfoDto;
import com.ros.accounting.cashup.controller.dto.TillSystemDto;
import com.ros.accounting.cashup.model.BankingInfo;
import com.ros.accounting.cashup.model.CashUp;
import com.ros.accounting.cashup.model.PDQSystem;
import com.ros.accounting.cashup.model.PettyCash;
import com.ros.accounting.cashup.model.ThirdPartyInfo;
import com.ros.accounting.cashup.model.TillSystem;

/**
 * 
 * This interface represents the converting entities and dtos
 *
 */
@Mapper
@Component
public interface RestDtoMapper {

	RestDtoMapper mapper = Mappers.getMapper(RestDtoMapper.class);

	@Mapping(target = "KPITotal", source = "KPITotal")
	@Mapping(target = "Delivery", source = "deliverytotal")
	@Mapping(target = "PDQ", source = "PDQtotal")
	@Mapping(target = "CASH", source = "CASHtotal")
	@Mapping(target = "EPOS", source = "EPOStotal")
	@Mapping(target = "note.reason", source = "reason")
	@Mapping(target = "note.reasonAddedBy", source = "reasonAddedBy")
	CashUp convertToCashUpEntity(CashUpDto dto);

	@Mapping(target = "KPITotal", source = "KPITotal")
	@Mapping(target = "deliverytotal", source = "delivery")
	@Mapping(target = "PDQtotal", source = "PDQ")
	@Mapping(target = "CASHtotal", source = "CASH")
	@Mapping(target = "EPOStotal", source = "EPOS")
	@Mapping(source = "note.reason", target = "reason")
	@Mapping(source = "note.reasonAddedBy", target = "reasonAddedBy")
	@Mapping(source = "cashreconciliationNote", target = "cashreconciliationNote")
	@Mapping(source = "cardreconciliationNote", target = "cardreconciliationNote")
	@Mapping(source = "thirdPartyreconciliationNote", target = "thirdPartyreconciliationNote")
	@Mapping(target = "cardStatus", source = "cardStatus")
	@Mapping(target = "thirdpartyStatus", source = "thirdpartyStatus")
	CashUpDto convertToCashUpDto(CashUp entity);

	CashUpSheetDto convertToCashUpSheetDto(CashUp entity);

	@Mapping(source = "note.reason", target = "reason")
	@Mapping(source = "note.reasonAddedBy", target = "reasonAddedBy")
	void updateCashUp(CashUp entity, @MappingTarget CashUpDto dto);

	@Mapping(target = "KPITotal", source = "KPITotal")
	@Mapping(target = "delivery", source = "deliverytotal")
	@Mapping(target = "PDQ", source = "PDQtotal")
	@Mapping(target = "CASH", source = "CASHtotal")
	@Mapping(target = "EPOS", source = "EPOStotal")
	@Mapping(source = "cashreconciliationNote", target = "cashreconciliationNote")
	@Mapping(source = "cardreconciliationNote", target = "cardreconciliationNote")
	@Mapping(source = "thirdPartyreconciliationNote", target = "thirdPartyreconciliationNote")
	@Mapping(target = "cardStatus", source = "cardStatus")
	@Mapping(target = "thirdpartyStatus", source = "thirdpartyStatus")
	void updateCashUp(CashUpDto dto, @MappingTarget CashUp cashUp);

	@Mapping(source = "pettyCashName", target = "pettyCashMaster.name")
	PettyCash convertToPettyCashEntity(PettyCashDto dto);

	@Mapping(source = "name", target = "tillMaster.name")
	@Mapping(source = "match", target = "tillMaster.match")
	@Mapping(source = "partialMatch", target = "tillMaster.partialMatch")
	@Mapping(source = "amount", target = "tillMaster.amount")
	TillSystem convertToTillCashEntity(TillSystemDto dto);

	@Mapping(source = "name", target = "pdqMachineMaster.name")
	@Mapping(source = "cardName", target = "pdqCardMaster.name")
	@Mapping(source = "match", target = "pdqCardMaster.match")
	@Mapping(source = "partialMatch", target = "pdqCardMaster.partialMatch")
	@Mapping(source = "amount", target = "pdqCardMaster.amount")
	@Mapping(source = "apiAmount", target = "pdqCardMaster.apiAmount")
	PDQSystem convertToPdqSystem(PDQSystemDto dto);

	@Mapping(source = "name", target = "thirdPartyInfoMaster.name")
	@Mapping(source = "match", target = "thirdPartyInfoMaster.match")
	@Mapping(source = "partialMatch", target = "thirdPartyInfoMaster.partialMatch")
	@Mapping(source = "amount", target = "thirdPartyInfoMaster.amount")
	@Mapping(source = "apiAmount", target = "thirdPartyInfoMaster.apiAmount")
	ThirdPartyInfo convertToThirdPartyInfo(ThirdPartyInfoDto dto);

	@Mapping(target = "pettyCashName", source = "pettyCashMaster.name")
	PettyCashDto convertToPettyCashDto(PettyCash entity);

	@Mapping(target = "name", source = "tillMaster.name")
	@Mapping(target = "match", source = "tillMaster.match")
	@Mapping(target = "partialMatch", source = "tillMaster.partialMatch")
	@Mapping(target = "amount", source = "tillMaster.amount")
	TillSystemDto convertToTillSystemDto(TillSystem entity);

	@Mapping(target = "name", source = "pdqMachineMaster.name")
	@Mapping(target = "cardName", source = "pdqCardMaster.name")
	@Mapping(target = "match", source = "pdqCardMaster.match")
	@Mapping(target = "partialMatch", source = "pdqCardMaster.partialMatch")
	@Mapping(target = "amount", source = "pdqCardMaster.amount")
	@Mapping(target = "apiAmount", source = "pdqCardMaster.apiAmount")
	PDQSystemDto convertToPdqSystemDto(PDQSystem entity);

	@Mapping(target = "name", source = "thirdPartyInfoMaster.name")
	@Mapping(target = "match", source = "thirdPartyInfoMaster.match")
	@Mapping(target = "partialMatch", source = "thirdPartyInfoMaster.partialMatch")
	@Mapping(target = "amount", source = "thirdPartyInfoMaster.amount")
	@Mapping(target = "apiAmount", source = "thirdPartyInfoMaster.apiAmount")
	ThirdPartyInfoDto convertToThirdPartyInfoDto(ThirdPartyInfo entity);

	BankingInfo convertToBankingEntity(BankingInfoDto dto);

	void updateBanking(BankingInfoDto bankingInfoDto, @MappingTarget BankingInfo bankingInfo);

	List<BankingInfoDto> convertToBankingDto(List<BankingInfo> list);

//	@Mapping(source = "cashUpDates" , target = "cashUps.cashUpDate")
	BankingInfoDto convertToBankingDto(BankingInfo bankingInfo);

	BankingInfoDto convertToBankingDto(Optional<BankingInfo> bankingInfo);

	CashUpDto convertToCashUpDto(Optional<CashUp> cashUp);

	BankingReconcilationDto convertToReconciliationEntity(BankingInfo bankingInfo);

	BankingInfo convertToReconciliationDto(BankingReconcilationDto bankingInfo);

	@Mapping(target = "note", ignore = true)
	CashReconciliationDto convertToCashDto(CashUp cashUp);

	@Mapping(target = "note", ignore = true)
	CashUp convertToCashEntity(CashReconciliationDto cashUp);

	void getBanking(List<BankingInfoDto> bankingInfoDto, @MappingTarget List<BankingInfo> bankingInfo);

}
