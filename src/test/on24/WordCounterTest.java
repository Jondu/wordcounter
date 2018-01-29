package on24;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Hando Lukats.
 */
public class WordCounterTest {

    @Test
    public void getCountsTest() {

        WordsCounter wordCounter = new WordsCounter();

        Map wordCounts = wordCounter.getCounts();

        assertNotNull(wordCounts);
    }

    @Test
    public void countTest(){
        WordsCounter wordCounter = new WordsCounter();

        String testString = "mets jänes mets";
        wordCounter.count(testString);

        Map<String, Integer> counts = wordCounter.getCounts();
        assertTrue(counts.get("mets") == 2);
        assertTrue(counts.get("jänes") == 1);
    }


    @Test
    public void countTestWithoutPunctuation(){
        WordsCounter wordCounter = new WordsCounter();

        String testString = "mets, jänes! mets.";
        wordCounter.count(testString);

        Map<String, Integer> counts = wordCounter.getCounts();
        assertTrue(counts.get("mets") == 2);
        assertTrue(counts.get("jänes") == 1);
    }
}
