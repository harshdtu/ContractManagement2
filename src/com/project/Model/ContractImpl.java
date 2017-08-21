package com.project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.data.Contract;
import com.project.data.ContractLog;
import com.project.database.SQLConnection;

public class ContractImpl implements ContractDao{
	Connection conn = SQLConnection.getConnection();

	@Override
	public boolean insertFinalContract(ContractLog contract) {
		PreparedStatement ps = null;
		boolean result = false;
			try {
				ps = conn.prepareStatement("Insert into Contract values(?,?,?,?,?,?,?,?,?,?,?)");
				ps.setInt(1,contract.getContract_id());
				ps.setInt(3,contract.getBuyer_id());
				ps.setInt(2,contract.getSeller_id());
				ps.setInt(4,contract.getDelivery_term_id());
				ps.setInt(5,contract.getPayment_term_id());
				ps.setInt(6,contract.getProposal_id());
				ps.setString(9,contract.getInvoice_date());
				ps.setString(8,contract.getPeriod_of_delivery());
				ps.setFloat(7,contract.getPrice());
				//ps.setInt(10,contract.getCount());
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				 try {
					result = ps.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return result;
			
		
	}

	@Override
	public Contract selectContract(int contract_id) {
		Statement stmt = null;
		Contract contract = new Contract();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from Contract where contract_id ="+contract_id);
			
			while(rs.next())
			{
				
				contract.setContract_id(rs.getInt(1));
				contract.setSeller_id(rs.getInt(2));
				contract.setBuyer_id(rs.getInt(3));
				contract.setDelivery_term_id(rs.getInt(4));
				contract.setPayment_term_id(rs.getInt(5));
				contract.setProposal_id(rs.getInt(6));
				contract.setPrice(rs.getFloat(7));
				contract.setPeriod_of_delivery(rs.getString(8));
				contract.setInvoice_date(rs.getString(9));
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
			return contract;
		
		
	
	}

	@Override
	public ArrayList<Contract> fetchAllContract(int user_id) {
		// TODO Auto-generated method stub
		Statement stmt = null;
		ArrayList<Contract> contracts = new ArrayList<>();
		Contract contract = new Contract();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from Contract where seller_id ="+user_id);
			
			while(rs.next())
			{
				
				contract.setContract_id(rs.getInt(1));
				contract.setSeller_id(rs.getInt(2));
				contract.setBuyer_id(rs.getInt(3));
				contract.setDelivery_term_id(rs.getInt(4));
				contract.setPayment_term_id(rs.getInt(5));
				contract.setProposal_id(rs.getInt(6));
				contract.setPrice(rs.getFloat(7));
				contract.setPeriod_of_delivery(rs.getString(8));
				contract.setInvoice_date(rs.getString(9));
				
				contracts.add(contract);
				
				
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
			return contracts;
	}

}
