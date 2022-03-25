package com.ros.accounting.cashup.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ros.accounting.cashup.controller.dto.BankingInfoDto;
import com.ros.accounting.cashup.controller.dto.BreakDownDto;
import com.ros.accounting.cashup.controller.dto.CashUpDto;
import com.ros.accounting.cashup.controller.dto.CashnPDQDto;
import com.ros.accounting.cashup.controller.dto.KPIDto;
import com.ros.accounting.cashup.controller.dto.KpiCoverDto;
import com.ros.accounting.cashup.controller.dto.PDQSystemDto;
import com.ros.accounting.cashup.controller.dto.SafeSummaryDto;
import com.ros.accounting.cashup.controller.dto.SalesDto;
import com.ros.accounting.cashup.controller.dto.TaxInfoDto;
import com.ros.accounting.cashup.controller.dto.ThirdPartyInfoDto;
import com.ros.accounting.cashup.controller.dto.TillSystemDto;
import com.ros.accounting.cashup.exceptions.RestaurantNotFoundException;
import com.ros.accounting.cashup.exceptions.cashUpNotFoundException;
import com.ros.accounting.cashup.mapper.RestDtoMapper;
import com.ros.accounting.cashup.model.BankingInfo;
import com.ros.accounting.cashup.model.BankingStatus;
import com.ros.accounting.cashup.model.CashUp;
import com.ros.accounting.cashup.model.CashUpInfoMode;
import com.ros.accounting.cashup.model.CashUpStatus;
import com.ros.accounting.cashup.model.CashUpTimeIndicator;
import com.ros.accounting.cashup.model.PettyCash;
import com.ros.accounting.cashup.model.TillSystem;
import com.ros.accounting.cashup.repository.BankingInfoRepository;
import com.ros.accounting.cashup.repository.CashUpRepository;
import com.ros.accounting.cashup.service.CashUpService;
import com.ros.accounting.log.RosLogDebug;
import com.ros.accounting.util.Properties;
import com.thoughtworks.xstream.io.json.JsonWriter.Format;

@Service
public class CashUpServiceImpl implements CashUpService {

	@Autowired
	private CashUpRepository cashUpRepo;

	@Autowired
	private BankingInfoRepository bankingInfoRepo;

	@Autowired
	private RestDtoMapper restDtoMapper;

	@Value(value = "${uservice.admin.url}")
	public String adminUrl;

