package service.parser;

import entity.LeafWord;
import entity.Word;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser extends AbstractParser{
    public static final String WORD_VALIDATION = "[\\w-]+";

    @Override
    public LeafWord parseLine(String string) {
        String previousChar = "";
        String nextChar = "";
        String stringWord = "";

        Pattern pattern = Pattern.compile(WORD_VALIDATION);
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();

            if (start != 0) {
                previousChar += string.charAt(start - 1);
            }
            if (end != string.length() && !String.valueOf(string.charAt(end)).equals(".")) {
                nextChar += string.charAt(end);
            }
            stringWord = string.substring(start, end);
        }
        LeafWord word = new Word(stringWord, previousChar, nextChar);
        return word;
    }

}
