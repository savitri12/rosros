/**
 * 
 */
package com.ros.accounting.cashup.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.accounting.cashup.controller.dto.BankingInfoDto;
import com.ros.accounting.cashup.controller.dto.BankingReconcilationDto;
import com.ros.accounting.cashup.controller.dto.CardReconciliationInfoDto;
import com.ros.accounting.cashup.controller.dto.CardReconciliationLogDto;
import com.ros.accounting.cashup.controller.dto.CashReconciliationDto;
import com.ros.accounting.cashup.controller.dto.CashReconciliationInfoDto;
import com.ros.accounting.cashup.controller.dto.CashReconciliationLogDto;
import com.ros.accounting.cashup.controller.dto.CashUpDateTimeDto;
import com.ros.accounting.cashup.controller.dto.CashUpDto;
import com.ros.accounting.cashup.controller.dto.CashnPDQDto;
import com.ros.accounting.cashup.controller.dto.PDQSystemDto;
import com.ros.accounting.cashup.controller.dto.ReconciliationInfoDto;
import com.ros.accounting.cashup.controller.dto.ReconciliationLogDto;
import com.ros.accounting.cashup.controller.dto.ThirdPartyInfoDto;
import com.ros.accounting.cashup.controller.dto.ThirdPartyReconciliationInfoDto;
import com.ros.accounting.cashup.controller.dto.ThirdPartyReconciliationLogDto;
import com.ros.accounting.cashup.controller.dto.TillSystemDto;
import com.ros.accounting.cashup.exceptions.cashUpNotFoundException;
import com.ros.accounting.cashup.mapper.RestDtoMapper;
import com.ros.accounting.cashup.model.BankingInfo;
import com.ros.accounting.cashup.model.BankingStatus;
import com.ros.accounting.cashup.model.CashUp;
import com.ros.accounting.cashup.model.CashUpStatus;
import com.ros.accounting.cashup.model.PDQSystem;
import com.ros.accounting.cashup.model.PettyCash;
import com.ros.accounting.cashup.model.ThirdPartyInfo;
import com.ros.accounting.cashup.model.TillSystem;
import com.ros.accounting.cashup.repository.BankingInfoRepository;
import com.ros.accounting.cashup.repository.CashUpRepository;
import com.ros.accounting.cashup.service.CashUpService;
import com.ros.accounting.cashup.service.ReconciliationService;
import com.ros.accounting.log.RosLogDebug;
import com.ros.accounting.util.Properties;

/**
 * @author Ayush
 *
 */

@Service
public class ReconciliationServiceImpl implements ReconciliationService {

	@Autowired
	private RestDtoMapper restDtoMapper;

	@Autowired
	private CashUpRepository cashUpRepo;

	@Autowired
	private BankingInfoRepository bankingRepo;

	@Autowired
	private CashUpService cashUpService;

	public static String cardContainsDate(List<CardReconciliationLogDto> list, Date id) {
		int i = 0;
		for (CardReconciliationLogDto object : list) {
			if (object.getDate().equals(id)) {
				return object.getCardName() + "_" + String.valueOf(i);
			}
			i = i + 1;
		}
		return "_";
	}

//
	public static String thirdPartyContainsDate(List<ThirdPartyReconciliationLogDto> list, Date id) {
		int i = 0;
		for (ThirdPartyReconciliationLogDto object : list) {
			if (object.getCashUpDate().equals(id)) {
				return object.getThirdPartyName() + "_" + String.valueOf(i);
			}
			i = i + 1;
		}
		return "_";
	}
//
//	public static String cashContainsDate(List<CashReconciliationLogDto> list, Date id) {
//		int i = 0;
//		for (CashReconciliationLogDto object : list) {
//			if (object.getDate().equals(id)) {
//				return String.valueOf(object.getBankedTotal()) + "_" + String.valueOf(i);
//			}
//			i = i + 1;
//		}
//		return "_";
//	}

	@Override
	public List<ReconciliationLogDto> reconcileCashUps(List<ReconciliationLogDto> reconciliationLogs)
			throws cashUpNotFoundException {

		return null;
	}

	@Override
	public ReconciliationLogDto reconcileCashUp(ReconciliationLogDto reconciliationLog) throws cashUpNotFoundException {

		return null;
	}

	@Override
	public List<ReconciliationLogDto> getCashUps(CashUpStatus status, Date fromDate, Date toDate)
			throws cashUpNotFoundException {

		return null;
	}

