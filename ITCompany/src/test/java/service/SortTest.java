package service;

import entity.Developer;
import entity.Employee;
import entity.JobFunction;
import entity.MainSkill;
import entity.Manager;
import entity.Tester;
import exceptions.InvalidDataException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import service.parser.Parser;
import service.parser.TextParser;
import service.sort.CompareByName;
import service.sort.CompareBySalary;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class SortTest {
    static Service<Employee> teamService = new TeamService();
    static Parser parser;

    @BeforeClass
    public static void  prepare() throws InvalidDataException, NoSuchFieldException {
       // teamService.deleteAll();
        parser = new TextParser();
        List<String> file = parser.read("C:\\Users\\dianasaz\\Documents\\file.txt");
        List<Employee> res = null;
        res = parser.load(file);
        teamService.addSome(res);
    }

    @Test
    public void shouldSortByName(){

        List<Employee> sortedList = teamService.sort(new CompareByName());

        List<Employee> result = new ArrayList<>();
        Employee e1 = new Tester("Ainwk", JobFunction.MIDDLE, MainSkill.JAVA);
        Employee e2 = new Developer("Ldwk nkdk", JobFunction.JUNIOR, MainSkill.PYTHON);
        Employee e3 = new Manager("Jjhs zlsl", JobFunction.SENIOR, MainSkill.PHP);
        Employee e4 = new Tester("Pbckw ndw", JobFunction.SENIOR, MainSkill.PHP);
        Employee e5 = new Manager("tdwk nkdk", JobFunction.JUNIOR, MainSkill.PYTHON);
        Employee e6 = new Developer("cdwk nkdk", JobFunction.MIDDLE, MainSkill.PYTHON);

        result.add(e1);
        result.add(e6);
        result.add(e3);
        result.add(e2);
        result.add(e4);
        result.add(e5);

        Assert.assertEquals(result, sortedList);
    }

    @Test
    public void shouldSortBySalaryAndMainSkill(){
        List<Employee> sortedList = teamService.sort(new CompareBySalary());

        List<Employee> result = new ArrayList<>();
        Employee e1 = new Tester("Ainwk", JobFunction.MIDDLE, MainSkill.JAVA);
        Employee e2 = new Developer("Ldwk nkdk", JobFunction.JUNIOR, MainSkill.PYTHON);
        Employee e3 = new Manager("Jjhs zlsl", JobFunction.SENIOR, MainSkill.PHP);
        Employee e4 = new Tester("Pbckw ndw", JobFunction.SENIOR, MainSkill.PHP);
        Employee e5 = new Manager("tdwk nkdk", JobFunction.JUNIOR, MainSkill.PYTHON);
        Employee e6 = new Developer("cdwk nkdk", JobFunction.MIDDLE, MainSkill.PYTHON);

        result.add(e2);
        result.add(e5);
        result.add(e1);
        result.add(e6);
        result.add(e3);
        result.add(e4);
        Assert.assertEquals(result, sortedList);
    }

    @AfterClass
    public static void clean(){
        teamService.deleteAll();
    }

}