package com.project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.project.data.ContractFeature;
import com.project.data.ContractProduct;
import com.project.database.SQLConnection;

public class ContractProductImpl implements ContractProductFeatureDao {
	Connection conn = SQLConnection.getConnection();
	
	@Override
	public boolean insertContractProduct(ContractProduct contractProduct) {
		// TODO Auto-generated method stub
			PreparedStatement ps = null;
			boolean result = false;
				try {
					ps = conn.prepareStatement("Insert into ContractProduct values(?,?)");
					ps.setInt(1,contractProduct.getContract_id());
					ps.setInt(2,contractProduct.getProduct_id());
					
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
