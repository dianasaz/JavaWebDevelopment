package service.parser;

public class ParserFactory {

    public static Parser getParser(Type parserType){
        Parser parser = null;
        switch (parserType){
            case TEXTPARSER:
                parser = new TextParser();
        }
        return parser;
    }
}
