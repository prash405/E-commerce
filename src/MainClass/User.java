package MainClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


import DataBase.*;
public class User {
	private String FirstName;
	private String LastName;
	private String Email;
	private String MobileNumber;
	private String City;
	private String Password;
	private String Username;
	
	public User() {
		
	}
	
	@Override
	public String toString() {
		return "User [FirstName=" + FirstName + ", LastName=" + LastName + ", Email=" + Email + ", MobileNumber="
				+ MobileNumber + ", City=" + City + ", Password=" + Password + ", Username=" + Username + "]";
	}
	Connect connect=new Connect();
	Connection st=connect.Connection();
	public User(String firstName, String lastName, String email, String mobileNumber, String city, String password,
			String username) {
		super();
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		MobileNumber = mobileNumber;
		City = city;
		Password = password;
		Username = username;
	}

	public User registration(Scanner scanner) {
		System.out.println("Enter Your First Name--");
		 FirstName=scanner.nextLine();
		System.out.println("Enter Your Last Name--");
		 LastName=scanner.nextLine();
		System.out.println("Enter Your Email --");
		 Email=scanner.nextLine();
		System.out.println("Enter Your City --");
		City=scanner.nextLine();
		System.out.println("Enter Your password --");
		 Password=scanner.nextLine();
		System.out.println("Enter Your user Name --");
		 Username=scanner.nextLine();
		System.out.println("Enter Your MobileNumber --");
		 MobileNumber=scanner.nextLine();
		return new User(FirstName,LastName,Email,MobileNumber,City,Password,Username);
	}
	
	public void RegistraionDatabase(User user) {
		Connect connect=new Connect();
		Connection st=connect.Connection();
		String query="insert into user(FirstName,LastName,Email,MobileNumber,City,Password,Username)values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement prepare=st.prepareStatement(query);
			prepare.setString(1,user.FirstName);
			prepare.setString(2,user.LastName);
			prepare.setString(3,user.Email);
			prepare.setString(4,user.MobileNumber);
			prepare.setString(5,user.City);
			prepare.setString(6,user.Password);
			prepare.setString(7,user.Username);
			prepare.execute();
			System.out.println("success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void loginInDatabase(String username,String password) {
		Connect connect=new Connect();
			Connection conn=connect.Connection();
			String sql="select * from user where Username=? and Password=?";
		try {
			PreparedStatement prepare=conn.prepareStatement(sql);
			
			prepare.setString(1, username);
			prepare.setString(2, password);
			
			ResultSet result=prepare.executeQuery();
			if(result.next()) {
				System.out.println("Login succesfully....Happy shopping");
				User user=new User(result.getString("FirstName"),result.getString("LastName"),
						result.getString("Email"),result.getString("MobileNumber"),result.getString("City")
						,result.getString("Password"),result.getString("Username")
						);
				System.out.println(user);
			}else {
				System.out.println("access denied...plz check ur username and password");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void displayProducts() {
		System.out.println("Product List:");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-20s %-40s %-20s %-10s\n", "Product ID", "Product Name", "Product Description", "Available Quantity", "Price");
        System.out.println("----------------------------------------------------------------------------------------------------------");
		String sql="select * from Products order by Name";
			try {
				PreparedStatement prapare = st.prepareStatement(sql);
				ResultSet resultSet = prapare.executeQuery();
				while(resultSet.next()) {
					String productName = resultSet.getString("Name");
					int productId = resultSet.getInt("ProductID");
					String productDescription =  resultSet.getString("Description");
					double price = resultSet.getDouble("Price");
					int availableQuantity = resultSet.getInt("Quantity");
					System.out.printf("%-20s %-20s %-40s %-20s %-10s \n", productId,productName, productDescription, availableQuantity,  price);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
	
	public void buyProducts(int id,int q) {
		Scanner scan=new Scanner(System.in);
		System.out.println("enter userID");
		int UserId=scan.nextInt();
		scan.nextLine();
		String sql="select * from products where ProductID=?";
		PreparedStatement prepare =null;
		
		try {
			prepare = st.prepareStatement(sql);
			prepare.setInt(1,id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ResultSet resultSet = prepare.executeQuery();
			if(resultSet.next()) {
				String productName = resultSet.getString("Name");
				int productId = resultSet.getInt("ProductID");
//				int Quantity =  resultSet.getInt("Quantity");
				Double Price =  resultSet.getDouble("Price")*q;
				System.out.println("product list");
		        System.out.printf("%-10s %-20s %-40s %-20s", "Product ID", "Product Name",  "Available Quantity", "Price");
				System.out.println("----------------------------------------------------------------");
				System.out.printf("%-10s %-20s %-40d %-20.2f\n", productId, productName, q, Price);
				System.out.println("----------------------------------------------------------------");
				String sql2="insert into cart(ProductID,Quantity,Price,UserId)values(?,?,?,?)";
				PreparedStatement prepare2 = st.prepareStatement(sql2);
			;
				prepare2.setInt(1,productId);
				prepare2.setInt(2,q);
				prepare2.setDouble(3,Price);
				prepare2.setInt(4,UserId);
				int result =prepare2.executeUpdate();
				System.out.println(result);
				if(result==1) {
					System.out.println("added to cart");
					String removedQty="UPDATE Products SET Quantity = Quantity - ? WHERE ProductID = ?";
					
					PreparedStatement prepareRemoved1 = st.prepareStatement(removedQty);
					prepareRemoved1.setInt(1,q);
					prepareRemoved1.setInt(2,id);
					int removed=prepareRemoved1.executeUpdate();
					if(removed==1) {
						System.out.println("Product lsit Update");
						displayProducts();
					}
				}else {
					System.out.println("unable to add in cart");
				}
				scan.close();
			}else {
				System.out.println("product not found plz check product id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void totalBill(int id) {
//		Scanner scan=new Scanner(System.in);
//		System.out.println("enter userID");
//		int UserId=scan.nextInt();
//		scan.nextLine();
		String sql="select sum(Price)as totalbill,UserID  from cart where UserId=?";
		PreparedStatement prepare =null;
		
		try {
			prepare = st.prepareStatement(sql);
			prepare.setInt(1,id);
			ResultSet resultSet = prepare.executeQuery();
			if(resultSet.next()) {
				int productBill = resultSet.getInt("totalBill");
				
				int UserID =  resultSet.getInt("UserID");
				
				System.out.println("Total Bill");
				System.out.println("--------------------------");
				System.out.println(productBill+"---"+UserID);
			}} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
}
	
	
	
	
	
	

}