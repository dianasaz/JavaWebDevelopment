import org.xml.sax.SAXException;
import parser.DOMParser;
import parser.SAXParser;
import parser.StAXParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Runner {
    public static void main(String... args) throws IOException, SAXException, ParserConfigurationException {
        //new SAXParser("src\\main\\resources\\medicins.xml").buildList();
        new DOMParser("src\\main\\resources\\medicins.xml").buildList();
        //new StAXParser("src\\main\\resources\\medicins.xml").buildList();
    }
}
