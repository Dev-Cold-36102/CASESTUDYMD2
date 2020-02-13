package CaseStudyMD2;

import java.io.IOException;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) throws IOException {
        String pathSrcDic = "dic1.txt";
        DicHashmap fileDic = new DicHashmap(pathSrcDic);
        HashMap<String, String> dicHashMap = fileDic.inputToHashMapDic();
        User user = new User(pathSrcDic, dicHashMap);
        user.logIn();
    }

}
