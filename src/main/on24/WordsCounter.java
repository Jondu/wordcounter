package on24;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hando Lukats.
 */
public class WordsCounter {

    private Map<String, Integer> counts = new HashMap<>();

    public Map<String, Integer> getCounts() {
        return counts;
    }

    public void count(String input) {
        String cleanedInput = input.replaceAll("[^\\p{L}0-9 ]", "");
        for (String word : cleanedInput.split(" ")) {
            if (counts.containsKey(word)) {
                counts.put(word, counts.get(word) + 1);
            } else if(word.length() > 0) {
                counts.put(word, 1);
            }
        }
    }

}

