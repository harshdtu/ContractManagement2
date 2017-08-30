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


	@Override
	public boolean insertContractProduct(ContractProductPrice contractProductPrice) {
		PreparedStatement ps = null;
		boolean result = false;
		int version=0;
		 Connection conn = SQLConnection.getConnection();
			try {
//				Statement stmt = conn.createStatement();
//
//				ResultSet rs = stmt.executeQuery("select max(\"version\") from \"contractPrice\" where \"contractId\"="+contractProductPrice.getContractId());
//				while(rs.next()) {
//					version =rs.getInt(1)+1;
//				}
				
				System.out.println("versionnn price" + contractProductPrice.getContractVersion());
				
				ps = conn.prepareStatement("Insert into \"contractPrice\" values(?,?,?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
		                ResultSet.CONCUR_UPDATABLE);

				ps.setInt(1,contractProductPrice.getContractId());
				ps.setInt(2,contractProductPrice.getProductId());
				ps.setDouble(3,contractProductPrice.getProductPrice());
				ps.setInt(4,contractProductPrice.getProductQuantity());
				ps.setInt(5,version);
				
				System.out.println("COntract Product price" +contractProductPrice.toString());
				result = ps.execute();
				
				conn.close();
				

			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			finally {
//				try {
////					conn.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			
			return result;
	}

	@Override
	public ArrayList<ContractProductPrice> selectContractProductDetails(int contractId, int contractVersion) {
		 Connection conn = SQLConnection.getConnection();
ArrayList<ContractProductPrice> contractPrice = new ArrayList<>();
ContractProductPrice contract = new ContractProductPrice();
PreparedStatement pstmt = null;
try {
	Statement stmt = conn.createStatement();
	ResultSet version = stmt.executeQuery("select max(\"version\") from \"contractPrice\" where \"contractId\" ="+contractId);
	
	
	pstmt=conn.prepareStatement("select * from \"contractPrice\" where \"contractId\" = " + contractId+"AND \"version\" ="+contractVersion);
	ResultSet rs= pstmt.executeQuery();
	while(rs.next()) {
		
		contract.setContractId(rs.getInt(1));
		contract.setProductId(rs.getInt(2));
		contract.setProductPrice(rs.getDouble(3));
		contract.setProductQuantity(rs.getInt(4));
		contract.setContractVersion(contractVersion);
		contractPrice.add(contract);
	}
	conn.close();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		return contractPrice;
	}

	@Override
	public ArrayList<ContractProductPrice> selectLatestContractProductDetails(
			int contractId) {
		 Connection conn = SQLConnection.getConnection();
		ArrayList<ContractProductPrice> contractPrice = new ArrayList<>();
		ContractProductPrice contract = new ContractProductPrice();
//Statement pstmt = null;
		try {
//			Statement stmt = conn.createStatement();
//			ResultSet version = stmt.executeQuery("select max(\"version\") from \"contractPrice\" where \"contractId\" ="+contractId);
			PreparedStatement pstmt=conn.prepareStatement("Select * from \"contractPrice\" where \"contractId\"= " + contractId+"AND \"version\" ="+"(Select max(\"version\") from \"contractPrice\" where \"contractId\" ="+contractId+")");
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()) {
				
				contract.setContractId(rs.getInt(1));
				contract.setProductId(rs.getInt(2));
				contract.setProductPrice(rs.getDouble(3));
				contract.setProductQuantity(rs.getInt(4));
				contract.setContractVersion(rs.getInt(5)+1);
				contractPrice.add(contract);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				return contractPrice;
	}

	@Override
	public ArrayList<ContractProductPrice> selectAllContractProductDetails(
			int contractId) {
		 Connection conn = SQLConnection.getConnection();
		ArrayList<ContractProductPrice> contractPrice = new ArrayList<>();
		ContractProductPrice contract = new ContractProductPrice();
		PreparedStatement pstmt = null;
		try {
//			Statement stmt = conn.createStatement();
			pstmt=conn.prepareStatement("select * from \"contractPrice\" where \"contractId\" = " + contractId);
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()) {
				
				contract.setContractId(rs.getInt(1));
				contract.setProductId(rs.getInt(2));
				contract.setProductPrice(rs.getDouble(3));
				contract.setProductQuantity(rs.getInt(4));
				contract.setContractVersion(rs.getInt(5));
				
				contractPrice.add(contract);
			}
//			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			 try {
				
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				return contractPrice;
	}



	

}
