package com.project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.project.data.ContractLog;
import com.project.database.SQLConnection;

public class ContractLogImpl implements ContractLogDao{

	
	public boolean insertContractLog (ContractLog contract){
		Connection conn = SQLConnection.getConnection();
		PreparedStatement ps = null;
		boolean res =false;
		int version=0;
			try {
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

				ResultSet rs = stmt.executeQuery("select max(\"version\") from \"contractLog\" where \"contractId\" ="+contract.getContract_id());
				if(rs.first()) {
						version=rs.getInt(1)+1;
					
				}
					ps = conn.prepareStatement("Insert into \"contractLog\" values(?,?,?,?,?,?,?,?,?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
			                ResultSet.CONCUR_UPDATABLE);

					ps.setInt(1,contract.getContract_id());
					ps.setString(2,contract.getSeller_id());
					ps.setString(3,contract.getBuyer_id());
					ps.setInt(4,contract.getStatus_id());
					ps.setInt(5,contract.getDelivery_term_id());
					ps.setInt(6,contract.getPayment_term_id());
					ps.setInt(7,contract.getProposal_id());
					ps.setFloat(8,contract.getPrice());
					ps.setString(9,contract.getPeriod_of_delivery());
					java.sql.Date sqlDate = java.sql.Date.valueOf(contract.getInvoice_date());
					ps.setDate(10,sqlDate);
					ps.setInt(11,version );
					res = ps.execute();
					

					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
	}
		finally {
		try {
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
			}
		}
		
			
			return res;
			
		
		
		
	}

	
	

