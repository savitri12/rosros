package com.ros.accounting.safesummary.service;

import com.ros.accounting.safesummary.dto.NewSafeSummaryDto;
import com.ros.accounting.safesummary.exceptions.SafeSummaryNotFoundException;
import com.ros.accounting.safesummary.model.NewSafeSummary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * @author Ayush
 */


@Service
public interface SafeSummaryService {

    NewSafeSummaryDto addSafeSummary(NewSafeSummaryDto newSafeSummaryDto)throws SafeSummaryNotFoundException;

    NewSafeSummaryDto editSafeSummary(NewSafeSummaryDto newSafeSummaryDto)throws SafeSummaryNotFoundException;

    String deleteSafeSummary(UUID safeSummaryId) throws SafeSummaryNotFoundException;

    List<NewSafeSummaryDto> viewAllSafeSummary(UUID restaurantId) throws  SafeSummaryNotFoundException;

    NewSafeSummaryDto viewSafeSummaryById(UUID safeSummaryId) throws SafeSummaryNotFoundException;

    NewSafeSummaryDto viewRecentSafeSummary(UUID restaurantId) throws SafeSummaryNotFoundException;
}
