package service.sort;

import entity.Employee;

import java.util.Comparator;

public class CompareByMainSkill implements Comparator<Employee> {
    @Override
    public int compare(Employee employee, Employee t1) {
        return employee.getMainSkill().compareTo(t1.getMainSkill());
    }
}
