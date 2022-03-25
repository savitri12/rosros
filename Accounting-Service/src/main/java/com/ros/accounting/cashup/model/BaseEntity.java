package com.ros.accounting.cashup.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity {

	private boolean status;

	@Temporal(value = TemporalType.DATE)
	private Date createdDate;

	private String createdBy;

	@Temporal(value = TemporalType.DATE)
	private Date lastModifiedDate;

	private String updatedBy;

	public void addMetaData() {
		this.setStatus(true);
		this.setCreatedDate(new Date());
		this.setCreatedBy("user");
	}

	public void addMetaData(String username) {
		this.setStatus(true);
		this.setCreatedDate(new Date());
		this.setCreatedBy(username);
	}

	public void editMetaData() {
		this.setStatus(true);
		this.setUpdatedBy("user");
		this.setLastModifiedDate(new Date());
	}
	
	public void editMetaData(String username) {
		this.setStatus(true);
		this.setUpdatedBy(username);
		this.setLastModifiedDate(new Date());
	}

}
