package com.project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.project.data.ContractProductFeatureLog;
import com.project.data.ContractProductPrice;
import com.project.database.SQLConnection;

public class ContractProductPriceImpl implements ContractProductPriceDAO {
	 Connection conn = SQLConnection.getConnection();

	@Override
	public boolean insertContractProduct(ContractProductPrice contractProductPrice) {
		PreparedStatement ps = null;
		boolean result = false;
			try {
				ps = conn.prepareStatement("Insert into ContractPrice values(?,?,?,?,?)");
				ps.setInt(1,contractProductPrice.getContractId());
				ps.setInt(2,contractProductPrice.getProductId());
				ps.setDouble(3,contractProductPrice.getProductPrice());
				ps.setInt(4,contractProductPrice.getProductQuantity());
				ps.setInt(5,contractProductPrice.getContractVersion());
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
	public ArrayList<ContractProductPrice> selectContractProductDetails(int contractId, int contractVersion) {

ArrayList<ContractProductPrice> contractPrice = new ArrayList<>();
ContractProductPrice contract = new ContractProductPrice();
PreparedStatement pstmt = null;
try {
	Statement stmt = conn.createStatement();
	ResultSet version = stmt.executeQuery("select max(version) from contractPrice where contractId="+contractId);
	
	
	pstmt=conn.prepareStatement("select * from contractPrice where contractId= " + contractId+"AND version ="+contractVersion);
	ResultSet rs= pstmt.executeQuery();
	while(rs.next()) {
		
		contract.setContractId(rs.getInt(1));
		contract.setProductId(rs.getInt(2));
		contract.setProductPrice(rs.getDouble(3));
		contract.setProductQuantity(rs.getInt(4));
		contract.setContractVersion(contractVersion);
		contractPrice.add(contract);
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		return contractPrice;
	}

	@Override
	public ArrayList<ContractProductPrice> selectLatestContractProductDetails(
			int contractId) {
		ArrayList<ContractProductPrice> contractPrice = new ArrayList<>();
		ContractProductPrice contract = new ContractProductPrice();
		PreparedStatement pstmt = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet version = stmt.executeQuery("select max(version) from contractPrice where contractId="+contractId);
			
			
			pstmt=conn.prepareStatement("select * from contractPrice where contractId= " + contractId+"AND version ="+version.getInt(1));
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()) {
				
				contract.setContractId(rs.getInt(1));
				contract.setProductId(rs.getInt(2));
				contract.setProductPrice(rs.getDouble(3));
				contract.setProductQuantity(rs.getInt(4));
				contract.setContractVersion(version.getInt(1)+1);
				contractPrice.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				return contractPrice;
	}

	@Override
	public ArrayList<ContractProductPrice> selectAllContractProductDetails(
			int contractId) {
		ArrayList<ContractProductPrice> contractPrice = new ArrayList<>();
		ContractProductPrice contract = new ContractProductPrice();
		PreparedStatement pstmt = null;
		try {
//			Statement stmt = conn.createStatement();
			pstmt=conn.prepareStatement("select * from contractPrice where contractId= " + contractId);
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()) {
				
				contract.setContractId(rs.getInt(1));
				contract.setProductId(rs.getInt(2));
				contract.setProductPrice(rs.getDouble(3));
				contract.setProductQuantity(rs.getInt(4));
				contract.setContractVersion(rs.getInt(5));
				
				contractPrice.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				return contractPrice;
	}



	

}
