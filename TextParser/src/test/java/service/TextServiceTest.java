package service;

import controller.reader.TextReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TextServiceTest {
    TextReader textReader;

    TextService textService;
    @Before
    public void prepare() throws IOException {
        textReader = new TextReader();

        textService = new TextService();
        textService.formatText(textReader.read("src\\main\\resources\\fileMain.txt"));
    }
    @Test
    public void testSort() throws IOException {
        textService.sort();

        TextService service = new TextService();
        service.formatText(textReader.read("src\\main\\resources\\sortedText.txt"));


        Assert.assertEquals(service.print(), textService.print());
    }

    @Test
    public void testFormat() throws IOException {
        String expected = "\tIt has survived not only five centuries, but also the leap into electronic\n" +
                "typesetting, remaining essentially unchanged. It was popularised in the with\n" +
                "the release of Letraset sheets containing Lorem Ipsum passages, and more\n" +
                "recently with desktop publishing software like Aldus PageMaker including\n" +
                "versions of Lorem Ipsum.\n" +
                "\tIt is a long established fact that a reader will be distracted by the readable\n" +
                "content of a page when looking at its layout. The point of using Ipsum is that\n" +
                "it has a more-or-less normal distribution of letters, as opposed to using\n" +
                "'Content here, content here' making it look like readable English.\n" +
                "\tIt is a established fact that a reader will be of a page when looking at its\n" +
                "layout.\n" +
                "\tBye.";

        Assert.assertEquals(expected, textService.print());
    }


}