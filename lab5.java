// Jaden
// Linnea

import java.util.Arrays;
public class lab5 {

    public static int convertAlphabetToIndex(String letter) {
        char l = letter.toLowerCase().charAt(0);
        int pos = l - 'a';
        return pos;
    }

    public static String convertIndexToAlphabet(int index) {
        int pos = index + 'a';
        String letter =  Character.toString((char)pos);
        return letter.toUpperCase();
    }

    public static String[] findPairWithCompany(String company, String[][] preferences) {
        int length = preferences.length;
        for(int i = 0; i < length; i++) {
            if(preferences[i][0].equals(company)) {
                return preferences[i];
            }
        }
        return null;
    }

    

    public static boolean testPairPreferences(String[][] preferences, String[][] programmerPreferences, String[][] companyPreferences){
        int length = preferences.length;
        for(int i = 0; i < length; i++) {

            String pairedCompany = preferences[i][0];

            int pairedProgrammer = Integer.parseInt(preferences[i][1]);

            String[] programmerPreferenceArray = programmerPreferences[pairedProgrammer - 1];

            int pairedCompanyIndex = Arrays.asList(programmerPreferenceArray).indexOf(pairedCompany);

            for(int j = pairedCompanyIndex - 1; j >= 0; j--) {

                String checkedCompany = programmerPreferenceArray[j];

                String[] checkedPair = findPairWithCompany(checkedCompany, preferences);

                String comparisonProgrammer = checkedPair[1];

                int checkedCompanyI = convertAlphabetToIndex(checkedCompany);

                String[] companyPreferenceArray = companyPreferences[checkedCompanyI];

                int currentProgrammerI = Arrays.asList(companyPreferenceArray).indexOf(String.valueOf(pairedProgrammer));

                int compareProgrammerI = Arrays.asList(companyPreferenceArray).indexOf(comparisonProgrammer);

                if(compareProgrammerI > currentProgrammerI) {
                    System.out.println("this pairing is not satisfactory: " + Arrays.toString(preferences[i]) + " with this pairing: " + Arrays.toString(checkedPair));
                    return false;
                }
            }
        }
        return true;
    }

    public static int findProgrammerPreference(int programmerNum, int companyA, int companyB, String[][] programmerPreferences) {
        String[] programmerPreferenceArray = programmerPreferences[programmerNum - 1];

        String companyAChar = convertIndexToAlphabet(companyA);
        String companyBChar = convertIndexToAlphabet(companyB);

        int indexA = Arrays.asList(programmerPreferenceArray).indexOf(companyAChar);
        int indexB = Arrays.asList(programmerPreferenceArray).indexOf(companyBChar);

        int programmerPreferenceI = Math.min(indexA, indexB);
        
        int preferredCompanyI = convertAlphabetToIndex(programmerPreferenceArray[programmerPreferenceI]);
        return preferredCompanyI;

    }

    public static void matchProgrammerWithCompany(int companyI, int programmerNum, int onPreference, boolean[] companyMatched, boolean[] programmerMatched, String[][] preferences) {
        preferences[onPreference][0] = convertIndexToAlphabet(companyI);
        preferences[onPreference][1] = Integer.toString(programmerNum);

        companyMatched[companyI] = true;
        programmerMatched[programmerNum -1] = true;
    }

