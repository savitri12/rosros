package com.ros.accounting.cashup.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.ros.accounting.cashup.controller.dto.CashUpDto;
import com.ros.accounting.cashup.exceptions.RestaurantNotFoundException;
import com.ros.accounting.cashup.exceptions.cashUpNotFoundException;
import com.ros.accounting.cashup.model.BankingInfo;
import com.ros.accounting.cashup.model.CashUp;
import com.ros.accounting.cashup.model.CashUpStatus;
import com.ros.accounting.cashup.repository.BankingInfoRepository;
import com.ros.accounting.cashup.repository.CashUpRepository;
import com.ros.accounting.cashup.service.CashUpService;
import com.ros.accounting.log.RosLogDebug;
import com.ros.accounting.util.ExceptionHandler;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cashup")
@CrossOrigin("*")
@Slf4j
public class CashUpController {

	@Autowired
	private CashUpService cashUpService;
	
	@Autowired
	private CashUpRepository cashUpRepository;

	@Autowired
	private BankingInfoRepository bankingInfoRepository;
	/**
	 * Add the
	 * 
	 * @param cashup
	 * @return
	 */
	@PostMapping
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Add new cashup sheet")
	public ResponseEntity<?> addNewCashUp(@RequestBody CashUpDto dto) {
		ResponseEntity<?> response;
		try {
			log.info("Adding new cashup: {}", dto);
			response = new ResponseEntity<CashUpDto>(cashUpService.addNewCashUp(dto), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}
	
	
//	@PostMapping("/lotCashup")
//	@ResponseBody
//	@RosLogDebug
//	@Operation(summary = "Add lot of cashups")
//	public void addCashups(@RequestParam int limit ) {
//		ResponseEntity<?> response;
//		for (int i = 0; i<limit; i++ ) {
//		UUID uuid = UUID.randomUUID();
//		CashUp cashup = new CashUp();
//		cashup.setId(uuid);
//		cashup.setCashUpDate(new Date());
//		cashup.setCashUpStatus(CashUpStatus.PUBLISHED);
//		cashUpRepository.save(cashup);
//		}
//	}
	
//	
//	@PostMapping("/lotBankings")
//	@ResponseBody
//	@RosLogDebug
//	@Operation(summary = "Add lot of bankings")
//	public void addManyBankings(@RequestParam int limit ) {
//		ResponseEntity<?> response;
//		for (int i = 0; i<limit; i++ ) {
//		UUID uuid = UUID.randomUUID();
//		List<CashUpDto> cashupDtos = this.getAllCashUpSheets(10, i);
//		List<CashUp> cashups = new ArrayList<>();
//		cashupDtos.forEach(cashupdto -> {
//			cashupdto.setCashUpStatus(CashUpStatus.BANKED);
//			cashups.add(cashUpRepository.getOne(cashupdto.getId()));
//		});
//		BankingInfo bankingInfo = new BankingInfo();
//		bankingInfo.setId(uuid);
//		bankingInfo.setCashUps(cashups);
//		bankingInfo.setBankingDate(new Date());
//		bankingInfo.setGiroSlipNumber("12345");
//		bankingInfo.setSealedBy("harsha");
//		bankingInfo.setStatus(true);
//		bankingRepository.save(bankingInfo);
//		}
//	}

	@PutMapping
	@RosLogDebug
	@Operation(summary = "Editing the cash up sheet")
	public ResponseEntity<?> editCashUp(@RequestBody CashUpDto dto) {
		ResponseEntity<?> response;
		try {
			log.info("Updating cashup: {}", dto);
			response = new ResponseEntity(cashUpService.editCashUp(dto), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Get cashup sheet based on id")
	public ResponseEntity<?> getCashUpById(@PathVariable(value = "id") UUID id) {
		ResponseEntity<?> response;
		try {
			log.info("Getting cashup by id : {}", id);
			response = new ResponseEntity<CashUpDto>(cashUpService.getCashUpById(id), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/summary")
	@RosLogDebug
	@Operation(summary = "Get cashup sheets based on the status and date(yyyy-MM-dd)")
	public ResponseEntity<?> getCashUpSheetsByStatus(@RequestParam(defaultValue = "DRAFT") CashUpStatus status,
			@RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate)
			throws ParseException {
		ResponseEntity<?> response = null;
		Date from = null, to = null;
		if (fromDate != null && toDate != null) {
			from = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
			to = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
		}
		log.info("cashup sheets: {}", status);
		try {
			response = new ResponseEntity<List<?>>(cashUpService.getCashUpSheets(status, from, to), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			e.printStackTrace();
		}
		return response;
	}

//    @GetMapping("/summary")
//@RosLogDebug
//@Operation(summary = "Get cashup sheets based on the status and date(yyyy-MM-dd)")
//public ResponseEntity<?> getCashUpSheetsByStatus(@RequestParam(defaultValue = "DRAFT") CashUpStatus status,
//@RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate)
//throws ParseException {​​​
//ResponseEntity<?> response = null;
//Date from = null, to = null;
//if (fromDate != null && toDate != null) {​​​
//from = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
//to = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
//}​​​
//log.info("cashup sheets: {​​​}​​​", status);
//try {​​​
//response = new ResponseEntity<List<?>>(cashUpService.getCashUpSheets(status, from, to), HttpStatus.OK);
//}​​​ catch (cashUpNotFoundException e) {​​​
//e.printStackTrace();
//}​​​
//return response;
//}​​​

	@GetMapping
	@RosLogDebug
	@ResponseBody
	@RolesAllowed({ "ROLE_USER" })
	@Operation(summary = "Find all cashup sheets available in the database")
	public ResponseEntity<?> getAllCashUpSheets(@RequestParam int limit,@RequestParam int pageNo) {
		ResponseEntity<?> response = null;
		try {
			response =  new ResponseEntity<List<?>>(cashUpService.getAllCashUpSheets(limit, pageNo),HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	
	@GetMapping("/cashupsForRestaurant")
	@RosLogDebug
	@ResponseBody
	@RolesAllowed({ "ROLE_USER" })
	@Operation(summary = "Find all cashup sheets for a given restaurant")
	public ResponseEntity<?> getAllCashUpSheetsforRestaurant(@RequestParam int limit,@RequestParam int pageNo,@RequestParam UUID restaurantId) {
		ResponseEntity<?> response = null;
		try {
			response =  new ResponseEntity<List<?>>(cashUpService.getAllCashUpSheets(limit, pageNo, restaurantId),HttpStatus.OK);
		}  catch (RestaurantNotFoundException e) {
			// TODO Auto-generated catch block
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.NOT_FOUND);
		}catch (cashUpNotFoundException e) {
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@DeleteMapping("{id}")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Deleting the cashup sheet")
	public ResponseEntity<?> deleteCashUp(@PathVariable UUID id) {
		ResponseEntity<?> response = null;
		try {
			log.info("Deleting cashup: {}", id);
			response = new ResponseEntity<String>(cashUpService.deleteCashUp(id), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	/**
	 * Create the
	 * 
	 * @param Banking
	 * @return
	 * @throws SheetNotFoundException
	 */

	@PostMapping(value = "/banking")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Create new banking sheet")
	public ResponseEntity<?> createBanking(@RequestBody BankingInfoDto dto) {
		ResponseEntity<?> response;
		try {
			log.info("Create new Banking: {}", dto);
			response = new ResponseEntity<BankingInfoDto>(cashUpService.CreateBanking(dto), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@PutMapping("/banking")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Editing the Basking sheet")
	public ResponseEntity<?> editBanking(@RequestBody BankingInfoDto dto) {
		ResponseEntity<?> response;
		try {
			log.info("Update Banking: {}", dto);
			response = new ResponseEntity(cashUpService.updateBanking(dto), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@ResponseBody
	@GetMapping("/banking")
	@RosLogDebug
	@Operation(summary = "Get all the Banking")
	public ResponseEntity<?> getAllbanking(@RequestParam int limit,@RequestParam int pageNo) throws cashUpNotFoundException {
		ResponseEntity<?> response = null;
		try {
			log.info("GetAll the Banking: {}");
			response = new ResponseEntity<List<BankingInfoDto>>(cashUpService.getAllBanks(limit, pageNo), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}
	
	@ResponseBody
	@GetMapping("/bankingForRestaurant")
	@RosLogDebug
	@Operation(summary = "Get all the Banking")
	public ResponseEntity<?> getAllbanking(@RequestParam int limit,@RequestParam int pageNo,@RequestParam UUID restaurantId) throws cashUpNotFoundException {
		ResponseEntity<?> response = null;
		try {
			log.info("GetAll the Banking: {}");
			response = new ResponseEntity<List<BankingInfoDto>>(cashUpService.getAllBanks(limit, pageNo, restaurantId), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/banking/{id}")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Get Banking by ID")
	public ResponseEntity<?> getbankigById(@PathVariable(value = "id") UUID id) throws cashUpNotFoundException {
		ResponseEntity<?> response = null;
		try {
			log.info("Get Bank By ID: {}");
			response = new ResponseEntity<BankingInfoDto>(cashUpService.getBankById(id), HttpStatus.OK);

		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@DeleteMapping("/banking/{id}")
	@RosLogDebug
	@ResponseBody
	@Operation(summary = "Delete Select banking")
	public ResponseEntity<?> deleteBankingById(@PathVariable("id") UUID id) {
		ResponseEntity<?> response = null;
		try {
			log.info("Deleting cashup: {}", id);
			response = new ResponseEntity<String>(cashUpService.DeleteBankingById(id), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		return response;
	}

}
