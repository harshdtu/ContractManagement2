package com.project.resource;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.project.Model.ContractImpl;

@Path("/contracts")
public class ContractResource {
	private ContractImpl contractImpl = new ContractImpl();
	@Path("/users/{userId}")
	public List<Contr>(@PathParam("userId")int userId) {
		return contractImpl.fetchAllContract(userId);
	}
}
