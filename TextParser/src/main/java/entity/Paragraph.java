package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Paragraph implements CompositeWord{
    private List<LeafWord> sentenses;

    @Override
    public void add(LeafWord leaf) {
        sentenses.add( leaf);
    }

    public Paragraph(){
        this.sentenses = new ArrayList<>();
    }

    @Override
    public List<LeafWord> getWords() {
        return sentenses;
    }

    @Override
    public String getData() {
        String res = "";
        for (LeafWord sentence : sentenses) {
            res += sentence.getData().trim() + ". ";
        }
        return res.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paragraph paragraph = (Paragraph) o;
        return Objects.equals(sentenses, paragraph.sentenses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sentenses);
    }

    @Override
    public String toString() {
        return sentenses + "";
    }
}
