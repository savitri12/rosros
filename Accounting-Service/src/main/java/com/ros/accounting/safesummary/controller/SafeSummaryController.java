package com.ros.accounting.safesummary.controller;


import com.ros.accounting.safesummary.dto.NewSafeSummaryDto;
import com.ros.accounting.safesummary.exceptions.MessageResponse;
import com.ros.accounting.safesummary.exceptions.SafeSummaryNotFoundException;
import com.ros.accounting.safesummary.repository.NewSafeSummaryRepository;
import com.ros.accounting.safesummary.service.SafeSummaryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * @author Ayush
 */


@RestController
@RequestMapping("/safesummary")
@CrossOrigin("*")
@Slf4j
public class SafeSummaryController {



    @Autowired
    private NewSafeSummaryRepository newSafeSummaryRepository;

    @Autowired
    private SafeSummaryService safeSummaryService;

    @Operation(summary = "add safe summary")
    @PostMapping()
    @ResponseBody
    public ResponseEntity<?> addSafeSummary(@RequestBody NewSafeSummaryDto newSafeSummaryDto){
        ResponseEntity<?> response;
        try{
            response = new ResponseEntity<NewSafeSummaryDto>(safeSummaryService.addSafeSummary(newSafeSummaryDto), HttpStatus.OK);
        }catch(SafeSummaryNotFoundException e){
            response =new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return response;
    }

    @Operation(summary="edit safe summary")
    @PutMapping
    @ResponseBody
    public ResponseEntity<?> editSafeSummary(@RequestBody NewSafeSummaryDto newSafeSummaryDto){
        ResponseEntity<?> response;
        try{
            response = new ResponseEntity<NewSafeSummaryDto>(safeSummaryService.editSafeSummary(newSafeSummaryDto),HttpStatus.OK);
        }catch(SafeSummaryNotFoundException e){
            response = new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return response;
    }

    @Operation(summary = "delete safe summary by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSafeSummary(@PathVariable(value = "id") UUID id){
        ResponseEntity<?> response;
        try{
            String str = safeSummaryService.deleteSafeSummary(id);
            response = new ResponseEntity<Object>(new MessageResponse(str),HttpStatus.OK);
        }catch(SafeSummaryNotFoundException e){
            response = new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return response;
    }

    @Operation(summary = "get all safe summary")
    @GetMapping("/getAllSafeSummary/{restaurantId}")
    @ResponseBody
    public ResponseEntity<?> getSafeSummary(@PathVariable(value = "restaurantId")UUID restaurantId){
        ResponseEntity<?> response;
        try{
            response = new ResponseEntity<Object>(safeSummaryService.viewAllSafeSummary(restaurantId),HttpStatus.OK);
        }catch(SafeSummaryNotFoundException e){
            response = new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return response;
    }


    @Operation(summary = "get safe summary by id")
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getSafeSummaryById(@PathVariable(value = "id")UUID id){
        ResponseEntity<?> response;
        try{
            response = new ResponseEntity<Object>(safeSummaryService.viewSafeSummaryById(id),HttpStatus.OK);
        }catch(SafeSummaryNotFoundException e){
            response = new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return response;
    }

    @Operation(summary = "get recent safe summary")
    @GetMapping("/getRecentSafeSummary/{restaurantId}")
    @ResponseBody
    public ResponseEntity<?> getRecentSafeSummary(@PathVariable(value = "restaurantId")UUID restaurantId){
        ResponseEntity<?> response;
        try{
            response = new ResponseEntity<Object>(safeSummaryService.viewRecentSafeSummary(restaurantId),HttpStatus.OK);
        }catch(SafeSummaryNotFoundException e){
            response = new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return response;
    }

}
