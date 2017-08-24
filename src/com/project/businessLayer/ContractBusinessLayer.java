package com.project.businessLayer;

import java.util.ArrayList;

import com.project.Model.ContractLogImpl;
import com.project.Model.ContractProductFeatureImpl;
import com.project.Model.ContractProductPriceImpl;
import com.project.data.Contract;
import com.project.data.ContractLog;
import com.project.data.ContractProductFeatureLog;
import com.project.data.ContractProductPrice;
import com.project.data.Product;

public class ContractBusinessLayer {
	ContractLogImpl contractLogImpl = new ContractLogImpl();
	ContractProductFeatureImpl contractPFLog = new ContractProductFeatureImpl();
	ContractProductPriceImpl contractPrice = new ContractProductPriceImpl();
	public Contract getSpecificContract(int contractId, int contractVersion) {
		
		Contract contract = new Contract();
		Product product=new Product();
		ContractLog contractLog = contractLogImpl.selectContractLog(contractId, contractVersion);
		contract.setContract(contractLog.getContract_id());
		contract.setBuyerId(contractLog.getBuyer_id());
		contract.setSellerId(contractLog.getSeller_id());
		contract.setDeliveryTermId(contractLog.getDelivery_term_id());
		contract.setPaymentTermId(contractLog.getPayment_term_id());
		contract.setDateofInvoice(contractLog.getInvoice_date());
		contract.setStatus(contractLog.getVersion());
		ArrayList<ContractProductPrice> contractProPrice =contractPrice.selectContractProductDetails(contractId,contractVersion);
		for(ContractProductPrice contractPrice : contractProPrice) {
		 product.setId(contractPrice.getProductId());
		 product.setPrice(contractPrice.getProductPrice());
		 product.setCategory(1); // fetch from api - TODO
		 product.setProductName("Mobile"); //fetch from api _TODO
		 product.setQuantity(contractPrice.getProductQuantity());
		
			ArrayList<ContractProductFeatureLog> contractFeature = contractPFLog.fetchContractProductFeature(contractId, contractVersion);
			 //product.setFeature(contractFeature);
		}
		return new Contract();
	}
	
	public Contract getLatestContract(int contractId) {
		return new Contract();
	}
	
	public boolean insertContract(Contract contract) {
		
		return true;
	}
	
	public int getContractStatus(int contractId) {
		int status = 0;
		return status;
	}
	
	public ArrayList<Contract> getAllContractsVersion(int contractId){
		return new ArrayList<Contract>();
	}
	
	public ArrayList<Contract> getAllContractsByUser(String userType, String userId){
		return new ArrayList<Contract>();
	}
}
