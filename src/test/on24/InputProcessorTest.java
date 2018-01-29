package on24;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

/**
 * Created by Hando Lukats.
 */
public class InputProcessorTest {

    @Test
    public void nextChunk() throws IOException {
        String testString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent hendrerit feugiat tortor ac porta. Nam quis dui id quam congue imperdiet.";
        InputStream stream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8.name()));
        InputProcessor inputProcessor = new InputProcessor(stream);
        inputProcessor.setBufferSize(57);
        String chunk = inputProcessor.getChunk();
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", chunk);
    }

    @Test
    public void getFullWordChunksOnly() throws IOException {
        String testString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent hendrerit feugiat tortor ac porta. Nam quis dui id quam congue imperdiet.";
        InputStream stream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8.name()));
        InputProcessor inputProcessor = new InputProcessor(stream);
        inputProcessor.setBufferSize(60);
        String chunk = inputProcessor.getChunk();
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", chunk);
        chunk = inputProcessor.getChunk();
        assertEquals(" Praesent hendrerit feugiat tortor ac porta. Nam quis dui id", chunk);
    }

    @Test
    public void getAllWords() throws IOException {
        String testString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent";
        InputStream stream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8.name()));
        InputProcessor inputProcessor = new InputProcessor(stream);
        inputProcessor.setBufferSize(65);
        inputProcessor.getChunk();
        String chunk = inputProcessor.getChunk();
        assertEquals(" Praesent", chunk);
        chunk = inputProcessor.getChunk();
        assertEquals("", chunk);
    }

    @Test
    public void returnEmptyStringWHenBufferEmpty() throws IOException {
        String testString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent";
        InputStream stream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8.name()));
        InputProcessor inputProcessor = new InputProcessor(stream);
        inputProcessor.setBufferSize(65);
        inputProcessor.getChunk();
        inputProcessor.getChunk();
        String chunk = inputProcessor.getChunk();
        assertEquals("", chunk);
    }

}