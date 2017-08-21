package com.project.Model;

import java.util.ArrayList;

import com.project.data.ContractProductLog;

public interface ContractProductLogDao {

	boolean insertContractProduct(ContractProductLog contractProductLog);
		ArrayList<ContractProductLog> selectContractProduct(int contract_id);
	//	boolean deleteContractProduct(int contract_id);
		
	
}
