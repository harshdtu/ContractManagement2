package com.project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.project.data.ContractProductFeatureLog;
import com.project.database.SQLConnection;

public class ContractProductLogImpl implements ContractProductLogDao {
	 Connection conn = SQLConnection.getConnection();

	@Override
	public ArrayList<ContractProductFeatureLog> selectContractProduct(int contract_id) {
		// TODO Auto-generated method stub
		
		ArrayList<ContractProductFeatureLog> contractProductLog = new ArrayList<>();
		ContractProductFeatureLog contractProduct = new ContractProductFeatureLog();
		try {
			PreparedStatement pstmt = conn.prepareStatement("Select * from ContractproductLog where contract_id ="+contract_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				
				contractProduct.setContract_id(rs.getInt(1));
				contractProduct.setProduct_id(rs.getInt(2));
				
				contractProductLog.add(contractProduct);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
			return contractProductLog;
	}

	@Override
	public boolean insertContractProduct(ContractProductFeatureLog contractProductLog) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		boolean result = false;
			try {
				ps = conn.prepareStatement("Insert into ContractFeatureLog values(?,?)");
				ps.setInt(1,contractProductLog.getContract_id());
				ps.setInt(2,contractProductLog.getProduct_id());
				
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
