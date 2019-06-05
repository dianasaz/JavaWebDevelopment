package service;

import entity.CompositeWord;
import entity.LeafWord;
import entity.Text;
import service.parser.ParagraphParser;
import service.parser.ParserChain;
import service.parser.SentenseParser;
import service.parser.TextParser;
import service.parser.WordParser;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextService {
    private static final String NEXT_LINE_SPLIT = ".{3,80}(?=\\s|\\Z)";
    private CompositeWord text;

    public void sort(){
        text.getWords().sort(new Comparator<LeafWord>() {
            @Override
            public int compare(LeafWord o1, LeafWord o2) {
                return Integer.compare(o1.getData().length(), o2.getData().length());
            }
        });
    }
/*
    public String read(String string) throws IOException {

        TextReader textReader = new TextReader();
        String stringText = textReader.read(string);

        return stringText;
    }
*/
    public CompositeWord formatText(String stringText){
        ParserChain componentParser = new WordParser()
                .linkWith(new SentenseParser())
                .linkWith(new ParagraphParser())
                .linkWith(new TextParser());

        text = (Text) componentParser.parseLine(stringText);

        return text;
    }

    public String print() {
        String stringText = text.getData();
        Pattern pattern = Pattern.compile(NEXT_LINE_SPLIT);
        Matcher matcher = pattern.matcher(stringText);

        String result = "";
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();

            String a = String.valueOf(stringText.charAt(start));
            if (a.equals(" ")) {
                result += stringText.substring(start + 1, end) + "\n";
            } else result += stringText.substring(start, end) + "\n";

        }
        if (String.valueOf(result.charAt(result.length() - 1)).equals("\n")) result = result.substring(0, result.length()-1);
        return result;
    }
}
