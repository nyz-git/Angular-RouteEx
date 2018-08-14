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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	
	@Override
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
		
	   
	    /*--------------Login user----------------------------*/
		
		DBManager dbManager = new DBManager();
		
		String email = userObj.get("email").getAsString();
		
		String password = userObj.get("password").getAsString();
		
		int code = dbManager.logIn(email, password);
		
		
		/*--------------Send result in json format-------------------*/
		
		
		JSONManager jsonManager = new JSONManager();
		
		PrintWriter writer = response.getWriter();
		
		JsonObject messageObj;
		 
		if(code == 1) {
		   
			User user = dbManager.getUSer(email);
			
			JsonObject userJson = jsonManager.userToJson(user);
			
			messageObj = new JsonObject();
			messageObj.addProperty("message", 1);
			messageObj.add("user",new Gson().toJsonTree(userJson));
			writer.write(messageObj.toString());
			
		}else if(code == -1) {
			
			messageObj = new JsonObject();
			messageObj.addProperty("message", -1);
			writer.write(messageObj.toString());
			
		}else {
			messageObj = new JsonObject();
			messageObj.addProperty("message", -2);
			writer.write(messageObj.toString());
		}
	    
 	    
    }
	
	
	


}
