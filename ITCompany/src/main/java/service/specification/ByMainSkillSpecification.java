package service.specification;

import entity.Employee;
import entity.MainSkill;

public class ByMainSkillSpecification implements Specification<Employee> {

    private MainSkill searchMainSkill;

    public ByMainSkillSpecification(MainSkill searchMainSkill){
        this.searchMainSkill = searchMainSkill;
    }

    @Override
    public boolean match(Employee bean) {
        return searchMainSkill.equals(bean.getMainSkill());
    }
}
