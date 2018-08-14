package com.iprimed.go;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class DBManager {
  
	private static final String DB_URL = "jdbc:mysql://localhost:3307/GreatOutdoor";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	
	Connection conn = null;
	List<Product> productList;
	
	private static final String PRODUCT_SELECT_QUERY = "SELECT * FROM Product";
	private static final String USER_SELECT_QUERY = "SELECT * FROM User";
	private static final String INSERT_USER_QUERY = "INSERT INTO User (firstName,lastName,phoneNumber,email,password,"
			+ "addressLine1,addressLine2,state,pincode) values (?,?,?,?,?,?,?,?,?)";
	
	private static final String QUERY_USER_PASS = "SELECT password FROM User WHERE EMAIL = ?";
	
	private static final String QUERY_USER_ID = "SELECT id FROM User WHERE EMAIL = ?";
	
	private static final String GET_USER_QUERY = "SELECT * FROM User WHERE EMAIL = ?";
	
	private static final String PLACE_ORDER_QUERY = "INSERT INTO Order_Table (userID,productId,quantity,price) "
			+ "values (?,?,?,?)";
	
	private static final String SELECT_LAST_ORDER_ID = "SELECT LAST_INSERT_ID() AS lastId FROM Order_Table LIMIT 1";
	
	
	private static final String INSERT_ADDRESS_QUERY = "INSERT INTO Delivery_Address_Table (addressLine1,addressLine2,state,pincode)"
			+ "values (?,?,?,?)";
	
	private static final String SELECT_LAST_ADDRESS_ID = "SELECT LAST_INSERT_ID() AS lastId FROM Delivery_Address_Table LIMIT 1";
	
	private static final String MAP_ORDER_ADDRESS = "INSERT INTO Order_Address_Table (orderId,addressId)"
			+ "values (?,?)";
	
	private static final String ADD_TO_CART = "INSERT INTO Cart_Table (userID,productId,quantity)"  
			+ "values (?,?,?)";
	
	private static final String SHOW_CART_QUERY = "SELECT * FROM Cart_Table WHERE userId = ?";
	
	private static final String REMOVE_FROM_CART = "DELETE FROM Cart_Table WHERE cartId = ?";
	
	
	private static final String GET_USER_EMAIL = "SELECT email FROM User where id = ?";
	
	private static final String GET_PRODUCT_NAME = "SELECT name FROM Product where id = ?";
	
	private static final String POST_USER_QUERY = "INSERT INTO Customer_Query (firstName,lastName,email,query) value"
			+ "(?,?,?,?)";
			
	
	
	
	public boolean openConnection() {
		
		if(conn ==  null) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		  return true;
		}else
			return true;
		
   }
	
  
  public void closeConnection() {
	  
	   if(conn != null)
		try {
			conn.close();
			conn = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
  }
	
	
	
	
	
	public List<Product> getProducts() {
		
		
		if(openConnection()) {
			
			
			try {
				Statement statement = conn.createStatement();
				 
	            ResultSet productSet = statement.executeQuery(PRODUCT_SELECT_QUERY);
			    
				productList = new ArrayList<Product>();
				
				while(productSet.next()) {
				
				  Product product = new Product();
				  
				  product.setId(productSet.getInt("id"));
				  product.setName(productSet.getString("name"));
				  product.setDescription(productSet.getString("description"));
				  product.setPrice(productSet.getInt("price"));
				  product.setCategory(productSet.getString("category"));
				  
				  
				 productList.add(product);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				return null;
			}
			
			return productList;
			
			
		}else
			return null;
	}
		

	
	public boolean checkExistingUser(String email) {
		
		
       if(openConnection()) {
			
			try {
				Statement statement = conn.createStatement();
				ResultSet userSet = statement.executeQuery(USER_SELECT_QUERY);
				while(userSet.next()) {
					  
					if (email.equals(userSet.getString("email"))) {
						System.out.println("User exists");
				        return true;
					}
				}
				
				
			} catch (SQLException e) {
				
				System.out.println("SQL Exception");
				return false;
				
			}
	
			System.out.println("User doesn't exists");	
		   return false;	
			
			
		}else
			System.out.println("Connection issue");
			return false;
		
	}

		
	
	
	
	public int logIn(String email,String password) {
	
		
		if(openConnection()) {
			
			if(checkExistingUser(email)) {
				
				
				try {
					PreparedStatement statement = conn.prepareStatement(QUERY_USER_PASS);
					statement.setString(1,email);
					
					ResultSet set = statement.executeQuery();
					
					String pass = null;
					
					while(set.next()) {
					pass = set.getString("password");
					System.out.println(pass);
					}
					
					
					
					if(pass.equals(password))
						return 1;
					else
						return -1;
					
				} catch (SQLException e) {
					e.printStackTrace();
					return -3;
				} 
				
				
				
			}else
				return -2;
			
			
			
		}else
			return -3;
		

	}
	
	
	public User getUSer(String email) {
		
		
		if(openConnection()) {
			
			PreparedStatement statement;
			
			User user = new User();
			try {
				
				statement = conn.prepareStatement(GET_USER_QUERY);
				statement.setString(1, email);
				ResultSet set = statement.executeQuery();
				
				while(set.next()) {
					user.setId(set.getInt("id"));
				    user.setFirstName(set.getString("firstName"));
				    user.setLastName(set.getString("lastName"));
				    user.setPhoneNumber(set.getString("phoneNumber"));
				    user.setEmail(set.getString("email"));
				    user.setPassword(set.getString("password"));
				    user.setAddressLine1(set.getString("addressLine1"));
				    user.setAddressLine2(set.getString("addressLine2"));
				    user.setState(set.getString("state"));
				    user.setPincode(set.getInt("pincode"));
				}
				
				return user;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			
		}else
			return null;
		
	}
	
	
	
	public int register(User user) {
	         
		
		
		if(openConnection()) {
			
			if(checkExistingUser(user.getEmail())) {
				
			   System.out.println("User already exist");
			   return -1;
				
			}else {
				
				
				try {
					
					PreparedStatement statement = conn.prepareStatement(INSERT_USER_QUERY);
					statement.setString(1, user.getFirstName());
					statement.setString(2, user.getLastName());
					statement.setString(3, user.getPhoneNumber());
					statement.setString(4, user.getEmail());
					statement.setString(5, user.getPassword());
					statement.setString(6,user.getAddressLine1());
					statement.setString(7,user.getAddressLine2());
					statement.setString(8,user.getState());
					statement.setInt(9,user.getPincode());
				
				    statement.executeUpdate();
					
				    
				    return getID(user.getEmail());
					
				    
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -2;
				}
				
				
				
			}
			 
          			
			
		}else {
			System.out.println("Connection issue");
			return -3;
		}
		
		
	
	}
	
	public int placeOrder(int userId,int productId,int quantity,int price) {
		
		if(openConnection()) {
			
			try {
				PreparedStatement preparedStatement = conn.prepareStatement(PLACE_ORDER_QUERY);
				preparedStatement.setInt(1, userId);
				preparedStatement.setInt(2, productId);
				preparedStatement.setInt(3, quantity);
				preparedStatement.setInt(4, price);
				
				preparedStatement.executeUpdate();
				
				
				Statement statement = conn.createStatement();
				ResultSet set = statement.executeQuery(SELECT_LAST_ORDER_ID);
				
				int orderId = 0;
				
				while(set.next())
				   orderId = set.getInt("lastId");	
				
				
				return orderId;
				
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
			
			
			
		}else
		
		  return -1;
	}
	
	public int getID(String email) {
		
		if(openConnection()) {
			int userId = 0;
			PreparedStatement statement;
			try {
				
				statement = conn.prepareStatement(QUERY_USER_ID);
				statement.setString(1, email);
				ResultSet set = statement.executeQuery();
				
				while(set.next())
					userId =  set.getInt("id");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
			
			
			return userId;
			
		}else {
			System.out.println("Connection issue");
			return -2;
		
		
		
	    }
	
	
	}
	
	
	public int[] saveAddresses(List<DeliveryAddress> addressList) {
		
		
		
		if(openConnection()) {
		
		  int[] addressIdArray = new int[addressList.size()];	
		  int index = 0;	
		  for(DeliveryAddress address: addressList) {
			
			try {
				

			    PreparedStatement preparedStatement;
				Statement statement;
			            
			   
			
				preparedStatement = conn.prepareStatement(INSERT_ADDRESS_QUERY);
				preparedStatement.setString(1, address.getAddressLine1());
				preparedStatement.setString(2, address.getAddressLine2());
				preparedStatement.setString(3, address.getState());
				preparedStatement.setInt(4, address.getPincode());
				
				preparedStatement.executeUpdate();
				
				statement = conn.createStatement();
				ResultSet set = statement.executeQuery(SELECT_LAST_ADDRESS_ID);
				
				while(set.next()) {
					addressIdArray[index] = set.getInt("lastId");
					index++;
				}
				
			
			    
			   
				
               				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		  }
		  
		  return addressIdArray;
		  
		}else
			return null;
	}
		
		
	

	
	
	public boolean mapOrderAddress(int orderId, int[] addressIdArray) {
		
		if(openConnection()) {
			
			PreparedStatement preparedStatement;
	      
			for(int i=0;i<addressIdArray.length;i++) {
		
			try {
				preparedStatement = conn.prepareStatement(MAP_ORDER_ADDRESS);
				preparedStatement.setInt(1, orderId);
				preparedStatement.setInt(2, addressIdArray[i]);
				
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			
		    }
			
			return true;
			
		}else
		return false;
	}
	
	
	public List<Cart> showCart(int userId) {
		if(openConnection()) {
			
			try {
				PreparedStatement statement = conn.prepareStatement(SHOW_CART_QUERY);
				
				statement.setInt(1, userId);
				
				ResultSet set = statement.executeQuery();
				
				List<Cart> cartList = new ArrayList<Cart>();
				
				while(set.next()) {
					//System.out.println(set.getInt("cartId")+" "+set.getInt("productId")+" "+set.getInt("quantity")+" "+set.getInt("price"));
				  
					Cart cart = new Cart();
					
					cart.setCartId(set.getInt("cartId"));
					cart.setUserId(set.getInt("userId"));
					cart.setProductId(set.getInt("productId"));
					cart.setQuantity(set.getInt("quantity"));
					
					cartList.add(cart);
				
				}
				
				return cartList;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			
			
		}else {
			return null;
		}
	}
	
	public boolean addToCart(int userId,int productId,int quantity) {
		
		if(openConnection()) {
			
			try {
				
				PreparedStatement statement = conn.prepareStatement(ADD_TO_CART);
				statement.setInt(1, userId);
				statement.setInt(2, productId);
				statement.setInt(3, quantity);
				
				
				statement.executeUpdate();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			
			
		}else 
			return false;
		
		
	}
	
	
	public boolean removeItemFromCart(int cartId) {
		if(openConnection()) {
			
			PreparedStatement statement;
			try {
				statement = conn.prepareStatement(REMOVE_FROM_CART);
				statement.setInt(1, cartId);
				statement.executeUpdate();
				
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			
		
		}else {
			return false;
		}
	}
	
	
	public String getUserMail(int userId) {
		
		if(openConnection()) {
			
			String email = null;
			
			PreparedStatement statement;
			try {
				statement = conn.prepareStatement(GET_USER_EMAIL);
				statement.setInt(1, userId);
				ResultSet set = statement.executeQuery();
				
				while(set.next()) {
					email = set.getString("email");
				}
				
				return email;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			
			
		}else {
			return null;
		}
		
	}
	
	public String getProductName(int productId) {
	if(openConnection()) {
			
			String name = null;
			
			PreparedStatement statement;
			try {
				statement = conn.prepareStatement(GET_PRODUCT_NAME);
				statement.setInt(1, productId);
				ResultSet set = statement.executeQuery();
				
				while(set.next()) {
					name = set.getString("name");
				}
				
				return name;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			
			
		}else {
			return null;
		}
	}
	
	
	
	
	public boolean postUserQuery(String firstName,String lastName,String email,String query) {
		
		
		if(openConnection()) {
			
			try {
				PreparedStatement statement = conn.prepareStatement(POST_USER_QUERY);
				statement.setString(1, firstName);
				statement.setString(2, lastName);
				statement.setString(3, email);
				statement.setString(4, query);
				
				statement.executeUpdate();
				
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			
		}else {
			return false;
		}
		
		
	}
	
	
	
	
}
