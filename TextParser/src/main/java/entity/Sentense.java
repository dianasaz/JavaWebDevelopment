package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sentense implements CompositeWord{
    private List<LeafWord> words;

    @Override
    public void add(LeafWord leaf) {
        words.add( leaf);
    }

    public Sentense(){
        this.words = new ArrayList<>();
    }

    @Override
    public List<LeafWord> getWords() {
        return words;
    }

    @Override
    public String getData() {
        String res = "";
        for (LeafWord word : words) {
            res += word.getData() + " ";
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentense sentense = (Sentense) o;
        return Objects.equals(words, sentense.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(words);
    }

    @Override
    public String toString() {
        return  words + "";
    }
}
