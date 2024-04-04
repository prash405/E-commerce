package Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import DataBase.Connect;

public class AdminMethod {
	private String productName;
	private String productDescription;
	private int availableQuantity;
	private double price;

	public AdminMethod() {

	}

	public AdminMethod(String productName, String productDescription, int availableQuantity, double price) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.availableQuantity = availableQuantity;
		this.price = price;
	}

	Connect connect = new Connect();
	Connection co = connect.Connection();

	public AdminMethod addProductItem() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter details for Product 1:");

		System.out.print("Product Name>> ");
		productName = scanner.nextLine();

		System.out.print("Product Description>> ");
		productDescription = scanner.nextLine();

		System.out.print("Available Quantity>> ");
		availableQuantity = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Price>> ");
		price = scanner.nextDouble();
		scanner.nextLine();
		scanner.close();

		AdminMethod product = new AdminMethod(productName, productDescription, availableQuantity, price);

		return product;

	}

	public void addProductItemDataBase(AdminMethod product) {
		Connect connect = new Connect();
		Connection co = connect.Connection();
		String sql = "insert into Products(Name,Description,Price,Quantity)values(?,?,?,?)";
		try {
			PreparedStatement prapare = co.prepareStatement(sql);
			prapare.setString(1, product.productName);
			prapare.setString(2, product.productDescription);
			prapare.setDouble(3, product.price);
			prapare.setInt(4, product.availableQuantity);
			int result = prapare.executeUpdate();
			if (result == 1) {
				System.out.println("Product succesfully added....");
			} else {
				System.out.println("unable to add product");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void displayProducts() {
		System.out.println("Product List:");
		System.out.println("---------------------------------------------------------");
		System.out.printf("%-10s %-20s %-40s %-20s %-10s\n", "Product ID", "Product Name", "Product Description",
				"Available Quantity", "Price");
		System.out.println("---------------------------------------------------------");
		String sql = "select * from Products order by Name";
		try {
			PreparedStatement prapare = co.prepareStatement(sql);
			ResultSet resultSet = prapare.executeQuery();
			while (resultSet.next()) {
				String productName = resultSet.getString("Name");
				int productId = resultSet.getInt("ProductID");
				String productDescription = resultSet.getString("Description");
				int availableQuantity = resultSet.getInt("Quantity");
				double price = resultSet.getDouble("Price");
				System.out.printf("%-10s %-10s %-20s %-40s %-20s %-10s\n", productId, productName, productDescription,
						availableQuantity, availableQuantity, price);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ramainingQtyProducts(int prodID) {

		System.out.println("Product List:");
		System.out.println("---------------------------------------------------------");
		System.out.printf("%-10s %-20s %-40s %-20s %-10s\n", "Product ID", "Product Name", "Product Description",
				"Available Quantity", "Price");
		System.out.println("---------------------------------------------------------");
		String sql = "select Name ,Quantity,Price from Products where productID=?";
		try {
			PreparedStatement prapare = co.prepareStatement(sql);
			prapare.setInt(1, prodID);
			ResultSet resultSet = prapare.executeQuery();
			while (resultSet.next()) {
				String productName = resultSet.getString("Name");
				int productId = resultSet.getInt("ProductID");
				String productDescription = resultSet.getString("Description");
				int availableQuantity = resultSet.getInt("Quantity");
				double price = resultSet.getDouble("Price");
				System.out.printf("%-10s %-10s %-20s %-40s %-20s %-10s\n", productId, productName, productDescription,
						availableQuantity, availableQuantity, price);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void CheckUser(int id) {
		System.out.println("Product List:");

		String sql = "select * from User where UserID=?";
		try {
			System.out.println("---------------------------------------------------------");
			System.out.printf("%-10s %-10s %-20s %-40s %-20s %-10s\n", "UserID", "FirstName", "email", "mobilenumber",
					"city", "username");
			System.out.println("---------------------------------------------------------");
			PreparedStatement prapare = co.prepareStatement(sql);

			prapare.setInt(1, id);
			ResultSet resultSet = prapare.executeQuery();
			while (resultSet.next()) {
				String UserID = resultSet.getString("UserID");
				String FirstName = resultSet.getString("FirstName");
				String email = resultSet.getString("Email");
				String mobilenumber = resultSet.getString("MobileNumber");
				String city = resultSet.getString("City");
				String username = resultSet.getString("Username");
				System.out.printf("%-10s %-10s %-20s %-40s %-20s %-10s\n", UserID, FirstName, email, mobilenumber, city,
						username);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void CheckUserHistory(int id) {
		System.out.println("Product List:");

		String sql = "select * from Cart where UserID=?";
		try {
			System.out.println("---------------------------------------------------------");
			System.out.printf("%-10s %-10s %-20s\n", "ProductID", "Quantity", "Price");
			System.out.println("---------------------------------------------------------");
			PreparedStatement prapare = co.prepareStatement(sql);

			prapare.setInt(1, id);
			ResultSet resultSet = prapare.executeQuery();
			while (resultSet.next()) {
				String ProductID = resultSet.getString("ProductID");
				String Quantity = resultSet.getString("Quantity");
				String Price = resultSet.getString("Price");
				System.out.printf("%-10s %-10s %-20s\n", ProductID, Quantity, Price);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