	@Override
	public ReconciliationInfoDto getPendingReconciliationSummary(Date fromDate, Date toDate, UUID restuarantId)
			throws cashUpNotFoundException {
		List<CashUp> cashUps = new ArrayList<>();
		List<BankingInfo> bankingSheets = new ArrayList<>();
		List<CashUp> cashUpSheets = new ArrayList<>();
		ReconciliationInfoDto reconciliationInfoDto = new ReconciliationInfoDto();
		float cashtotal = 0;
		float cardtotal = 0;
		float thirdpartytotal = 0;
		cashUps = cashUpRepo.findReconciliationSheets(fromDate, toDate, restuarantId);
//		bankInfos = cashUpRepo.findBankingSheets(fromDate, toDate,restuarantId);
//		bankingSheets = bankingRepo.findReconciliationSheets(fromDate, toDate);
		bankingSheets = bankingRepo.findBankingSheets(fromDate, toDate, restuarantId);
//		cashUpSheets = cashUpRepo.findDatewiseReconciledForRestaurant(restuarantId);
		int pendingCash = 0;
		int pendingCard = 0;
		int pendingThirdParty = 0;

//		for (BankingInfo bankingInfo : bankInfos) {
//			if(bankingInfo!=null) {
		for (BankingInfo bankingInfo : bankingSheets) {
			if (bankingInfo != null) {
				if (bankingInfo.getReconcileStatus() != null) {

					if (bankingInfo.getReconcileStatus().equals(BankingStatus.PENDING)) {
						pendingCash++;
						cashtotal += bankingInfo.getBankedTotal();
					}
				}
			}
		}
		for (CashUp cashUp : cashUps) {
//			if (!cashUp.getCashStatus().equals("RECONCILED") && cashUp.getCashUpStatus().equals(CashUpStatus.BANKED)) {
//				pendingCash++;
//				System.out.println(cashUp.getCashUpStatus());
//				if (cashUp.getCashnPdq() != null) {
//					if (cashUp.getCashnPdq().getTillSystems() != null) {
//
//						for (TillSystem tillSystemDto : cashUp.getCashnPdq().getTillSystems()) {
//							cashtotal += tillSystemDto.getTillMaster().getAmount();
//						}
//					}
//				}
//			}
			if (!cashUp.getCardStatus().equals("RECONCILED") && !cashUp.getCashUpStatus().equals(CashUpStatus.DRAFT)) {
				pendingCard++;

				if (cashUp.getCashnPdq() != null) {
					if (cashUp.getCashnPdq().getPdqSystems() != null) {
						float prevcardTotal = cardtotal;
						for (PDQSystem pdqSystem : cashUp.getCashnPdq().getPdqSystems()) {
							cardtotal += pdqSystem.getPdqCardMaster().getAmount();
							System.out.println("card total is " + cardtotal);
						}
						if (prevcardTotal == cardtotal) {
							pendingCard--;
						}

					}
				}
			}

			if (!cashUp.getThirdpartyStatus().equals("RECONCILED")
					&& !cashUp.getCashUpStatus().equals(CashUpStatus.DRAFT)) {
				pendingThirdParty++;

				if (cashUp.getThirdPartyInfo() != null) {
					float prevThirdPartyTotal = thirdpartytotal;
					for (ThirdPartyInfo thirdParty : cashUp.getThirdPartyInfo()) {
						thirdpartytotal += thirdParty.getThirdPartyInfoMaster().getAmount();
						System.out.println("third party total is " + thirdpartytotal);
					}
					if (prevThirdPartyTotal == thirdpartytotal) {
						pendingThirdParty--;
					}
				}
			}
		}

		reconciliationInfoDto.setCashtotals(cashtotal);
		reconciliationInfoDto.setCardtotals(cardtotal);
		reconciliationInfoDto.setThirdpartytotals(thirdpartytotal);
		reconciliationInfoDto.setPendingCash(pendingCash);
		reconciliationInfoDto.setPendingCard(pendingCard);
		reconciliationInfoDto.setPendingThirdParty(pendingThirdParty);
		return reconciliationInfoDto;
	}

