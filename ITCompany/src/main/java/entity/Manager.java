package entity;

import java.util.Objects;
import java.util.Optional;

public class Manager extends Employee{

    public Manager(String name, JobFunction jobFunction, MainSkill mainSkill){
        super(mainSkill, name, jobFunction);
        setJob(Job.MANAGER);
    }

}