	/**
	 * Adding cashup to the database
	 * 
	 * @throws ParseException
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Override
	@RosLogDebug
	public CashUpDto addNewCashUp(CashUpDto cashUp) throws cashUpNotFoundException {
		List<CashUp> cashUps = checkCashUpByDate(cashUp.getCashUpDate());

		int amCount = 0, pmCount = 0, count = 0;

		for (CashUp data : cashUps) {
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//			String s = formatter.format(data.getCashUpDate());
//			System.out.println("Date is: " + s);
//			if(s.equals(data.getCashUpDate())) {
//				count++;
//			}
//			System.out.println("count is: " + count);
//			Date d = formatter.parse(s);
//	        System.out.println("String Date is: "+ d);
//	        DateFormat time = new SimpleDateFormat("hh:mm:ss a");

			if (data.getCashUpTimeIndicator().equals(CashUpTimeIndicator.AM)) {
				amCount++;
				System.out.println("AM: " + amCount);
				System.out.println("CashUp dates: " + data.getCashUpDate());
			} else if (data.getCashUpTimeIndicator().equals(CashUpTimeIndicator.PM)) {
				pmCount++;
			}
		}

		if (amCount < 1 && cashUp.getCashUpTimeIndicator().equals(CashUpTimeIndicator.AM)
				|| pmCount < 1 && cashUp.getCashUpTimeIndicator().equals(CashUpTimeIndicator.PM)) {
			if (cashUps.size() < 2) {
				return saveCashUpData(cashUp);
			} else
				throw new cashUpNotFoundException(Properties.cashUpCreationNotPossible);

		} else
			throw new cashUpNotFoundException(Properties.cashUpCreationNotPossible);
	}

	private CashUpDto saveCashUpData(CashUpDto cashUpDto) {

		calculateTotal(cashUpDto);
		CashUp cashUpEntity = restDtoMapper.convertToCashUpEntity(cashUpDto);
		cashUpEntity.addMetaData(cashUpDto.getCreatedBy());

		updateManualEntry(cashUpEntity);

		return restDtoMapper.convertToCashUpDto(cashUpRepo.save(cashUpEntity));
	}

	private void calculateTotal(CashUpDto cashUp) {

		float eposTotal = 0, cashTotal = 0, pdqTotal = 0, deliveryTotal = 0, KPItotal = 0;

		float voidTotal = 0, breakDownTotal = 0;

		for (SalesDto sale : cashUp.getSales()) {
			eposTotal = sale.getCreditCardTip() + sale.getDrinksPayment() + sale.getFoodPayment()
					+ sale.getOtherPayment() + sale.getServiceCharges() + sale.getTakeAwayPayment();
			for (TaxInfoDto taxInfo : sale.getTaxInfo()) {
				eposTotal = eposTotal + taxInfo.getAmount();
			}
		}
		cashUp.setEPOStotal(eposTotal);
		if (cashUp.getCashnPdq() != null || cashUp.getCashnPdq().getTillSystems() != null) {

			for (TillSystemDto tillSystemDto : cashUp.getCashnPdq().getTillSystems()) {
				cashTotal += tillSystemDto.getAmount();

			}
			cashUp.setCASHtotal(cashTotal);
		}
		for (PDQSystemDto pdqSystemDto : cashUp.getCashnPdq().getPdqSystems()) {
			pdqTotal += pdqSystemDto.getAmount();

		}
		cashUp.setPDQtotal(pdqTotal);

		for (ThirdPartyInfoDto thirdPartyInfoDto : cashUp.getThirdPartyInfo()) {
			deliveryTotal = deliveryTotal + thirdPartyInfoDto.getAmount();

		}
		cashUp.setDeliverytotal(deliveryTotal);

		for (KpiCoverDto kpiCoverDto : cashUp.getKpi().getKpiCovers()) {
			voidTotal = KPItotal + kpiCoverDto.getAmount();

		}

		for (BreakDownDto breakDownDto : cashUp.getKpi().getBreakDownDetails()) {
			breakDownTotal = KPItotal + breakDownDto.getAmount();

		}
		KPItotal = voidTotal + breakDownTotal;
		cashUp.setKPITotal(KPItotal);

	}

	private void updateManualEntry(CashUp cashUpEntity) {
		cashUpEntity.getSales().forEach(sales -> {
			sales.setCashUpInfoMode(CashUpInfoMode.MANUAL);
		});
		cashUpEntity.getCashnPdq().setCashUpInfoMode(CashUpInfoMode.MANUAL);
		cashUpEntity.getThirdPartyInfo().forEach(info -> {
			info.setCashUpInfoMode(CashUpInfoMode.MANUAL);
		});
	}

	private List<CashUp> checkCashUpByDate(Date cashUpDate) {
		return cashUpRepo.checkCashUpByDate(cashUpDate);
	}

//	private void updateEntityData(CashUpDto cashUp, CashUp cashUpEntity) {
//		for (PettyCashDto pettyCashDto : cashUp.getCashnPdq().getPettyCashs()) {
//			PettyCash pettyCash = restDtoMapper.convertToPettyCashEntity(pettyCashDto);
//			pettyCash.getPettyCashMaster().setName(pettyCashDto.getPettyCashName());
//		}
//		for (TillSystemDto tillDto : cashUp.getCashnPdq().getTillSystems()) {
//			TillSystem till = restDtoMapper.convertToTillCashEntity(tillDto);
//			till.getTillMaster().setName(tillDto.getName());
//		}
//		for (PDQSystemDto pdqDto : cashUp.getCashnPdq().getPdqSystems()) {
//			PDQSystem pdq = restDtoMapper.convertToPdqSystem(pdqDto);
//			pdq.getPdqMachineMaster().setName(pdqDto.getName());
//			pdq.getPdqCardMaster().setName(pdqDto.getCardName());
//		}
//		for (ThirdPartyInfoDto thirdPartyInfoDto : cashUp.getThirdPartyInfo()) {
//			ThirdPartyInfo thirdParty = restDtoMapper.convertToThirdPartyInfo(thirdPartyInfoDto);
//			thirdParty.getThirdPartyInfoMaster().setName(thirdPartyInfoDto.getName());
//		}
//	}

	@Override
	@RosLogDebug
	public CashUpDto getCashUpById(UUID id) throws cashUpNotFoundException {
		Optional<CashUp> cashUpFromDB = cashUpRepo.findById(id);
//		CashUp cashUp = cashUpFromDB.orElseThrow(() -> new cashUpNotFoundException(Properties.cashUpNotFound)); 
		if (cashUpFromDB.isPresent()) {

			restDtoMapper.convertToCashUpDto(cashUpFromDB);
			return restDtoMapper.convertToCashUpDto(cashUpRepo.getOne(id));

		} else
			throw new cashUpNotFoundException(Properties.cashUpNotFound);

	}

	@Override
	@RosLogDebug
	public CashUpDto editCashUp(CashUpDto dto) throws cashUpNotFoundException {

		CashUp cashUp = cashUpRepo.getOne(dto.getId());

		if (cashUp.getCashUpStatus() == CashUpStatus.DRAFT) {

			calculateTotal(dto);

			dto.setCashUpTimeIndicator(cashUp.getCashUpTimeIndicator());
			restDtoMapper.updateCashUp(dto, cashUp);
			cashUp.editMetaData(dto.getCreatedBy());

			return restDtoMapper.convertToCashUpDto(cashUpRepo.save(cashUp));
		} else
			throw new cashUpNotFoundException(Properties.cashUpCreationNotPossible);
	}

	@Override
	@RosLogDebug
	public String deleteCashUp(UUID id) throws cashUpNotFoundException {
		Optional<CashUp> cashUpFromDB = cashUpRepo.findById(id);
		if (cashUpFromDB.get().getCashUpStatus() != CashUpStatus.PUBLISHED) {
			CashUp cashUp = cashUpFromDB.orElseThrow(() -> new cashUpNotFoundException(Properties.cashUpNotFound));
			cashUpRepo.delete(cashUp);
			return Properties.cashUpDeleted;
		} else
			throw new cashUpNotFoundException(Properties.cashUpDeleteException);
	}

	@Override
	@RosLogDebug
	public List<?> getCashUpSheets(CashUpStatus status, Date fromDate, Date toDate) throws cashUpNotFoundException {
		List<CashUp> cashUpFromDB = new ArrayList<>();
		List<CashUpDto> cashUpDtos = new ArrayList<>();
		List<BankingInfo> cashBankingInfos = new ArrayList<>();
		List<BankingInfoDto> bankingDtos = new ArrayList<>();
		List<Date> cashUpDates = new ArrayList<>();
		if (status.equals(CashUpStatus.DRAFT)) {
			cashUpFromDB = cashUpRepo.findCashUpSheets(status, fromDate, toDate);
			cashUpFromDB.forEach(data -> {
				cashUpDtos.add(restDtoMapper.convertToCashUpDto(data));
			});

			return cashUpDtos;
		} else if (status.equals(CashUpStatus.PUBLISHED)) {
			cashUpFromDB = cashUpRepo.findCashUpSheets(status, fromDate, toDate);
			cashUpDtos.addAll(checkPendingDeposits(cashUpFromDB, fromDate, toDate));
			return cashUpDtos;
		} else if (status.equals(CashUpStatus.BANKED)) {
			cashUpFromDB = cashUpRepo.findCashUpSheets(status, fromDate, toDate);
			cashBankingInfos.addAll(getBankingData(cashUpFromDB));
			cashBankingInfos.forEach(data -> {
				data.getCashUps().forEach(cashUp -> {
					cashUpDates.add(cashUp.getCashUpDate());
				});
				bankingDtos.add(restDtoMapper.convertToBankingDto(data));
			});
			for (BankingInfoDto bankingInfoDto : bankingDtos) {
				bankingInfoDto.setCashUpDates(cashUpDates);
			}
			return bankingDtos;
		} else
			throw new cashUpNotFoundException(Properties.cashUpNotFound);
	}

	private List<BankingInfo> getBankingData(List<CashUp> cashUpFromDB) {
		List<BankingInfo> bankingInfo = new ArrayList<>();
		for (CashUp cashUp : cashUpFromDB) {
			bankingInfo.add(cashUp.getBankingInfo());
		}
		return bankingInfo;
	}

	private List<CashUpDto> checkPendingDeposits(List<CashUp> cashUpFromDB, Date fromDate, Date toDate) {
		float tillAmount = 0;
		List<CashUpDto> cashUpDtos = new ArrayList<>();
		for (CashUp data : cashUpFromDB) {
			for (TillSystem till : data.getCashnPdq().getTillSystems()) {
				tillAmount += till.getTillMaster().getAmount();
				if (tillAmount != 0) {
					cashUpDtos.add(restDtoMapper.convertToCashUpDto(data));
				} else
					cashUpDtos.add(restDtoMapper.convertToCashUpDto(data));
			}
		}

		return cashUpDtos;

	}

	/*
	 * Deposit services
	 */
	@Override
	public BankingInfoDto CreateBanking(BankingInfoDto bankingDto) throws cashUpNotFoundException {

		BankingInfo bankingEntity = restDtoMapper.convertToBankingEntity(bankingDto);
		bankingEntity.addMetaData();
		BankingInfo bankingInfo = updateBankingInfo(bankingDto, bankingEntity);
		BankingInfoDto bankingInfoDto = restDtoMapper.convertToBankingDto(bankingInfoRepo.save(bankingInfo));
		bankingInfoDto = updateCashUpDatestoBankinginfo(bankingInfo, bankingInfoDto);
		return bankingInfoDto;
	}

