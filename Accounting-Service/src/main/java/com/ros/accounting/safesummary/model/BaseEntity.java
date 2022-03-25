package com.ros.accounting.safesummary.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {

    private boolean status;

    @Temporal(value = TemporalType.DATE)
    private Date createdDate;

    private String createdBy;

    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;

    private String updatedBy;

    public void addMetaData(){
        this.setStatus(true);
        this.setCreatedDate(new Date());
        this.setCreatedBy("user");
    }

    public void editMetaData(){
        this.setStatus(true);
        this.setCreatedDate(new Date());
        this.setCreatedBy("user");
    }
}
