package com.project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.project.data.Contract;
import com.project.data.ContractLog;
import com.project.database.SQLConnection;

public class ContractLogImpl implements ContractLogDao{
	Connection conn = SQLConnection.getConnection();
	
	public boolean insertContractLog (ContractLog contract){
		PreparedStatement ps = null;
		boolean result = false;
			try {
				ps = conn.prepareStatement("Insert into ContractLog values(?,?,?,?,?,?,?,?,?,?)");
				ps.setInt(1,contract.getContract_id());
				ps.setInt(3,contract.getBuyer_id());
				ps.setInt(2,contract.getSeller_id());
				ps.setInt(5,contract.getDelivery_term_id());
				ps.setInt(6,contract.getPayment_term_id());
				ps.setInt(7,contract.getProposal_id());
				ps.setInt(4,contract.getStatus_id());
				ps.setString(10,contract.getInvoice_date());
				ps.setString(9,contract.getPeriod_of_delivery());
				ps.setFloat(8,contract.getPrice());
				
				
				
			} catch (SQLException e) {
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
	public ContractLog selectContract(int contract_id){
		Statement stmt = null;
		ContractLog contractLog = new ContractLog();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from ContractLog where contract_id ="+contract_id);
			
			while(rs.next())
			{
				
				contractLog.setContract_id(rs.getInt(1));
				contractLog.setSeller_id(rs.getInt(2));
				contractLog.setBuyer_id(rs.getInt(3));
				contractLog.setStatus_id(rs.getInt(4));
				contractLog.setDelivery_term_id(rs.getInt(5));
				contractLog.setPayment_term_id(rs.getInt(6));
				contractLog.setProposal_id(rs.getInt(7));
				contractLog.setPrice(rs.getFloat(8));
				contractLog.setPeriod_of_delivery(rs.getString(9));
				contractLog.setInvoice_date(rs.getString(10));
				
				
				
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
			return contractLog;
		
		
		

		
	}
//	public boolean finalizeContract(int contract_id){
//		PreparedStatement ps = null;
//		 ps = conn.prepareStatement("Update from ContractLog");
//		return false;
//		
//	}
}
