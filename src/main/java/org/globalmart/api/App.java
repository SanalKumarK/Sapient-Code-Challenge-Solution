package org.globalmart.api;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Global Mart Services.
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String[] services = new String[]{"globalMartDSService","catalogueService", "priceService"};
    	
    	String serviceName = "";    	
    	if(args.length > 0) {
    		serviceName = args[0];
    	} else {
    		System.err.println("Please enter a valid Service Name:\n" + String.join(",", services));
    		System.exit(0);
    	}
    	
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");        
        try{
        	GlobalMartService service = (GlobalMartService) applicationContext.getBean(serviceName);
        	System.out.println("Starting Global Mart Service - " + serviceName);
        	service.initServer();	
        } catch(Exception e){
        	System.err.println("Please enter a valid Service Name:\n" + String.join(",", services));
        	System.exit(0);
        }
    }
}
