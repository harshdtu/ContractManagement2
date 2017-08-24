package com.project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import com.project.data.ContractFeature;
import com.project.data.ContractProduct;
import com.project.data.ContractProductFeatureLog;
import com.project.database.SQLConnection;

public class ContractProductFeatureImpl implements ContractProductFeatureLogDao {
	Connection conn = SQLConnection.getConnection();
	
	@Override
	public boolean insertContractProductFeature(ArrayList<ContractProductFeatureLog> contract) {
		// TODO Auto-generated method stub
			PreparedStatement ps = null;
			boolean result = false;
				try {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select max(count) from contractProductFeatureLog where contractId="+contract.get(0).getContract_id());
					
				for(ContractProductFeatureLog c : contract) {
					ps = conn.prepareStatement("Insert into ContractProduct values(?,?,?,?)");
					ps.setInt(1,c.getContract_id());
					ps.setInt(4,c.getProduct_id());
					ps.setInt(2,c.getFeature_id());
					ps.setInt(3, rs.getInt(0)+1);
					}
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
	public ArrayList<ContractFeature> fetchContractFeature(int contractId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ContractProduct> fetchContractProduct(int contractId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
