package controller;

import entity.Element;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Read {
    private final Logger logger = LogManager.getLogger(Read.class);

    public Element[][] read(String string) throws NoSuchFieldException {
        List<String> lines;
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

        return getElements(lines);
    }

    private Element[][] getElements(List<String> lines){
        Element[][] m = new Element[lines.size()][];

        for (int a = 0; a < lines.size(); a++){
            String[] res = lines.get(a).split(" ");
            Element[] d = new Element[res.length];
            for (int i = 0; i < res.length; i++) {
                Element s = new Element(Integer.valueOf(res[i]));
                d[i] = s;
            }
            m[a] = d;
        }
        return m;
    }
}
