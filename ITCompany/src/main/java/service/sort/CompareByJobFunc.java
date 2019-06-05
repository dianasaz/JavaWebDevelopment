package service.sort;

import entity.Employee;

import java.util.Comparator;

public class CompareByJobFunc implements Comparator<Employee> {

    @Override
    public int compare(Employee employee, Employee t1) {
        return employee.getJobFunction().compareTo(t1.getJobFunction());
    }
}
