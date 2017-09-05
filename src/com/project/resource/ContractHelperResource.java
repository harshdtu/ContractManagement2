package com.project.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.project.businessLayer.ContractBusinessLayer;
import com.project.data.DeliveryTerm;
import com.project.data.PaymentTerm;

@Path("/contracts/helper")
public class ContractHelperResource {
	private ContractBusinessLayer contractBL;
	public ContractHelperResource(){
		contractBL = new ContractBusinessLayer();
	}
	@Path("/deliveryTerms")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<DeliveryTerm> getDeliveryTerms() {
		return contractBL.getAllDeliveryTerms();
	}
	
	@Path("/paymentTerms")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PaymentTerm> getPaymentTerms() {
		return contractBL.getAllPaymentTerms();
	}
}
