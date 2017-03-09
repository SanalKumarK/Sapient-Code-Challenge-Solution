package org.globalmart.api.db;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.globalmart.api.model.GlobalMartDao;
import org.globalmart.api.model.Response;

@Path("GlobalMartDS")
public class GlobalMartDSAPI {
	
	private GlobalMartDao globalMartDao = null;
	
	private GlobalMartDao getGlobalMartDao(){
		if(globalMartDao == null) {
			globalMartDao = new GlobalMartDao();
		}
		return globalMartDao;
	}

	@GET
	@Path("createProductTable")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProductTable(){
		return getGlobalMartDao().createProductTable();
	}
}
