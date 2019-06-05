package entity;

import java.util.Optional;

public class JobFactory {
    public static Employee getType(Job job, String name, JobFunction jobFunction, MainSkill mainSkill){
        Employee employee = null;
        switch (job){
            case TESTER: {
                employee = new Tester(name, jobFunction, mainSkill);
                break;
            }
            case MANAGER:{
                employee = new Manager(name, jobFunction, mainSkill);
                break;
            }
            case DEVELOPER:{
                employee = new Developer(name, jobFunction, mainSkill);
                break;
            }
        }
        return employee;
    }
}