	@Override
	public ReconciliationInfoDto getReconciledSummary(Date fromDate, Date toDate, UUID restuarantId)
			throws cashUpNotFoundException {
		List<CashUp> cashUps = new ArrayList<>();
		List<BankingInfo> bankingSheets = new ArrayList<>();
		ReconciliationInfoDto reconciliationInfoDto = new ReconciliationInfoDto();
		float reconciledTotal = 0;
		float cashtotal = 0;
		float cardtotal = 0;
		float thirdpartytotal = 0;
		cashUps = cashUpRepo.findReconciliationSheets(fromDate, toDate, restuarantId);
		bankingSheets = bankingRepo.findBankingSheets(fromDate, toDate, restuarantId);

		for (BankingInfo bankingInfo : bankingSheets) {
			if (bankingInfo.getReconcileStatus() != null) {
				if (bankingInfo.getReconcileStatus().equals(BankingStatus.RECONCILED)) {
					if (bankingInfo.getBankedTotal() != 0) {
						cashtotal += bankingInfo.getBankedTotal();
					}
				}
			}
		}

		for (CashUp cashUp : cashUps) {
			if (cashUp.getCashnPdq() != null) {
//				if (cashUp.getCashStatus().equals("RECONCILED")) {
//					if (cashUp.getCashnPdq().getTillSystems() != null) {
//						for (TillSystem tillSystemDto : cashUp.getCashnPdq().getTillSystems()) {
//							cashtotal += tillSystemDto.getTillMaster().getAmount();
//
//						}
//					}
//				}
				if (cashUp.getCashnPdq().getPdqSystems() != null) {
					if (cashUp.getCardStatus().equals("RECONCILED")) {
						for (PDQSystem pdqSystem : cashUp.getCashnPdq().getPdqSystems()) {
							cardtotal += pdqSystem.getPdqCardMaster().getAmount();
						}
					}
				}
			}
			if (cashUp.getThirdPartyInfo() != null) {
				if (cashUp.getThirdpartyStatus().equals("RECONCILED")) {
					for (ThirdPartyInfo thirdParty : cashUp.getThirdPartyInfo()) {
						thirdpartytotal += thirdParty.getThirdPartyInfoMaster().getAmount();
					}
				}
			}
		}
		reconciledTotal = cashtotal + cardtotal + thirdpartytotal;

		reconciliationInfoDto.setCashtotals(cashtotal);
		reconciliationInfoDto.setCardtotals(cardtotal);
		reconciliationInfoDto.setThirdpartytotals(thirdpartytotal);
		reconciliationInfoDto.setReconciledTotal(reconciledTotal);

		return reconciliationInfoDto;
	}

