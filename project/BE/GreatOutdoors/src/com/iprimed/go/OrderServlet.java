package com.iprimed.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/*----------------Get request body----------------------*/
		String data;
		
		String line;
	    
		StringBuilder builder = new StringBuilder();
	    
	
		BufferedReader reader = request.getReader();
	    
	    while ((line = reader.readLine()) != null) {
	        builder.append(line);
	    }
	    
	    data = builder.toString();
	    
        /*-------------Convert request body to json--------------------*/
	    
	    JsonParser parser = new JsonParser();
	    
	    JsonObject orderObj = parser.parse(data).getAsJsonObject();
		
	    /*------------place order----------------*/
		 
	    DBManager dbManager = new DBManager();
	   
	    int cartId = orderObj.get("cartId").getAsInt();
	    int userId = orderObj.get("userId").getAsInt();
	    int productId = orderObj.get("productId").getAsInt();
	    int quantity = orderObj.get("quantity").getAsInt();
	    int price = orderObj.get("price").getAsInt();
	    
	    JsonArray array = orderObj.get("addresses").getAsJsonArray();
        
		
		JSONManager jsonManager = new JSONManager();
		
		List<DeliveryAddress> addressList = jsonManager.addressJsonToAddressObject(array);
		
		
		int orderId = dbManager.placeOrder(userId, productId, quantity, price);
		
		int[] addressIdArray = dbManager.saveAddresses(addressList);
		
		PrintWriter writer = response.getWriter();
		
		JsonObject messageObj;
		
		EmailManager eManager = new EmailManager();
		
		if(dbManager.mapOrderAddress(orderId, addressIdArray)) {
		  
			messageObj = new JsonObject();
			messageObj.addProperty("message", orderId);
			writer.write(messageObj.toString());
			
			String to = dbManager.getUserMail(userId);
			
			String productName = dbManager.getProductName(productId);
			
			try {
			
			eManager.sendMail(to, productName, quantity, price);

			}catch(Exception e) {
				System.out.println("Email error");
			}
			
			
			if(cartId > 0) {
				
				dbManager.removeItemFromCart(cartId);
				
			}
			
			
	    }else {
	    	messageObj = new JsonObject();
			messageObj.addProperty("message", -1);
			writer.write(messageObj.toString());
	    }
	    	
		
	}
	
	
}
