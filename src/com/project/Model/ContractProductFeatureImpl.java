package com.project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.data.ContractProductFeatureLog;
import com.project.database.SQLConnection;

public class ContractProductFeatureImpl implements ContractProductFeatureLogDao {
	Connection conn = SQLConnection.getConnection();

	@Override
	public boolean insertContractProductFeature(ArrayList<ContractProductFeatureLog> contractProductFeatureLog) {
		PreparedStatement ps = null;
		boolean result = false;
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select max(\"version\") from \"contractFeatureProductLog\" where \"contractId\"="+contractProductFeatureLog.get(0).getContractId());
				int version = rs.getInt(1) + 1;
				for(ContractProductFeatureLog con : contractProductFeatureLog)
				{
					ps = conn.prepareStatement("Insert into \"ContractLog\" values(?,?,?,?,?,?,?,?,?,?)");
					ps.setInt(1,con.getContractId());
					ps.setInt(4,con.getProductId());
					ps.setInt(2,con.getFeatureId());
					ps.setInt(3,version);
					
				}
					
			
			
				
				
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

	@Override
	public ArrayList<ContractProductFeatureLog> fetchContractProductFeature(int contractId, int contractVersion) {
	ArrayList<ContractProductFeatureLog> contractPFLog = new ArrayList<>();
	Statement stmt = null;
	ContractProductFeatureLog contract = new ContractProductFeatureLog();
	try {
	stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery("Select * from \"contractLog\" where \"contractId\" ="+contractId +"AND \"version\" ="+ contractVersion );
	
	while(rs.next())
	{
		
		contract.setContractId(rs.getInt(1));
		contract.setProductId(rs.getInt(4));
		contract.setFeatureId(rs.getInt(2));
		contract.setVersion(rs.getInt(3));
		
		contractPFLog.add(contract);
		
		
	}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return contractPFLog;
	
	

	
}

	@Override
	public ArrayList<ContractProductFeatureLog> fetchFinalContractProductFeature(
			int contractId) {
		ArrayList<ContractProductFeatureLog> contractPFLog = new ArrayList<>();
		Statement stmt = null;
		ContractProductFeatureLog contract = new ContractProductFeatureLog();
		try {
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("Select * from \"contractLog\" where \"contractId\" ="+contractId +"AND \"version\" = (Select max(\"version\") from \"contractLog\" where \"contractId\" ="+ contractId+")" );
		
		while(rs.next())
		{
			
			contract.setContractId(rs.getInt(1));
			contract.setProductId(rs.getInt(4));
			contract.setFeatureId(rs.getInt(2));
			contract.setVersion(rs.getInt(3));
			
			contractPFLog.add(contract);
			
			
		}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contractPFLog;
		
	}

	@Override
	public ArrayList<ContractProductFeatureLog> fetchVersionsContractProductFeature(
			int contractId) {
		ArrayList<ContractProductFeatureLog> contractPFLog = new ArrayList<>();
		Statement stmt = null;
		ContractProductFeatureLog contract = new ContractProductFeatureLog();
		try {
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("Select * from \"contractLog\" where \"contractId\" ="+contractId );
		
		while(rs.next())
		{
			
			contract.setContractId(rs.getInt(1));
			contract.setProductId(rs.getInt(4));
			contract.setFeatureId(rs.getInt(2));
			contract.setVersion(rs.getInt(3));
			
			contractPFLog.add(contract);
			
			
		}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contractPFLog;
		
	}
}
