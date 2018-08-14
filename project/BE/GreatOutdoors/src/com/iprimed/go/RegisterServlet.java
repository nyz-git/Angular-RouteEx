package com.iprimed.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {


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
	    
	    JsonObject userObj = parser.parse(data).getAsJsonObject();
	        
 
	    /*--------------Register user--------------------------------*/
	   
	    DBManager dbManager = new DBManager();
	    
	    JSONManager jsonManager = new JSONManager();
	    
	    int userId = dbManager.register(jsonManager.jsonToUser(userObj));
	    
	   
	    
	    /*--------------Send result in json format-----------------------------------*/
	    
	    PrintWriter writer = response.getWriter();
	    
	    JsonObject messageObj;
	    
	    if(userId == -1) {
	    	messageObj = new JsonObject();
	    	messageObj.addProperty("message", -1);
	    	writer.write(messageObj.toString());
	    }else if(userId > 0) {
	    	
	    	messageObj = new JsonObject();
	    	messageObj.addProperty("message", userId);
	    	writer.write(messageObj.toString());
	    	
	    }
	    
		
		
	}

}