	@Override
	public ReconciliationLogDto getReconciliationLog(Date fromDate, Date toDate, UUID restuarantId, String reconType)
			throws cashUpNotFoundException {
		List<CashUp> cashUps = new ArrayList<>();
		List<BankingInfo> bankInfos = new ArrayList<>();
		List<CashReconciliationLogDto> cashreconDto = new ArrayList<>();
		List<CardReconciliationLogDto> cardreconDto = new ArrayList<>();
		List<ThirdPartyReconciliationLogDto> thirdPartyreconDto = new ArrayList<>();
		ReconciliationLogDto reconciliationLogDto = new ReconciliationLogDto();

		if (reconType.equals("card") || reconType.equals("thirdparty")) {
			cashUps = cashUpRepo.findReconciliationSheets(fromDate, toDate, restuarantId);

			for (CashUp cashUp : cashUps) {
				if (cashUp.getCashUpStatus().equals(CashUpStatus.PUBLISHED)
						|| cashUp.getCashUpStatus().equals(CashUpStatus.RECONCILED)
						|| cashUp.getCashUpStatus().equals(CashUpStatus.BANKED)) {

					if (reconType.equals("card")) {
						for (PDQSystem pdqSystem : cashUp.getCashnPdq().getPdqSystems()) {
							if (pdqSystem.getPdqCardMaster().getAmount() > 0) {
								CardReconciliationLogDto cardItem = new CardReconciliationLogDto();
								cardItem.setDate(cashUp.getCashUpDate());
//							if (cashUp.getCashUpStatus().equals(CashUpStatus.PUBLISHED))
//							{
//								cardItem.setSheetStatus("PENDING");
//							}
//							else {
//								cardItem.setSheetStatus("RECONCILED");
//							}
								String cardContainsInfo = cardContainsDate(cardreconDto, cashUp.getCashUpDate());

								if (cardContainsInfo != "_") {
									String name = cardContainsInfo.split("_")[0];
									int nameIndex = Integer.valueOf(cardContainsInfo.split("_")[1]);
									name += "," + pdqSystem.getPdqCardMaster().getName();

									String status = cardreconDto.get(nameIndex).getSheetStatus();
									if (cashUp.getCashUpStatus().equals(CashUpStatus.PUBLISHED)) {
										cardItem.setSheetStatus("PENDING");
									} else {
										cardItem.setSheetStatus(status);
									}

									float totalAmount = cardreconDto.get(nameIndex).getTotalAmout();
									totalAmount += pdqSystem.getPdqCardMaster().getAmount();

									float totalApiAmount = cardreconDto.get(nameIndex).getTotalAmout();
									totalApiAmount += pdqSystem.getPdqCardMaster().getApiAmount();

									List<CashUpDateTimeDto> cashupids = cardreconDto.get(nameIndex).getCashupIds();
									CashUpDateTimeDto presentCashupId = new CashUpDateTimeDto();
									presentCashupId.setCashUpTimeIndicator(cashUp.getCashUpTimeIndicator());
									presentCashupId.setId(cashUp.getId());
									cashupids.add(presentCashupId);

									cardreconDto.remove(nameIndex);
									cardItem.setSheetStatus(cashUp.getCardStatus());
									cardItem.setCardName(name);
									cardItem.setCashupIds(cashupids);
									cardItem.setTotalAmout(totalAmount);
									cardItem.setTotalApiAmout(totalApiAmount);
								} else {
									cardItem.setCardName(pdqSystem.getPdqCardMaster().getName());

									float totalAmount = 0;
									totalAmount += pdqSystem.getPdqCardMaster().getAmount();

									float totalApiAmount = 0;
									totalApiAmount += pdqSystem.getPdqCardMaster().getApiAmount();

									List<CashUpDateTimeDto> cashupids = new ArrayList<>();
									CashUpDateTimeDto presentCashupId = new CashUpDateTimeDto();
									presentCashupId.setCashUpTimeIndicator(cashUp.getCashUpTimeIndicator());
									presentCashupId.setId(cashUp.getId());
									cashupids.add(presentCashupId);

									cardItem.setCashupIds(cashupids);
									cardItem.setTotalAmout(totalAmount);
									cardItem.setTotalApiAmout(totalApiAmount);

									if (cashUp.getCashUpStatus().equals(CashUpStatus.PUBLISHED)) {
										cardItem.setSheetStatus("PENDING");
									} else {
										cardItem.setSheetStatus("RECONCILED");
									}

								}
								cardreconDto.add(cardItem);
							}

						}
					}

					else if (reconType.equals("thirdparty")) {
						for (ThirdPartyInfo thirdParty : cashUp.getThirdPartyInfo()) {
							if (thirdParty.getThirdPartyInfoMaster().getAmount() > 0) {
								ThirdPartyReconciliationLogDto thirdPartyItem = new ThirdPartyReconciliationLogDto();

								thirdPartyItem.setCashUpDate(cashUp.getCashUpDate());

								String thirdPartyContainsInfo = thirdPartyContainsDate(thirdPartyreconDto,
										cashUp.getCashUpDate());

								if (thirdPartyContainsInfo != "_") {
									String name = thirdPartyContainsInfo.split("_")[0];
									int nameIndex = Integer.valueOf(thirdPartyContainsInfo.split("_")[1]);
									name += "," + thirdParty.getThirdPartyInfoMaster().getName();

									String status = thirdPartyreconDto.get(nameIndex).getSheetStatus();
									if (cashUp.getCashUpStatus().equals(CashUpStatus.PUBLISHED)) {
										thirdPartyItem.setSheetStatus("PENDING");
									} else {
										thirdPartyItem.setSheetStatus(status);
									}

									float totalAmount = thirdPartyreconDto.get(nameIndex).getTotalAmount();
									totalAmount += thirdParty.getThirdPartyInfoMaster().getAmount();

									float totalApiAmount = thirdPartyreconDto.get(nameIndex).getTotalApiAmount();
									totalApiAmount += thirdParty.getThirdPartyInfoMaster().getApiAmount();

									List<CashUpDateTimeDto> cashupids = thirdPartyreconDto.get(nameIndex)
											.getCashupIds();
									CashUpDateTimeDto presentCashupId = new CashUpDateTimeDto();
									presentCashupId.setCashUpTimeIndicator(cashUp.getCashUpTimeIndicator());
									presentCashupId.setId(cashUp.getId());
									cashupids.add(presentCashupId);
									thirdPartyreconDto.remove(nameIndex);
									thirdPartyItem.setThirdPartyName(name);
									thirdPartyItem.setSheetStatus(cashUp.getThirdpartyStatus());
									thirdPartyItem.setCashupIds(cashupids);
									thirdPartyItem.setTotalAmount(totalAmount);
									thirdPartyItem.setTotalApiAmount(totalApiAmount);

								} else {
									thirdPartyItem.setThirdPartyName(thirdParty.getThirdPartyInfoMaster().getName());

									if (cashUp.getCashUpStatus().equals(CashUpStatus.PUBLISHED)) {
										thirdPartyItem.setSheetStatus("PENDING");
									} else {
										thirdPartyItem.setSheetStatus("RECONCILED");
									}

									float totalAmount = 0;
									totalAmount += thirdParty.getThirdPartyInfoMaster().getAmount();

									float totalApiAmount = 0;
									totalApiAmount += thirdParty.getThirdPartyInfoMaster().getApiAmount();

									List<CashUpDateTimeDto> cashupids = new ArrayList<>();
									CashUpDateTimeDto presentCashupId = new CashUpDateTimeDto();
									presentCashupId.setCashUpTimeIndicator(cashUp.getCashUpTimeIndicator());
									presentCashupId.setId(cashUp.getId());
									cashupids.add(presentCashupId);
									thirdPartyItem.setSheetStatus(cashUp.getThirdpartyStatus());
									thirdPartyItem.setCashupIds(cashupids);
									thirdPartyItem.setTotalAmount(totalAmount);
									thirdPartyItem.setTotalApiAmount(totalApiAmount);
								}

								thirdPartyreconDto.add(thirdPartyItem);
							}
						}
					}

				}

			}
			reconciliationLogDto.setCardreconDto(cardreconDto);
			reconciliationLogDto.setThirdPartyreconDto(thirdPartyreconDto);

		} else if (reconType.equals("cash")) {
			cashUps = cashUpRepo.findReconciliationSheets(fromDate, toDate, restuarantId);
			bankInfos = bankingRepo.findBankingSheets(fromDate, toDate, restuarantId);
//			List<CashUp> cashUpDates = new ArrayList<>();

			System.out.println("No of banking Sheets: " + bankInfos.size());
			for (BankingInfo bankingInfo : bankInfos) {

				CashReconciliationLogDto cashItem = new CashReconciliationLogDto();
				cashItem.setId(bankingInfo.getId());
				cashItem.setDate(bankingInfo.getBankingDate());
				cashItem.setReconcileStatus(bankingInfo.getReconcileStatus());

				System.out.println("Reconcilation Date: " + cashItem.getDate());
				cashItem.setTotalAmount(bankingInfo.getBankedTotal());

				cashreconDto.add(cashItem);

			}
			reconciliationLogDto.setCashreconDto(cashreconDto);

		}
		return reconciliationLogDto;
	}