    public static String[][] findSatisfactoryPairing(String[][] companyPreferences, String[][] programmerPreferences ) {
        int length = companyPreferences.length;

        String[][] preferences = new String[length][2];

        boolean[] companyMatched = new boolean[length];

        boolean[] programmerMatched = new boolean[length];

        int programmerMatchCount = 0;

        int[] companyIndex = new int[length];


        for(int companyI = 0; companyI < length; companyI++) {

            if(programmerMatchCount >= length) {
                break;
            }

            while(!companyMatched[companyI]){

                int currentCompanyPrefernceIndex = companyIndex[companyI];

                String currentProgrammer = companyPreferences[companyI][currentCompanyPrefernceIndex];

                int currentProgrammerNum = Integer.parseInt(currentProgrammer);

                int topCompanyIForCurrentProgrammer = companyI;

                if(!programmerMatched[currentProgrammerNum - 1]){
                    if(companyI + 1 >= length) {
                        matchProgrammerWithCompany(companyI, currentProgrammerNum, programmerMatchCount, companyMatched, programmerMatched, preferences);
                        programmerMatchCount++;
                        break;
                    }
                    for(int compareCompanyI = companyI + 1; compareCompanyI < length; compareCompanyI++) {

                        int compareCompanyPrefernceIndex = companyIndex[compareCompanyI];

                        String compareCompanyProgrammer = companyPreferences[compareCompanyI][compareCompanyPrefernceIndex];
                        
                        int compareCompanyProgrammerNum = Integer.parseInt(compareCompanyProgrammer);

                     if (currentProgrammerNum == compareCompanyProgrammerNum) {
                        topCompanyIForCurrentProgrammer = findProgrammerPreference(currentProgrammerNum, topCompanyIForCurrentProgrammer, compareCompanyI, programmerPreferences);
                     }

                     //if at the end of the comparing companies, match current programmer with current company
                     if(compareCompanyI == length -1) {
                        //if current company is not top company for that programmer, move onto their next company preference;
                        if(topCompanyIForCurrentProgrammer != companyI) {
                            companyIndex[companyI]++;
                        }
                        matchProgrammerWithCompany(topCompanyIForCurrentProgrammer, currentProgrammerNum, programmerMatchCount, companyMatched, programmerMatched, preferences);
                        programmerMatchCount++;

                     }

                    }
                }   
                else {
                    companyIndex[companyI]++;
                }
            }
        }
        for(int i=0; i < length; i++){
            System.out.println(Arrays.toString(preferences[i]));
        }
        return preferences;
    }

    public static void main(String[] args) {
        // System.out.println(convertAlphabetToIndex("C"));
        // System.out.println(convertIndexToAlphabet(3));

        // System.out.println(findProgrammerPreference(1, 0, 4, programmerPreferences));

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

        String[][] companyPreferences2 = 
        {{"5", "1", "2", "3", "4"}, // A
        {"1", "2", "3", "4", "5"},  // B
        {"2", "5", "1", "3", "4"},  // C
        {"1", "3", "2", "4", "5"},  // D
        {"4", "3", "5", "2", "1"}}; // E 
    
        String[][] programmerPreferences2 = 
        {{"E", "A", "D", "B", "C"}, // 1  
        {"D", "A", "E", "C", "B"},  // 2
        {"C", "E", "D", "A", "B"},  // 3
        {"D", "E", "B", "A", "C"},  // 4
        {"A", "D", "B", "C", "E"}};  // 5

        String[][] companyPreferences3 = 
        {{"1", "2", "3", "4", "5"}, // A
        {"1", "2", "3", "4", "5"},  // B
        {"2", "5", "1", "3", "4"},  // C
        {"1", "3", "2", "4", "5"},  // D
        {"4", "3", "5", "2", "1"}}; // E 
    
        String[][] programmerPreferences3 = 
        {{"E", "A", "D", "B", "C"}, // 1  
        {"D", "E", "B", "A", "C"},  // 2
        {"C", "E", "D", "A", "B"},  // 3
        {"C", "E", "D", "A", "B"},  // 4
        {"A", "D", "B", "C", "E"}};  // 5

        String[][] testUnsatisfactoryPreferences =
        {
            {"C","4"},
            {"A", "3"},
            {"B", "5"},
            {"D", "2"},
            {"E", "1"}

        };
        System.out.println("are fake Preferences valid:");
        System.out.println(testPairPreferences(testUnsatisfactoryPreferences, programmerPreferences, companyPreferences));

        System.out.println("test trial 1");
        String[][] preferences = findSatisfactoryPairing(companyPreferences, programmerPreferences);
        System.out.println("are Preferences valid: " + testPairPreferences(preferences, programmerPreferences, companyPreferences));

        System.out.println("test trial 2");
        String[][] preferences2 = findSatisfactoryPairing(companyPreferences2, programmerPreferences2);
        System.out.println("are Preferences valid: " + testPairPreferences(preferences2, programmerPreferences2, companyPreferences2));

        System.out.println("test trial 3");
        String[][] preferences3 = findSatisfactoryPairing(companyPreferences3, programmerPreferences3);
        System.out.println("are Preferences valid: " + testPairPreferences(preferences3, programmerPreferences3, companyPreferences3));


    }
    
}
