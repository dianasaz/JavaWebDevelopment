package service.specification;

import entity.Employee;

import java.util.Optional;

public class ByNameSpecification implements Specification<Employee> {
    private String searchName;

    public ByNameSpecification(String searchName){
        this.searchName = searchName;
    }

    @Override
    public boolean match(Employee bean) {
        return this.searchName.equals(bean.getName());
    }
}