	@Override
	@RosLogDebug
	public CashUpDto cardReconciliation(CardReconciliationInfoDto cards) throws cashUpNotFoundException {

		System.out.println(cards);

		CashUp cashUp = cashUpRepo.getOne(cards.getId());

		if (cashUp.getCashUpStatus().equals(CashUpStatus.PUBLISHED)
				|| cashUp.getCashUpStatus().equals(CashUpStatus.BANKED)) {
			CashUpDto dto = restDtoMapper.convertToCashUpDto(cashUp);
			cashUpService.checkNullData(dto);
			CashnPDQDto cashnpdq = dto.getCashnPdq();
			String thirdpartyStatus = cashUp.getThirdpartyStatus();
			String cashStatus = cashUp.getCashStatus();
			List<PDQSystemDto> pdqList = new ArrayList<>();
			for (PDQSystemDto pdq : cashnpdq.getPdqSystems()) {
				for (PDQSystemDto card : cards.getCards()) {
					if (pdq.getId().equals(card.getId())) {
						pdq.setMatch(card.isMatch());
						pdq.setPartialMatch(card.isPartialMatch());

					}
				}
				pdqList.add(pdq);
			}
			if (thirdpartyStatus.equals("RECONCILED") && cashStatus.equals("RECONCILED")) {
				dto.setCashUpStatus(CashUpStatus.RECONCILED);
			}
//			dto.setCardStatus("RECONCILED");
			cashnpdq.setPdqSystems(pdqList);
			dto.setCashnPdq(cashnpdq);
			dto.setCardreconciliationNote(cards.getCardreconciliationNote());
			restDtoMapper.updateCashUp(dto, cashUp);
			cashUp.setCardStatus("RECONCILED");
			cashUp.setCardreconciliationNote(cards.getCardreconciliationNote());
			return restDtoMapper.convertToCashUpDto(cashUpRepo.save(cashUp));
		} else
			throw new cashUpNotFoundException(Properties.reconciliationNotPossible);
	}

