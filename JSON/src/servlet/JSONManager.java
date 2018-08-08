package servlet;

import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import model.Product;
import model.User;

public class JSONManager {

	public String getuserJSON(List<User> userList) {

		JsonArray userArray = new JsonArray();

		for (User user : userList) {

			JsonObject jsonObject = new JsonObject();

			jsonObject.addProperty("id", user.getId());
			jsonObject.addProperty("firstName", user.getFirstName());
			jsonObject.addProperty("lastName", user.getLastName());
			jsonObject.addProperty("phoneNumber", user.getPhoneNumber());
			jsonObject.addProperty("email", user.getEmail());
			jsonObject.addProperty("password", user.getPassword());
			jsonObject.addProperty("addressLine1", user.getAddressLine1());
			jsonObject.addProperty("addressLine2", user.getAddressLine2());
			jsonObject.addProperty("state", user.getState());
			jsonObject.addProperty("pincode", user.getPincode());

			userArray.add(jsonObject);

		}

		return userArray.toString();

	}
	
public String getAllProductsJSON(List<Product> productList){
		
		JsonArray allProductArray = new JsonArray();
			
			for (Product product : productList) {

				JsonObject jsonObjectp = new JsonObject();

				jsonObjectp.addProperty("id", product.getId());
				jsonObjectp.addProperty("name", product.getName());
				jsonObjectp.addProperty("description", product.getDescription());
				jsonObjectp.addProperty("price", product.getPrice());
				jsonObjectp.addProperty("category", product.getCategory());

				allProductArray.add(jsonObjectp);
			}
			return allProductArray.toString();
		}
}
