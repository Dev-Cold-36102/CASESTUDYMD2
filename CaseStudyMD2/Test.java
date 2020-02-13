package CaseStudyMD2;

import java.io.IOException;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) throws IOException {
       DicHashmap fileDic = new DicHashmap("dic.txt");
        HashMap<String, String> dicHashMap = fileDic.inputToHashMapDic();

        AdminControl admin = new AdminControl(dicHashMap,"dic.txt");
        User user=new User("dic.txt",dicHashMap);
        user.logIn();
//admin.searchWord();
//            admin.changePronounce("a");
//
//        guest.exportListWord();

//        guest.addNewWord();
//        admin.LogOut();
    }

}