	private BankingInfo updateBankingInfo(BankingReconcilationDto bankingInfoDto, BankingInfo banking)
			throws cashUpNotFoundException {
		List<CashUp> cashUps = new ArrayList<>();
		List<CashReconciliationDto> cashReconciliationDtos = bankingInfoDto.getCashUps();
		// Add all the cashUps present in a Banking Sheets
		for (CashUp cashUp : banking.getCashUps()) {
			cashUps.add(cashUp);
		}

		if (cashUps.size() != 0) {
			for (CashUp cashUp : cashUps) {
				for (CashReconciliationDto cashReconciliationDto : cashReconciliationDtos) {
					if (cashUp.getId().equals(cashReconciliationDto.getId())) {
						cashReconciliationDto.setId(cashUp.getId());
						cashUp.setMatch(cashReconciliationDto.isMatch());
						cashUp.setPartialMatch(cashReconciliationDto.isPartialMatch());
//						cashReconciliationDtos.add(cashReconciliationDto);
					}
				}
				cashUp.setCashStatus("RECONCILED");
				if (cashUp.getCardStatus().equals("RECONCILED") && cashUp.getThirdpartyStatus().equals("RECONCILED"))
					cashUp.setCashUpStatus(CashUpStatus.RECONCILED);
			}
//			bankingInfoDto.setCashUps(cashReconciliationDtos);
			banking.setNote(bankingInfoDto.getNote());
			banking.setReconcileStatus(BankingStatus.RECONCILED);
			banking.setCashUps(cashUps);
			return banking;
		} else
			throw new cashUpNotFoundException(Properties.reconciliationNotPossible);
	}

	@Override
	public BankingReconcilationDto cashReconcilied(BankingReconcilationDto bankingInfoDto)
			throws cashUpNotFoundException {

		BankingInfo banking = bankingRepo.getOne(bankingInfoDto.getId());
//		bankingInfoDto.setDate(banking.getBankingDate());
		System.out.println("Banking Date: " + banking.getBankingDate());
		BankingInfo bankingInfo = updateBankingInfo(bankingInfoDto, banking);
		BankingReconcilationDto bankinInfoDto = restDtoMapper
				.convertToReconciliationEntity(bankingRepo.save(bankingInfo));
		bankingInfoDto = updateCashUpinfo(bankingInfo, bankinInfoDto);

		return bankingInfoDto;

	}

	private BankingReconcilationDto updateCashUpinfo(BankingInfo bankingInfo, BankingReconcilationDto bankingInfoDto) {
		List<CashReconciliationDto> cashUpDates = new ArrayList<>();
		bankingInfoDto.setBankingDate(bankingInfo.getBankingDate());

		for (CashUp cashUp : bankingInfo.getCashUps()) {
			float totalCash = calculateTotalCash(cashUp.getId());
			CashReconciliationDto cashReconciliationDto = new CashReconciliationDto();
			cashReconciliationDto.setId(cashUp.getId());
			cashReconciliationDto.setCashUpDate(cashUp.getCashUpDate());
			cashReconciliationDto.setCashUpTimeIndicator(cashUp.getCashUpTimeIndicator());
			cashReconciliationDto.setCashTotal(totalCash);
			cashReconciliationDto.setMatch(cashUp.isMatch());
			cashReconciliationDto.setPartialMatch(cashUp.isPartialMatch());
			cashReconciliationDto.setCashStatus(cashUp.getCashStatus());
			cashReconciliationDto.setCardStatus(cashUp.getCardStatus());
			cashReconciliationDto.setThirdpartyStatus(cashUp.getThirdpartyStatus());
			cashUpDates.add(cashReconciliationDto);

		}
		bankingInfoDto.getNote();
		bankingInfoDto.setCashUps(cashUpDates);
		return bankingInfoDto;

	}

//	@Override
//	public CashUpDto cashReconciliation(CashReconciliationInfoDto cash) throws cashUpNotFoundException {
//		CashUp cashUp = cashUpRepo.getOne(cash.getId());
//
//		if (cashUp.getCashUpStatus().equals(CashUpStatus.BANKED)) {
//			CashUpDto dto = restDtoMapper.convertToCashUpDto(cashUp);
//			cashUpService.checkNullData(dto);
//			CashnPDQDto cashnpdq = dto.getCashnPdq();
//			String thirdpartyStatus = cashUp.getThirdpartyStatus();
//			String cardStatus = cashUp.getCardStatus();
//			List<TillSystemDto> tillList = new ArrayList<>();
//			for (TillSystemDto till : cashnpdq.getTillSystems()) {
//				for (TillSystemDto tills : cash.getCash()) {
//					System.out.println(till.getId());
//					System.out.println(tills.getId());
//					if (till.getId().equals(tills.getId())) {
//						till.setMatch(tills.isMatch());
//						till.setPartialMatch(tills.isPartialMatch());
//					}
//				}
//				tillList.add(till);
//			}
//			if (thirdpartyStatus.equals("RECONCILED") && cardStatus.equals("RECONCILED")) {
//				dto.setCashUpStatus(CashUpStatus.RECONCILED);
//			}
//			dto.setCashStatus("RECONCILED");
//
//			cashnpdq.setTillSystems(tillList);
//			dto.setCashnPdq(cashnpdq);
//			dto.setCashreconciliationNote(cash.getCashreconciliationNote());
//			System.out.println(dto);
//
//			restDtoMapper.updateCashUp(dto, cashUp);
//			System.out.println("step last cashup");
//			System.out.println(cashUp);
//			return restDtoMapper.convertToCashUpDto(cashUpRepo.save(cashUp));
//		} else
//			throw new cashUpNotFoundException(Properties.reconciliationNotPossible);
//	}

