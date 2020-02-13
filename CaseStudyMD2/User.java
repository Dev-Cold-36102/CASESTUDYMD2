package CaseStudyMD2;

import java.util.HashMap;
import java.util.Scanner;

public class User {
    Scanner scanner = new Scanner(System.in);
    private String srcDic;
    private HashMap<String, String> DicHashMap;

    public User(String srcDic, HashMap<String, String> dicHashMap) {
        this.srcDic = srcDic;
        DicHashMap = dicHashMap;

    }


    public void logIn() {
        Admin admin = new Admin(this.DicHashMap, srcDic, "khai", "khai");
        Guest guest = new Guest(this.DicHashMap, admin);
        System.out.println("1.log in       2.Log in as a guest");
        int choice = scanner.nextInt();
        String username;
        String pass;
        if (choice == 1) {
            scanner.nextLine();
            do {
                System.out.print("username: ");
                username = scanner.nextLine();
                System.out.print("pass: ");
                pass = scanner.nextLine();
                if (!username.equals(admin.getUsername()) || !pass.equals(admin.getPass())) {
                    System.out.println("username or pass is incorect. 1.relogin   2.Log in as a guest");
                    int submit = scanner.nextInt();
                    if (submit == 2){
                        break;
                    } else scanner.nextLine();

                } else {
                    System.out.println("log in success!");
                    admin.showMenu();
                }
            } while (!username.equals(admin.getUsername()) || !pass.equals(admin.getPass()));
        }
        guest.showMenu();

    }
}

