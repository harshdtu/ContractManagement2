package com.project.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.data.DeliveryTerm;
import com.project.data.PaymentTerm;
import com.project.database.SQLConnection;

public class PaymentsImpl implements PaymentsDao {


	public ArrayList<PaymentTerm> fetchAllPaymentTerms() {

		// TODO Auto-generated method stub
		ArrayList<PaymentTerm> payment = new ArrayList<>();
	
		Connection conn = SQLConnection.getConnection();
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt
					.executeQuery("select * from \"Payment_terms\"");
			while (rs.next()) {
				PaymentTerm pay = new PaymentTerm();
				pay.setId(rs.getInt(1));
				pay.setDesc(rs.getString(3));
                payment.add(pay);
				
			}
		}
			catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}

			return payment;

	

	}	

}
