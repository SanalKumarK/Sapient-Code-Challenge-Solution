package org.globalmart.api.catalogue;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.globalmart.api.model.GlobalMartDao;
import org.globalmart.api.model.Product;
import org.globalmart.api.model.ProductRequest;
import org.globalmart.api.model.Response;

@Path("ProductCatalogue")
public class ProductCatalogueAPI {

	private GlobalMartDao globalMartDao = null;
	
	private GlobalMartDao getGlobalMartDao(){
		if(globalMartDao == null) {
			globalMartDao = new GlobalMartDao();
		}
		return globalMartDao;
	}
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProduct(ProductRequest product){
		return getGlobalMartDao().addProduct(product);		
	}
	
	@GET
	@Path("find")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Product> findProduct(@QueryParam("productType") String productType){
		return getGlobalMartDao().getProducts(productType);
	}
	
	@DELETE
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProduct(@QueryParam("name") String name){
		return getGlobalMartDao().deleteProduct(name);
	}
}
