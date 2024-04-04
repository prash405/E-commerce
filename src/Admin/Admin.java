package Admin;

import java.util.Scanner;

import MainClass.User;

public class Admin {
	
		public void adminPortal() {
			
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("1-----add product\n2----- Calculate Bill\n3----- totalBill Bill\n4----- CheckUser\n5----- CheckUserHistory");
		int choice = scanner.nextInt();
		scanner.nextLine();
		AdminMethod admin = new AdminMethod();
		User user = new User();

		switch (choice) {
		case 1:
			AdminMethod product = admin.addProductItem();
			admin.addProductItemDataBase(product);
			break;
		case 2:
			admin.displayProducts();
			break;
		case 3:
			System.out.println("enter your User ID");
			int id1 = scanner.nextInt();
			scanner.nextLine();
			user.totalBill(id1);
			break;
		case 4:
			System.out.println("enter your Product ID");
			int ProdId = scanner.nextInt();
			scanner.nextLine();
			user.totalBill(ProdId);
			admin.ramainingQtyProducts( ProdId);
			break;
		case 5:
			System.out.println("enter your user ID");
			int userid = scanner.nextInt();
			scanner.nextLine();
			
			admin.CheckUser(userid);
			break;
		case 6:
			System.out.println("enter your user ID");
			userid = scanner.nextInt();
			scanner.nextLine();
			
			admin.CheckUserHistory(userid);
			break;
		default:
			System.out.println("invalid input");
			break;
		}
	}
}
