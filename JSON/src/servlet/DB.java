package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.User;

public class DB {

	List<User> userList;
	Connection con;
	Statement statement;
	PreparedStatement ps;

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
		System.out.println(allProductList);
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
}
