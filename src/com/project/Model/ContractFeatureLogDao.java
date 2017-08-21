package com.project.Model;

import java.util.ArrayList;

import com.project.data.ContractFeatureLog;

public interface ContractFeatureLogDao {
	
	boolean addContractFeature(ContractFeatureLog contractFeatureLog);
	ArrayList<ContractFeatureLog> selectContractFeature(int contract_id);
//	boolean deleteContractFeature(int contract_id);
	
}
