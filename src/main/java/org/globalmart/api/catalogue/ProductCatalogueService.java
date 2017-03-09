package org.globalmart.api.catalogue;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.globalmart.api.GlobalMartService;

public class ProductCatalogueService implements GlobalMartService {

	public void initServer() {
		System.out.println("Product Catalogue Service is initializing...");
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		
		Server jettyServer = new Server(8081);
		jettyServer.setHandler(context);
		
		//It holds the name, params, and some state of the servlet.
		//Holder will organize the loading of the servlet when needed
		ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);
		
		//Tells the servlet which REST class to load 
		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", ProductCatalogueAPI.class.getCanonicalName());
		
		try {
			System.out.println("Product Catalogue Service is started at 8081");
			jettyServer.start();
			jettyServer.join();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jettyServer.destroy();
		}
		
	}
}
