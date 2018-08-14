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
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	
	
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		System.out.println(userId);
		
		 DBManager dbManager = new DBManager();
		 
		 List<Cart> cartList = dbManager.showCart(userId);
		 
		 for(Cart c: cartList) {
			 System.out.println(c.getCartId()+" "+c.getProductId()+" "+c.getQuantity());
		 }

		 JSONManager jsonManager = new JSONManager();
		 
		 JsonArray cartArray = jsonManager.cartListToJsonArray(cartList);
		 
		 System.out.println(cartArray.toString());
		 
		 PrintWriter writer = response.getWriter();
		 	 
		 
		 writer.write(cartArray.toString());
		 
		
		
	}

	
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
	    
	    JsonObject requestObj = parser.parse(data).getAsJsonObject();
		
	    /*------------add to cart----------------*/
		 
	    DBManager dbManager = new DBManager();
        
	    PrintWriter writer = response.getWriter();
	    
	    
	   
	    int operation = requestObj.get("operation").getAsInt();
	    
	    if(operation == 1) {
	    
	    int userId = requestObj.get("userId").getAsInt();
	    int productId = requestObj.get("productId").getAsInt();
	    int quantity = requestObj.get("quantity").getAsInt();
	    
	    JsonObject messageObj;
	   
	    
	    if(dbManager.addToCart(userId, productId, quantity)) {
	    	
	    	messageObj = new JsonObject();
	    	messageObj.addProperty("message", 1);
	    	writer.write(messageObj.toString());
	    	
	    }else {
	    	
	    	messageObj = new JsonObject();
	    	messageObj.addProperty("message", -1);
	    	writer.write(messageObj.toString());
	    }
	    
	  }else if(operation == -1) {
		  
		  int cartId = requestObj.get("cartId").getAsInt();
		  
		  JsonObject messageObj;
		  
		  if(dbManager.removeItemFromCart(cartId)) {
			  
			    messageObj = new JsonObject();
		    	messageObj.addProperty("message",1);
		    	writer.write(messageObj.toString());
			  
		  }else {
			 
			    messageObj = new JsonObject();
		    	messageObj.addProperty("message", -1);
		    	writer.write(messageObj.toString());
		  }
		  
		  
	  }
		
	}

}
