package CaseStudyMD2;

import java.io.IOException;
import java.util.HashMap;

public class Guest extends GuestControl {
    private Admin admin;

    public Guest(HashMap<String, String> DicHashMap, Admin admin) {
        super(DicHashMap);
        this.admin = admin;
    }

    public void showMenu() {
        System.out.println(print('-', 100));
        System.out.println("1.search word   2.view word searched   3.export list word searched");
        System.out.println("4.log in        5.exit");
        System.out.println(print('-', 100));
        System.out.print("choose: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                scanner.nextLine();
                searchWord();
                break;
            }
            case 2: {
                scanner.nextLine();
                viewWordSearched();
                break;
            }
            case 3: {
                scanner.nextLine();
                try {
                    exportListWord();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 4: {
                scanner.nextLine();
                logIn();
                break;
            }
            case 5: {
                scanner.nextLine();
                System.exit(0);
            }
        }
        showMenu();
    }


    @Override
    public void logIn() {
        String username;
        String pass;
        int submitReLogin = 0;
            do {
                System.out.print("username: ");
                username = scanner.nextLine();
                System.out.print("pass: ");
                pass = scanner.nextLine();
                if (this.admin.getAccountListMap().containsKey(username)) {
                    if (this.admin.getAccountListMap().get(username).equals(pass)) {
                        this.admin.setUsername(username);
                        this.admin.setPass(pass);
                        System.out.println("log in success!");
                        this.admin.showMenu();
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
       showMenu();

    }
}
