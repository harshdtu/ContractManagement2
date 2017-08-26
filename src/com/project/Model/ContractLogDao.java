package com.project.Model;

import java.util.ArrayList;

import com.project.data.ContractLog;


public interface ContractLogDao {
	public boolean insertContractLog (ContractLog contractLog);
	public ContractLog selectLatestContractLog(int contractId);
	public ContractLog selectContractLog(int contract_id, int contractVersion);
	public ArrayList<ContractLog> selectAllContractLogSeller(String sellerId);
	public ArrayList<ContractLog> selectAllContractLogBuyer(String buyerId);
	public ArrayList<ContractLog> selectAllContractLogVersions(int contractId);


}
