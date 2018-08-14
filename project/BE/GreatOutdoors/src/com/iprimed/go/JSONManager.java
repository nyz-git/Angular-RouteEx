package com.iprimed.go;

import java.util.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JSONManager {
	
	private String imageUrl = "http://localhost:8080/GreatOutdoors/images/";
	
	
	public String getProductJSON(List<Product> productList) {
		  
		JsonArray productArray = new JsonArray();
		
		
		
		for(Product product: productList) {
			
			JsonObject object = new JsonObject();
			
			object.addProperty("id", product.getId());
			object.addProperty("name", product.getName());
			object.addProperty("description", product.getDescription());
			object.addProperty("price", product.getPrice());
			object.addProperty("category", product.getCategory());
			object.addProperty("image1",imageUrl+product.getCategory()+"/"+product.getId()+"/1.jpg");
			object.addProperty("image2",imageUrl+product.getCategory()+"/"+product.getId()+"/2.jpg");
			object.addProperty("image3",imageUrl+product.getCategory()+"/"+product.getId()+"/3.jpg");
			object.addProperty("image4",imageUrl+product.getCategory()+"/"+product.getId()+"/4.jpg");
			object.addProperty("image5",imageUrl+product.getCategory()+"/"+product.getId()+"/5.jpg");
			productArray.add(object);
			
		}
		
        return productArray.toString();		
 		
	}
	
	
	public User jsonToUser(JsonObject obj) {
		
		User user = new User();
		
		user.setFirstName(obj.get("firstName").getAsString());
	    user.setLastName(obj.get("lastName").getAsString());
	    user.setEmail(obj.get("email").getAsString());
	    user.setPassword(obj.get("password").getAsString());
	    user.setAddressLine1(obj.get("addressLine1").getAsString());
	    user.setAddressLine2(obj.get("addressLine2").getAsString());
	    user.setState(obj.get("state").getAsString());
	    user.setPincode(obj.get("pincode").getAsInt());
	    user.setPhoneNumber(obj.get("phoneNumber").getAsString());
	    
		
		return user;
		
		
   }
	
	
   public JsonObject userToJson(User user) {
	   
	   JsonObject obj = new JsonObject();
	   
	   obj.addProperty("id", user.getId());
	   obj.addProperty("firstName", user.getFirstName());
	   obj.addProperty("lastName", user.getLastName());
	   obj.addProperty("phoneNumber", user.getPhoneNumber());
	   obj.addProperty("email",user.getEmail());
	   obj.addProperty("password", user.getPassword());
	   obj.addProperty("addressLine1", user.getAddressLine1());
	   obj.addProperty("addressLine2", user.getAddressLine2());
	   obj.addProperty("state", user.getState());
	   obj.addProperty("pincode", user.getPincode());
	   
	   return obj;
	   
	   
   }
   
   public List<DeliveryAddress> addressJsonToAddressObject(JsonArray addressArray) {
	   
	   List<DeliveryAddress> addressList = new ArrayList<DeliveryAddress>();
	   
	   for(int i=0;i<addressArray.size();i++) {
		   
		   DeliveryAddress address = new DeliveryAddress();
		   
		   JsonObject obj = (JsonObject) addressArray.get(i);
		   
		   address.setAddressLine1(obj.get("addressLine1").getAsString());
		   address.setAddressLine2(obj.get("addressLine2").getAsString());
		   address.setState(obj.get("state").getAsString());
		   address.setPincode(obj.get("pincode").getAsInt());
		   
		   addressList.add(address);
		   
	   }
	   
	   return addressList;
	   
	   
   }
	
   
   public JsonArray cartListToJsonArray(List<Cart> cartList) {
	   
	   JsonArray array = new JsonArray();
	   
	   for(Cart cart: cartList) {
	   
	   JsonObject object = new JsonObject();
	   
	      object.addProperty("cartId", cart.getCartId());
	      object.addProperty("userId", cart.getUserId());
	      object.addProperty("productId", cart.getProductId());
	      object.addProperty("quantity", cart.getQuantity());
	     
	   
	      array.add(object); 
	   
	   }
	   return array;
	   
	   
   }
	
	
	
	

}
