package CaseStudyMD2;

import java.util.HashMap;
import java.util.Scanner;

public class User {
    Scanner scanner = new Scanner(System.in);
    private String srcDic;
    private HashMap<String, String> DicHashMap;

    public User(String srcDic, HashMap<String, String> dicHashMap) {
        this.srcDic = srcDic;
        this.DicHashMap = dicHashMap;
    }


    public void logIn() {
        Admin admin = new Admin(this.DicHashMap, srcDic, "account.txt");
        Guest guest = new Guest(this.DicHashMap, admin);
        System.out.println("1.log in       2.Log in as a guest");
        int choice = scanner.nextInt();
        String username;
        String pass;
        int submitReLogin = 0;
        if (choice == 1) {
            scanner.nextLine();
            do {
                System.out.print("username: ");
                username = scanner.nextLine();
                System.out.print("pass: ");
                pass = scanner.nextLine();
                if (admin.getAccountListMap().containsKey(username)) {
                    if (admin.getAccountListMap().get(username).equals(pass)) {
                        admin.setUsername(username);
                        admin.setPass(pass);
                        System.out.println("log in success!");
                        admin.showMenu();
                        break;
                    } else {
                        do {
                            System.out.println("username or pass is incorect. 1.relogin   2.Log in as a guest");
                            submitReLogin = scanner.nextInt();
                            if (submitReLogin == 2) {
                                break;
                            } else scanner.nextLine();
                        } while (submitReLogin!=1&&submitReLogin!=2);
                    }
                } else {
                    do {
                        System.out.println("username or pass is incorect. 1.relogin   2.Log in as a guest");
                        submitReLogin = scanner.nextInt();
                        if (submitReLogin == 2) {
                            break;
                        } else scanner.nextLine();
                    } while (submitReLogin!=1&&submitReLogin!=2);
                }
            } while (submitReLogin == 1);
        }
        guest.showMenu();
    }

}

