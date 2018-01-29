package on24;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Hando Lukats.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        InputProcessor inputProcessor = new InputProcessor(new FileInputStream(args[0]));
        WordsCounter wordsCounter = new WordsCounter();
        String chunk;
        do {
            chunk = inputProcessor.getChunk();
            wordsCounter.count(chunk);

        } while (chunk.length() != 0);

        LinkedHashMap<String, Integer> collection = wordsCounter.getCounts().entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        writeFile(collection);
    }

    private static void writeFile(Map<String, Integer> map) throws IOException {

        FileWriter fstream;
        BufferedWriter out;

        fstream = new FileWriter("output.txt");
        out = new BufferedWriter(fstream);


        for (Map.Entry<String, Integer> p : map.entrySet()) {
            out.write(p.getKey() +":"+ p.getValue() + "\n");

        }

        out.close();
    }


}
