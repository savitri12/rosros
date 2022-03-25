package com.ros.accounting.cashup.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ros.accounting.cashup.model.master.PDQCardMaster;
import com.ros.accounting.cashup.model.master.PDQMachineMaster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PDQSystem implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pdq_system_id",length = 16)
	private UUID id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pdq_machine_master_id")
	private PDQMachineMaster pdqMachineMaster;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pdq_card_master_id")
	private PDQCardMaster pdqCardMaster;

	public void setDefaultValues() {
		PDQMachineMaster pdqMachineMaster =new PDQMachineMaster(null,"vouchers","VC",null);
		
		PDQCardMaster pdqCardMaster =new PDQCardMaster(null,"NA",false,false,"NA",null,null,null);
		
		this.setPdqCardMaster(pdqCardMaster);
		this.setPdqMachineMaster(pdqMachineMaster);
	}
	
}
