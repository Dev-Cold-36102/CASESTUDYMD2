package CaseStudyMD2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuestControl extends ControlAbstract {
    private HashMap<String, String> DicHashMap;
    private ArrayList<String> wordSearchedList = new ArrayList();
    Scanner scanner = new Scanner(System.in);

    public GuestControl(HashMap<String, String> DicHashMap) {
        this.DicHashMap = DicHashMap;
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
                return;
            }
        }
        showMenu();
    }

    public void searchWord() {
        System.out.print("Please enter the word: ");
        String keySearch = scanner.nextLine();
        if (!isWordExist(keySearch)) {
            System.out.println("that word is incorrect or the dic does not have it!");
            System.out.println("what do you want? 1.enter other word   2. search Approximate Word");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 2) {
                searchApproximateWord(keySearch);
            }
            searchWord();
        } else {
            wordSearchedList.add(keySearch);
            System.out.println("1. PRONOUNCE\n2.TYPE OF WORD\n3.MEAN\n4.FULL");
            System.out.print("please enter the value you want to search: ");
            int valueSearch = scanner.nextInt();
            switch (valueSearch) {
                case 1: {
                    searchPronounce(keySearch);
                    break;
                }
                case 2: {
                    searchTypeOfWord(keySearch);
                    break;
                }
                case 3: {
                    searchMean(keySearch);
                    break;
                }
                case 4: {
                    searchAll(keySearch);
                    break;
                }
            }
        }
    }


    @Override
    public void searchMean(String keySearch) {
//        scanner.nextLine();
//        System.out.print("Please enter the word: ");
//        String keySearch = scanner.nextLine();
        String regexMean = "^/(.*?)_(.*?)$";
        Pattern pattern = Pattern.compile(regexMean);
        Matcher matcher = pattern.matcher(this.DicHashMap.get(keySearch));
        if (matcher.find()) {
            print('-', 40);
            System.out.printf("|  %-20s|%s |\n", "WORD", "MEAN");

            System.out.printf(" - %-20s%s \n", keySearch, matcher.group(2).replaceAll("_", "\n" + print(' ', 25)));
            print('-', 40);
        }


    }

    @Override
    public void searchPronounce(String keySearch) {
        scanner.nextLine();
        String regexPronounce = "^/(.*?)_";
        Pattern pattern = Pattern.compile(regexPronounce);
        Matcher matcher = pattern.matcher(this.DicHashMap.get(keySearch));
        //draw display

        if (matcher.find()) {
            print('-', 40);
            System.out.printf("| %-20s|%-15s |\n", "WORD", "PRONOUNCE");
            System.out.printf("| %-20s|%-15s |\n", keySearch, matcher.group(1));
            print('-', 40);

        }
    }

    public void searchAll(String keySearch) {
        HashMap<String, String> typeWordAndMeanMap = new HashMap<>();
        String regexPronounce = "^/(.*?)_";
        Pattern patternPronounce = Pattern.compile(regexPronounce);
        Matcher matcherPronounce = patternPronounce.matcher(this.DicHashMap.get(keySearch));
        String regexTypeOfWord = "\\*";

        Pattern patternTypeOfWord = Pattern.compile(regexTypeOfWord);
        Matcher matcherTypeOfWord = patternTypeOfWord.matcher(this.DicHashMap.get(keySearch));
        int amountOfType = 0;
        while (matcherTypeOfWord.find()) {
            amountOfType++;
        }
        if (amountOfType > 2) {
            this.DicHashMap.replace(keySearch, this.DicHashMap.get(keySearch) + "_*");
            String regexAll = "\\*(.*?)_(.*?)(_\\*)";
            Pattern patternAll = Pattern.compile(regexAll);
            Matcher matcherAll = patternAll.matcher(this.DicHashMap.get(keySearch));
            while (matcherAll.find()) {
                typeWordAndMeanMap.put(matcherAll.group(1), matcherAll.group(2));
            }

        } else {
            String regexAll = "\\*(.*?)_(.*?)$";
            Pattern patternAll = Pattern.compile(regexAll);
            Matcher matcherAll = patternAll.matcher(this.DicHashMap.get(keySearch));
            if (matcherAll.find()) {
                typeWordAndMeanMap.put(matcherAll.group(1), matcherAll.group(2));
            }
        }

        print('-', 100);
        System.out.printf("| %-15s|%-20s|%-25s|%s |\n", "WORD", "PRONOUNCE", "TYPE", "MEAN");
        if (matcherPronounce.find()) {
            System.out.printf("|-%-15s|%-20s", keySearch, matcherPronounce.group(1));
        }
        int countLine = 0;
        for (String keyType : typeWordAndMeanMap.keySet()) {
            if (amountOfType <= 2) {
                System.out.printf("|%-25s%s", keyType, typeWordAndMeanMap.get(keyType).replaceAll("_", "\n"));
                break;
            } else {
                if (countLine == 0) {
                    System.out.printf("|%-25s%s", keyType, typeWordAndMeanMap.get(keyType).replaceAll("_", "\n" + print(' ', 65)));
                    countLine++;
                } else
                    System.out.printf("\n|%-38s%-25s%s", " ", keyType, typeWordAndMeanMap.get(keyType).replaceAll("_", "\n" + print(' ', 65)));
            }
        }

    }

    public String print(char ch, int amountOfCh) {
        String string = "";
        for (int i = 0; i < amountOfCh; i++) {
            string += String.valueOf(ch);
        }
        return string;
    }

    @Override
    public void searchTypeOfWord(String keySearch) {
        scanner.nextLine();
        String regexTypeOfWord = "\\*(.*?)_";
        Pattern patternTypeOfWord = Pattern.compile(regexTypeOfWord);
        Matcher matcherTypeOfWord = patternTypeOfWord.matcher(this.DicHashMap.get(keySearch));
        while (matcherTypeOfWord.find()) {
            System.out.println(matcherTypeOfWord.group(1).replaceAll("_", "\n"));
        }
    }

    @Override
    public void viewWordSearched() {
        if (wordSearchedList.size() == 0) {
            System.out.println("You have not found any words!");
        } else {
            for (int i = 0; i < wordSearchedList.size(); i++) {
                System.out.println("- " + wordSearchedList);
            }
        }

    }

    @Override
    public void exportListWord() throws IOException {
        File fileExport;
        String nameFile;
        int choice = 0;
        boolean submitReplaceOrAdd = false;
        do {
            do {

                System.out.println("enter the name of file: ");
                nameFile = scanner.nextLine();
            } while (nameFile.equals(""));

            String FILENAME = nameFile + ".txt";
            fileExport = new File(FILENAME);
            if (!fileExport.exists()) {
                fileExport.createNewFile();
            } else {
                do {
                    System.out.print("that file is existed.What do you want?\n1.Replace\n2.Enter an other name\n3.Add word to that file\n ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                } while (choice != 1 && choice != 2 && choice != 3);
            }
        } while (choice == 2);

        if (choice == 1) {

            String submitReplace;
            do {
                System.out.println("Are you sure? y/n");
                submitReplace = scanner.nextLine();
            } while (!submitReplace.equals(String.valueOf('y')) && !submitReplace.equals(String.valueOf('n')));

            if (submitReplace.equals(String.valueOf('y'))) {
                submitReplaceOrAdd = false;
            } else exportListWord();

        } else submitReplaceOrAdd = true;
        String wordSearchedString = "";
        for (int i = 0; i < wordSearchedList.size(); i++) {
            wordSearchedString += "- " + wordSearchedList.get(i) + "\n";
        }
        writeFile(fileExport, wordSearchedString, submitReplaceOrAdd);

    }

    @Override
    public void searchApproximateWord(String keySearch) {
        String regexKeySearch = "(" + keySearch + ")";
        Pattern patternKeySearch = Pattern.compile(regexKeySearch);
        int countApproximateWord = 0;
        for (HashMap.Entry<String, String> entry : this.DicHashMap.entrySet()) {
            Matcher matcherKeySearch = patternKeySearch.matcher(entry.getKey());
            if (matcherKeySearch.find()) {
                if (countApproximateWord == 0)
                    System.out.println("Approximate Word:");
                countApproximateWord++;
                System.out.println("-" + entry.getKey());
            }
        }
        if (countApproximateWord == 0) {
            System.out.println("Sorry, we can't find!");
        }
    }

    public boolean isWordExist(String keySearch) {
        if (!this.DicHashMap.containsKey(keySearch)) {
            return false;
        } else return true;
    }

    public void writeFile(File fileExport, String content, boolean submitReplaceOrAdd) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileExport, submitReplaceOrAdd))) {
            bufferedWriter.write(content);
            System.out.println("Done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logIn() {
    }

}
