package entity;

import java.util.List;

public interface CompositeWord extends LeafWord{
    void add(LeafWord leaf);

    List<LeafWord> getWords();
}
