package service.sort;

import entity.Employee;

import java.util.Comparator;

public class CompareBySalary implements Comparator<Employee> {

    @Override
    public int compare(Employee employee, Employee t1) {
        if (employee.getSalary() > t1.getSalary()) {
            return 1;
        } else if (employee.getSalary() < t1.getSalary()) {
            return -1;
        } else {
            return 0;
        }
    }
}
