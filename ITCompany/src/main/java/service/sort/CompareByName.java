package service.sort;

import entity.Employee;

import java.util.Comparator;

public class CompareByName implements Comparator<Employee> {
    @Override
    public int compare(Employee employee, Employee t1) {
        return employee.getName().compareToIgnoreCase(t1.getName());
    }
}
