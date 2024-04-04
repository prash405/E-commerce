package Index;

import java.util.Scanner;

import Admin.Admin;

import MainClass.RunUser;


public class IndexPoint {
public static void main(String[] args) {
	Admin admin = new Admin();
	RunUser user = new RunUser();
	Scanner scan=new Scanner(System.in);
	System.out.println("1-----User\n2-----Admin\n3-----Guest");
	int key=scan.nextInt();
	scan.nextLine();
	switch(key) {
	case 1:
		user.UserPortal();
		break;
	case 2:
		admin.adminPortal();
		break;
	default:
		System.out.println("invalid input");
		break;
	}
}
}
