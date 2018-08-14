package com.iprimed.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class QueryServlet
 */
@WebServlet("/QueryServlet")
public class QueryServlet extends HttpServlet {

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
	    
	    JsonObject queryObj = parser.parse(data).getAsJsonObject();
		
	   
	    /*--------------post query----------------------------*/
		
		DBManager dbManager = new DBManager();
		
		String firstName = queryObj.get("firstName").getAsString();
		
		String lastName = queryObj.get("lastName").getAsString();
		
        String email = queryObj.get("email").getAsString();
		
		String query = queryObj.get("query").getAsString();
		
        PrintWriter writer = response.getWriter();
		
		JsonObject messageObj;
		
		if(dbManager.postUserQuery(firstName, lastName, email, query)) {
			
			messageObj = new JsonObject();
			messageObj.addProperty("message", 1);
		
			writer.write(messageObj.toString());
			
		}else {
			
			messageObj = new JsonObject();
			messageObj.addProperty("message", -1);
	
			writer.write(messageObj.toString());
			
		}
		
	}

}
