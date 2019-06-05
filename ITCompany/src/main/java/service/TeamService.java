package service;

import entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.specification.Specification;
import service.sort.CompareByMainSkill;

import java.util.*;

public class TeamService implements Service<Employee> {
    private final Logger logger = LogManager.getLogger(TeamService.class);

    private List<Employee> employees = new ArrayList<>();
    private Team team = new Team(employees);
    private int cost;
    private final int WORKHOURS = 8;

    @Override
    public void add(Job job, String name, JobFunction jobFunction, MainSkill mainSkill) {

        Employee employee = JobFactory.getType(job, name, jobFunction, mainSkill);
        employees.add(employee);
        logger.log(Level.INFO, "employee was added");
    }

    public int size(){
        return team.size();
    }

    public List<Employee> getEmployee(){
        return employees;
    }

    @Override
    public void addSome(List<Employee> list) {
        for (int i = 0; i < list.size(); i++){
            employees.add(list.get(i));
        }
    }

    @Override
    public void delete(Employee employee) {
     employees.remove(employee);
     logger.log(Level.INFO, employee + "Was deleted");
    }

    @Override
    public List<Employee> find(Specification specification) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee: employees){
            if (specification.match(employee)) {
                result.add(employee);
                logger.log(Level.INFO, employee + "employee with needed specification was founded");
            }
        }
        return result;
    }

    @Override
    public List<Employee> sort(Comparator comp) {
        Comparator<Employee> employeeComparator = comp.thenComparing(new CompareByMainSkill());
        employees.sort(employeeComparator);
        return employees;
    }

    @Override
    public int getCostOfTeam() {
        return cost;
    }

    @Override
    public void setCostOfTeam(int days) {
        cost = employees.size() * days * WORKHOURS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamService that = (TeamService) o;
        return Objects.equals(employees, that.employees) &&
                Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employees, team);
    }

    @Override
    public String toString() {
        return "TeamService{" +
                "employees=" + employees +
                ", team=" + team +
                '}';
    }

    @Override
    public void deleteAll(){
        if (employees != null) {
            for (int i = 0; i < employees.size(); i++) {
                employees.remove(employees.get(i));
            }
        }
    }
}
