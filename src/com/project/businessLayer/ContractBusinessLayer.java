package com.project.businessLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.Model.ContractLogImpl;
import com.project.Model.ContractProductFeatureImpl;
import com.project.Model.ContractProductPriceImpl;
import com.project.data.Contract;
import com.project.data.ContractLog;
import com.project.data.ContractProductFeatureLog;
import com.project.data.ContractProductPrice;
import com.project.data.Feature;
import com.project.data.Product;
import com.project.database.SQLConnection;

public class ContractBusinessLayer {
	ContractLogImpl contractLogImpl = new ContractLogImpl();
	ContractProductFeatureImpl contractProductFeatureLog = new ContractProductFeatureImpl();
	ContractProductPriceImpl contractPrice = new ContractProductPriceImpl();

	public Contract getSpecificContract(int contractId, int contractVersion) {
		ArrayList<Feature> Contractfeature = new ArrayList<>();
		ArrayList<Product> ContractProduct = new ArrayList<>();
		Contract contract = new Contract();
		Product product = new Product();
		ContractLog contractLog = contractLogImpl.selectContractLog(contractId, contractVersion);
		contract.setContract(contractLog.getContract_id());
		contract.setBuyerId(contractLog.getBuyer_id());
		contract.setSellerId(contractLog.getSeller_id());
		contract.setDeliveryTermId(contractLog.getDelivery_term_id());
		contract.setPaymentTermId(contractLog.getPayment_term_id());
		contract.setDateofInvoice(contractLog.getInvoice_date());
		contract.setStatus(contractLog.getVersion());
		ArrayList<ContractProductPrice> contractProPrice = contractPrice.selectContractProductDetails(contractId,
				contractVersion);
		for (ContractProductPrice contractPrice : contractProPrice) {
			product.setId(contractPrice.getProductId());
			product.setPrice(contractPrice.getProductPrice());
			product.setCategory(1); // fetch from api - TODO
			product.setProductName("Mobile"); // fetch from api _TODO
			product.setQuantity(contractPrice.getProductQuantity());
			ArrayList<ContractProductFeatureLog> contractFeature = contractProductFeatureLog
					.fetchContractProductFeature(contractId, contractVersion);
			for (ContractProductFeatureLog contractPF : contractFeature) {
				Feature featureobj = new Feature();
				featureobj.setFeatureId(contractPF.getFeatureId());
				featureobj.setName("Feature");// fetch from api_TODO
				Contractfeature.add(featureobj);
			}
			product.setFeature(Contractfeature);
			ContractProduct.add(product);
		}
		contract.setProduct(ContractProduct);
		return contract;
	}

	public Contract getLatestContract(int contractId) {

		ContractLog contractLog = contractLogImpl.selectLatestContractLog(contractId);
		Contract contract = new Contract();
		Product product = new Product();
		Feature feature = new Feature();
		ArrayList<Product> contractProduct = new ArrayList<>();
		ArrayList<Feature> contractFeature = new ArrayList<>();
		contract.setContract(contractLog.getContract_id());
		contract.setBuyerId(contractLog.getBuyer_id());
		contract.setSellerId(contractLog.getSeller_id());
		contract.setDateofInvoice(contractLog.getInvoice_date());
		contract.setDeliveryTermId(contractLog.getDelivery_term_id());
		contract.setPaymentTermId(contractLog.getPayment_term_id());
		contract.setStatus(contractLog.getVersion());
		ArrayList<ContractProductPrice> contractProductPrice = contractPrice
				.selectLatestContractProductDetails(contractId);

		for (ContractProductPrice contractPP : contractProductPrice) {

			product.setId(contractPP.getProductId());
			product.setPrice(contractPP.getProductPrice());
			product.setCategory(1); // fetch from api - TODO
			product.setProductName("Mobile"); // fetch from api _TODO
			product.setQuantity(contractPP.getProductQuantity());
			ArrayList<ContractProductFeatureLog> contractProductFeature = contractProductFeatureLog
					.fetchFinalContractProductFeature(contractId);
			for (ContractProductFeatureLog contractPF : contractProductFeature) {

				feature.setFeatureId(contractPF.getFeatureId());
				feature.setName("Feature");// fetch from api_TODO
				contractFeature.add(feature);
			}
			product.setFeature(contractFeature);
			contractProduct.add(product);
		}
		contract.setProduct(contractProduct);

		return contract;
	}

