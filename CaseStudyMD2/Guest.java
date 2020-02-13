package CaseStudyMD2;

import java.util.HashMap;

public class Guest extends GuestControl {
    private  Admin admin;
    public Guest(HashMap<String, String> DicHashMap,Admin admin) {
        super(DicHashMap);
        this.admin=admin;
    }

    public boolean isGuestOrAdmin() {
        System.out.print(" username: ");
        String username = scanner.nextLine();
        System.out.print("pass: ");
        String pass = scanner.nextLine();
        String submitReLogin = "";
        while (!username.equals("CSMD2") || !pass.equals("C1119H1")) {
            System.out.print("username or pass is incoRrect");
            System.out.print("input 'y' to relogin or 'n' to Log in as a guest: ");
            submitReLogin = scanner.nextLine();
            if (submitReLogin.equals(String.valueOf('y'))) {
                isGuestOrAdmin();
            } else return false;
        }
        return true;
    }

@Override
    public void logIn() {
        if (isGuestOrAdmin()) {
            System.out.println("log in success!");
            this.admin.showMenu();
        } showMenu();
    }
}
