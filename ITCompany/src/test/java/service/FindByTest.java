package service;

import entity.Employee;
import entity.Job;
import entity.JobFunction;
import exceptions.InvalidDataException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import service.parser.Parser;
import service.parser.TextParser;
import service.specification.Specification;

import java.util.List;

@RunWith(JUnit4.class)
public class FindByTest {
    static Service<Employee> teamService = new TeamService();
    static Parser parser;

    @BeforeClass
    public static void  prepare() throws InvalidDataException, NoSuchFieldException {
        teamService.deleteAll();
        parser = new TextParser();
        List<String> file = parser.read("C:\\Users\\dianasaz\\Documents\\file.txt");
        List<Employee> res = null;
        res = parser.load(file);
        teamService.addSome(res);
    }
    @Test
    public void findByJobFunction(){
        Specification<Employee> employeeSpecification = entity -> entity.getJobFunction() == JobFunction.SENIOR;
        List<Employee> salList = teamService.find(employeeSpecification);

        Assert.assertEquals(2, salList.size());
    }

    @Test
    public void findByJob(){
        Specification<Employee> employeeSpecification = entity -> entity.getJob() == Job.MANAGER;
        List<Employee> salList = teamService.find(employeeSpecification);

        Assert.assertEquals(2, salList.size());
    }

    @AfterClass
    public static void clean(){
        teamService.deleteAll();
    }


}