	@Override
	@RosLogDebug
	public CashUpDto thirdPartyReconciliation(ThirdPartyReconciliationInfoDto thirdParty)
			throws cashUpNotFoundException {

		CashUp cashUp = cashUpRepo.getOne(thirdParty.getId());

		if (cashUp.getCashUpStatus().equals(CashUpStatus.PUBLISHED)
				|| cashUp.getCashUpStatus().equals(CashUpStatus.BANKED)) {
			CashUpDto dto = restDtoMapper.convertToCashUpDto(cashUp);
			cashUpService.checkNullData(dto);
			String cardStatus = cashUp.getCardStatus();
			String cashStatus = cashUp.getCashStatus();
			List<ThirdPartyInfoDto> thirdparty = dto.getThirdPartyInfo();
			List<ThirdPartyInfoDto> tpList = new ArrayList<>();
			for (ThirdPartyInfoDto tparty : thirdparty) {
				for (ThirdPartyInfoDto thirdpartydto : thirdParty.getThirdParty()) {
					if (tparty.getId().equals(thirdpartydto.getId())) {
						tparty.setMatch(thirdpartydto.isMatch());
						tparty.setPartialMatch(thirdpartydto.isPartialMatch());
					}
				}

				tpList.add(tparty);
			}
			dto.setThirdpartyStatus("RECONCILED");
			System.out.println(cardStatus);
			if (cardStatus.equals("RECONCILED") && cashStatus.equals("RECONCILED")) {
				System.out.println("inside cashup");
//				cashUp.setCashUpStatus(CashUpStatus.RECONCILED);
				dto.setCashUpStatus(CashUpStatus.RECONCILED);
			}
			dto.setThirdPartyInfo(thirdparty);
			dto.setThirdPartyreconciliationNote(thirdParty.getThirdPartyreconciliationNote());
//			dto.setThirdpartyStatus("RECONCILED");
			restDtoMapper.updateCashUp(dto, cashUp);
			cashUp.setThirdpartyStatus("RECONCILED");
			System.out.println("step last cashup");
			CashUp cashup = cashUpRepo.save(cashUp);
			System.out.println("completed saving");
			return restDtoMapper.convertToCashUpDto(cashup);

		} else
			throw new cashUpNotFoundException(Properties.reconciliationNotPossible);
	}

	@Override
	public List<PDQSystemDto> getCardSheetsToReconcile(UUID id) throws cashUpNotFoundException {
		CashUp cashUp = cashUpRepo.getOne(id);
		List<PDQSystemDto> pdqcard = new ArrayList<>();
		if (cashUp.getCashnPdq() != null) {
			for (PDQSystem pdq : cashUp.getCashnPdq().getPdqSystems()) {
				PDQSystemDto pdqItem = new PDQSystemDto();
				pdqItem.setId(pdq.getId());
				pdqItem.setCardName(pdq.getPdqCardMaster().getName());
				pdqItem.setAmount(pdq.getPdqCardMaster().getAmount());
				pdqItem.setApiAmount(pdq.getPdqCardMaster().getApiAmount());
				pdqItem.setMatch(pdq.getPdqCardMaster().isMatch());
				pdqItem.setPartialMatch(pdq.getPdqCardMaster().isPartialMatch());
				pdqItem.setCardNote(cashUp.getCardreconciliationNote());
				pdqcard.add(pdqItem);
			}

		}
		return pdqcard;
	}

