package CaseStudyMD2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminControl extends GuestControl {
    private File srcDicFile;
    private HashMap<String, String> DicHashMap;

    public AdminControl(HashMap<String, String> DicHashMap, String srcDic) {
        super(DicHashMap);
         this.DicHashMap  = DicHashMap;
        File fileDic = new File(srcDic);
        this.srcDicFile = fileDic;
    }


    public void addNewWord() {
        System.out.println("please input:");
        System.out.print("-word: ");
        String newWord = scanner.nextLine();
        System.out.print("-pronounce: /.../ : ");
        String newPronounce = scanner.nextLine();
        System.out.print("-type of word:");
        String newTypeOfWord = scanner.nextLine();
        System.out.print("-mean: ");
        String newMean = scanner.nextLine();
        String contentNewWord = newWord + " " + newPronounce + "\n" + "*" + newTypeOfWord + "\n" + "-" + newMean + "\n\n" + "@";
        writeFile(this.srcDicFile, contentNewWord, true);
    }

    public void changeDic() {
        System.out.print("Please enter the word: ");
        String keyChange = scanner.nextLine();
        if (!isWordExist(keyChange)) {
            searchApproximateWord(keyChange);
        }
        System.out.println("what value you want to change?");
        System.out.print("1.word    2.pronounce    3.mean\nchoose: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                scanner.nextLine();
                changeWord(keyChange);
                break;
            }
            case 2: {
                scanner.nextLine();
                changePronounce(keyChange);
                break;
            }
            case 3: {
                scanner.nextLine();
                changeMean(keyChange);
                break;
            }
        }

    }

    public void changeWord(String keyChange) {
        System.out.print("new word:");
        String newWord = scanner.nextLine();
        String stringDic = creatStringDic();
        stringDic = stringDic.replaceAll(keyChange, newWord);
        stringDic = stringDic.replaceAll("_", "\n");
        writeFile(this.srcDicFile, stringDic, false);
    }

    public void changePronounce(String keyChange) {
        System.out.print("new pronounce:/.../ :");
        String newPronounce = scanner.nextLine();
        String stringDic = creatStringDic();
        String regexMean = "^/(.*?)_(.*?)$";
        Pattern pattern = Pattern.compile(regexMean);
        Matcher matcher = pattern.matcher(this.DicHashMap.get(keyChange));

        if (matcher.matches()) {
            String oldPronounce = matcher.group(1);
            stringDic = stringDic.replaceAll(oldPronounce, newPronounce);
        }
        stringDic = stringDic.replaceAll("_", "\n");
        writeFile(this.srcDicFile, stringDic, false);
    }

    public void changeMean(String keyChange) {
        System.out.print("new mean(Use _ to separate between lines): ");
        String newMean = scanner.nextLine();
        String stringDic = creatStringDic();
        String regexMean = "^/(.*?)(_\\*)(.*?)$";
        Pattern pattern = Pattern.compile(regexMean);
        Matcher matcher = pattern.matcher(this.DicHashMap.get(keyChange));
        if (matcher.matches()) {
            String oldMean = matcher.group(3);
            oldMean = oldMean.replaceAll("\\s", "#+");
//            System.out.println(matcher.matches());
//            System.out.println(oldMean);

            stringDic = stringDic.replace(oldMean, newMean);
        }
        stringDic = stringDic.replaceAll("(#+)", " ");
        stringDic = stringDic.replaceAll("_", "\n");
        writeFile(this.srcDicFile, stringDic, false);
    }

    public void deleteWord() {
        System.out.print("enter the word you want to delete: ");
        String wordDelete = scanner.nextLine();
        this.DicHashMap.remove(wordDelete);
        String stringDic = "";
        String regexMean = "^/(.*?)_(.*?)$";
        Pattern pattern = Pattern.compile(regexMean);
        for (String keyWord : this.DicHashMap.keySet()) {
            Matcher matcherMean = pattern.matcher(this.DicHashMap.get(keyWord));
                if (!keyWord.equals(wordDelete)) {
                    stringDic +="@"+ keyWord + " " + this.DicHashMap.get(keyWord).replaceAll("//","/")+"\n\n";
                }

        }
        stringDic = stringDic.replaceAll("_", "\n");
        stringDic += "@";
        writeFile(this.srcDicFile, stringDic, false);
    }

    public String creatStringDic() {
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
        return stringDic;
    }

}

