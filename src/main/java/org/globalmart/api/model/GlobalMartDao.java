package org.globalmart.api.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GlobalMartDao {

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Response createProductTable() {
		Connection c = null;
		Statement stmt = null;
		Response response = new Response();
		try {
			c = getGlobalMartConn();
			
			stmt = c.createStatement();
			String sql = "create table Product( "
					+ "name TEXT UNIQUE, "
					+ "Id INTEGER PRIMARY KEY,"
					+ " type TEXT,"
					+ "price REAL,"
					+ "manufacturer TEXT,"
					+ "productionDate DATE,"
					+ "expirationDate DATE)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
			response.setStatus("SUCCESS");
			response.setMessage("Table created successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			response.setStatus("FAILURE");
			response.setMessage("Table already exists");
		}
		
		return response;
	}

	public Response addProduct(ProductRequest product) {
		Response response = new Response();
		Connection c = null;
		Statement stmt = null;
		try {
			c = getGlobalMartConn();
			
			stmt = c.createStatement();
			String query = " INSERT INTO Product "
					+ "(NAME,ID, TYPE, PRICE, MANUFACTURER) " + "VALUES ('"
					+ product.getName() + "'," + product.getId() + "," + "'" + product.getType() + "',"
					+ product.getPrice() + "," + "'" + product.getManufacturer() + "');";
			stmt.executeUpdate(query);

			stmt.close();
			c.commit();
			c.close();
			response.setStatus("SUCCESS");
			response.setMessage("Product "+product.getName()+" created successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			response.setStatus("FAILURE");
			response.setMessage("Product is not added successfully " + e.getMessage());
		}
		return response;
	}

	public Product getProduct(String name) {
		Connection c = null;
		Statement stmt = null;
		Product product = null;
		try {
			c = getGlobalMartConn();

			stmt = c.createStatement();
			String query = " SELECT * FROM PRODUCT WHERE NAME LIKE '%" + name + "%';";

			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				product = new Product();
				product.setId(rs.getInt(Product.COL_ID));
				product.setName(rs.getString(Product.COL_NAME));
				product.setType(rs.getString(Product.COL_TYPE));
				product.setPrice(rs.getLong(Product.COL_PRICE));
				product.setManufacturer(rs.getString(Product.COL_MANUF));
			}
			rs.close();

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return product;
	}
	
	public ArrayList<Product> getProducts(String productType) {
		Connection c = null;
		Statement stmt = null;
		Product product = null;
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			c = getGlobalMartConn();

			stmt = c.createStatement();
			String query = " SELECT * FROM PRODUCT WHERE TYPE LIKE '%" + productType + "%';";

			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				product = new Product();
				product.setId(rs.getInt(Product.COL_ID));
				product.setName(rs.getString(Product.COL_NAME));
				product.setType(rs.getString(Product.COL_TYPE));
				product.setPrice(rs.getLong(Product.COL_PRICE));
				product.setManufacturer(rs.getString(Product.COL_MANUF));
				products.add(product);
			}
			rs.close();

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return products;
	}
	
	public Response deleteProduct(String name) {
		Response response = new Response();
		Connection c = null;
		Statement stmt = null;
		try {
			c = getGlobalMartConn();
			Product product = getProduct(name);
			if(product!=null){
				stmt = c.createStatement();
				String query = "DELETE FROM PRODUCT WHERE NAME='"+name+"';";
				stmt.executeUpdate(query);

				stmt.close();
				c.commit();
				c.close();
				response.setStatus("SUCCESS");
				response.setMessage("Product " + name +" deleted successfully");
			} else {
				response.setStatus("FAILURE");
				response.setMessage("Product " + name +" is not found");
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			response.setStatus("FAILURE");
			response.setMessage("Product " + name +" is not deleted ");
		}
		return response;
	}
	
	private Connection getGlobalMartConn(){
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:sqlite:globalmart.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
}
