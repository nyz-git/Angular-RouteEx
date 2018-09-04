package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.Product;
import model.User;

public class DB {

	List<User> userList;
	Connection con;
	Statement statement;
	PreparedStatement ps;
	Cart cart;

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/greatoutdoor", "root", "root");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public List<User> getUser() {

		con = getConnection();

		try {
			statement = con.createStatement();

			ResultSet userSet = statement.executeQuery("select * from user");

			userList = new ArrayList<User>();

			while (userSet.next()) {

				User user = new User();

				user.setId(userSet.getInt(1));
				user.setFirstName(userSet.getString(2));
				user.setLastName(userSet.getString(3));
				user.setPhoneNumber(userSet.getInt(4));
				user.setEmail(userSet.getString(5));
				user.setPassword(userSet.getString(6));
				user.setAddressLine1(userSet.getString(7));
				user.setAddressLine2(userSet.getString(8));
				user.setState(userSet.getString(9));
				user.setPincode(userSet.getInt(10));

				userList.add(user);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return null;
		}

		return userList;

	}

	public int login(String email, String password) {

		int i = 0;
		con = getConnection();

		try {
			ps = con.prepareStatement("select * from user where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println(rs.getString(2));
				i = 1;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;

	}

	public int register(String firstName, String lastName, String phone, String email, String password,
			String addressLine1, String addressLine2, String state, String pincode) {
		int i = 0;
		con = getConnection();
		User user = new User();
		try {

			ps = con.prepareStatement("insert into user values(?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, user.getId());
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, phone);
			ps.setString(5, email);
			ps.setString(6, password);
			ps.setString(7, addressLine1);
			ps.setString(8, addressLine2);
			ps.setString(9, state);
			ps.setString(10, pincode);

			i = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();

		}
		return i;
	}

	public List<Product> getAllProduct() {

		con = getConnection();
		List<Product> allProductList;

		try {
			statement = con.createStatement();

			ResultSet productSet = statement.executeQuery("select * from product");

			allProductList = new ArrayList<Product>();

			while (productSet.next()) {

				Product product = new Product();

				product.setId(productSet.getInt(1));
				product.setName(productSet.getString(2));
				product.setDescription(productSet.getString(3));
				product.setPrice(productSet.getInt(4));
				product.setCategory(productSet.getString(5));

				allProductList.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		System.out.println(allProductList.toString());
		return allProductList;

	}

	public User getUserByEmail(String email) {
		User user = new User();
		con = getConnection();
		try {
			ps = con.prepareStatement("select * from user where email=?");
			ps.setString(1, email);
			ResultSet userSet = ps.executeQuery();
			while (userSet.next()) {
				user.setId(userSet.getInt(1));
				user.setFirstName(userSet.getString(2));
				user.setLastName(userSet.getString(3));
				user.setPhoneNumber(userSet.getInt(4));
				user.setEmail(email);
				user.setAddressLine1(userSet.getString(7));
				user.setAddressLine2(userSet.getString(8));
				user.setState(userSet.getString(9));
				user.setPincode(userSet.getInt(10));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;

	}

	public int addToCart(Cart cart) {
		this.cart = cart;
		int i = 0;
		con = getConnection();

		try {
			ps = con.prepareStatement("insert into cart_table values(?,?,?,?,?)");
			ps.setInt(1, cart.getCartId());
			ps.setInt(2, cart.getUserId());
			ps.setInt(3, cart.getProductId());
			ps.setInt(4, cart.getQuantity());
			ps.setInt(5, cart.getAmount());
			i = ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;

	}

	public Cart getCartByCartId(int userId) {
		Cart cart = null;
		con = getConnection();
		try {
			ps = con.prepareStatement("select * from cart_table where userId = ?");
			ps.setInt(1, userId);
			ResultSet cartSet = ps.executeQuery();

			while (cartSet.next()) {
				cart = new Cart();
				cart.setCartId(cartSet.getInt(1));
				cart.setUserId(userId);
				cart.setProductId(cartSet.getInt(3));
				cart.setQuantity(cartSet.getInt(4));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cart;

	}

	public List<Cart> getAllCart() {

		con = getConnection();
		List<Cart> allCartList;

		try {
			statement = con.createStatement();

			ResultSet cartSet = statement.executeQuery("select * from cart_table");

			allCartList = new ArrayList<Cart>();

			while (cartSet.next()) {

				Cart cart = new Cart();
				cart.setCartId(cartSet.getInt(1));
				cart.setUserId(cartSet.getInt(2));
				cart.setProductId(cartSet.getInt(3));
				cart.setQuantity(cartSet.getInt(4));
				cart.setAmount(cartSet.getInt(5));

				allCartList.add(cart);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return allCartList;

	}

	public int updateCart(int cartId, int quantity, int amount) {

		con = getConnection();
		int i = 0;
		try {
			ps = con.prepareStatement("update cart_table set quantity = ?, amount = ? where cartId = ?");
			ps.setInt(1, quantity);
			ps.setInt(2, amount);
			ps.setInt(3, cartId);

			i = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;
	}

	public int deleteCart(int cartId) {

		con = getConnection();
		int i = 0;
		try {
			ps = con.prepareStatement("delete from cart_table where cartId = ?");
			ps.setInt(1, cartId);

			i = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;
	}

}
