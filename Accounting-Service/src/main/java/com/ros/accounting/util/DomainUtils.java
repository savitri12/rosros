package com.ros.accounting.util;

import java.util.Date;

import com.ros.accounting.cashup.model.BaseEntity;

public class DomainUtils {
	
	public static void addMetaData(BaseEntity cashUp) {
		cashUp.setStatus(true);
		cashUp.setCreatedDate(new Date());
		cashUp.setLastModifiedDate(null);
		cashUp.setCreatedBy("user");
		cashUp.setUpdatedBy("user");
	}

}
