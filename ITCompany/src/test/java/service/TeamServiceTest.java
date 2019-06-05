package service;

import entity.*;
import exceptions.InvalidDataException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import service.parser.Parser;
import service.parser.TextParser;
import service.specification.Specification;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class TeamServiceTest {
    static Service<Employee> teamService;
    static Parser parser;

    @Before
    public void prepare() throws NoSuchFieldException, InvalidDataException {
        teamService = new TeamService();
        teamService.deleteAll();
        parser = new TextParser();
        List<String> file = parser.read("C:\\Users\\dianasaz\\Documents\\file.txt");
        List<Employee> res = null;
        res = parser.load(file);
        teamService.addSome(res);
    }

    @Test
    public void shouldDelete() throws NoSuchFieldException, InvalidDataException {

        List<Employee> result = new ArrayList<>();
        Employee e1 = new Tester("Ainwk", JobFunction.MIDDLE, MainSkill.JAVA);
        Employee e2 = new Developer("Ldwk nkdk", JobFunction.JUNIOR, MainSkill.PYTHON);
        //Employee e3 = new Manager("Jjhs zlsl", JobFunction.SENIOR, MainSkill.PHP);
       // Employee e4 = new Tester("Pbckw ndw", JobFunction.SENIOR, MainSkill.PHP);
        Employee e5 = new Manager("tdwk nkdk", JobFunction.JUNIOR, MainSkill.PYTHON);
        Employee e6 = new Developer("cdwk nkdk", JobFunction.MIDDLE, MainSkill.PYTHON);
      //  Employee e7 = new Tester("Jopa Bedro", JobFunction.JUNIOR, MainSkill.SWIFT);
        //Employee e8 = new Manager("Lola Pedro", JobFunction.SENIOR, MainSkill.PHP);

        result.add(e1);
        result.add(e2);
        //result.add(e3);
        //result.add(e4);
        result.add(e5);
        result.add(e6);
       // result.add(e7);
        //result.add(e8);

        Specification<Employee> employeeSpecification = entity -> entity.getJobFunction() == JobFunction.SENIOR;
        List<Employee> search = teamService.find(employeeSpecification);
        for (int i = 0; i < search.size(); i++){
            teamService.delete(search.get(i));
        }

        Assert.assertEquals(result, teamService.getEmployee());
    }

    @Test
    public void shouldGetCostOfTeam(){
        int expected = 192;

        teamService.setCostOfTeam(4);

        int actual = teamService.getCostOfTeam();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldAdd(){

        List<Employee> result = new ArrayList<>();
        Employee e1 = new Tester("Ainwk", JobFunction.MIDDLE, MainSkill.JAVA);
        Employee e2 = new Developer("Ldwk nkdk", JobFunction.JUNIOR, MainSkill.PYTHON);
        Employee e3 = new Manager("Jjhs zlsl", JobFunction.SENIOR, MainSkill.PHP);
        Employee e4 = new Tester("Pbckw ndw", JobFunction.SENIOR, MainSkill.PHP);
        Employee e5 = new Manager("tdwk nkdk", JobFunction.JUNIOR, MainSkill.PYTHON);
        Employee e6 = new Developer("cdwk nkdk", JobFunction.MIDDLE, MainSkill.PYTHON);
        Employee e7 = new Tester("Jopa Bedro", JobFunction.JUNIOR, MainSkill.SWIFT);
        Employee e8 = new Manager("Lola Pedro", JobFunction.SENIOR, MainSkill.PHP);
        result.add(e1);
        result.add(e2);
        result.add(e3);
        result.add(e4);
        result.add(e5);
        result.add(e6);
        result.add(e7);
        result.add(e8);


        teamService.add(Job.TESTER, "Jopa Bedro", JobFunction.JUNIOR, MainSkill.SWIFT);
        teamService.add(Job.MANAGER, "Lola Pedro", JobFunction.SENIOR, MainSkill.PHP);

        Assert.assertEquals(result, teamService.getEmployee());
    }

    @After
    public void clean(){
        teamService.deleteAll();
    }

}
