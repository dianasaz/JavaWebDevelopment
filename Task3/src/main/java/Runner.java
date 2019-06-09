import org.xml.sax.SAXException;
import parser.DOMParser;
import parser.SAXParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Runner {
    public static void main(String... args) throws IOException, SAXException, ParserConfigurationException {
        //SAXParser parser = new SAXParser("src\\main\\resources\\medicins.xml");
        DOMParser parserOne = new DOMParser("src\\main\\resources\\medicins.xml");
    }
}
