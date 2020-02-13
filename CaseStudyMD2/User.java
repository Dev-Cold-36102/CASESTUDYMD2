package CaseStudyMD2;

import java.io.File;
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

    public boolean isGuestOrAdmin() {
        System.out.print(" username: ");
        String username = scanner.nextLine();
        System.out.print("pass: ");
        String pass = scanner.nextLine();
        String submitReLogin = "";
        while (!username.equals("CSMD2") || !pass.equals("C1119H1")) {
            System.out.print("username or pass is incorrect");
            System.out.print("input 'y' to relogin or 'n' to Log in as a guest: ");
            submitReLogin = scanner.nextLine();
            if (submitReLogin.equals(String.valueOf('y'))) {
                isGuestOrAdmin();
            } else return false;
        }
        return true;
    }

    public void logIn() {
        Admin admin=new Admin(this.DicHashMap,srcDic);
        Guest guest=new Guest(this.DicHashMap,admin);
        if (isGuestOrAdmin()) {
            admin.showMenu();
        } else {
            guest.showMenu();
        }
    }
}
