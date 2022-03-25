package com.ros.accounting.safesummary.model;
import lombok.*;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "safesummary")

public class NewSafeSummary extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "safe_summary_id", length = 16)
    private UUID safeSummaryId;



    @Temporal(TemporalType.TIMESTAMP)
    private Date safeSummaryDate;

    @Column
    private String witness;

    @Column
    private float safeFloat;

    @Column
    private float tillFloat;

    @Column
    private float safeCash;

    @Column
    private float miscOtherExpenditure;

    @Column
    private String reason;

    @Column
    private UUID restaurantId;

}
