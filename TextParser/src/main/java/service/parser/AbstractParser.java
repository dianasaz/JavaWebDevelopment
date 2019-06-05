package service.parser;

import entity.LeafWord;

public abstract class AbstractParser implements ParserChain {
    private AbstractParser next;

    @Override
    public ParserChain linkWith(ParserChain next) {
        ((AbstractParser) next).next = this;
        return next;
    }

    protected LeafWord nextParse(String line){
        if (next != null){
            return next.parseLine(line);
        } else {
            return null;
        }
    }
}
