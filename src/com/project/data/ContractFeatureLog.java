package com.project.data;

public class ContractFeatureLog {
	int contract_id;
	int feature_id;
	int count;
	public int getContract_id() {
		return contract_id;
	}
	public void setContract_id(int contract_id) {
		this.contract_id = contract_id;
	}
	public int getFeature_id() {
		return feature_id;
	}
	public void setFeature_id(int feature_id) {
		this.feature_id = feature_id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ContractFeatureLog(int contract_id, int feature_id,int count) {
		super();
		this.contract_id = contract_id;
		this.feature_id = feature_id;
		this.count=count;
	}
	public ContractFeatureLog() {
		// TODO Auto-generated constructor stub
	}

}
