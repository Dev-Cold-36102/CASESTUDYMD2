package CaseStudyMD2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.management.ThreadInfo;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Admin extends AdminControl implements AddAccount, DeleteAccount, ChangeAccount {
    private String username;
    private String pass;
    private File fileAccount;
    private HashMap<String, String> accountListMap;

    public HashMap<String, String> getAccountListMap() {
        return this.accountListMap;
    }

    public void setAccountListMap(HashMap<String, String> accountListMap) {
        this.accountListMap = accountListMap;
    }


    public Admin(HashMap<String, String> DicHashMap, String srcDic, String srcAccountFile) {
        super(DicHashMap, srcDic);
        File file = new File(srcAccountFile);
        this.fileAccount = file;
        this.accountListMap = new HashMap<>();
        setAccount();

//        this.username = username;
//        this.pass = pass;
    }

    public void setAccount() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(this.fileAccount);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        scanner.useDelimiter("\\Z");
        String stringAccount = scanner.next();
        scanner.close();
        stringAccount = stringAccount.replaceAll("\\n", "enter");
        String regexAccount = "(-username:)(.*?)(enter)(-pass:)(.*?)(enter)";
        Pattern patternAccount = Pattern.compile(regexAccount);
        Matcher matcherAccount = patternAccount.matcher(stringAccount);
        while (matcherAccount.find()) {
//            this.setUsername(matcherAccount.group(2));
//            this.setPass(matcherAccount.group(5));
            this.accountListMap.put(matcherAccount.group(2), matcherAccount.group(5));
        }
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
        System.out.println("5.change word   6.delete word          7.log out                     8.change account"  );
        System.out.println("9.add account   10.delete account      11.exit");
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
                addAccount();
                break;
            }
            case 10: {
                scanner.nextLine();
                deleteAccount();
                break;
            }
            case 11: {
                scanner.nextLine();
                System.exit(0);
            }
        }
        showMenu();
    }

    public boolean LogOut() {
        System.out.print("are you sure? y/n: ");
        String submit = scanner.nextLine();
        if (submit.equals(String.valueOf('y'))) {
            System.out.println("log out done!");
            return true;
        } else return false;
    }

    @Override
    public void changeAccount() {
        if (identityVerification()) {
            System.out.print("new username: ");
            String newUserName = scanner.nextLine();
            System.out.print("new pass: ");
            String newPass = scanner.nextLine();
            String stringAccount = creatStringAccount();


            stringAccount = stringAccount.replace(this.getUsername(), newUserName);
            stringAccount = stringAccount.replace(this.getPass(), newPass);
            stringAccount = stringAccount.replaceAll("enter", "\n");
            writeFile(this.fileAccount, stringAccount, false);
            setAccount();
        } else System.out.println("verification failed");
    }


    @Override
    public void addAccount() {
        if (identityVerification()) {
            System.out.print("new username: ");
            String newUserName = scanner.nextLine();
            System.out.print("new pass: ");
            String newPass = scanner.nextLine();
            String newAccountContent = "username:" + newUserName + "\n" + "-pass:" + newPass + "\n" + "-";
            writeFile(this.fileAccount, newAccountContent, true);
            setAccount();
        }
    }

    @Override
    public void deleteAccount() {
        if (identityVerification()) {
            System.out.print("are you sure? y/n");
            String submit=scanner.nextLine();
            if (submit.equals(String.valueOf('y'))) {
                String stringAccount=creatStringAccount();
                String regexAccount="-username:"+this.getUsername()+"(enter)"+"-pass:"+this.getPass()+"(enter)"+"-";
                stringAccount=stringAccount.replace(regexAccount,"-");
                stringAccount=stringAccount.replaceAll("enter","\n");
                writeFile(this.fileAccount,stringAccount,false);
                setAccount();
            }
        }
    }

    public boolean isAccountExist(String username, String pass) {
        if (this.accountListMap.containsKey(username)) {
            if (this.accountListMap.get(username).equals(pass)) {
                return true;
            } else return false;
        } else return false;
    }

    public boolean identityVerification() {
        System.out.println("Identity verification first!");
        int choice = 0;
        do {
            System.out.print("username: ");
            String username = scanner.nextLine();
            System.out.print("pass: ");
            String pass = scanner.nextLine();
            if (isAccountExist(username, pass)) {
                System.out.println("verification successful!\n");
                break;
            } else {
                do {
                    System.out.println("username or pass is incorect. 1.relogin   2.back to menu");
                    choice = scanner.nextInt();
                    if (choice == 2) {
                        return false;
                    } else scanner.nextLine();
                } while (choice != 1);
            }
        } while (choice == 1);
        return true;
    }

    public String creatStringAccount() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(this.fileAccount);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        scanner.useDelimiter("\\Z");
        String stringAccount = scanner.next();
        scanner.close();
        stringAccount = stringAccount.replaceAll("\\n", "enter");
        return stringAccount;
    }

}
