package com.project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.data.ContractFeatureLog;
import com.project.database.SQLConnection;

public class ContractFeatureLogImpl implements ContractFeatureLogDao {
	 Connection conn = SQLConnection.getConnection();
	@Override
	public boolean addContractFeature(ContractFeatureLog contractFeatureLog) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		boolean result = false;
			try {
				ps = conn.prepareStatement("Insert into ContractFeatureLog values(?,?)");
				ps.setInt(1,contractFeatureLog.getContract_id());
				ps.setInt(2,contractFeatureLog.getFeature_id());
				
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
	public ArrayList<ContractFeatureLog> selectContractFeature(int contract_id) {
		// TODO Auto-generated method stub
		Statement stmt = null;
		ArrayList<ContractFeatureLog> contractFeatureLog = new ArrayList<>();
		ContractFeatureLog contractFeature = new ContractFeatureLog();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from ContractFeatureLog where contract_id ="+contract_id);
			
			while(rs.next())
			{
				
				contractFeature.setContract_id(rs.getInt(1));
				contractFeature.setFeature_id(rs.getInt(2));
				
				contractFeatureLog.add(contractFeature);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
			return contractFeatureLog;
	}

	

	

}
