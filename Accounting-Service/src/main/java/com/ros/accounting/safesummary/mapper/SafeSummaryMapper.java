package com.ros.accounting.safesummary.mapper;

import com.ros.accounting.safesummary.dto.NewSafeSummaryDto;
import com.ros.accounting.safesummary.model.NewSafeSummary;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;


/**
 * @author Ayush
 */

@Mapper
@Component
public interface SafeSummaryMapper {

    NewSafeSummaryDto convertToSafeSummaryDto(NewSafeSummary safeSummary);

    NewSafeSummary convertToSafeSummaryEntity(NewSafeSummaryDto safeSummaryDto);

    void updateSafeSummary(NewSafeSummaryDto newSafeSummaryDto, @MappingTarget NewSafeSummary newSafeSummary);
}
