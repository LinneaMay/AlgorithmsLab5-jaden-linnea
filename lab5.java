import java.util.Arrays;
public class lab5 {

    static String[][] companyPreferences = 
    {{"2", "5", "1", "3", "4"}, // A
    {"1", "2", "3", "4", "5"},  // B
    {"5", "3", "2", "1", "4"},  // C
    {"1", "3", "2", "4", "5"},  // D
    {"2", "3", "5", "4", "1"}}; // E 

    static String[][] programmerPreferences = 
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

    public static String convertIndexToAlphabet(int index) {
        int pos = index + 'a';
        String letter =  Character.toString((char)pos);
        return letter.toUpperCase();
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
        
        findSatisfactoryPairing(companyPreferences, programmerPreferences);
    }
    
}
