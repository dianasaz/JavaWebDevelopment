package service.specification;

import entity.Employee;
import entity.JobFunction;


public class ByJobFuncSpecification implements Specification<Employee>{

    private JobFunction searchJobFunc;

    public ByJobFuncSpecification(JobFunction searchJobFunc){
        this.searchJobFunc = searchJobFunc;
    }

    @Override
    public boolean match(Employee bean) {
        return searchJobFunc.equals(bean.getJobFunction());
    }
}
