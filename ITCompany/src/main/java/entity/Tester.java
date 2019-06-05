package entity;

import java.util.Objects;
import java.util.Optional;

public class Tester extends Employee{

    public Tester(String name, JobFunction jobFunction, MainSkill mainSkill){
        super(mainSkill, name, jobFunction);
        setJob(Job.TESTER);
    }
}
