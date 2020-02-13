package CaseStudyMD2;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminControl extends GuestControl {
    private File srcDicFile;
    private HashMap<String, String> DicHashMap;

    public AdminControl(HashMap<String, String> DicHashMap, String srcDic) {
        super(DicHashMap);
        this.DicHashMap = DicHashMap;
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
        String stringDic = "@";
        for (HashMap.Entry<String, String> entry : this.DicHashMap.entrySet()) {
            if (entry.getKey().equals(keyChange)) {
                stringDic += newWord + " " + entry.getValue().replaceAll("_", "\n") + "\n\n@";
            } else stringDic += entry.getKey() + " " + entry.getValue().replaceAll("_", "\n") + "\n\n@";
        }
        writeFile(this.srcDicFile, stringDic, false);
    }

    public void changePronounce(String keyChange) {
        System.out.print("new pronounce:/.../ :");
        String newPronounce = scanner.nextLine();
        String stringDic = "@";
        String regexMean = "^/(.*?)_(.*?)$";
        Pattern pattern = Pattern.compile(regexMean);
        Matcher matcher = pattern.matcher(this.DicHashMap.get(keyChange));
        String mean = "";
        if (matcher.find()) {
            mean = matcher.group(2);
        }
        for (HashMap.Entry<String, String> entry : this.DicHashMap.entrySet()) {
            if (entry.getKey().equals(keyChange)) {
                stringDic += entry.getKey() + " " + newPronounce + "\n" + mean.replaceAll("_", "\n");
            } else {
                Matcher matcher1 = pattern.matcher(entry.getValue());
                if (matcher1.find())
                    stringDic += entry.getKey() + " " + matcher1.group(1) + "\n" + matcher1.group(2).replaceAll("_", "\n");
            }
            stringDic += "\n\n" + "@";
        }
        writeFile(this.srcDicFile, stringDic, false);
    }

    public void changeMean(String keyChange) {
        System.out.print("new mean(Use _ to separate between lines): ");
        String newMean = scanner.nextLine();
        String stringDic = "@";
        String regexMean = "^/(.*?)_(.*?)$";
        Pattern pattern = Pattern.compile(regexMean);
        Matcher matcher = pattern.matcher(this.DicHashMap.get(keyChange));
        String pronounce = "";
        if (matcher.find()) {
            pronounce = matcher.group(1);
        }
        for (HashMap.Entry<String, String> entry : this.DicHashMap.entrySet()) {
            if (entry.getKey().equals(keyChange)) {
                stringDic += entry.getKey() + " " + pronounce + "\n" + newMean.replaceAll("_", "\n");
            } else {
                Matcher matcher1 = pattern.matcher(entry.getValue());
                if (matcher1.find())
                    stringDic += entry.getKey() + " " + matcher1.group(1) + "\n" + matcher1.group(2).replaceAll("_", "\n");
            }
            stringDic += "\n\n" + "@";
        }
        writeFile(this.srcDicFile, stringDic, false);
    }

    public void deleteWord() {
        System.out.print("enter the word you want to delete: ");
        String wordDelete = scanner.nextLine();
        this.DicHashMap.remove(wordDelete);
        String stringDic = "@";
        String regexMean = "^/(.*?)_(.*?)$";
        Pattern pattern = Pattern.compile(regexMean);
        for (HashMap.Entry<String, String> entry : this.DicHashMap.entrySet()) {
            Matcher matcher = pattern.matcher(entry.getValue());
            if (matcher.find()) {
                stringDic += entry.getKey() + " " + matcher.group(1) + "\n" + matcher.group(2).replaceAll("_", "\n");
            }
        }
        stringDic += "\n\n" + "@";
        writeFile(this.srcDicFile, stringDic, false);
    }


}