	private BankingInfoDto updateCashUpDatestoBankinginfo(BankingInfo bankingInfo, BankingInfoDto bankingInfoDto) {
		List<Date> cashUpDates = new ArrayList<>();
		List<CashUpTimeIndicator> cashUpTimeIndicators = new ArrayList<>();
		int noOfcashUps = 0;
		for (CashUp date : bankingInfo.getCashUps()) {
			cashUpDates.add(date.getCashUpDate());
			cashUpTimeIndicators.add(date.getCashUpTimeIndicator());
			noOfcashUps++;
		}
		bankingInfoDto.setCashUpDates(cashUpDates);
		bankingInfoDto.setCashUpTimeIndicators(cashUpTimeIndicators);
		bankingInfoDto.setNoOfcashUps(noOfcashUps);

		return bankingInfoDto;

	}

	private BankingInfo updateBankingInfo(BankingInfoDto banking, BankingInfo bankingEntity)
			throws cashUpNotFoundException {
		List<CashUp> cashUps = new ArrayList<>();
		banking.getCashUpDates().forEach(date -> {
			cashUps.addAll(cashUpRepo.checkCashUpByDate(date));
		});
		if (cashUps.size() != 0) {
			for (CashUp cashUp : cashUps) {
				if (cashUp.getCashUpStatus() == CashUpStatus.DRAFT || cashUp.getCashUpStatus() == CashUpStatus.BANKED)
					throw new cashUpNotFoundException(Properties.bankingNotCreated);
				else if (cashUp.getCashUpStatus() == CashUpStatus.PUBLISHED) {
					cashUp.setCashUpStatus(CashUpStatus.BANKED);
					cashUp.setBankingInfo(bankingEntity);
				}
			}
			bankingEntity.setReconcileStatus(BankingStatus.PENDING);
			bankingEntity.setCashUps(cashUps);
			return bankingEntity;
		} else
			throw new cashUpNotFoundException(Properties.bankingNotCreated);
	}

