package service.parser;

import entity.CompositeWord;
import entity.LeafWord;

public interface ParserChain {
    LeafWord parseLine(String string);

    ParserChain linkWith( ParserChain next);
}
