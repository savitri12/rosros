package com.ros.accounting.cashup.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ros.accounting.cashup.controller.dto.BankingInfoDto;
import com.ros.accounting.cashup.controller.dto.BankingReconcilationDto;
import com.ros.accounting.cashup.controller.dto.CardReconciliationInfoDto;
import com.ros.accounting.cashup.controller.dto.CashReconciliationInfoDto;
import com.ros.accounting.cashup.controller.dto.CashUpDto;
import com.ros.accounting.cashup.controller.dto.PDQSystemDto;
import com.ros.accounting.cashup.controller.dto.ReconciliationInfoDto;
import com.ros.accounting.cashup.controller.dto.ReconciliationLogDto;
import com.ros.accounting.cashup.controller.dto.ThirdPartyReconciliationInfoDto;
import com.ros.accounting.cashup.exceptions.cashUpNotFoundException;
import com.ros.accounting.cashup.model.CashUpStatus;
import com.ros.accounting.cashup.model.ReconciliationType;
import com.ros.accounting.cashup.service.ReconciliationService;
import com.ros.accounting.log.RosLogDebug;
import com.ros.accounting.util.ExceptionHandler;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/reconciliation")
@CrossOrigin("*")
@Slf4j
public class ReconciliationController {

	@Autowired
	private ReconciliationService reconciliationService;

	/**
	 * Add the
	 * 
	 * @param cashup
	 * @return
	 */
//	@PostMapping
//	@ResponseBody
//	@RosLogDebug
//	@Operation(summary = "Reconcile CashUp Sheets")
//	public ResponseEntity<?> reconcileCashUps(@RequestBody List<ReconciliationLogDto> reconciliationLogDto) {
//		ResponseEntity<?> response;
//		try {
//			log.info("Reconciling Cash Up Sheets: {}", reconciliationLogDto);
//			response = new ResponseEntity<List<ReconciliationLogDto>>(reconciliationService.reconcileCashUps(reconciliationLogDto), HttpStatus.OK);
//		} catch (cashUpNotFoundException e) {
//			log.error(e.getMessage());
//			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
//		}
//		return response;
//	}

	/**
	 * Add the
	 * 
	 * @param cashup
	 * @return
	 */
//	@PostMapping
//	@ResponseBody
//	@RosLogDebug
//	@Operation(summary = "Reconcile a CashUp Sheet")
//	public ResponseEntity<?> reconcileCashUp(@RequestBody ReconciliationLogDto reconciliationLogDto) {
//		ResponseEntity<?> response;
//		try {
//			log.info("Reconciling a Cash Up Sheet: {}", reconciliationLogDto);
//			response = new ResponseEntity<ReconciliationLogDto>(reconciliationService.reconcileCashUp(reconciliationLogDto), HttpStatus.OK);
//		} catch (cashUpNotFoundException e) {
//			log.error(e.getMessage());
//			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
//		}
//		return response;
//	}

	@PostMapping(value = "/cashReconciliation")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Cash Reconciliation")
	public ResponseEntity<?> cashReconciliation(@RequestBody BankingReconcilationDto cash) {
		ResponseEntity<?> response;
//		System.out.println(id);
		try {
//			log.info("Adding new cashup: {}");
			response = new ResponseEntity<BankingReconcilationDto>(reconciliationService.cashReconcilied(cash),
					HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@PostMapping(value = "/cardReconciliation")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Card Reconciliation")
	public ResponseEntity<?> cardReconiciliation(@RequestBody CardReconciliationInfoDto cards) {
		ResponseEntity<?> response;
//		System.out.println(id);
		try {
			log.info("Adding new cashup: {}");
			response = new ResponseEntity<CashUpDto>(reconciliationService.cardReconciliation(cards), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@PostMapping(value = "/thirdPartyReconciliation")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Third Party Reconciliation")
	public ResponseEntity<?> thirdPartyReconiciliation(@RequestBody ThirdPartyReconciliationInfoDto thirdParty) {
		ResponseEntity<?> response;
//		System.out.println(id);
		try {
//			log.info("Adding new cashup: {}");
			response = new ResponseEntity<CashUpDto>(reconciliationService.thirdPartyReconciliation(thirdParty),
					HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/summary")
	@RosLogDebug
	@Operation(summary = "Get cashup sheets for reconciliation for a given duration")
	public ResponseEntity<?> getCashUpSheetsForReconciliation(@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate, @RequestParam UUID resturantID) throws ParseException {
		ResponseEntity<?> response = null;
		Date from = null, to = null;
		if (fromDate != null && toDate != null) {
			from = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
			to = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
		}
		log.info("cashup sheets: {}");
		try {
			response = new ResponseEntity<>(reconciliationService.getPendingReconciliationSummary(from, to, resturantID),
					HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			e.printStackTrace();
		}
		return response;
	}

	@CrossOrigin
	@GetMapping("/reconciledsummary")
	@RosLogDebug
	@Operation(summary = "Get reconciled cash up sheet total for a given duration")
	public ResponseEntity<?> getReconciledCashUpSheets(@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate,@RequestParam UUID restuarantId) throws ParseException {
		ResponseEntity<?> response = null;
		Date from = null, to = null;
		if (fromDate != null && toDate != null) {
			from = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
			to = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
		}
		log.info("cashup sheets: {}");
		try {
			response = new ResponseEntity<>(reconciliationService.getReconciledSummary(from, to, restuarantId), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/reconciledcalendarsummary")
	@RosLogDebug
	@Operation(summary = "Get calendar log for a given duration")
	public ResponseEntity<?> getReconciledCalendarSummary(@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate, @RequestParam UUID restuarantId, String rType) throws ParseException {
		ResponseEntity<?> response = null;
		Date from = null, to = null;
		if (fromDate != null && toDate != null) {
			from = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
			to = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
		}
		log.info("cashup sheets: {}");
		try {
			response = new ResponseEntity<>(reconciliationService.getReconciliationLog(from, to, restuarantId, rType), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/getsheetsforcash")
	@RosLogDebug
	@Operation(summary = "Get cash sheets for cash reconciliation")
	public ResponseEntity<?> getCashSheetsToReconcile(@RequestParam UUID id) throws ParseException {
		ResponseEntity<?> response = null;
		log.info("cashup sheets: {}");
		try {
			response = new ResponseEntity<>(reconciliationService.getCashSheetsToReconcile(id), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/getsheetsforcard")
	@RosLogDebug
	@Operation(summary = "Get cash sheets for card reconciliation")
	public ResponseEntity<?> getCardSheetsToReconcile(@RequestParam UUID id) throws ParseException {
		ResponseEntity<?> response = null;
		log.info("cashup sheets: {}");
		try {
			response = new ResponseEntity<>(reconciliationService.getCardSheetsToReconcile(id), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping("/getsheetsforthirdparty")
	@RosLogDebug
	@Operation(summary = "Get cash sheets for third party reconciliation")
	public ResponseEntity<?> getThirdPartySheetsToReconcile(@RequestParam UUID id) throws ParseException {
		ResponseEntity<?> response = null;
		log.info("cashup sheets: {}");
		try {
			response = new ResponseEntity<>(reconciliationService.getThirdPartySheetsToReconcile(id), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			e.printStackTrace();
		}
		return response;
	}
}
