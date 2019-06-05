package service.parser;

import entity.CompositeWord;
import entity.LeafWord;
import entity.Sentense;
import java.util.Arrays;
import java.util.List;

public class SentenseParser extends AbstractParser {

  private static final String SPLIT_WORDS = "\\s";

    @Override
    public LeafWord parseLine(String line) {
        List<String> stringWords = Arrays.asList(line.split(SPLIT_WORDS));
        CompositeWord sentense = new Sentense();
        stringWords.stream().forEach( o-> {
            LeafWord leafWord = nextParse(o);
            sentense.add(leafWord);
        });
        return sentense;
    }
}