	@Override
	public String DeleteBankingById(UUID id) throws cashUpNotFoundException {
		Optional<BankingInfo> banking = bankingInfoRepo.findById(id);
		if (banking.isPresent()) {
			bankingInfoRepo.deleteById(id);
			return Properties.bankingDeleted;
		} else

			throw new cashUpNotFoundException(Properties.notFindTheRequestedBank);

	}

	@Override
	public BankingInfoDto getBankById(UUID id) throws cashUpNotFoundException {
		BankingInfo bankingInfo = bankingInfoRepo.getOne(id);
		if (!bankingInfo.equals(null)) {

			BankingInfoDto bankingInfoDto = restDtoMapper.convertToBankingDto(bankingInfo);
			bankingInfoDto = updateCashUpDatestoBankinginfo(bankingInfo, bankingInfoDto);

			return bankingInfoDto;

		} else
			throw new cashUpNotFoundException(Properties.bankingNotFound);
	}

	@Override
	@RosLogDebug
	public BankingInfoDto updateBanking(BankingInfoDto dto) throws cashUpNotFoundException {
		BankingInfo bankingInfo = bankingInfoRepo.getOne(dto.getId());
		if (bankingInfo != null) {

			restDtoMapper.updateBanking(dto, bankingInfo);
			BankingInfoDto bankingInfoDto = restDtoMapper.convertToBankingDto(bankingInfoRepo.save(bankingInfo));
			bankingInfoDto = updateCashUpDatestoBankinginfo(bankingInfo, bankingInfoDto);

			return bankingInfoDto;
		} else
			throw new cashUpNotFoundException(Properties.bankingNotCreated);
	}

