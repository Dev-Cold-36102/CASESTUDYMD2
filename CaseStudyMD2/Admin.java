package CaseStudyMD2;

import java.io.File;
import java.util.HashMap;

public class Admin extends AdminControl {


    public Admin(HashMap<String, String> DicHashMap, String srcDic) {
        super(DicHashMap, srcDic);


    }

    @Override
    public void LogOut() {
        System.out.print("are you sure? y/n: ");
        String submit = scanner.nextLine();
        if (submit.equals(String.valueOf('y'))) {
            return;
        }
    }
}
