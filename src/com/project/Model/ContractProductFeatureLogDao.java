package com.project.Model;

import java.util.ArrayList;

import com.project.data.ContractProductFeatureLog;

public interface ContractProductFeatureLogDao {

	boolean insertContractProductFeature(ArrayList<ContractProductFeatureLog> contractProductFeatureLog);
	ArrayList<ContractProductFeatureLog> fetchContractProductFeature(int contractId, int contractVersion);
	ArrayList<ContractProductFeatureLog> fetchVersionsContractProductFeature(int contractId);
	ArrayList<ContractProductFeatureLog> fetchFinalContractProductFeature(int contractId);
}
