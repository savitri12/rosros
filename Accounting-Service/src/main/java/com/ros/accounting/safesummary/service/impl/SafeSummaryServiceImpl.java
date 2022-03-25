package com.ros.accounting.safesummary.service.impl;

import com.ros.accounting.safesummary.dto.NewSafeSummaryDto;
import com.ros.accounting.safesummary.exceptions.SafeSummaryNotFoundException;
import com.ros.accounting.safesummary.mapper.SafeSummaryMapper;
import com.ros.accounting.safesummary.model.NewSafeSummary;
import com.ros.accounting.safesummary.repository.NewSafeSummaryRepository;
import com.ros.accounting.safesummary.service.SafeSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Ayush
 */


@Service
public class SafeSummaryServiceImpl implements SafeSummaryService {


    @Autowired
    private NewSafeSummaryRepository newSafeSummaryRepository;

    @Autowired
    private SafeSummaryMapper safeSummaryMapper;

    @Override
    public NewSafeSummaryDto addSafeSummary(NewSafeSummaryDto newSafeSummaryDto) throws SafeSummaryNotFoundException {
        NewSafeSummary newSafeSummary = safeSummaryMapper.convertToSafeSummaryEntity(newSafeSummaryDto);
        newSafeSummary.addMetaData();
        return safeSummaryMapper.convertToSafeSummaryDto(newSafeSummaryRepository.save(newSafeSummary));
    }

    @Override
    public NewSafeSummaryDto editSafeSummary(NewSafeSummaryDto newSafeSummaryDto) throws SafeSummaryNotFoundException{
        if(newSafeSummaryRepository.existsById(newSafeSummaryDto.getSafeSummaryId())){
            NewSafeSummary newSafeSummary = newSafeSummaryRepository.getOne(newSafeSummaryDto.getSafeSummaryId());
            safeSummaryMapper.updateSafeSummary(newSafeSummaryDto, newSafeSummary);
            newSafeSummary.editMetaData();
            return safeSummaryMapper.convertToSafeSummaryDto(newSafeSummaryRepository.save(newSafeSummary));
        }
        else
            throw new SafeSummaryNotFoundException("Safe Summary does not exist");
    }

    @Override
    public String deleteSafeSummary(UUID safeSummaryId) throws SafeSummaryNotFoundException{
        Optional<NewSafeSummary> NewSafeSummaryFromDB = newSafeSummaryRepository.findById(safeSummaryId);
        if(NewSafeSummaryFromDB.isPresent()){
            newSafeSummaryRepository.deleteById(safeSummaryId);
            return "Safe Summary deleted successfully";
        }else
            throw new SafeSummaryNotFoundException("Safe Summary with id:" + safeSummaryId + "does not exist");
    }

    @Override
    public List<NewSafeSummaryDto> viewAllSafeSummary(UUID restaurantId)throws SafeSummaryNotFoundException{
        List<NewSafeSummary> NewSafeSummaryFromDB = newSafeSummaryRepository.findSafeSummaryById(restaurantId);
        List<NewSafeSummaryDto> safeSummaryDtoList = new ArrayList<>();
        NewSafeSummaryDto newSafeSummaryDto;

        if(NewSafeSummaryFromDB.size() == 0)
            throw new SafeSummaryNotFoundException("Safe Summary Not Found");


        for (NewSafeSummary newSafeSummary : NewSafeSummaryFromDB) {
            newSafeSummaryDto = safeSummaryMapper.convertToSafeSummaryDto(newSafeSummary);
            safeSummaryDtoList.add(newSafeSummaryDto);

        }
        return safeSummaryDtoList;

    }

    @Override
    public NewSafeSummaryDto viewRecentSafeSummary(UUID restaurantId)throws SafeSummaryNotFoundException{
        List<NewSafeSummary> NewSafeSummaryFromDB = newSafeSummaryRepository.findSafeSummaryById(restaurantId);
//        NewSafeSummaryFromDB = newSafeSummaryRepository.findAllByOrderBySafeSummaryDateDesc();
        List<NewSafeSummaryDto> safeSummaryDtoList = new ArrayList<>();
        NewSafeSummaryDto newSafeSummaryDto;


        if(NewSafeSummaryFromDB.size() == 0)
            throw new SafeSummaryNotFoundException("Safe Summary Not Found");


        for (NewSafeSummary newSafeSummary : NewSafeSummaryFromDB) {
            newSafeSummaryDto = safeSummaryMapper.convertToSafeSummaryDto(newSafeSummary);
            safeSummaryDtoList.add(newSafeSummaryDto);
        }

        safeSummaryDtoList.sort(Comparator.comparing(NewSafeSummaryDto::getSafeSummaryDate));
        return safeSummaryDtoList.get(safeSummaryDtoList.size()-1);



    }

    @Override
    public NewSafeSummaryDto viewSafeSummaryById(UUID safeSummaryId) throws SafeSummaryNotFoundException{
        if(newSafeSummaryRepository.existsById(safeSummaryId)){
            NewSafeSummary newSafeSummary = newSafeSummaryRepository.getOne(safeSummaryId);
            return safeSummaryMapper.convertToSafeSummaryDto(newSafeSummary);
        }
        else
            throw new SafeSummaryNotFoundException("Safe Summary does not exist");
    }

}

