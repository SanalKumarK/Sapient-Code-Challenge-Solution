package org.globalmart.api.pricing;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.globalmart.api.model.GlobalMartDao;
import org.globalmart.api.model.PriceResponse;
import org.globalmart.api.model.Product;


@Path("/ProductPrice")
public class ProductPriceAPI {

	private GlobalMartDao globalMartDao = null;
	
	private GlobalMartDao getGlobalMartDao(){
		if(globalMartDao == null) {
			globalMartDao = new GlobalMartDao();
		}
		return globalMartDao;
	}
	
	@GET
	@Path("price")
	public PriceResponse getPrice(@QueryParam("name") String name) {
		Product product = getGlobalMartDao().getProduct(name);
		PriceResponse response = new PriceResponse();
		if(product != null) {
			response.setName(product.getName());
			response.setPrice(product.getPrice().toString());	
		} else {
			System.out.println("Product " + name + " is not existing in the catalogue!!");
		}
		return response;
	}
}
