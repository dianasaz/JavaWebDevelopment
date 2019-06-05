package entity;

import java.util.Objects;
import java.util.Optional;

public class Developer extends Employee {

    public Developer(String name, JobFunction jobFunction, MainSkill mainSkill){
        super(mainSkill, name, jobFunction);
        setJob(Job.DEVELOPER);
    }
}
