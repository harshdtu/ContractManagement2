package com.project.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.project.data.Contract;

@Path("/proposals/{proposalId}")
public class ProposalResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Contract getProposal(@PathParam("proposalId") int proposalId) {
		return new Contract();
	}
	
}
