package parser;

public class ParserFactory {
    public static Parser getParser(String string){
        Parser parser = null;
        switch (string){
            case "DOM":{
                parser = new DOMParser();
                break;
            }
            case "SAX":{
                parser = new SAXParser();
                break;
            }
            case "StAX":{
                parser = new StAXParser();
                break;
            }
        }
        return parser;
    }
}
