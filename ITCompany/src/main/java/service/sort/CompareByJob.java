package service.sort;

import entity.Employee;

import java.util.Comparator;

public class CompareByJob implements Comparator<Employee> {
    @Override
    public int compare(Employee employee, Employee t1) {
        return employee.getJob().compareTo(t1.getJob());
    }
}