	@Override
	public ContractLog selectLatestContractLog(int contractId) {
		Connection conn = SQLConnection.getConnection();
		Statement stmt = null;
		
		ContractLog contractLog = new ContractLog();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("Select * from \"contractLog\" where \"contractId\" ="+contractId +"AND \"version\" = (Select max(\"version\") from \"contractLog\" where \"contractId\" ="+ contractId+")" );
			
			while(rs.next())
			{
				
				contractLog.setContract_id(rs.getInt(1));
				contractLog.setSeller_id(rs.getString(2));
				contractLog.setBuyer_id(rs.getString(3));
				contractLog.setStatus_id(rs.getInt(4));
				contractLog.setDelivery_term_id(rs.getInt(5));
				contractLog.setPayment_term_id(rs.getInt(6));
				contractLog.setProposal_id(rs.getInt(7));
				contractLog.setPrice(rs.getFloat(8));
				contractLog.setPeriod_of_delivery(rs.getString(9));
				contractLog.setInvoice_date(rs.getString(10));
				contractLog.setVersion(rs.getInt(11));

				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
		
		try {
			conn.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		}
			return contractLog;
	}

	@Override
	public ContractLog selectContractLog(int contract_id, int contractVersion) {
		Connection conn = SQLConnection.getConnection();
		Statement stmt = null;
		ContractLog contractLog = new ContractLog();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("Select * from \"contractLog\" where \"contractId\" ="+contract_id +"AND \"version\" ="+contractVersion);
			
			while(rs.next())
			{
				
				contractLog.setContract_id(rs.getInt(1));
				contractLog.setSeller_id(rs.getString(2));
				contractLog.setBuyer_id(rs.getString(3));
				contractLog.setStatus_id(rs.getInt(4));
				contractLog.setDelivery_term_id(rs.getInt(5));
				contractLog.setPayment_term_id(rs.getInt(6));
				contractLog.setProposal_id(rs.getInt(7));
				contractLog.setPrice(rs.getFloat(8));
				contractLog.setPeriod_of_delivery(rs.getString(9));
				contractLog.setInvoice_date(rs.getString(10));
				contractLog.setVersion(rs.getInt(11));
				
		
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
			
			return contractLog;
	}


	@Override
	public ArrayList<ContractLog> selectAllContractLogSeller(String sellerId) {
		Connection conn = SQLConnection.getConnection();
		ArrayList<ContractLog> historyContract = new ArrayList<>();
		ContractLog contract = new ContractLog();
		Statement stmt = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("Select * from \"contractLog\" where \"sellerId\" ="+ sellerId  );
			
			while(rs.next())
			{
				
				contract.setContract_id(rs.getInt(1));
				contract.setSeller_id(rs.getString(2));
				contract.setBuyer_id(rs.getString(3));
				contract.setStatus_id(rs.getInt(4));
				contract.setDelivery_term_id(rs.getInt(5));
				contract.setPayment_term_id(rs.getInt(6));
				contract.setProposal_id(rs.getInt(7));
				contract.setPrice(rs.getFloat(8));
				contract.setPeriod_of_delivery(rs.getString(9));
				contract.setInvoice_date(rs.getString(10));
				contract.setVersion(rs.getInt(11));
				
				historyContract.add(contract);
				
				
				
				
			}


		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
			
			return historyContract;
	}
	

	@Override
	public ArrayList<ContractLog> selectAllContractLogBuyer(String buyerId) {
		Connection conn = SQLConnection.getConnection();
		ArrayList<ContractLog> historyContract = new ArrayList<>();
		
		Statement stmt = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("Select * from \"contractLog\" where \"buyerId\" ="+buyerId  );
			
			while(rs.next())
			{
				ContractLog contract = new ContractLog();
				contract.setContract_id(rs.getInt(1));
				contract.setSeller_id(rs.getString(2));
				contract.setBuyer_id(rs.getString(3));
				contract.setStatus_id(rs.getInt(4));
				contract.setDelivery_term_id(rs.getInt(5));
				contract.setPayment_term_id(rs.getInt(6));
				contract.setProposal_id(rs.getInt(7));
				contract.setPrice(rs.getFloat(8));
				contract.setPeriod_of_delivery(rs.getString(9));
				contract.setInvoice_date(rs.getString(10));
				contract.setVersion(rs.getInt(11));
				
				historyContract.add(contract);
			
			}


		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
			
			return historyContract;
	}




	@Override
	public ArrayList<ContractLog> selectAllContractLogVersions(int contractId) {
		Connection conn = SQLConnection.getConnection();
		ArrayList<ContractLog> historyContract = new ArrayList<>();
		
		Statement stmt = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("Select * from \"contractLog\" where \"contractId\" ="+contractId  );
			
			while(rs.next())
			{
				ContractLog contract = new ContractLog();
				contract.setContract_id(rs.getInt(1));
				contract.setSeller_id(rs.getString(2));
				contract.setBuyer_id(rs.getString(3));
				contract.setStatus_id(rs.getInt(4));
				contract.setDelivery_term_id(rs.getInt(5));
				contract.setPayment_term_id(rs.getInt(6));
				contract.setProposal_id(rs.getInt(7));
				contract.setPrice(rs.getFloat(8));
				contract.setPeriod_of_delivery(rs.getString(9));
				contract.setInvoice_date(rs.getString(10));
				contract.setVersion(rs.getInt(11));
				
				historyContract.add(contract);

				
			}
	

		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
			
			return historyContract;
	}




//	@Override
//	public ContractLog selectContractLogStatus(int contract_id) {
//		ContractLog contract = new ContractLog();
//		Statement stmt = null;
//		try {
//			stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery("Select * from contractLog where contractId ="+contractId  );
//			
//			while(rs.next())
//			{
//				
//				contract.setContract_id(rs.getInt(1));
//				contract.setSeller_id(rs.getString(2));
//				contract.setBuyer_id(rs.getString(3));
//				contract.setStatus_id(rs.getInt(4));
//				contract.setDelivery_term_id(rs.getInt(5));
//				contract.setPayment_term_id(rs.getInt(6));
//				contract.setProposal_id(rs.getInt(7));
//				contract.setPrice(rs.getFloat(8));
//				contract.setPeriod_of_delivery(rs.getString(9));
//				contract.setInvoice_date(rs.getString(10));
//				contract.setVersion(rs.getInt(11));
//				
//				
//				
//				
//				
//				
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//			
//			return contract;
//	}
}
