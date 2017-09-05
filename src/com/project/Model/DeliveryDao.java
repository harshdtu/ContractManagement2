package com.project.Model;

import java.util.ArrayList;

import com.project.data.DeliveryTerm;

public interface DeliveryDao {
	
	public ArrayList<DeliveryTerm> fetchAllDeliveryTerm();

}
