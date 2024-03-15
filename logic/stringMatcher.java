package logic;

import java.util.ArrayList;
import java.util.List;

public class stringMatcher {
    public static void findOccurrences(String text, String word) {
        String lowerCaseText = text.toLowerCase();
        String lowerCaseWord = word.toLowerCase();  

        int index = 0;
        int count = 0;
        List<Integer> positions = new ArrayList<>();

        while (index != -1) {
            index = lowerCaseText.indexOf(lowerCaseWord, index);

            if (index != -1) {
                count++;
                positions.add(index);
                index += lowerCaseWord.length();
            }
        }

        System.out.println("Number of occurrences: " + count);
        System.out.println("Word's positions: " + positions);
    }
}