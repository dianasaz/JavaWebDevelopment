package runner;

import controller.reader.TextReader;
import service.TextService;

import java.io.IOException;

public class Run {
    public static void main(String... args) throws IOException {
        TextReader textReader = new TextReader();
        TextService textService = new TextService();

        textService.formatText(textReader.read("src\\main\\resources\\fileMain.txt"));

        System.out.println(textService.print());
        textService.sort();
        System.out.println(textService.print());
    }
}
