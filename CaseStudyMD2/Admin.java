package CaseStudyMD2;

import java.io.IOException;
import java.util.HashMap;

public class Admin extends AdminControl {
    private String username;
    private String pass;

    public Admin(HashMap<String, String> DicHashMap, String srcDic, String username, String pass) {
        super(DicHashMap, srcDic);
        this.username = username;
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void showMenu() {
        System.out.println(print('-', 100));
        System.out.println("1.search word   2.view word searched   3.export list word searched   4.add word");
        System.out.println("5.change word   6.delete word          7.log out                     8.change account  9.exit");
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
                addNewWord();
                break;
            }
            case 5: {
                scanner.nextLine();
                changeDic();
                break;
            }
            case 6: {
                scanner.nextLine();
                deleteWord();
                break;
            }
            case 7: {
                scanner.nextLine();
                if (LogOut()) {
                    return;
                }
                break;
            }
            case 8: {
                scanner.nextLine();
                changeAccount();
                break;
            }
            case 9: {
                scanner.nextLine();
                System.exit(0);
            }
        }
        showMenu();
    }

    public void changeAccount() {
        System.out.print("new username: ");
        String newUserName = scanner.nextLine();
        System.out.print("new pass: ");
        String newPass = scanner.nextLine();
        this.setUsername(newUserName);
        this.setPass(newPass);
        System.out.println("change success!");
    }

    public boolean LogOut() {
        System.out.print("are you sure? y/n: ");
        String submit = scanner.nextLine();
        if (submit.equals(String.valueOf('y'))) {
            System.out.println("log out done!");
            return true;
        } else return false;
    }
}
