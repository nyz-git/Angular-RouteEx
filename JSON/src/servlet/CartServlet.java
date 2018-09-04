package servlet;

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

import model.Cart;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
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

		JsonObject cartObj = parser.parse(data).getAsJsonObject();

		// adding to cart

		DB db = new DB();
		Cart cart = new Cart();
		cart.setUserId(cartObj.get("userId").getAsInt());
		cart.setProductId(cartObj.get("productId").getAsInt());
		cart.setQuantity(cartObj.get("quantity").getAsInt());
		cart.setAmount(cartObj.get("amount").getAsInt());

		int r = db.addToCart(cart);

		/*--------------Send result in json format-------------------*/

		PrintWriter out = response.getWriter();
		JsonObject messageObj;

		if (r == 1) {
			// getting cart via userId
			Cart getCartObj = db.getCartByCartId(cart.getUserId());

			// converting getCartObj object to json object
			JSONManager jsonManager = new JSONManager();
			JsonObject cartJsonObj = jsonManager.cartToJson(cart);

			messageObj = new JsonObject();
			messageObj.addProperty("message", 1);

			out.write(messageObj.toString());

			System.out.println("S");
		} else {
			messageObj = new JsonObject();
			messageObj.addProperty("message", 0);
			out.write(messageObj.toString());

			System.out.println("F");
		}

	}

}
