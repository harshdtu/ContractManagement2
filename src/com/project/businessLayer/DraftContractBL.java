package com.project.businessLayer;

import com.project.data.Contract;
import com.project.data.Feature;
import com.project.data.Product;
import com.project.restClient.RestApiCalls;
import com.project.restClient.pojo.SelectedProposal;
import com.project.restClient.pojo.SelectedProposalProduct;
import com.project.restClient.pojo.SelectedProposalProductFeature;

public class DraftContractBL {
	private Contract mapContractProposal(SelectedProposal proposal) {
		Contract contract = new Contract();
		contract.setStatus(0);
		// buyid value hard-coded
		contract.setBuyerId("buyer@com");
		// proposals team returning integer as seller id
		contract.setSellerId(proposal.getBidsellerid().toString());
		contract.setDeliveryTermId(proposal.getDTermId());
		contract.setPaymentTermId(proposal.getPTermId());
		for(SelectedProposalProduct proposalProduct: proposal.getProducts()) {
			Product p = new Product();
			p.setId(proposalProduct.getId());
			p.setProductName(proposalProduct.getNameOfProduct());
			p.setQuantity(proposalProduct.getQuantity());
			// proposals team doesn't return price
			p.setPrice(29182.221);
			// proposals team doesn't return category
			p.setCategory(1);
			for(SelectedProposalProductFeature productFeature: proposalProduct.getFeatures()) {
				Feature f = new Feature();
				f.setFeatureId(productFeature.getFid());
				f.setName(productFeature.getFeatureName());
				p.addFeature(f);
			}
			
			contract.addProduct(p);
		}
		return contract;
	}
	
	public Contract getDraftContract(int proposalId) {
		SelectedProposal proposal = RestApiCalls.getProposal(proposalId);
		Contract contract = mapContractProposal(proposal);
		// setting contract id same as proposal id
		contract.setContract(proposalId);
		return contract;
	}
}
