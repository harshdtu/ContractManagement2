package com.project.data;

public class ContractProductFeatureLog {

	int contract_id;
	int product_id;
	int feature_id;
	public int getContract_id() {
		return contract_id;
	}
	public void setContract_id(int contract_id) {
		this.contract_id = contract_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getFeature_id() {
		return feature_id;
	}
	public void setFeature_id(int feature_id) {
		this.feature_id = feature_id;
	}
	
	public ContractProductFeatureLog(int contract_id, int product_id, int feature_id) {
		super();
		this.contract_id = contract_id;
		this.product_id = product_id;
		this.feature_id = feature_id;
		
	}
	public ContractProductFeatureLog() {
//		super();
	}
	
	
}
