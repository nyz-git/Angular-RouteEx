package servlet;

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

@WebServlet("/DeleteCartServlet")
public class DeleteCartServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
		JsonObject deleteCartObj = parse.parse(data).getAsJsonObject();
		
//--------------------------Update Cart---------------------------
		
		DB db = new DB();
		
		int cartId = deleteCartObj.get("cartId").getAsInt();
		
		int i = db.deleteCart(cartId);
		
/*--------------Send result in json format-------------------*/
		
		PrintWriter out = response.getWriter();
		JsonObject messageObj;
		
		if (i == 1) {
			messageObj = new JsonObject();
			messageObj.addProperty("message", 1);
			out.write(messageObj.toString());
			System.out.println("Cart Deleted");
		} else {
			messageObj = new JsonObject();
			messageObj.addProperty("message", 0);
			out.write(messageObj.toString());
			System.out.println("Cart Deletion Fail");
		}
	}

}