	@Override
	@RosLogDebug
	public List<BankingInfoDto> getAllBanks(int limit, int pageNo) throws cashUpNotFoundException {
		List<BankingInfo> bankingInfos = bankingInfoRepo.findDatewiseBankings(limit, pageNo * limit);
		List<BankingInfoDto> bankingInfoDtos = new ArrayList<>();
		BankingInfoDto bankingInfoDto;

		if (bankingInfos.size() != 0) {

			for (BankingInfo bankingInfo : bankingInfos) {
				bankingInfoDto = restDtoMapper.convertToBankingDto(bankingInfo);
				bankingInfoDto = updateCashUpDatestoBankinginfo(bankingInfo, bankingInfoDto);
				bankingInfoDtos.add(bankingInfoDto);
			}

			return bankingInfoDtos;
		} else
			throw new cashUpNotFoundException(Properties.bankingNotFound);

	}

	@Override
	@RosLogDebug
	public List<BankingInfoDto> getAllBanks(int limit, int pageNo, UUID restaurantId) throws cashUpNotFoundException {
		List<BankingInfo> bankingInfos = bankingInfoRepo.findDatewiseBankingsForRestaurant(limit, pageNo * limit,
				restaurantId);
		List<BankingInfoDto> bankingInfoDtos = new ArrayList<>();
		BankingInfoDto bankingInfoDto;

		if (bankingInfos.size() != 0) {

			for (BankingInfo bankingInfo : bankingInfos) {
				bankingInfoDto = restDtoMapper.convertToBankingDto(bankingInfo);
				bankingInfoDto = updateCashUpDatestoBankinginfo(bankingInfo, bankingInfoDto);
				bankingInfoDtos.add(bankingInfoDto);
			}

			return bankingInfoDtos;
		} else
			throw new cashUpNotFoundException(Properties.bankingNotFound);

	}

	@Override
	public List<CashUpDto> getAllCashUpSheets(int limit, int pageNo) throws cashUpNotFoundException {
		List<CashUp> cashUps = cashUpRepo.findDatewiseCashups(limit, pageNo * limit);
		List<CashUpDto> cashUpDtos = new ArrayList<>();
		if (cashUps.size() != 0) {
			cashUps.forEach(cashUp -> {
				cashUpDtos.add(restDtoMapper.convertToCashUpDto(cashUp));
			});
			this.checkNullData(cashUpDtos);

			return cashUpDtos;
		} else
			throw new cashUpNotFoundException(Properties.cashUpNotFound);
	}

	@Override
	public List<CashUpDto> getAllCashUpSheets(int limit, int pageNo, UUID restaurantUUID)
			throws cashUpNotFoundException, RestaurantNotFoundException {

//		checkIfRestaurantExists(restaurantUUID);
		List<CashUp> cashUps = cashUpRepo.findDatewiseCashupsForRestaurant(limit, pageNo * limit, restaurantUUID);
		List<CashUpDto> cashUpDtos = new ArrayList<>();
		if (cashUps.size() != 0) {
			cashUps.forEach(cashUp -> {
				cashUpDtos.add(restDtoMapper.convertToCashUpDto(cashUp));
			});
			this.checkNullData(cashUpDtos);

			return cashUpDtos;
		} else
			throw new cashUpNotFoundException(Properties.cashUpNotFound);
	}

