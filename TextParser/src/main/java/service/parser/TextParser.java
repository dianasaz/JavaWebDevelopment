package service.parser;

import entity.CompositeWord;
import entity.LeafWord;
import entity.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextParser extends AbstractParser {
    private static final String PARAGRAPH_SPLIT_REGEX = "\t";


    @Override
    public LeafWord parseLine(String string) {
        List<String> stringParagraphs = Arrays.asList(string.split(PARAGRAPH_SPLIT_REGEX));
        CompositeWord text = new Text();
        stringParagraphs.stream().forEach( o-> {
            LeafWord leafWord = nextParse(o);
            text.add(leafWord);
        });
        return text;
    }
}