	public boolean insertContract(Contract contract) {

		ContractLog contractLog = new ContractLog();
		int version=0;
		ArrayList<ContractProductFeatureLog> contractPFLog = new ArrayList<ContractProductFeatureLog>();
		ContractProductPrice price = new ContractProductPrice();
		contractLog.setContract_id(contract.getContract());
		contractLog.setBuyer_id(contract.getBuyerId());
		contractLog.setSeller_id(contract.getSellerId());
		contractLog.setDelivery_term_id(contract.getDeliveryTermId());
		contractLog.setPayment_term_id(contract.getPaymentTermId());
		contractLog.setInvoice_date(contract.getDateofInvoice());
		contractLog.setPeriod_of_delivery(contract.getPeriodOfDelivery());
		contractLog.setStatus_id(contract.getStatus());
		contractLogImpl.insertContractLog(contractLog);

		ArrayList<Product> product = contract.getProduct();
		for (Product contractProduct : product) {
			// get version _TODO;

			ArrayList<Feature> feature = contractProduct.getFeature();

			for (Feature ft : feature) {
				ContractProductFeatureLog PFLog = new ContractProductFeatureLog();
				PFLog.setContractId(contract.getContract());
				PFLog.setProductId(contractProduct.getId());
				PFLog.setVersion(0);
				PFLog.setFeatureId(ft.getFeatureId());
				contractPFLog.add(PFLog);
				System.out.println("Check featuree   " + PFLog.getFeatureId() + "  product " + PFLog.getProductId());

			}

			price.setContractId(contract.getContract());
			price.setProductId(contractProduct.getId());
			price.setProductPrice(contractProduct.getPrice());
			price.setProductQuantity(contractProduct.getQuantity());
			Connection conn = SQLConnection.getConnection();
			try {
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs;
				rs = stmt.executeQuery("select max(\"version\") from \"contractPrice\" where \"contractId\"="+contract.getContract());
				if(rs.first()) {
						version=rs.getInt(1)+1;
						System.out.println("ALL VERSION Price" + version);
					}
					
				
			conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			price.setContractVersion(version);// set contract version

			System.out.println("Check pp" + price.toString());
			contractPrice.insertContractProduct(price);

		}
		System.out.println("*********************");
		
		System.out.println("COUNTTTTT");
		for (ContractProductFeatureLog c : contractPFLog) {
			System.out.println(c);
		}
		System.out.println("*********************");

		
		contractProductFeatureLog.insertContractProductFeature(contractPFLog);

		return true;
	}

	public int getContractStatus(int contractId) {

		ContractLog contract = contractLogImpl.selectLatestContractLog(contractId);
		return contract.getStatus_id();
	}

	public ArrayList<Contract> getAllContractsVersion(int contractId) {

		ArrayList<Contract> contractList = new ArrayList<>();
		ArrayList<Product> contractProduct = new ArrayList<>();
		ArrayList<Feature> contractFeature = new ArrayList<>();
		Feature feature = new Feature();
		Product product = new Product();
		Contract contract = new Contract();
		ArrayList<ContractLog> contractLog = contractLogImpl.selectAllContractLogVersions(contractId);
		ArrayList<ContractProductFeatureLog> contractPFLog = contractProductFeatureLog
				.fetchVersionsContractProductFeature(contractId);
		ArrayList<ContractProductPrice> contractPPrice = contractPrice.selectAllContractProductDetails(contractId);
		for (ContractLog cl : contractLog) {

			contract.setBuyerId(cl.getBuyer_id());
			contract.setContract(cl.getContract_id());
			contract.setDateofInvoice(cl.getInvoice_date());
			contract.setDeliveryTermId(cl.getDelivery_term_id());
			contract.setPaymentTermId(cl.getPayment_term_id());
			contract.setSellerId(cl.getSeller_id());
			contract.setStatus(cl.getStatus_id());
			for (ContractProductPrice price : contractPPrice) {

				product.setPrice(price.getProductPrice());
				product.setId(price.getProductId());
				product.setProductName("Mobile");// TODO fetch from api
				product.setCategory(0);// TODO fetch from api
				product.setQuantity(price.getProductQuantity());

				for (ContractProductFeatureLog pfLog : contractPFLog) {
					if (pfLog.getProductId() == price.getProductId()) {
						feature.setFeatureId(pfLog.getFeatureId());
						feature.setName("gorilla screen");// TODO fetch from api

						contractFeature.add(feature);

					} else {
						continue;
					}
					product.setFeature(contractFeature);
				}
				contractProduct.add(product);
			}
			contract.setProduct(contractProduct);
			contractList.add(contract);
		}
		return contractList;
	}

	public ArrayList<Contract> getAllContractsByUser(String userType, String userId) {
		ArrayList<Contract> contract = new ArrayList<>();
		ArrayList<ContractProductFeatureLog> contractPf = new ArrayList<>();
		ArrayList<ContractProductPrice> price = new ArrayList<>();
		ContractProductPrice cp = new ContractProductPrice();
		ContractProductFeatureLog PFLog = new ContractProductFeatureLog();

		Contract contractObj = new Contract();
		ArrayList<Product> contractProduct = new ArrayList<>();
		ArrayList<Feature> contractFeature = new ArrayList<>();
		Feature feature = new Feature();
		Product product = new Product();

		if (userType == "Seller") {

			ArrayList<ContractLog> contractLog = contractLogImpl.selectAllContractLogSeller(userId);
			for (ContractLog cl : contractLog) {

				contractPf = contractProductFeatureLog.fetchFinalContractProductFeature(cl.getContract_id());
				price = contractPrice.selectLatestContractProductDetails(cl.getContract_id());

				contractObj.setContract(cl.getContract_id());
				contractObj.setBuyerId(cl.getBuyer_id());
				contractObj.setDateofInvoice(cl.getInvoice_date());
				contractObj.setDeliveryTermId(cl.getDelivery_term_id());
				contractObj.setPaymentTermId(cl.getPayment_term_id());
				contractObj.setBuyerId(cl.getSeller_id());

				for (ContractProductPrice p : price) {
					product.setCategory(0);
					product.setId(p.getProductId());
					product.setPrice(p.getProductPrice());
					product.setProductName("Mobile");

					for (ContractProductFeatureLog cpfLog : contractPf) {
						feature.setFeatureId(cpfLog.getFeatureId());
						feature.setName("ABCD");

						contractFeature.add(feature);

					}

					product.setFeature(contractFeature);
					contractProduct.add(product);
				}

				contractObj.setProduct(contractProduct);
				contract.add(contractObj);

			}

		}
		// else
		// {
		// ArrayList<ContractLog> contractLog =
		// contractLogImpl.selectAllContractLogSeller(userId);
		// for(ContractLog cl : contractLog) {
		//
		// contractPf =
		// contractProductFeatureLog.fetchFinalContractProductFeature(cl.getContract_id());
		// price =
		// contractPrice.selectLatestContractProductDetails(cl.getContract_id());
		//
		// contractObj.setContract(cl.getContract_id());
		// contractObj.setBuyerId(cl.getBuyer_id());
		// contractObj.setDateofInvoice(cl.getInvoice_date());
		// contractObj.setDeliveryTermId(cl.getDelivery_term_id());
		// contractObj.setPaymentTermId(cl.getPayment_term_id());
		// contractObj.setBuyerId(cl.getSeller_id());
		//
		// for(ContractProductPrice p : price) {
		// product.setCategory(0);
		// product.setId(p.getProductId());
		// product.setPrice(p.getProductPrice());
		// product.setProductName("Mobile");
		//
		//
		// for(ContractProductFeatureLog cpfLog : contractPf) {
		// feature .setFeatureId(cpfLog.getFeatureId());
		// feature.setName("ABCD");
		//
		// contractFeature.add(feature);
		//
		// }
		//
		// product.setFeature(contractFeature);
		// contractProduct.add(product);
		// }
		//
		//
		//
		// contractObj.setProduct(contractProduct);
		// contract.add(contractObj);
		// return contract;
		//
		// }
		//
		// }

		return contract;
	}
}
