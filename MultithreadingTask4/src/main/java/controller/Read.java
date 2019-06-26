package controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Read {
    private static final Logger logger = LogManager.getLogger(Read.class);

    public static List<String> read(String string) throws NoSuchFieldException {
        List<String> lines = new ArrayList<>();
        try{
            lines = Files.readAllLines(Paths.get(string), Charset.forName("UTF-8"));
            logger.log(Level.INFO, "file read");
        }
        catch (IOException e){
            throw new NoSuchFieldException("File doesn't exist");
        }
        if (lines == null){
            logger.log(Level.INFO, "NULL IN FILE");
        }
        return lines;
    }
}
