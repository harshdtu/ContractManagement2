package com.project.Model;

import java.util.ArrayList;

import com.project.data.ContractProductFeatureLog;

public interface ContractProductLogDao {

	boolean insertContractProduct(ContractProductFeatureLog contractProductLog);
		ArrayList<ContractProductFeatureLog> selectContractProduct(int contract_id);
	//	boolean deleteContractProduct(int contract_id);
		
	
}
