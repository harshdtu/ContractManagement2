package com.project.Model;

import com.project.data.ContractLog;


public interface ContractLogDao {
	boolean insertContractLog (ContractLog contract);
	ContractLog selectContract(int contract_id);
//	boolean finalizeContract(int contract_id);
	
}
