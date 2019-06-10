package parser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface Parser {
    void parse(String file) throws ParserConfigurationException, IOException, SAXException;
}