	@Override
	public BankingReconcilationDto getCashSheetsToReconcile(UUID id) throws cashUpNotFoundException {
		BankingInfo bankingInfo = bankingRepo.getOne(id);
		BankingInfoDto bankingInfoDto = restDtoMapper.convertToBankingDto(bankingInfo);
		List<TillSystemDto> tills = new ArrayList<>();
		BankingReconcilationDto bankingReconcilationDto = new BankingReconcilationDto();
		List<CashReconciliationDto> cashReconciliationDtos = new ArrayList<>();
		Set<CashUp> cashUpDates = new HashSet<>();
		bankingReconcilationDto.setId(bankingInfo.getId());

		/*
		 * Store the Unique cashUp sheets based on dates
		 */
//		int noOfsheet = 0;
//		for (CashUp cashUp : bankingInfo.getCashUps()) {
//			Date date = cashUp.getCashUpDate();
//			if (cashUp.getCashUpDate().equals(date)) {
//				noOfsheet += 1;
//				if (noOfsheet == 1) {
//					cashUpDates.add(cashUp);
//				}
//			}
//			noOfsheet -= 1;
//		}

		for (CashUp cashUp : bankingInfo.getCashUps()) {
//			float totalCash = calculateTotalCash(cashUp.getCashUpDate());
			float totalCash = calculateTotalCash(cashUp.getId());
			System.out.println(totalCash);

			CashReconciliationDto cashReconciliationDto = new CashReconciliationDto();
			cashReconciliationDto.setId(cashUp.getId());
			cashReconciliationDto.setCashUpDate(cashUp.getCashUpDate());
			cashReconciliationDto.setCashUpTimeIndicator(cashUp.getCashUpTimeIndicator());
			cashReconciliationDto.setCashTotal(totalCash);
			cashReconciliationDto.setMatch(cashUp.isMatch());
			cashReconciliationDto.setPartialMatch(cashUp.isPartialMatch());
			cashReconciliationDto.setCashStatus(cashUp.getCashStatus());
			cashReconciliationDtos.add(cashReconciliationDto);

			bankingReconcilationDto.setCashUps(cashReconciliationDtos);
			bankingReconcilationDto.setNote(bankingInfo.getNote());
			bankingReconcilationDto.setBankingDate(bankingInfo.getBankingDate());
			bankingReconcilationDto.setReconcileStatus(bankingInfo.getReconcileStatus());
			bankingReconcilationDto.setBankingTotal(bankingInfo.getBankingTotal());
			bankingReconcilationDto.setBankedTotal(bankingInfo.getBankedTotal());

		}
		return bankingReconcilationDto;

	}

	private float calculateTotalCash(UUID id) {

//		List<CashUp> cashUps = new ArrayList<>();
		float pettyCashTotal = 0;
		float tillTotal = 0;
		float cashTotal = 0;
//		int cashUpSheet = 0;
		CashUp cashUp = new CashUp();
		cashUp = cashUpRepo.getOne(id);

//		cashUps.addAll(cashUpRepo.checkCashUpByDate(cashUpDate));
//		for (CashUp cashUp : cashUps) {
//		for (PettyCash pettyCash : cashUp.getCashnPdq().getPettyCashs()) {
//			pettyCashTotal += pettyCash.getAmount();
//		}

		for (TillSystem tillSystem : cashUp.getCashnPdq().getTillSystems()) {
			tillTotal += tillSystem.getTillMaster().getAmount();

		}
//		cashTotal = pettyCashTotal + tillTotal;

//		}
		return tillTotal;
	}

	@Override
	public List<ThirdPartyInfoDto> getThirdPartySheetsToReconcile(UUID id) throws cashUpNotFoundException {
		CashUp cashUp = cashUpRepo.getOne(id);
		List<ThirdPartyInfoDto> tParty = new ArrayList<>();
		if (cashUp.getCashnPdq() != null) {
			for (ThirdPartyInfo tp : cashUp.getThirdPartyInfo()) {
				ThirdPartyInfoDto thirdparty = new ThirdPartyInfoDto();
				thirdparty.setId(tp.getId());
				thirdparty.setName(tp.getThirdPartyInfoMaster().getName());
				thirdparty.setAmount(tp.getThirdPartyInfoMaster().getAmount());
				thirdparty.setApiAmount(tp.getThirdPartyInfoMaster().getApiAmount());
				thirdparty.setMatch(tp.getThirdPartyInfoMaster().isMatch());
				thirdparty.setPartialMatch(tp.getThirdPartyInfoMaster().isPartialMatch());
				thirdparty.setNote(cashUp.getThirdPartyreconciliationNote());
				tParty.add(thirdparty);
			}
		}
		return tParty;
	}

}
