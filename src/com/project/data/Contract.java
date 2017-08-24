package com.project.data;

import java.util.ArrayList;
import java.util.Date;

public class Contract {

	int contract;
	String sellerId;
	String buyerId;
	int deliveryTermId;
	int paymentTermId;
	Date dateofInvoice;
	String periodOfDelivery;
	ArrayList<Product> product ;
	public Contract(int contract, String sellerId, String buyerId, int deliveryTermId, int paymentTermId,
			Date dateofInvoice, String periodOfDelivery, ArrayList<Product> product) {
		super();
		this.contract = contract;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.deliveryTermId = deliveryTermId;
		this.paymentTermId = paymentTermId;
		this.dateofInvoice = dateofInvoice;
		this.periodOfDelivery = periodOfDelivery;
		this.product = product;
	}
	public Contract() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getContract() {
		return contract;
	}
	public void setContract(int contract) {
		this.contract = contract;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public int getDeliveryTermId() {
		return deliveryTermId;
	}
	public void setDeliveryTermId(int deliveryTermId) {
		this.deliveryTermId = deliveryTermId;
	}
	public int getPaymentTermId() {
		return paymentTermId;
	}
	public void setPaymentTermId(int paymentTermId) {
		this.paymentTermId = paymentTermId;
	}
	public Date getDateofInvoice() {
		return dateofInvoice;
	}
	public void setDateofInvoice(Date dateofInvoice) {
		this.dateofInvoice = dateofInvoice;
	}
	public String getPeriodOfDelivery() {
		return periodOfDelivery;
	}
	public void setPeriodOfDelivery(String periodOfDelivery) {
		this.periodOfDelivery = periodOfDelivery;
	}
	public ArrayList<Product> getProduct() {
		return product;
	}
	public void setProduct(ArrayList<Product> product) {
		this.product = product;
	}
	
	
	
}
