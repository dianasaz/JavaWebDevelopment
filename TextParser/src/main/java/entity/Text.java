package entity;

import java.util.ArrayList;
import java.util.List;

public class Text implements CompositeWord{
    private List<LeafWord> paragraphs;

    @Override
    public void add(LeafWord leaf) {
        paragraphs.add( leaf);
    }

    public Text(){
        this.paragraphs = new ArrayList<>();
    }

    @Override
    public List<LeafWord> getWords() {
        return paragraphs;
    }

    @Override
    public String getData() {
        String res = "";
        for (int i = 0; i < paragraphs.size(); i++){
            if (i == 0) {
                res += "\t" + paragraphs.get(i).getData();
            } else res += "\n" + "\t" + paragraphs.get(i).getData();
        }
        return "\t" + res.trim();
    }
}
