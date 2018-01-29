package on24;

import java.io.*;

/**
 * Created by Hando Lukats.
 */
public class InputProcessor {

    private int bufferSize = 1024;
    private char[] buffer = new char[bufferSize];
    private String leftoverString = "";

    private Reader reader;

    public InputProcessor(InputStream input) throws UnsupportedEncodingException {
        this.reader = new InputStreamReader(input, "UTF-8");
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
        this.buffer = new char[bufferSize];
    }

    public String getChunk() throws IOException {
        StringBuilder out = new StringBuilder();
        int rsz = reader.read(buffer, 0,  buffer.length);
        if (rsz < 0) {
            String ret = leftoverString;
            leftoverString = "";
            return ret;
        }
        out.append(buffer, 0, rsz);
        String chunk = out.toString();
        int indexOfLastSpace = chunk.lastIndexOf(" ");
        String resultString = leftoverString.concat(chunk.substring(0, indexOfLastSpace));
        leftoverString = chunk.substring(indexOfLastSpace);
        return resultString;
    }


}
