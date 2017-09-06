package com.project.Model;

import java.util.ArrayList;

import com.project.data.ContractProductPrice;

public interface ContractProductPriceDAO {
	public boolean insertContractProduct(ContractProductPrice contractProductPrice);
	public ArrayList<ContractProductPrice> selectContractProductDetails(int contractId, int contractVersion);
	public ArrayList<ContractProductPrice> selectAllContractProductDetails(int contractId);
	public ArrayList<ContractProductPrice> selectLatestContractProductDetails(int contractId);
	

}