	private void checkIfRestaurantExists(UUID restaurantUUID) {
		HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
//	Boolean responseEntity = restTemplate
//			.exchange(adminUrl + "/restaurant/checkIfExists?restaurantId=" + restaurantUUID, HttpMethod.GET,
//					requestEntity, Boolean.class)
//			.getBody();
	}

	@Override
	@RosLogDebug
	public void checkNullData(CashUpDto cashUpDto) {
		if (cashUpDto.getCashUpTimeIndicator() == null) {
			cashUpDto.setCashUpTimeIndicator(CashUpTimeIndicator.PM);
		}

		if (cashUpDto.getCashUpStatus() == null) {
			cashUpDto.setCashUpStatus(CashUpStatus.PUBLISHED);
		}

		if (cashUpDto.getReason() == null) {
			cashUpDto.setReason("Not Applicable");
		}
		if (cashUpDto.getReasonAddedBy() == null) {
			cashUpDto.setReasonAddedBy("Migration team");
		}
		if (cashUpDto.getCardreconciliationNote() == null) {
			cashUpDto.setCardreconciliationNote("Not Applicable");
		}
		if (cashUpDto.getCashreconciliationNote() == null) {
			cashUpDto.setCashreconciliationNote("Not Applicable");
		}
		if (cashUpDto.getThirdPartyreconciliationNote() == null) {
			cashUpDto.setThirdPartyreconciliationNote("Not Applicable");
		}
		if (cashUpDto.getCardStatus() == null) {
			cashUpDto.setCardStatus("Not Applicable");
		}
		if (cashUpDto.getCashStatus() == null) {
			cashUpDto.setCashStatus("Not Applicable");
		}

		if (cashUpDto.getThirdpartyStatus() == null) {
			cashUpDto.setThirdpartyStatus("Not Applicable");
		}

		if (cashUpDto.getCashnPdq() == null) {
			CashnPDQDto cashnPDQDto = new CashnPDQDto();
			cashnPDQDto.setDefaultValues();
			cashUpDto.setCashnPdq(cashnPDQDto);
		}
		if (cashUpDto.getKpi() == null) {
			KPIDto kpiDto = new KPIDto();
			kpiDto.setDefaultValues();
			cashUpDto.setKpi(kpiDto);

		}
		if (cashUpDto.getSafeSummary() == null) {
			SafeSummaryDto safeSummaryDto = new SafeSummaryDto();
			safeSummaryDto.setDefaultValues();
			cashUpDto.setSafeSummary(safeSummaryDto);
		}
		if (cashUpDto.getThirdPartyInfo() == null) {
			List<ThirdPartyInfoDto> thirdPartyInfoDtos = new ArrayList<>();
			ThirdPartyInfoDto thirdPartyInfoDto = new ThirdPartyInfoDto();
			thirdPartyInfoDto.setDefaultValues();
			thirdPartyInfoDtos.add(thirdPartyInfoDto);
			cashUpDto.setThirdPartyInfo(thirdPartyInfoDtos);

		}
		if (cashUpDto.getSales() == null) {
			List<SalesDto> salesDtos = new ArrayList<>();
			SalesDto salesDto = new SalesDto();
			salesDto.setDefaultValues();
			salesDtos.add(salesDto);
			cashUpDto.setSales(salesDtos);
		}
	}

//	public void checkNullData(PDQSystemDto pdqSystemDto) {
//		if (pdqSystemDto==null) {
//			pdqSystemDto.setDefaultValues();
//		}
//	}

	@Override
	@RosLogDebug
	public void checkNullData(List<CashUpDto> cashUpDtos) {
		// TODO Auto-generated method stub

		cashUpDtos.forEach(cashUpDto -> {
			this.checkNullData(cashUpDto);

		});
	}

}