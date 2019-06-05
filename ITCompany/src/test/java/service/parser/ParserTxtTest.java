package service.parser;

import entity.Developer;
import entity.Employee;
import entity.JobFunction;
import entity.MainSkill;
import entity.Manager;
import entity.Tester;
import exceptions.InvalidDataException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class ParserTxtTest {
    @Test
    public void shouldParse() throws NoSuchFieldException, InvalidDataException {
        List<Employee> result = new ArrayList<>();
        Employee e1 = new Tester("Ainwk", JobFunction.MIDDLE, MainSkill.JAVA);
        Employee e2 = new Developer("Ldwk nkdk", JobFunction.JUNIOR, MainSkill.PYTHON);
        Employee e3 = new Manager("Jjhs zlsl", JobFunction.SENIOR, MainSkill.PHP);
        Employee e4 = new Tester("Pbckw ndw", JobFunction.SENIOR, MainSkill.PHP);
        Employee e5 = new Manager("tdwk nkdk", JobFunction.JUNIOR, MainSkill.PYTHON);
        Employee e6 = new Developer("cdwk nkdk", JobFunction.MIDDLE, MainSkill.PYTHON);

        result.add(e1);
        result.add(e2);
        result.add(e3);
        result.add(e4);
        result.add(e5);
        result.add(e6);

        Parser parser = new TextParser();

        List<String> file = parser.read("G:\\courses\\ITCompany\\src\\main\\resources\\file.txt");
        List<Employee> res = null;
        res = parser.load(file);


        Assert.assertEquals(result, res);

    }

    @Test (expected = InvalidDataException.class)
    public void shouldParseWithException() throws NoSuchFieldException, InvalidDataException {
        List<Employee> expected = new ArrayList<>();
        Employee e1 = new Tester("Hinwk", JobFunction.MIDDLE, MainSkill.JAVA);
        Employee e2 = new Developer("hdwk nkdk", JobFunction.JUNIOR, MainSkill.PYTHON);
        Employee e3 = new Manager("Jjhs zlsl", JobFunction.SENIOR, MainSkill.PHP);
        Employee e4 = new Tester("Abckw ndw", JobFunction.SENIOR, MainSkill.PHP);
        expected.add(e1);
        expected.add(e2);
        expected.add(e3);
        expected.add(e4);

        Parser parser = ParserFactory.getParser(Type.PARSERTXT);

        List<String> file = parser.read("G:\\courses\\ITCompany\\src\\main\\resources\\fileWithException.txt");
        List<Employee> actual = parser.load(file);

        //Assert.assertEquals(expected, actual);

    }
}

