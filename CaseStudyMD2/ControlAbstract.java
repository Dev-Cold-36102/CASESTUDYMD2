package CaseStudyMD2;

import java.io.IOException;

public abstract class ControlAbstract {
    public abstract void searchMean(String keySearch);

    public abstract void searchPronounce(String keySearch);

    public abstract void searchTypeOfWord(String keySearch);

    public abstract void viewWordSearched();

    public void exportListWord() throws IOException {}

    public abstract void searchApproximateWord(String keySearch);

}
