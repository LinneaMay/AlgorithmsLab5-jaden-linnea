import java.util.Collections;
import java.util.List;
public class lab5 {

    String[][] companyPreferences = {{"2", "5", "1", "3", "4"}, 
    {"1", "2", "3", "4", "5"},
    {"5", "3", "2", "1", "4"},
    {"1", "3", "2", "4", "5"},
    {"2", "3", "5", "4", "1"}};

    public static int convertAlphabetToIndex(String letter) {
        char l = letter.toLowerCase().charAt(0);
        int pos = l - 'a';
        return pos;
    }

    
}
