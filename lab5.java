import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class lab5 {

    String[][] companyPreferences = 
    {{"2", "5", "1", "3", "4"}, // A
    {"1", "2", "3", "4", "5"},  // B
    {"5", "3", "2", "1", "4"},  // C
    {"1", "3", "2", "4", "5"},  // D
    {"2", "3", "5", "4", "1"}}; // E 

    String[][] programmerPreferences = 
    {{"E", "A", "D", "B", "C"}, // 1
    {"D", "E", "B", "A", "C"},  // 2
    {"D", "B", "C", "E", "A"},  // 3
    {"C", "B", "D", "A", "E"},  // 4
    {"A", "D", "B", "C", "E"}}; // 5

    public static int convertAlphabetToIndex(String letter) {
        char l = letter.toLowerCase().charAt(0);
        int pos = l - 'a';
        return pos;
    }

    public String[] findCompanyBestPref(int n) {
        String bestPref = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 4) {
                    bestPref = companyPreferences[i][j];
                    break;
                }
                if (companyPreferences[i][j].equals(companyPreferences[i][j])) {
                    i++;
                    j = -1;
                } else {
                    bestPref = companyPreferences[i][j];
                }
            }
        }
        return bestPref;
    }

    public void createMatches(int n) {
        String[] matches = new String[5];
            findCompanyBestPref(n);
        System.out.println(Arrays.toString(matches));
    }

    public static void main(String[] args) {
        lab5 lab = new lab5();
        lab.createMatches(5);
    }
}
