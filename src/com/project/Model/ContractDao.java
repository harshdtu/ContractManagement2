package com.project.Model;

import java.util.ArrayList;

import com.project.data.Contract;
import com.project.data.ContractLog;

public interface ContractDao {

	boolean insertFinalContract(ContractLog contract);
	Contract selectContract(int contract_id);
	ArrayList<Contract> fetchAllContract(int user_id);
}
