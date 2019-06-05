package service.specification;

import entity.Employee;
import entity.Job;

public class ByJobSpecification implements Specification<Employee> {
    private Job searchJob;

    public ByJobSpecification(Job searchJob){
        this.searchJob = searchJob;
    }
    @Override
    public boolean match(Employee bean) {
        return searchJob.equals(bean.getJob());
    }
}
