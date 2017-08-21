package com.project.Model;

import java.util.ArrayList;

import com.project.data.ContractProduct;
import com.project.data.ContractProductLog;

public interface ContractProductDao {

	boolean insertContractProduct(ContractProduct contractProduct);
	//ArrayList<ContractProductLog> selectContractProduct(int contract_id);
	//	boolean deleteContractProduct(int contract_id);
		
	
}
