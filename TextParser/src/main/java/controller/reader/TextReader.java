package controller.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TextReader implements ReaderInterface {
    public static final String PARAGRAPH_MATCH = "[A-Z].*[\\n\\r\\t\\.]";

    private final Logger logger = LogManager.getLogger(TextReader.class);

    public String read(String string) throws IOException {
        List<String> lines;
        String text = "";
        try{
            lines = Files.readAllLines(Paths.get(string), Charset.forName("UTF-8"));
            logger.log(Level.INFO, "file read");
        }
        catch (IOException e){
            throw new IOException("File doesn't exist");
        }
        if (lines == null){
            logger.log(Level.INFO, "NULL IN FILE");
        }
        for (String line: lines){
            if (line.matches(PARAGRAPH_MATCH)){
                text += "\t";
            }
            text += line;
        }
        return text;
    }
}
