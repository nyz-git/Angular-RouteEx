package servlet;
 import utility.*;
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
		
		String data;
		String line;
		
		StringBuilder builder = new StringBuilder();
		
		BufferedReader reader = request.getReader();
		
		while((line = reader.readLine())!= null){
			builder.append(line);
		}
		
		data = builder.toString();
		
//----------------------------Convert request to json---------------
		
		JsonParser parse = new JsonParser();
		JsonObject regObj = parse.parse(data).getAsJsonObject();
		
//--------------------------Register User---------------------------
		
		DB dbObj = new DB();
		
		String firstName = regObj.get("firstName").getAsString();
		String lastName = regObj.get("lastName").getAsString();
		String phone = regObj.get("phone").getAsString();
		String email = regObj.get("email").getAsString();
		String password = regObj.get("password").getAsString();
		String addressLine1 = regObj.get("addressLine1").getAsString();
		String addressLine2 = regObj.get("addressLine2").getAsString();
		String state = regObj.get("state").getAsString();
		String pincode = regObj.get("pincode").getAsString();
		
		int i = dbObj.register(firstName, lastName, phone, email, password, addressLine1, addressLine2, state, pincode);
		
/*--------------Send result in json format-------------------*/
		
		PrintWriter out = response.getWriter();
		JsonObject messageObj;
		
		if (i == 1) {
			System.out.println("Registration in progress.......");
			new EmailManager().sendRegisterationEmail(firstName, email);
			messageObj = new JsonObject();
			messageObj.addProperty("message", 1);
			out.write(messageObj.toString());
			System.out.println("Registration Done!!!!!");
			System.out.println("SUCCESS");
		} else {
			messageObj = new JsonObject();
			messageObj.addProperty("message", 0);
			out.write(messageObj.toString());
			System.out.println("FAILURE");
		}
	}

}