package com.project.Model;

import java.util.ArrayList;

import com.project.data.ContractLog;


public interface ContractLogDao {
	boolean insertContractLog (ContractLog contract);
	ContractLog selectSingleContract(int contract_id);
	ArrayList<ContractLog> selectHistoryContract(int contract_id);

}
