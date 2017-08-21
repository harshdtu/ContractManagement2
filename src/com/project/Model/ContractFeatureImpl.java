package com.project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.project.data.ContractFeature;
import com.project.database.SQLConnection;

public class ContractFeatureImpl implements ContractFeatureDao {
  Connection conn = SQLConnection.getConnection();
	@Override
	public boolean addContractFeature(ContractFeature contractFeature) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		boolean result = false;
			try {
				ps = conn.prepareStatement("Insert into ContractFeature values(?,?)");
				ps.setInt(1,contractFeature.getContract_id());
				ps.setInt(2,contractFeature.getFeature_id());
				
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

	

}
