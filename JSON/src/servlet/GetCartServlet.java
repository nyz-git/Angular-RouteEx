package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.Product;

@WebServlet("/GetCartServlet")
public class GetCartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		JSONManager jsonManager = new JSONManager();
		DB db = new DB();
		
		List<Cart> cartList = db.getAllCart();
		System.out.println(cartList.toString());
		String cartJson = jsonManager.getAllCartJSON(cartList);
		
		out.write(cartJson);
	
	}


}
