package CaseStudyMD2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DicHashmap {
    private File srcDicFile;

    public DicHashmap(String srcDic) {
        File fileDic = new File(srcDic);
        this.srcDicFile = fileDic;
    }

    public HashMap<String, String> inputToHashMapDic() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(this.srcDicFile);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        scanner.useDelimiter("\\Z");
        String stringDic = scanner.next();
        scanner.close();
        stringDic = stringDic.replaceAll("\\n", "_");
        stringDic = stringDic.replaceAll("(\\s/)", "///");
        String regexKeyValue = "@(.*?)/(.*?)_{2}";
        HashMap<String, String> dictionaryMap = new HashMap<>();
        Pattern pattern = Pattern.compile(regexKeyValue);
        Matcher matcher = pattern.matcher(stringDic);
        while (matcher.find()) {
            dictionaryMap.put(matcher.group(1), matcher.group(2));
        }
        return dictionaryMap;
    }
}
