package com.ros.accounting.safesummary.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class NewSafeSummaryDto {
    private static final long serialVersionUID = 1L;

    private UUID safeSummaryId;

    private UUID restaurantId;

    private Timestamp safeSummaryDate;

    private String witness;

    private float safeFloat;

    private float tillFloat;

    private float safeCash;

    private float miscOtherExpenditure;

    private String reason;

}
