package com.project.Model;

import java.util.ArrayList;

import com.project.data.PaymentTerm;

public interface PaymentsDao {
	
	public ArrayList<PaymentTerm> fetchAllPaymentTerms();

}
