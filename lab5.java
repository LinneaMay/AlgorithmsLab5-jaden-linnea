import java.util.Collections;
import java.util.List;
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

    
}
