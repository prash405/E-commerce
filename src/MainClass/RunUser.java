package MainClass;

import java.util.Scanner;

public class RunUser {
	

public  void UserPortal() {
		
	
	Scanner scanner=new Scanner(System.in);
	System.out.println("1-----registration\n2-----Login\n3------- show list in order by name\n4------- add to cart\n5--------- Total bill");
	int choice=scanner.nextInt();
	scanner.nextLine();
	User user=new User();
	
	switch(choice) {
	case 1:
		User newUser=user.registration(scanner);
		user.RegistraionDatabase(newUser);
		break;
	case 2:
		System.out.println("enter your username");
		String username=scanner.nextLine();
		System.out.println("enter your password");
		String password=scanner.nextLine();
		user.loginInDatabase(username,password);
		break;
		
	case 3:
		user.displayProducts();
		break;
		
	case 4:
		user.displayProducts();
		System.out.println("enter your Product ID");
		int id=scanner.nextInt();
		scanner.nextLine();
		System.out.println("enter your Quantity");
		int q=scanner.nextInt();
		scanner.nextLine();
		user.buyProducts(id,q);
		break;
	case 5:
		System.out.println("enter your User ID");
		int id1=scanner.nextInt();
		scanner.nextLine();
		user.totalBill(id1);
		break;
	default:
		System.out.println("invalid input");
		
	}
	
}
}
