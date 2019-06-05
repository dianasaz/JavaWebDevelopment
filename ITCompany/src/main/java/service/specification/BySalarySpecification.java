package service.specification;

import entity.Employee;

public class BySalarySpecification implements Specification<Employee> {
    // private int searchSalary;
    private int maxSalary;
    private int minSalary;

    public BySalarySpecification(int maxSalary, int minSalary) {
        // this.searchSalary = searchSalary;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
    }

    @Override
    public boolean match(Employee bean) {
        return (bean.getSalary() > minSalary && bean.getSalary() < maxSalary) ? true : false;
    }
}
