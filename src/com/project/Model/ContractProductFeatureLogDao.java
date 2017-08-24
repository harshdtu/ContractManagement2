package com.project.Model;

import java.util.ArrayList;

import com.project.data.ContractFeature;
import com.project.data.ContractProduct;
import com.project.data.ContractProductFeatureLog;

public interface ContractProductFeatureLogDao {

	boolean insertContractProductFeature(ArrayList<ContractProductFeatureLog> arr);
	ArrayList<ContractFeature> fetchContractFeature(int contractId);
	ArrayList<ContractProduct> fetchContractProduct(int contractId);
		
	
}
